package main.java.cz2006project.mojojo.Entity;

import java.util.ArrayList;
import java.util.Date;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Dhruv on 2/24/2015.
 */

public class MedicalRecords {


    private String NRIC;
    private ArrayList<String> allergy = new ArrayList<String>();
    private ArrayList<String> healthStatus = new ArrayList<String>();
    private Date lastCheckup;
    private String medication;



    public String getNRIC() {
        return NRIC;
    }

    public void setNRIC(String NRIC)
    {
        this.NRIC = NRIC;
    }

    public ArrayList<String> getAllergy() {
        return allergy;
    }

    public void setAllergy(ArrayList<String> allergy) {
        this.allergy = allergy;
    }

    public ArrayList<String> getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(ArrayList<String> healthStatus) {
        this.healthStatus = healthStatus;
    }

    public Date getLastCheckup() {
        return lastCheckup;
    }

    public void setLastCheckup(Date lastCheckup) {
        this.lastCheckup = lastCheckup;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }





}
