package main.java.cz2006project.mojojo.Entity;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;
import java.util.List;


@ParseClassName("Appointment")
public class Appointment extends ParseObject {

    public Appointment(ParseObject clinic, ParseObject patient, ParseObject doctor, String notes, ParseUser appointmentNo, Date date, String time)
    {
        setClinic(clinic);
        setPatient(patient);
        setDoctor(doctor);
        setAppointmentNo(appointmentNo);
        setAppointmentDate(date);
        setAppointmentTime(time);
    }


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

    public void setReminder(String reminder) {
        put("reminder", reminder);
    }


    public String getReminder(){
        return getString("reminder");
    }

    public void setAppointmentNo(ParseUser appointmentNo) {
        put("AppointmentNumber", appointmentNo);
    }


    public ParseUser getAppointmentNo(){
        return getParseUser("AppointmentNumber");
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

    public List<ParseUser> getFollowUpAppointmentNo()
    {
        return(getList("followUpAppointmentNo"));
    }
    public void setFollowUpAppointmenttNo(List<ParseUser> appointmentNo)
    {
        put("followUpAppointmentNo",appointmentNo);
    }
    public boolean getHasAttended()
    {
        return getBoolean("hasAttended") ;
    }
    public void  setHasAttended(boolean hasAttended)
    {
        put("hasAttended",hasAttended);
    }


}

