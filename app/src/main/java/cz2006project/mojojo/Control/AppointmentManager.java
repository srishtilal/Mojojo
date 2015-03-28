package main.java.cz2006project.mojojo.Control;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;
import java.util.List;

import main.java.cz2006project.mojojo.Entity.Appointment;

/**
 * Created by SL & ZX again on 2/24/2015.
 */

    public class AppointmentManager{

    private List<Appointment> AppointmentList = null;
    private ParseQuery<Appointment> Query = null;


    public void AppointmentManager()
    {
        Query = new ParseQuery<Appointment>("Appointment");
    }

    public void CreateAppointment(ParseObject clinic, ParseObject patient, ParseObject doctor, String notes, int appointmentNo, Date date, String time)
    {
        Appointment appointment = new  Appointment(clinic, patient,doctor,notes,appointmentNo,date,time);

    }


    public void CreateFollowUpAppointment(ParseObject clinic, ParseObject patient, ParseObject doctor, String notes, int appointmentNo, Date date, String time)
    {
        if (VerifyFollowUpAppointment(appointmentNo)== false)
        {
            Error();
        }

        else
        {
            Appointment appointment = new  Appointment(clinic, patient,doctor,notes,appointmentNo,date,time);
            AppointmentList.add(appointment);

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

    public void StoreAppointment (List<Appointment> AppointmentList)
    {

        
    }  /* I DONT KNOW IF THIS IS THE LOGICAL WAYYY HELLPPPPP!>< (as in the .add(appointment) thingy) */



    public void ModifyAppointment(int AppointmentNo, Date AppointmentDate , String AppointmentTime)
     /*assuming the modifyhere is after the user has keyed in the new date*/
    {
        if (ValidateAppointment(AppointmentNo)== true)
        {
            AppointmentList.get(AppointmentNo).setAppointmentDate(AppointmentDate) ;
            AppointmentList.get(AppointmentNo).setAppointmentTime(AppointmentTime);

        }
        else
        {
            Error();
        }

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



    public void SendReminders(String Reminder, int AppointmentNo)
    {

        
    }

    public String Error()
    {
        return ("There is an error!");
    }




}
