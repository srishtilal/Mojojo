package main.java.cz2006project.mojojo.Entity;

import java.sql.Time;
import java.util.Date;

/**
 * Created by srishti on 15/3/15.
 */
public class Schedule {


    private Time appointmentTime;
    private Date appointmentDate;
    private String comment;

    public Time getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Time appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
