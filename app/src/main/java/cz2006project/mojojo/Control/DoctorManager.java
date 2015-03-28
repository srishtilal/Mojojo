package main.java.cz2006project.mojojo.Control;

import android.content.Entity;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseQuery;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;
import main.java.cz2006project.mojojo.Entity.Doctor;
import main.java.cz2006project.mojojo.Entity.MedicalRecords;

import java.util.Scanner;

/**
 * Created by sai on 23/3/15.
 */

/*getschedule
setschedule
getDoctorNRIC
setDoctorNRIC
getpassword
setpassword
getname()
setname(name)
getclinic
getspecialty
setspecialy
 */

/*public class DoctorManager {

    private ParseQuery<Doctor> Query = null;
    private ParseQuery<MedicalRecords> = null

    public void AuthenticateDoctor(){       //compare with the doctorNRIC
        ParseQuery<ParseObject> query = ParseQuery.getQuery("doctorinfo");
        query.whereEqualTo("DoctorNRIC", Doctor.getname());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (object == null) {
                    if (password == Doctor.getpassword(Doctor.getDoctorNRIC)){
                        //grant access to the user
                    }
                    else {
                        System.out.println("Wrong password! Please try again!")
                    }

                } else {
                    System.out.println("No such NRIC input in our database");
                }
            }
        });
    }

    public void PrescribeMedicine(String message) {
        //validate patient or doctor enter he nric
        //update the message into the database
        // create a pointer to th object id s
    }

    public void UpdateSchedule() {

    }

    public void ViewMedicalRecord() {

    }
}
