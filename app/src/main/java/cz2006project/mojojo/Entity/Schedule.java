package main.java.cz2006project.mojojo.Entity;

import java.sql.Time;
import java.util.Date;
import com.parse.ParseObject;
/**
 * Created by srishti on 15/3/15.
 */
public class Schedule extends ParseObject {


    private Time appointmentTime;
    private Date appointmentDate;
    private String comment;

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
