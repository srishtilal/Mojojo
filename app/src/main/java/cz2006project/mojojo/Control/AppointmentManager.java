package main.java.cz2006project.mojojo.Control;

/**import com.parse.ParseObject;

import com.parse.ParseQuery;

import com.parse.ParseUser;

import java.util.ArrayList;

import main.java.cz2006project.mojojo.Entity.Appointment;

*/
/**
 * Created by Dhruv again on 2/24/2015.
 */


/**public class AppointmentManager extends ParseObject
{
    private ArrayList<Appointment> AppointmentList;
    public AppointmentManager(ArrayList<Appointment> AppointmentList)
    {

    }
    public void AppointmentManager()
            {
                this.AppointmentList = AppointmentList;
                if (AppointmentList == null)
                {AppointmentList= getParseObject("Appointment");}



            }


            public void CreateAppointment(){


            }

            public void  CancelAppointment(int AppointmentNo){

            }

            public void ModifyAppointment(int AppointmentNo){

            }

            public Boolean VerifyFollowUpAppointment(ParseObject appointment , String ){

                public void CreateAppointment()
                {
                    if (VerifyFollowUpAppointment()== false)
                    {ParseObject NewAppointment = new ParseObject("Appointment");}

                }
            public void  CancelAppointment(int AppointmentNo)
            {
                if (ValidateAppointment()== true)
                {Appointment.delete};

            }

            public void ModifyAppointment(int AppointmentNo)
            {

            }

            public void ViewAppointment()
            {


            }

            public Boolean VerifyFollowUpAppointment()
            {

                ParseQuery<ParseObject> query = ParseQuery.getQuery(appointment.getObjectId());
                return true;
            }

            public Boolean ValidateAppointment(){

            }
        }
        }

}
*/