package main.java.cz2006project.mojojo.Entity;

import com.parse.ParseObject;

import java.sql.Time;
import java.util.Date;
/**
 * Created by ZX&SL on 26/3/15.
 */
public class Schedule extends ParseObject {


    public Time getAppointmentTime()
    {
        return getAppointmentTime();
    }



    public void setAppointmentTime(Time appointmentTime)
    {
        put("appointmentTime",appointmentTime);
    }



    public Date getAppointmentDate()
    {
        return getDate ("appointmentDate");
    }



    public void setAppointmentDate(Date appointmentDate)
    {
        put("appointmentDate",appointmentDate);
    }



    public String getComment()
    {
        return getString ("comment");
    }



    public void setComment(String comment)
    {
        put("comment",comment);
    }


}
