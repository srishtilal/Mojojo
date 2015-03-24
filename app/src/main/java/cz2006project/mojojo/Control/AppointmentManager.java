package main.java.cz2006project.mojojo.Control;

import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Dhruv again on 2/24/2015.
 */

public class AppointmentManager {

    private ArrayList<Appointment> AppointmentList;
    public void AppointmentManager(){


    }


    public void CreateAppointment(){



    }

    public void  CancelAppointment(int AppointmentNo){

    }

    public void ModifyAppointment(int AppointmentNo){

    }

    public Boolean VerifyFollowUpAppointment(ParseObject appointment , String ){

        ParseQuery<ParseObject> query = ParseQuery.getQuery(appointment.getObjectId());
        return true;
    }

    public Boolean ValidateAppointment(){

    }

}
