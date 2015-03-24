package main.java.cz2006project.mojojo.Control;

import java.util.ArrayList;
import java.util.Date;

import main.java.cz2006project.mojojo.Entity.Patient;

/**
 * Created by Dhruv on 2/24/2015.
 */
public class PatientManager {


    private ArrayList<Patient> patientList = new ArrayList<Patient>();
    private boolean isLogIn;

    public void removePatient(String NRIC){

        for (Patient patient : patientList)
        {
            if (patient.getPatientNRIC() ==  NRIC)
            {
                patientList.remove(patient);
            }

        }

    }

    public void registerPatient(String pName, String pPassword, String pEmail, String pNRIC, String pUserName, boolean pGender, Date pDateOfBirth){

        Patient newPatient = new Patient();

        newPatient.setPatientName(pName);
        newPatient.setPassword(pPassword);
        newPatient.setPatientNRIC(pNRIC);
        newPatient.setDateOfBirth(pDateOfBirth);
        newPatient.setEmail(pEmail);
        newPatient.setUserName(pUserName);

        patientList.add(newPatient);


    }

    public void authenticatePatient{



    }


    public void validatePatient{



    }

}
*/