package main.java.cz2006project.mojojo.Boundary.Appointments;

/**
 * Created by srishti on 30/3/15.
 */

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.text.method.DateTimeKeyListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.app.DatePickerDialog;
import android.widget.DatePicker;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz2006project.mojojo.R;
import main.java.cz2006project.mojojo.Control.ParseTables;
import main.java.cz2006project.mojojo.CustomTimePicker;
import main.java.cz2006project.mojojo.MaterialEditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingAppointmentsFragment extends Fragment {

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
    private List<String> nameList = null;
    private List<Date> dateList = null;
    private List<List<Object>> totalTemp = null;
    private List<String> emailList = null;
    private List<String>tempList = null;
    private HashMap params = null;
    ParseUser user = null;

    public UpcomingAppointmentsFragment(){

    }

    public static UpcomingAppointmentsFragment newInstance(Boolean check){
        UpcomingAppointmentsFragment upcomingAppointmentsFragment = new UpcomingAppointmentsFragment();
        Bundle b = new Bundle();
        b.putBoolean("check", check);
        upcomingAppointmentsFragment.setArguments(b);
        return upcomingAppointmentsFragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if(this.getArguments() != null)
        {
            check_my_appointments = getArguments().getBoolean("check");
        }
        sendMail();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragments_upcoming_appointments_list, container, false);
        appointmentsList = (RecyclerView) v.findViewById(R.id.listviewappointments);
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
            CardView cd = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_appointments, parent, false);
            ViewHolder viewHolder = new ViewHolder(cd);
            viewHolder.itemView.setOnClickListener(AppointmentsAdapter.this);
            viewHolder.itemView.setTag(viewHolder);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.appointment_number.setText((String)appointments.get(position).get(ParseTables.Appointment.APPOINTMENTNUMBER));
            holder.doctor.setText("Doctor: " + (String)appointments.get(position).get(ParseTables.Appointment.DOCTOR));
            holder.clinic.setText("Clinic: " + (String)appointments.get(position).get(ParseTables.Appointment.CLINIC));
            holder.appointment_date.setText(appointments.get(position).get(ParseTables.Appointment.DATE)+" "+appointments.get(position).get(ParseTables.Appointment.TIME));
            holder.appointment_creator.setText((String)appointments.get(position).get(ParseTables.Appointment.PATIENT));
            //holder.notes.setText((String)appointments.get(position).get(ParseTables.Appointment.NOTES));
            //holder.medicalissue.setText((String)appointments.get(position).get(ParseTables.Appointment.MEDICALISSUE));


            if(check_my_appointments){
                holder.appointment_creator.setVisibility(View.GONE);
                holder.appointment_delete.setVisibility(View.VISIBLE);
                holder.appointment_changedate.setVisibility(View.VISIBLE);
                holder.appointment_changetime.setVisibility(View.VISIBLE);

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
            TextView appointment_number;
            TextView clinic;
            TextView doctor;
            RelativeLayout expanded_area;
            TextView notes;
            TextView appointment_date;
            TextView appointment_time;
            Button appointment_delete;
            Button appointment_changedate;
            Button appointment_changetime;


            TextView appointment_creator;


            public ViewHolder(View itemView) {
                super(itemView);
                this.appointment_number = (TextView) itemView.findViewById(R.id.appointment_type);
                this.clinic = (TextView) itemView.findViewById(R.id.clinic);
                this.doctor = (TextView) itemView.findViewById(R.id.doctor);
                this.expanded_area = (RelativeLayout) itemView.findViewById(R.id.expanded_area);
                this.appointment_creator = (TextView) itemView.findViewById(R.id.appointment_creator);

                this.notes = (TextView) itemView.findViewById(R.id.notes);
                this.appointment_date = (TextView) itemView.findViewById(R.id.appointment_date);
                this.appointment_time = (TextView) itemView.findViewById(R.id.appointment_time);
                this.appointment_delete = (Button) itemView.findViewById(R.id.appointment_delete);
                this.appointment_changedate = (Button) itemView.findViewById(R.id.appointment_changedate);
                this.appointment_changetime=(Button) itemView.findViewById(R.id.appointment_changetime);
                //this.appointment_change = (Button) itemView.findViewById(R.id.appointment_change);




                appointment_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        sendMail();
                        builder.setTitle("Confirm");
                        builder.setMessage("Are you sure you want to cancel this appointment?");

                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                appt = appointments.get(getPosition());
                                Calendar calendar = Calendar.getInstance();
                                int year = calendar.get(Calendar.YEAR);
                                int month = calendar.get(Calendar.MONTH);
                                int day = calendar.get(Calendar.DATE);
                                calendar.set(year, month, day, 0, 0, 0);
                                Date BegginingOfToday = calendar.getTime();
                               if (appt.getDate("Date").after(BegginingOfToday)) {
                                    appt.deleteInBackground(new DeleteCallback() {
                                        @Override
                                        public void done(ParseException e) {
                                            if (e == null) {
                                                fetchData();
                                            } else {
                                                Toast.makeText(getActivity().getApplicationContext(), "Internet Connection Problem", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    });
                                }

                                dialog.dismiss();
                            }

                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();


                    }
                });

                appointment_changedate.setOnClickListener(new View.OnClickListener() {



                    @Override
                    public void onClick(View v) {
                        appt = appointments.get(getPosition());
                        DatePickerFragment datePicker = new DatePickerFragment();
                        datePicker.show(getActivity().getSupportFragmentManager(), "Set Date");

                    }
                });


                appointment_changetime.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        appt = appointments.get(getPosition());
                        TimePickerFragment timePickerFragment = new TimePickerFragment();
                        timePickerFragment.show(getActivity().getSupportFragmentManager(), "Set Time");

                    }
                });
            }
        }
    }

    public void fetchData(){
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                "Appointment");

        if(check_my_appointments){
            query.whereEqualTo("patient", ParseUser.getCurrentUser().getString("name"));
            Calendar currentDate = Calendar.getInstance();
            Date current = currentDate.getTime();
            query.whereGreaterThanOrEqualTo("Date", current);
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

    public void sendMail() {
        ParseUser user = ParseUser.getCurrentUser();

            @Override
            public void done(List<ParseObject> users, ParseException e) {
                // The query returns a list of objects from the "questions" class
                if (e == null) {
                    for (ParseObject user : users) {
                        // Get the questionTopic value from the question object
                        final String userName = user.getString("name");
                        final String email = user.getString("email");
                        nameList.add(userName);
                        emailList.add(email);

                        final ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Appointment");
                        query2.whereContainedIn("patient", nameList);
                        query2.findInBackground(new FindCallback<ParseObject>() {
                            public void done(List<ParseObject> Appointment, ParseException e) {

                                if (e == null) {
                                    for (ParseObject Appt : Appointment) {
                                        int i = 0;
                                        Date date = Appt.getDate("date");
                                        String time = Appt.getString("time");
                                        for (String name : nameList) {
                                            int j = 0;
                                            if (name == Appt.getString("patient")) {
                                                List innerList = (List) totalTemp.get(i);
                                                String temp = Appt.getString("patient") + '/' + emailList.get(i) + '/' + time;
                                                tempList.add(temp);
                                                dateList.add(date);
                                                innerList.add(date);
                                                innerList.add(temp);
                                            }
                                            j++;
                                        }
                                        i++;
                                    }
                                } else {
                                    Log.d("notretreive", "Error: " + e.getMessage());
                                }
                            }
                        });

//                        Log.d("patient", "name: " + user.getString("name"));
                    }

                } else {

                    Log.d("notretreive", "Error: " + e.getMessage());
                }

                for (int i = 0; i < totalTemp.size(); i++) {
                    List innerList = (List) totalTemp.get(i);
                    Date date = (Date) innerList.get(0);
                    Date currentDate = new Date();
                    String temp = (String) innerList.get(1);
                    String[] random = temp.split("/");
                    if(date.compareTo(currentDate) > 3 || date.compareTo(currentDate) == 0)
                    {
                        params.put("text", "Your Appointment is on the " + date.toString() + "Please come punctually.");
                        params.put("subject", "Reminder");
                        params.put("fromEmail", "qwikDoc@gmail.com");
                        params.put("fromName", "Source User");
                        params.put("toEmail", random[1]);
                        params.put("toName", random[0]);
                    }

                    ParseCloud.callFunctionInBackground("sendMail", params, new FunctionCallback<Object>() {
                        @Override
                        public void done(Object response, ParseException exc) {
                            Log.e("cloud code example", "response: " + response);
                        }
                    });
                    Log.d("user", "name: " + user.getString("name"));
                }

            }
        });
    };
}




    /*public void sendMail(View v)
    {
        params = new HashMap<>();
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.whereEqualTo("username",ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseObject>()
        {

            @Override
            public void done(List<ParseObject> users, ParseException e) {
                // The query returns a list of objects from the "questions" class
                if (e == null) {
                    for (ParseObject user : users) {
                        // Get the questionTopic value from the question object
                        final String userName = user.getString("name");
                        final String email = user.getString("email");
                        nameList.add(userName);
                        emailList.add(email);

                        final ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Appointment");
                        query2.whereContainedIn("patient", nameList);
                        query2.findInBackground(new FindCallback<ParseObject>() {
                            public void done(List<ParseObject> Appointment, ParseException e) {

                                if (e == null) {
                                    for (ParseObject Appt : Appointment) {
                                        int i = 0;
                                        Date date = Appt.getDate("date");
                                        String time = Appt.getString("time");
                                        for (String name : nameList) {
                                            int j = 0;
                                            if (name == Appt.getString("patient")) {
                                                List innerList = (List) totalTemp.get(i);
                                                String temp = Appt.getString("patient") + '/' + emailList.get(i) + '/' + time;
                                                tempList.add(temp);
                                                dateList.add(date);
                                                innerList.add(date);
                                                innerList.add(temp);
                                            }
                                            j++;
                                        }
                                        i++;
                                    }
                                } else {
                                    Log.d("notretreive", "Error: " + e.getMessage());
                                }
                            }
                        });

//                        Log.d("patient", "name: " + user.getString("name"));
                    }

                } else {

                    Log.d("notretreive", "Error: " + e.getMessage());
                }

                for (int i = 0; i < totalTemp.size(); i++)
                {
                    List innerList = (List)totalTemp.get(i);
                    Date date = (Date) innerList.get(0);
                    Date currentDate = new Date();
                    String temp = (String)innerList.get(1);
                    String[] random = temp.split("/");

                    if(date.compareTo(currentDate) > 3 || date.compareTo(currentDate) == 0)
                    {
                        params.put("Reminder: ", "Your Appointment is on the " + date.toString() + "Please come punctually.");
                        params.put("subject", "Reminder");
                        params.put("from qwikdoc", "qwikDoc@gmail.com");
                        /*params.put("fromName", "Source User");
                        params.put("toEmail", random[1]);
                        params.put("toName", random[0]);
                    }

                    ParseCloud.callFunctionInBackground("sendMail", params, new FunctionCallback<Object>() {
                        @Override
                        public void done(Object response, ParseException exc) {
                            Log.e("cloud code example", "response: " + response);
                        }
                    });
                }
            }
        });
    }:
}*/