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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import cz2006project.mojojo.R;
import main.java.cz2006project.mojojo.Control.ParseTables;
import main.java.cz2006project.mojojo.Entity.Appointment;
import main.java.cz2006project.mojojo.MaterialEditText;
import main.java.cz2006project.mojojo.ProgressBarCircular;

public class editAppointment extends Fragment {


    Button create;
    static View v;
    private Spinner cspinner, dspinner;
    private CheckBox isFollowUp;
private int AppointmentNumber;
    private String appNo;
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
        isFollowUp = (CheckBox) v.findViewById(R.id.checkBox);
        //mspinner = (Spinner) v.findViewById(R.id.medicalissue);

        ParseQueryAdapter.QueryFactory<ParseObject> Clinic =
                new ParseQueryAdapter.QueryFactory<ParseObject>() {
                    public ParseQuery<ParseObject> create() {
                        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Clinic");
                        return query;}};

        ParseQueryAdapter<ParseObject> adapter = new ParseQueryAdapter<ParseObject>(getActivity(), Clinic);
        adapter.setTextKey("name");
        adapter.notifyDataSetChanged();

        cspinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        cspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

final String clinicName = cspinner.getSelectedItem().toString();

                ParseQueryAdapter.QueryFactory<ParseObject> Doctor =
                        new ParseQueryAdapter.QueryFactory<ParseObject>() {
                            public ParseQuery<ParseObject> create() {
                                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Doctor");
                                query.whereEqualTo("Clinic" ,"Evil Inc");

                                //query.whereEqualTo("name", clinicName).include("doctor").include("Name");
                                return query;
                            }
                        };


                ParseQueryAdapter<ParseObject> adapter1 = new ParseQueryAdapter<ParseObject>(getActivity(), Doctor);
                adapter1.setTextKey("Name");
                dspinner = (Spinner) v.findViewById(R.id.doctorspinner);
                dspinner.setAdapter(adapter1);

            }


            public void onNothingSelected(AdapterView<?> parent) {
                dspinner.setAdapter(null);
            }
        });





/*


        ParseQueryAdapter.QueryFactory<ParseObject> Doctor =
                new ParseQueryAdapter.QueryFactory<ParseObject>() {
                    public ParseQuery<ParseObject> create() {
                        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Doctor");
                        query.whereEqualTo("clinic", cspinner.getSelectedItem().toString());

                        return query;}};


        ParseQueryAdapter<ParseObject> adapter1 = new ParseQueryAdapter<ParseObject>(getActivity(), Doctor);
        adapter1.setTextKey("Name");
        dspinner = (Spinner) v.findViewById(R.id.doctorspinner);
        dspinner.setAdapter(adapter1);
        dspinner.setSelection(1);
*/

/*
        ParseQueryAdapter.QueryFactory<ParseObject> Doctor =
                new ParseQueryAdapter.QueryFactory<ParseObject>() {
                    public ParseQuery<ParseObject> create() {
                        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Doctor");
                        query.whereEqualTo("Name", v.findViewById(R.id.doctorspinner));


                        return query;}};

        ParseQueryAdapter<ParseObject> adapter2 = new ParseQueryAdapter<ParseObject>(getActivity(), factory);
        adapter.setTextKey("specialty");
        mspinner = (Spinner) v.findViewById(R.id.medicalissue);
        mspinner.setAdapter(adapter);
        mspinner.setSelection(1);
*/



//        ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
//        query.findInBackground(new FindCallback<ParseObject>() {
//            public void done(List<ParseObject> cliniclist, ParseException e) {
//                if (e == null) {
//                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//                            getActivity(), R.array.clinics_array, android.R.layout.simple_spinner_item);
//                    cspinner.setAdapter(adapter);
//                    Log.d("score", "Retrieved " + cliniclist.size() + " scores");
//                } else {
//                    Log.d("score", "Error: " + e.getMessage());
//                }
//            }
//        });


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
        CheckBox isFollowUp  = (CheckBox)v.findViewById(R.id.checkBox);
        //ParseObject doctor = dspinner.getSelectedItem().toString();



        ParseQuery<ParseObject> query = ParseQuery.getQuery("Appointment");
        query.orderByDescending("AppointmentNumber");
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                //Is relationship exists?
                AppointmentNumber = parseObject.getInt("appN") + 1;
                 appNo = String.valueOf(AppointmentNumber);

            }
        });



        //Spinner mspinner = (Spinner)v.findViewById(R.id.medicalissue);

        appointments.put(ParseTables.Appointment.APPOINTMENTNUMBER, appNo);

        appointments.put(ParseTables.Appointment.PATIENT, ParseUser.getCurrentUser().getString("name"));
        appointments.put(ParseTables.Appointment.DOCTOR,  dspinner.getSelectedItem().toString());
        appointments.put(ParseTables.Appointment.CLINIC,  cspinner.getSelectedItem().toString());
        if(isFollowUp.isChecked()) {
            String cstatus = "Yes";
            appointments.put(ParseTables.Appointment.FOLLOWUP, cstatus);

        } else {
            String cstatus = "No";
            appointments.put(ParseTables.Appointment.FOLLOWUP, cstatus);

        }
        //appointments.put(ParseTables.Appointment.MEDICALISSUE,  mspinner.getSelectedItem().toString());
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
        if (appointments.get(ParseTables.Appointment.APPOINTMENTNUMBER).isEmpty()) {
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





            Appointment appointment = new Appointment();


        appointment.put(ParseTables.Appointment.APPOINTMENTNUMBER, appointments.get(ParseTables.Appointment.APPOINTMENTNUMBER));

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


}

