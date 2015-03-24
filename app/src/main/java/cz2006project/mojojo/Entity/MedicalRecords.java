package main.java.cz2006project.mojojo.Entity;

import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by Dhruv on 2/24/2015.
 */

public class MedicalRecords extends ParseObject{

    public String getNRIC() {
        return getString("NRIC");
    }

    public void setNRIC(String NRIC)
    {
        put("NRIC",NRIC);
    }

    public ParseObject getAllergy() {
        return getParseObject("allergy");
    }

    public void setAllergy(ParseObject allergy) {
        put("allergy",allergy);
    }

    public ParseObject getHealthStatus() {
        return getParseObject("healthStatus");
    }

    public void setHealthStatus(ParseObject healthStatus) {
        put("healthStatus",healthStatus);
    }

    public Date getLastCheckup() {
        return getDate("lastCheckup");
    }

    public void setLastCheckup(Date lastCheckup) {
        put("lastCheckup",lastCheckup);
    }

    public String getMedication() {
        return getString("medication");
    }

    public void setMedication(String medication) {
        put("medication",medication);
    }





}
