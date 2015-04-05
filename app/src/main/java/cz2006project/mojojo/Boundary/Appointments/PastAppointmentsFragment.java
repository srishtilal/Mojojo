package main.java.cz2006project.mojojo.Boundary.Appointments;

/**
 * Created by srishti on 5/4/15.
 */

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import cz2006project.mojojo.R;
import main.java.cz2006project.mojojo.Control.ParseTables;
import main.java.cz2006project.mojojo.CustomTimePicker;
import main.java.cz2006project.mojojo.Entity.Appointment;


/**
 * Created by srishti on 30/3/15.
 */


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class PastAppointmentsFragment extends Fragment {

    RecyclerView appointmentsList;
    RecyclerView.Adapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    private boolean refresh = false;
    private boolean check_my_appointments=true;
    View v;
    LinearLayout appointmentsMainLayout;
    ScrollView emptyAppointment;
    public static ParseObject appt;
    public static int yeartest, monthtest, daytest, hourtest, minutetest;
    public static Calendar calendar;

    public PastAppointmentsFragment(){

    }

    public static PastAppointmentsFragment newInstance(Boolean check){
        PastAppointmentsFragment pastAppointmentsFragment = new PastAppointmentsFragment();
        Bundle b = new Bundle();
        b.putBoolean("check", check);
        pastAppointmentsFragment.setArguments(b);
        return pastAppointmentsFragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if(this.getArguments() != null){
            check_my_appointments = getArguments().getBoolean("check");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragments_past_appointments_list, container, false);
        appointmentsList = (RecyclerView) v.findViewById(R.id.listviewpastappointments);
        appointmentsMainLayout = (LinearLayout) v.findViewById(R.id.appointments_main_list);
        emptyAppointment = (ScrollView) v.findViewById(R.id.empty_appointments);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        appointmentsList.setLayoutManager(layoutManager);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh = true;
                fetchData();
            }
        });
        fetchData();


        return v;
    }

    public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.ViewHolder> implements View.OnClickListener {

        private int expandedPosition = -1;
        private List<ParseObject> appointments;

        public AppointmentsAdapter(List<ParseObject> appointments) {
            this.appointments = appointments;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CardView cd = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.listviewpastappointments, parent, false);
            ViewHolder viewHolder = new ViewHolder(cd);
            viewHolder.itemView.setOnClickListener(AppointmentsAdapter.this);
            viewHolder.itemView.setTag(viewHolder);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.appointment_type.setText("Appointment Type: " + (String)appointments.get(position).get(ParseTables.Appointment.TYPE));
            holder.doctor.setText("Doctor: " + (String)appointments.get(position).get(ParseTables.Appointment.DOCTOR));
            holder.clinic.setText("Clinic: " + (String)appointments.get(position).get(ParseTables.Appointment.CLINIC));
            holder.appointment_date.setText(appointments.get(position).get(ParseTables.Appointment.DATE)+" "+appointments.get(position).get(ParseTables.Appointment.TIME));
            holder.appointment_creator.setText((String)appointments.get(position).get(ParseTables.Appointment.PATIENT));

            //holder.notes.setText((String)appointments.get(position).get(ParseTables.Appointment.NOTES));
            //holder.medicalissue.setText((String)appointments.get(position).get(ParseTables.Appointment.MEDICALISSUE));


            if(check_my_appointments){
                holder.appointment_creator.setVisibility(View.GONE);
                holder.appointment_followup.setVisibility(View.VISIBLE);
            }

            if (position == expandedPosition) {
                holder.expanded_area.setVisibility(View.VISIBLE);
            } else {
                holder.expanded_area.setVisibility(View.GONE);
            }
        }

        @Override
        public int getItemCount() {
            return appointments.size();
        }

        @Override
        public void onClick(View v) {
            final ViewHolder holder = (ViewHolder) v.getTag();
            if (holder.getPosition() == expandedPosition) {
                holder.expanded_area.setVisibility(View.GONE);
                expandedPosition = -1;
            } else {
                if (expandedPosition >= 0) {
                    int prev = expandedPosition;
                    notifyItemChanged(prev);
                }
                expandedPosition = holder.getPosition();
                notifyItemChanged(expandedPosition);
            }

        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView appointment_type;
            TextView clinic;
            TextView doctor;
            RelativeLayout expanded_area;
            TextView notes;
            TextView appointment_date;
            TextView appointment_time;
            Button appointment_followup;
            Button appointment_change;


            TextView appointment_creator;


            public ViewHolder(View itemView) {
                super(itemView);
                this.appointment_type = (TextView) itemView.findViewById(R.id.appointment_type);
                this.clinic = (TextView) itemView.findViewById(R.id.clinic);
                this.doctor = (TextView) itemView.findViewById(R.id.doctor);
                this.expanded_area = (RelativeLayout) itemView.findViewById(R.id.expanded_area);
                this.appointment_creator = (TextView) itemView.findViewById(R.id.appointment_creator);

                this.notes = (TextView) itemView.findViewById(R.id.notes);
                this.appointment_date = (TextView) itemView.findViewById(R.id.appointment_date);
                this.appointment_time = (TextView) itemView.findViewById(R.id.appointment_time);
                this.appointment_followup = (Button) itemView.findViewById(R.id.appointment_followup);
                //this.appointment_change = (Button) itemView.findViewById(R.id.appointment_change);
                appointment_followup.setOnClickListener(new View.OnClickListener() {
                    ParseTables.Appointment appointment= new ParseTables.Appointment();

                    @Override
                    public void onClick(View v) {
                        appt = appointments.get(getPosition());
                        DatePickerFragment datePicker = new DatePickerFragment();
                        datePicker.show(getActivity().getSupportFragmentManager(), "Set Date");
                        TimePickerFragment timePickerFragment = new TimePickerFragment();
                        timePickerFragment.show(getActivity().getSupportFragmentManager(), "Set Time");




                            }

                });

               /* appointment_followup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ParseObject appt = appointments.get(getPosition());
                        appt.deleteInBackground(new DeleteCallback() {
                            @Override
                            public void done(ParseException e) {
                                if(e == null){
                                    fetchData();
                                }
                                else{
                                    Toast.makeText(getActivity().getApplicationContext(), "Internet Connection Problem", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });*/

                /*appointment_change.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ParseObject appt = appointments.get(getPosition());
                        appt.deleteInBackground(new DeleteCallback() {
                            @Override
                            public void done(ParseException e) {
                                if(e == null){
                                    fetchData();
                                }
                                else{
                                    Toast.makeText(getActivity().getApplicationContext(), "Internet Connection Problem", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });*/


            }
        }
    }

    private void pushDataToParse() {

        ParseObject appointment = new ParseObject("Appointment");
/*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Appointment");
        //query.orderByDescending("AppointmentNumber");
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                //Is relationship exists?

                if (parseObject == null) {
                    Log.d("appNo", "The getFirst request failed.");
                } else {
                    Log.d("appNo", "Retrieved the object.");
                    AppointmentNumber = parseObject.getString("AppointmentNumber");
                    int newAppointmentNumber = Integer.parseInt(AppointmentNumber);
                    appNo = String.valueOf(newAppointmentNumber);
                }



            }
        });*/



        //appointment.put(ParseTables.Appointment.APPOINTMENTNUMBER, appNo);

        calendar.set(yeartest, monthtest, daytest, hourtest, minutetest);
        appt.put(ParseTables.Appointment.DATE, calendar.getTime());
        appointment.put("Date",appt.getDate("Date"));
        appointment.put("time", appt.getString("time"));
        appointment.put("clinic", appt.getString("clinic"));
        appointment.put(ParseTables.Appointment.DOCTOR, appt.getString("doctor"));
        appointment.put(ParseTables.Appointment.TYPE, appt.getString("type"));

        appointment.put(ParseTables.Appointment.FOLLOWUP, appt.getString("followup"));
        appointment.put(ParseTables.Appointment.NOTES, appt.getString("notes"));
        appointment.put(ParseTables.Appointment.PATIENT, appt.getString("patient"));

        appointment.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {


                Toast.makeText(getActivity().getApplicationContext(),
                        getString(R.string.appointment_created), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }


    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            yeartest=year;
            monthtest=monthOfYear;
            daytest=dayOfMonth;





        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String time;
            String min = Integer.toString(minute);
            if (minute < 10) {
                min = "0" + Integer.toString(minute);
                minutetest=00;
            }
            else {
                min = "30";
                minutetest=30;
                }
            if (hourOfDay > 12) {
                hourOfDay = hourOfDay - 12;
                time = String.valueOf(hourOfDay) + ":" + min + " pm";
                hourtest=hourOfDay;
            } else {
                time = String.valueOf(hourOfDay) + ":" + min + " am";
                hourtest=hourOfDay;
            }




            appt.put("time", time);

        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = 9;
            int minute = 0;
            CustomTimePicker cusTimePicker = new CustomTimePicker(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));


            return cusTimePicker;
        }

    }


    public void fetchData(){
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                "Appointment");

        if(check_my_appointments){
            query.whereEqualTo("patient", ParseUser.getCurrentUser().getString("name"));
            Calendar currentDate = Calendar.getInstance();
            Date current = currentDate.getTime();
            query.whereLessThan("Date", current);
        }
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                doneFetching(parseObjects);
            }
        });


    }

    public void doneFetching(List<ParseObject> appointments){
        adapter = new AppointmentsAdapter(appointments);
        appointmentsList.setAdapter(adapter);
        if (refresh == true) {
            swipeRefreshLayout.setRefreshing(false);
            refresh = false;
        }
        if(check_my_appointments && adapter.getItemCount() == 0){
            appointmentsMainLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            default:
                return super.onOptionsItemSelected(item);
        }
    }





}

