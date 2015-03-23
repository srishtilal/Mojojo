package main.java.cz2006project.mojojo.Entity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Dhruv on 2/24/2015.
 */
public class Appointment {

    private Date appointmentDate;
    private Time time;
    private String otherSpecification;
    private Doctor DoctorName;
    private Clinic cinicName;
    private int AppointmentNo;
    private ArrayList<int[]> FollowUpAppointment = new ArrayList<int[]>();

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getOtherSpecification() {
        return otherSpecification;
    }

    public void setOtherSpecification(String otherSpecification) {
        this.otherSpecification = otherSpecification;
    }

    public Doctor getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(Doctor doctorName) {
        DoctorName = doctorName;
    }

    public Clinic getCinicName() {
        return cinicName;
    }

    public void setCinicName(Clinic cinicName) {
        this.cinicName = cinicName;
    }

    public int getAppointmentNo() {
        return AppointmentNo;
    }

    public void setAppointmentNo(int appointmentNo) {
        AppointmentNo = appointmentNo;
    }

    public ArrayList<int[]> getFollowUpAppointment() {
        return FollowUpAppointment;
    }

    public void setFollowUpAppointment(ArrayList<int[]> followUpAppointment) {
        FollowUpAppointment = followUpAppointment;
    }
}