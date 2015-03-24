package main.java.cz2006project.mojojo.Entity;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;


public class Patient extends ParseObject{

    public String getPatientName() { return getString("patientName");
    }

    public void setPatientName(String patientName) {put("patientNRIC",patientName);
    }

    public ParseUser getPatientNRIC() {return getParseUser("patientNRIC");
    }

    public void setPatientNRIC(String patientNRIC) { put("patientNRIC",patientNRIC);
    }

    public String getPassword() {
        return getString("password");
    }

    public void setPassword(String password) {
        put("password",password);
    }

    public String getEmail() {
        return getString("email");
    }

    public void setEmail(String email) {
        put("email",email);
    }

    public Date getDateOfBirth() {
        return getDate("dateOfBirth");
    }

    public void setDateOfBirth(Date dateOfBirth) {
        put("dateOfBirth",dateOfBirth);
    }

    public boolean isGender() {
        return getBoolean("gender");
    }

    public void setGender(boolean gender) {
        put("gender",gender);
    }

    public ParseObject getMedicalRecords() {
        return getParseObject("medicalRecords");
    }

    public void setMedicalRecords(ParseObject medicalRecords) {
        put("medicalRecords", medicalRecords);
    }

    public ParseObject getAppointments() {
        return getParseObject("appointments");
    }

    public void setAppointments(ParseObject appointments) {
        put("appointments",appointments);
    }

    public String getUserName() {
        return getString("userName");
    }

    public void setUserName(String userName) {
        put("userName",userName);
    }
}