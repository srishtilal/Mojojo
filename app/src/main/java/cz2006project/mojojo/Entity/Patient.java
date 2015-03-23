package main.java.cz2006project.mojojo.Entity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Date;

import main.java.cz2006project.mojojo.Entity.Appointment;


public class Patient {

    private String patientName;
    private String patientNRIC;
    private String password;
    private String email;
    private String userName;
    private Date dateOfBirth;
    private boolean gender;
    private ArrayList <MedicalRecords> medicalRecords = new ArrayList<MedicalRecords>();
    private ArrayList <Appointment> appointments = new ArrayList<Appointment>();

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientNRIC() {
        return patientNRIC;
    }

    public void setPatientNRIC(String patientNRIC) {
        this.patientNRIC = patientNRIC;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public ArrayList<MedicalRecords> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(ArrayList<MedicalRecords> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}