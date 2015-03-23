package main.java.cz2006project.mojojo.Entity;

import java.util.ArrayList;

/**
 * Created by Dhruv on 2/24/2015.
 */
public class Doctor {

    private ArrayList<String> specialty = new ArrayList<String>();
    private Clinic clinic;
    private String name;
    private String password;
    private String NRIC;
    private ArrayList<Schedule> schedule = new ArrayList<Schedule>();


    public ArrayList<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<Schedule> schedule) {
        this.schedule = schedule;
    }

    public String getNRIC() {
        return NRIC;
    }

    public void setNRIC(String NRIC) {
        this.NRIC = NRIC;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public ArrayList<String> getSpecialty() {
        return specialty;
    }

    public void setSpecialty(ArrayList<String> specialty) {
        this.specialty = specialty;
    }




}
