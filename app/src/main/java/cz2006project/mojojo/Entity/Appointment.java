package main.java.cz2006project.mojojo.Entity;

import com.parse.ParseObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Dhruv on 2/24/2015.
 */
public class Appointment extends ParseObject {

    private Date appointmentDate;
    private Time time;
    private String otherSpecification;
    private Doctor DoctorName;
    private Clinic clinicName;
    private int AppointmentNo;
    private Boolean IsFollowUpAppointment;
    public Date getAppointmentDate() {
        return getDate("appointmentDate");
    }

    public void setAppointmentDate(Date appointmentDate) {
        put("appointmentDate",appointmentDate);
    }

    public Time getTime() {
        return getTime();
    }

    public void setTime(Time time) {
        put("time",time);
    }

    public String getOtherSpecification() {
        return getString("otherSpecification");
    }

    public void setOtherSpecification(String otherSpecification) {
        put("otherSpecification",otherSpecification);
    }

    public Doctor getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(Doctor doctorName) {
        DoctorName = doctorName;
    }

    public Clinic getCinicName() {
        return clinicName;
    }

    public void setCinicName(Clinic cinicName) {
        this.clinicName = cinicName;
    }

    public int getAppointmentNo() {
        return getInt("appointmentNo");
    }

    public void setAppointmentNo(int appointmentNo) {
        getString("appointmentNo");
    }
    public boolean IsFollowUpAppointment()
    {
        return getBoolean("IsFollowUpAppointment");
    }
    public void setIsFollowUpAppointment(Boolean number)
    {
        put("IsFollowUpAppointment",number);
    }
}