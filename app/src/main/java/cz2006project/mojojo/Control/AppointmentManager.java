package main.java.cz2006project.mojojo.Control;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.ArrayList;

import main.java.cz2006project.mojojo.Entity.Appointment;

/**
 * Created by Dhruv again on 2/24/2015.
 */

    public class AppointmentManager {

    private ArrayList<Appointment> AppointmentList;
    private ParseQuery<Appointment> Query = null;
    public void AppointmentManager(){


    }


    public void CreateAppointment(){



    }

    public void  CancelAppointment(int AppointmentNo){

    }

    public void ModifyAppointment(int AppointmentNo){

    }

    public Boolean VerifyFollowUpAppointment(int AppointmentNo)
    {
        Query = new ParseQuery<Appointment>("Appointment");
        Query = ParseQuery.getQuery("Appointment");
        Query.whereEqualTo("AppointmentNo",AppointmentNo);
        try
        {
            AppointmentList = (ArrayList)Query.find();
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

    public Boolean ValidateAppointment(){

    }

}
