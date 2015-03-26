package main.java.cz2006project.mojojo.Entity;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.net.InetSocketAddress;
import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Created by Dhruv on 2/24/2015.
 */
public class Appointment extends ParseObject
{
    private Doctor DoctorName;
    private Clinic clinicName;
    private String patientNRIC;


    public Date getAppointmentDate()
    {
        return getDate("appointmentDate");
    }

    public String getReminder() {return getString("Reminder");}

    public void setReminder (String Reminder)
    {
        put("Reminder",Reminder);
    }

    public void setAppointmentDate(Date appointmentDate)
    {
        put("appointmentDate",appointmentDate);
    }



    public Time getTime()
    {
        return getTime();
    }


    public void setTime(Time time)
    {
        put("time",time);
    }



    public String getOtherSpecification()
    {
        return getString("otherSpecification");
    }



    public void setOtherSpecification(String otherSpecification)
    {
        put("otherSpecification",otherSpecification);
    }



    public Doctor getDoctorName()
    {
        return DoctorName;
    }



    public void setDoctorName(Doctor doctorName)
    {
        DoctorName = doctorName;
    }



    public Clinic getCinicName()
    {
        return clinicName;
    }



    public void setCinicName(Clinic cinicName)
    {
        this.clinicName = cinicName;
    }



    public ParseUser getAppointmentNo()
    {
        return getParseUser("appointmentNo");
    }



    public void setAppointmentNo(int appointmentNo)
    {
        put("appointmentNo", appointmentNo);
    }


    public boolean IsFollowUpAppointment()
    {
        return getBoolean("IsFollowUpAppointment");
    }


    public void setIsFollowUpAppointment(Boolean number)
    {
        put("IsFollowUpAppointment",number);
    }

    public List<Integer> getRequiredAppointment()
    {
    return getList("RequiredAppointment");
    }

    public void setRequiredAppointment(List <Integer> ApptList)
    {
        put("RequiredAppointment",ApptList);
    }


}