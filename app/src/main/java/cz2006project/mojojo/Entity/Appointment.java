package main.java.cz2006project.mojojo.Entity;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;
import java.util.List;


@ParseClassName("Appointment")
public class Appointment extends ParseObject {

    public Appointment(ParseObject clinic, ParseObject patient, ParseObject doctor, String notes, int appointmentNo, Date date, String time)
    {
        setClinic(clinic);
        setPatient(patient);
        setDoctor(doctor);
        setNotes(notes);
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

    public List<Integer> getFollowUpAppointmentNo()
    {
        return(getList("followUpAppointmentNo"));
    }
    public void setFollowUpAppointmenttNo(List<Integer> appointmentNo)
    {
        put("followUpAppointmentNo",appointmentNo);
    }


}

