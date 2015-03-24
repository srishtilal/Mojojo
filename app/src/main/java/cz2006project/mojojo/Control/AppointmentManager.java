package main.java.cz2006project.mojojo.Control;

import com.parse.ParseObject;
<<<<<<< Updated upstream
import com.parse.ParseQuery;
=======
import com.parse.ParseUser;

import java.util.ArrayList;

import main.java.cz2006project.mojojo.Entity.Appointment;
>>>>>>> Stashed changes

/**
 * Created by Dhruv again on 2/24/2015.
 */

<<<<<<< Updated upstream
public class AppointmentManager {

    private ParseObject AppointmentList;

    public void AppointmentManager(){
=======
public class AppointmentManager extends ParseObject{

    private ArrayList<Appointment> AppointmentList;

>>>>>>> Stashed changes

    public AppointmentManager(ArrayList<Appointment> AppointmentList)
    {
        this.AppointmentList = AppointmentList;
        if (AppointmentList == null)
        {AppointmentList= getParseObject("Appointment");}

    }

<<<<<<< Updated upstream

    public void CreateAppointment(){


    }

    public void  CancelAppointment(int AppointmentNo){

    }

    public void ModifyAppointment(int AppointmentNo){

    }

    public Boolean VerifyFollowUpAppointment(ParseObject appointment , String ){
=======
    public void CreateAppointment()
    {
        if (VerifyFollowUpAppointment()== false)
        {ParseObject NewAppointment = new ParseObject("Appointment");}

    }
    public void  CancelAppointment(int AppointmentNo)
    {
        if (ValidateAppointment()== true)
        {Appointment.delete}
        
    }

    public void ModifyAppointment(int AppointmentNo)
    {

    }

    public void ViewAppointment()
    {


    }

    public Boolean VerifyFollowUpAppointment()
    {
>>>>>>> Stashed changes

        ParseQuery<ParseObject> query = ParseQuery.getQuery(appointment.getObjectId());
        return true;
    }

    public Boolean ValidateAppointment(){

    }

<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
