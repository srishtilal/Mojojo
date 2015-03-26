package main.java.cz2006project.mojojo.Control;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;
import java.util.List;

import main.java.cz2006project.mojojo.Entity.Appointment;

/**
 * Created by Dhruv again on 2/24/2015.
 */

    public class AppointmentManager {

    private List<Appointment> AppointmentList;
    private ParseQuery<Appointment> Query = null;


    public void AppointmentManager()
    {
        Query = new ParseQuery<Appointment>("Appointment");

    }

    public void CreateAppointment()
    {
        Appointment appointment = new  Appointment();
    }


    public void CreateFollowUpAppointment(int AppointmentNo)
    {
        if (VerifyFollowUpAppointment(AppointmentNo) != true)
        {
            Appointment appointment = new  Appointment();
        }

        else if (VerifyFollowUpAppointment(AppointmentNo) ==true)
        {
            Appointment appointment = new  Appointment();
        }
    }



    public void  CancelAppointment(int AppointmentNo)
    {
        if (ValidateAppointment( AppointmentNo) == true)
        {
            AppointmentList.remove(AppointmentNo);
        }

        else
        {
            Error();
        }


    }

    public void StoreAppointment (int AppointmentNo)
    {

    }



    /*public void ModifyAppointment(int AppointmentNo)
    {
        if (ValidateAppointment(AppointmentNo)== true)

    }
    */

    public Boolean VerifyFollowUpAppointment(int AppointmentNo)
    {
        Query = ParseQuery.getQuery("Appointment");
        Query.whereEqualTo("AppointmentNo",AppointmentNo);
        try
        {
            AppointmentList = Query.find();
            for(Appointment Appt : AppointmentList)
            {
                if(Appt.getIsFollowUpAppointment())
                    return true;
            }
        }
        catch (com.parse.ParseException e)
        {
                e.printStackTrace();
        }
        return false;
    }



    public Boolean ValidateAppointment(int AppointmentNo)
    {
        Query = ParseQuery.getQuery("Appointment");
        Query.whereEqualTo("AppointmentNo",AppointmentNo);
        try {
            AppointmentList = Query.find();
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean SetReminders(String Reminder, int AppointmentNo)
    {
        for (int i = 0 ; i < AppointmentList.size(); i++)
        {
            if (ValidateAppointment(AppointmentNo) == true)
            {
                AppointmentList.get(i).setReminder(Reminder);
                return true;
            }
        }
        return false;

    }


    public void SendReminders()
    {

    }

    public String Error()
    {
        return ("There is an error!");
    }




}
