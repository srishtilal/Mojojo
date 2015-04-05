package main.java.cz2006project.mojojo.Boundary.Appointments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import cz2006project.mojojo.R;
import main.java.cz2006project.mojojo.Control.ParseTables;
import main.java.cz2006project.mojojo.CustomTimePicker;
import main.java.cz2006project.mojojo.Entity.Appointment;
import main.java.cz2006project.mojojo.MaterialEditText;
import main.java.cz2006project.mojojo.ProgressBarCircular;

public class CreateAppointmentFragment extends Fragment {


    Button create;
    static View v;
    private Spinner cspinner, dspinner, tspinner;
    private CheckBox isFollowUp;
    private String AppointmentNumber;
    private String appNo;




    private static HashMap<String, Object> appointments;
    ImageButton setDate;
    ImageButton setTime;
    List<String> cliniclist = new ArrayList<>();
    List<String> doctorlist = new ArrayList();


    ProgressBarCircular progressBarCircular;


    public CreateAppointmentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        appointments = new HashMap<>();
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.createappointment, container, false);
        create = (Button) v.findViewById(R.id.submit_button);
        setDate = (ImageButton) v.findViewById(R.id.date_picker);
        setTime = (ImageButton) v.findViewById(R.id.time_picker);
        cspinner = (Spinner) v.findViewById(R.id.clinicspinner);
        dspinner = (Spinner) v.findViewById(R.id.doctorspinner);
        isFollowUp = (CheckBox) v.findViewById(R.id.checkBox);
        tspinner = (Spinner) v.findViewById(R.id.typespinner);


        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.appointment_type_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        tspinner.setAdapter(adapter2);

        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Clinic");
        query.whereExists("name");

        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> clinics, ParseException e) {
                // The query returns a list of objects from the "questions" class
                if (e == null) {
                    for (ParseObject clinic : clinics) {
                        // Get the questionTopic value from the question object
                        String clinicname = clinic.getString("name");
                        cliniclist.add(clinicname);
                        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, cliniclist);
                        cspinner.setAdapter(adapter);

                        Log.d("clinic", "name: " + clinic.getString("name"));
                    }

                } else {
                    Log.d("notretreive", "Error: " + e.getMessage());
                }


                cspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        // TODO Auto-generated method stub


                        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Doctor");
                        query.whereExists("Name");
                        query.whereEqualTo("Clinic", cspinner.getSelectedItem().toString());



                                                doctorlist.clear();

                        query.findInBackground(new FindCallback<ParseObject>() {

                            @Override
                            public void done(List<ParseObject> doctors, ParseException e) {
                                // The query returns a list of objects from the "questions" class
                                if (e == null) {
                                    for (ParseObject doctor : doctors) {
                                        // Get the questionTopic value from the question object
                                        String initial = cspinner.getSelectedItem().toString();

                                        String doctorname = doctor.getString("Name");
                                        doctorlist.add(doctorname);

                                        Log.d("doctor", "name: " + doctor.getString("Name"));
                                        ArrayAdapter adapter1 = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, doctorlist);
                                        dspinner.setAdapter(adapter1);
                                                               Log.d("doctor", "name: " + doctor.getString("Name"));
                                                               adapter1 = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, doctorlist);
                                                               dspinner.setAdapter(adapter1);



                                        //new AdapterHelper().update( new ArrayList (doctorlist));
                                        //adapter1.notifyDataSetChanged();

                                    }

                                } else {
                                    Log.d("notretreive", "Error: " + e.getMessage());
                                }
                            }
                        });
                    }
                                           /*public class AdapterHelper {
                                               @SuppressWarnings({ "rawtypes", "unchecked" })
                                               public void update(ArrayList<String> doctorlist){
                                                   doctorlist.clear();
                                                   for (Object doctorname : doctorlist){
                                                       adapter1.add(doctorname);
                                                   }
                                               }
                                           }
*/


                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });


            }
        });







        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.show(getActivity().getSupportFragmentManager(), "Set Time");
            }
        });

        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePicker = new DatePickerFragment();
                datePicker.show(getActivity().getSupportFragmentManager(), "Set Date");
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                create.setClickable(false);
                addInput();
                if (checkIfEmpty()) {
                    pushDataToParse();
                }

            }
        });
        return v;

    }


    public void addInput() {
        Spinner dspinner = (Spinner)v.findViewById(R.id.doctorspinner);
        Spinner cspinner = (Spinner)v.findViewById(R.id.clinicspinner);
        Spinner tspinner = (Spinner)v.findViewById(R.id.typespinner);

        CheckBox isFollowUp  = (CheckBox)v.findViewById(R.id.checkBox);



        appointments.put(ParseTables.Appointment.PATIENT, ParseUser.getCurrentUser().getString("name"));
        appointments.put(ParseTables.Appointment.TYPE,  tspinner.getSelectedItem().toString());
        appointments.put(ParseTables.Appointment.DOCTOR,  dspinner.getSelectedItem().toString());
        appointments.put(ParseTables.Appointment.CLINIC,  cspinner.getSelectedItem().toString());
        if(isFollowUp.isChecked()) {
            String cstatus = "Yes";
            appointments.put(ParseTables.Appointment.FOLLOWUP, cstatus);

        } else {
            String cstatus = "No";
            appointments.put(ParseTables.Appointment.FOLLOWUP, cstatus);

        }
        appointments.put(ParseTables.Appointment.NOTES, ((MaterialEditText) v.findViewById(R.id.notes)).getText() + "");



    }

    private boolean checkIfEmpty() {
        if (appointments.get(ParseTables.Appointment.DOCTOR).toString().isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(), "Please select doctor", Toast.LENGTH_LONG).show();
            return false;
        }

        if (appointments.get(ParseTables.Appointment.TYPE).toString().isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(), "Please select doctor", Toast.LENGTH_LONG).show();
            return false;
        }
        if (appointments.get(ParseTables.Appointment.CLINIC).toString().isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(), "Please select clinic", Toast.LENGTH_LONG).show();
            return false;
        }

       /* if (appointments.get(ParseTables.Appointment.APPOINTMENTNUMBER).isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(), "Appointment Number not generated", Toast.LENGTH_LONG).show();
            return false;
        }
*/



        if(!appointments.containsKey(ParseTables.Appointment.DATE)){
            Toast.makeText(getActivity().getApplicationContext(), "Please enter date", Toast.LENGTH_LONG).show();
            return false;
        }
        if(!appointments.containsKey(ParseTables.Appointment.TIME)){
            Toast.makeText(getActivity().getApplicationContext(), "Please enter time", Toast.LENGTH_LONG).show();
            return false;
        }


        return true;
    }

    private void pushDataToParse() {





        Appointment appointment = new Appointment();
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
        appointment.put(ParseTables.Appointment.DATE, appointments.get(ParseTables.Appointment.DATE));
        appointment.put(ParseTables.Appointment.TIME, appointments.get(ParseTables.Appointment.TIME));
        appointment.put(ParseTables.Appointment.CLINIC, appointments.get(ParseTables.Appointment.CLINIC));
        appointment.put(ParseTables.Appointment.DOCTOR, appointments.get(ParseTables.Appointment.DOCTOR));
        appointment.put(ParseTables.Appointment.TYPE, appointments.get(ParseTables.Appointment.TYPE));

        appointment.put(ParseTables.Appointment.FOLLOWUP, appointments.get(ParseTables.Appointment.FOLLOWUP));
        appointment.put(ParseTables.Appointment.NOTES, appointments.get(ParseTables.Appointment.NOTES));
        appointment.put(ParseTables.Appointment.PATIENT, appointments.get(ParseTables.Appointment.PATIENT));

        appointment.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                create.setClickable(true);
                Toast.makeText(getActivity().getApplicationContext(),
                        getString(R.string.appointment_created), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            monthOfYear++;
            String date = String.valueOf(dayOfMonth) + "/" + monthOfYear + "/" + year;
            ((MaterialEditText)v.findViewById(R.id.appointment_date)).setText(date);

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, monthOfYear - 1, dayOfMonth);
            appointments.put(ParseTables.Appointment.DATE, calendar.getTime());





        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog date =   new DatePickerDialog(getActivity(), this, year, month, day);



            Calendar cal = Calendar.getInstance();
            cal.set(year, month + 1, day);

            date.getDatePicker().setMinDate(c.getTimeInMillis());
            date.getDatePicker().setMaxDate(cal.getTimeInMillis());



            //If you need you can set min date too


            return date;
        }

    }



    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


            String time;
            String min = Integer.toString(minute);
            if(minute == 0){
                min = "00";
            }
            else
            min = "30";
            if(hourOfDay > 12){
                hourOfDay = hourOfDay - 12;
                time = String.valueOf(hourOfDay) + ":" + min + " pm";
            }else {
                time = String.valueOf(hourOfDay) + ":" + min + " am";
            }
            appointments.put(ParseTables.Appointment.TIME, time);
            ((MaterialEditText)v.findViewById(R.id.appointment_time)).setText(time);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = 9;
            int minute = 0;
            CustomTimePicker cusTimePicker = new CustomTimePicker(getActivity(), this, hour, minute , DateFormat.is24HourFormat(getActivity()));


            return cusTimePicker;
        }

    }
}

