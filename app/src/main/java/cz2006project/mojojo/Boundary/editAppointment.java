package main.java.cz2006project.mojojo.Boundary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseUser;

import java.util.HashMap;

import cz2006project.mojojo.R;
import main.java.cz2006project.mojojo.Control.ParseTables;
import main.java.cz2006project.mojojo.MaterialEditText;
import main.java.cz2006project.mojojo.ProgressBarCircular;

public class editAppointment extends Fragment {


    Button create;
    static View v;
    private Spinner cspinner, dspinner, mspinner;
    private static HashMap<String, String> appointments;
    ImageButton setDate;
    ImageButton setTime;
    ProgressBarCircular progressBarCircular;


    public editAppointment() {
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
        mspinner = (Spinner) v.findViewById(R.id.medicalissue);

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAppointment.TimePickerFragment timePickerFragment = new changeAppointment.TimePickerFragment();
                timePickerFragment.show(getActivity().getSupportFragmentManager(), "Set Time");
            }
        });
        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAppointment.DatePickerFragment datePicker = new changeAppointment.DatePickerFragment();
                datePicker.show(getActivity().getSupportFragmentManager(), "Set Date");
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarCircular.setBackgroundColor(getResources().getColor(R.color.eventsColorPrimary));
                progressBarCircular.setVisibility(View.VISIBLE);
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
        Spinner mspinner = (Spinner)v.findViewById(R.id.medicalissue);


        appointments.put(ParseTables.Appointment.PATIENT, ParseUser.getCurrentUser().getString("name"));
        appointments.put(ParseTables.Appointment.DOCTOR,  dspinner.getSelectedItem().toString());
        appointments.put(ParseTables.Appointment.CLINIC,  cspinner.getSelectedItem().toString());
        appointments.put(ParseTables.Appointment.MEDICALISSUE,  mspinner.getSelectedItem().toString());
        appointments.put(ParseTables.Appointment.NOTES, ((MaterialEditText) v.findViewById(R.id.notes)).getText() + "");



    }

    private boolean checkIfEmpty() {
        if (appointments.get(ParseTables.Appointment.DOCTOR).isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(), "Please select doctor", Toast.LENGTH_LONG).show();
            return false;
        }
        if (appointments.get(ParseTables.Appointment.CLINIC).isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(), "Please select clinic", Toast.LENGTH_LONG).show();
            return false;
        }


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
       /* Spinner dspinner = (Spinner)v.findViewById(R.id.doctorspinner);
        Spinner cspinner = (Spinner)v.findViewById(R.id.clinicspinner);
        Spinner mspinner = (Spinner)v.findViewById(R.id.medicalissue);

        ParseQuery<ParseObject> queryDoctor = ParseQuery.getQuery("Doctor");
        ParseQuery<ParseObject> queryPatient = ParseQuery.getQuery("Patient");
        ParseQuery<ParseObject> queryClinic = ParseQuery.getQuery("Clinic");

        queryDoctor.whereEqualTo("Name", dspinner);
        queryPatient.whereEqualTo("Name", cspinner);
        queryPatient.whereEqualTo("Name", ParseUser.getCurrentUser().getString("name"));
        queryDoctor.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> doctorList, List<ParseObject> patientList, List<ParseObject> clinicList, ParseException e) {
                if (e == null) {
                    Log.d("doctor", "Retrieved " + doctorList.size() + " doctors");
                    Log.d("patient", "Retrieved " + patientList.size() + " doctors");
                    Log.d("clinic", "Retrieved " + clinicList.size() + " doctors");


                    Appointment appointment = new Appointment(clinicList.get(0), patientList.get(0), doctorList.get(0),notes );
                    appointments.put(ParseTables.Appointment.NOTES, ((MaterialEditText) v.findViewById(R.id.notes)).getText() + "");



                }

            }

        });
            Appointment appointment = new Appointment();

        appointment.put(ParseTables.Appointment.DATE, appointments.get(ParseTables.Appointment.DATE));
        appointment.put(ParseTables.Appointment.TIME, appointments.get(ParseTables.Appointment.TIME));
        appointment.put(ParseTables.Appointment.CLINIC, appointments.get(ParseTables.Appointment.CLINIC));
        appointment.put(ParseTables.Appointment.DOCTOR, appointments.get(ParseTables.Appointment.DOCTOR));
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
            appointments.put(ParseTables.Appointment.DATE, date);
            ((MaterialEditText)v.findViewById(R.id.appointment_date)).setText(date);
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

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String time;
            String min = Integer.toString(minute);
            if(minute < 10){
                min = "0" +Integer.toString(minute);
            }
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
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }

    }


    public void addItemsOnSpinner2() {
/*
        cspinner = (Spinner) v.findViewById(R.id.clinicspinner);
        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(editAppointment.this,android.R.layout.simple_spinner_item, list);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(addItemsOnSpinner2.this,android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cspinner.setAdapter(dataAdapter);
        */
    }

}

