package main.java.cz2006project.mojojo.Control;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

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


    public void CreateAppointment(int AppointmentNo)
    {
        if (VerifyFollowUpAppointment(AppointmentNo) == true)
        {Appointment appointment = new  Appointment();};

    }



    public void  CancelAppointment(int AppointmentNo)
    {
        if (ValidateAppointment( AppointmentNo) == true)
        {
            AppointmentList.remove();

        }



    }



    public void ModifyAppointment(int AppointmentNo)
    {

    }



    public Boolean VerifyFollowUpAppointment(int AppointmentNo)
    {
        Query = ParseQuery.getQuery("Appointment");
        Query.whereEqualTo("AppointmentNo",AppointmentNo);
        try
        {
            AppointmentList = Query.find();
            for(Appointment Appt : AppointmentList)
            {
                if(Appt.IsFollowUpAppointment())
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

}
