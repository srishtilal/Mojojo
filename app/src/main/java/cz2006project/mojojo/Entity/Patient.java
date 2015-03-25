package main.java.cz2006project.mojojo.Entity;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Date;
import java.util.List;


public class Patient extends ParseObject{

    private ParseQuery<Appointment> Query = null;

    public String getPatientName()
    {
        return getString("patientName");
    }



    public void setPatientName(String patientName)
    {
        put("patientNRIC",patientName);
    }



    public ParseUser getPatientNRIC()
    {
        return getParseUser("patientNRIC");
    }



    public void setPatientNRIC(String patientNRIC)
    {
        put("patientNRIC",patientNRIC);
    }



    public String getPassword()
    {
        return getString("password");
    }


    public void setPassword(String password)
    {
        put("password",password);
    }



    public String getEmail()
    {
        return getString("email");
    }



    public void setEmail(String email)
    {
        put("email",email);
    }



    public Date getDateOfBirth()
    {
        return getDate("dateOfBirth");
    }



    public void setDateOfBirth(Date dateOfBirth)
    {
        put("dateOfBirth",dateOfBirth);
    }



    public boolean isGender()
    {
        return getBoolean("gender");
    }



    public void setGender(boolean gender)
    {
        put("gender",gender);
    }



    public ParseObject getMedicalRecords()
    {
        return getParseObject("medicalRecords");
    }



    public void setMedicalRecord(List<MedicalRecords> medicalRecords)
    {
        put("medicalRecords", medicalRecords);
    }



    public List<Appointment> getAppointments()
    {
        return getList("Appointments");
    }



    public void setAppointments(List<Appointment> Appointments)
    {
        put("Appointment",Appointments);
    }



    public String getUserName()
    {
        return getString("userName");
    }



    public void setUserName(String userName)
    {
        put("userName",userName);
    }


}