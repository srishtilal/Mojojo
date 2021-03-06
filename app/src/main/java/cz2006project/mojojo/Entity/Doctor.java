package main.java.cz2006project.mojojo.Entity;

import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.net.InetSocketAddress;
import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Created by Dhruv on 2/24/2015.
 */
@ParseClassName("Doctor")
public class Doctor extends ParseObject {


    public Doctor(){
        //A default constructor is required
    }

    final Doctor doctor = new Doctor();



    public void setDoctor(ParseObject doctor) {
        put("doctor", doctor);

    }

    public ParseObject getDoctor(){
        return getParseObject("doctor");
    }

    public void setClinic(ParseObject clinic) {
        put("clinic", clinic);
    }

    public ParseObject getClinic(){
        return getParseObject("clinic");
    }

    public void setPatient(ParseObject patient) {
        put("patient", patient);
    }


    public ParseObject getPatient(){
        return getParseObject("patient");
    }

    public void setNotes(String notes) {
        put("notes", notes);
    }


    public String getNotes(){
        return getString("notes");
    }

    public void setIsFollowUpAppointment(boolean isFollowUpAppointment) {
        put("followUp", isFollowUpAppointment);
    }


    public boolean getIsFollowUpAppointment(){
        return getBoolean("followup");
    }

    public void setReminder(String reminder) {
        put("reminder", reminder);
    }


    public String getReminder(){
        return getString("reminder");
    }

    public void setAppointmentNo(int appointmentNo) {
        put("AppointmentNumber", appointmentNo);
    }


    public int getAppointmentNo(){
        return getInt("AppointmentNumber");
    }

    public void setAppointmentDate(Date appointmentDate) {
        put("Date", appointmentDate);
    }


    public Date getAppointmentDate(){
        return getDate("Date");
    }

    public void setAppointmentTime(String appointmentTime) {
        put("Time", appointmentTime);
    }


    public String getAppointmentTime(){
        return getString("date");
    }


}

