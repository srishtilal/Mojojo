package main.java.cz2006project.mojojo.Entity;

import java.util.ArrayList;
import com.parse.ParseObject;

/**
 * Created by Dhruv on 2/24/2015.
 */
public class Doctor extends ParseObject {

    private ArrayList<String> specialty = new ArrayList<String>();
    private Clinic clinic;
    private String name;
    private String password;
    private String NRIC;
    private ArrayList<Schedule> schedule = new ArrayList<Schedule>();


    public ArrayList<Schedule> getSchedule()
    {
        return schedule;
    }



    public void setSchedule(ArrayList<Schedule> schedule)
    {
        put("schedule",schedule);
    }



    public String getNRIC()
    {
        return NRIC;
    }



    public void setNRIC(String NRIC)
    {
        put("NRIC",NRIC);
    }



    public String getPassword()
    {
        return password;
    }



    public void setPassword(String password)
    {
        put("password",password);
    }



    public String getName()
    {
        return name;
    }



    public void setName(String name)
    {
        put("name",name);
    }



    public Clinic getClinic()
    {
        return clinic;
    }



    public void setClinic(Clinic clinic)
    {
        put("clinic",clinic);
    }



    public ArrayList<String> getSpecialty()
    {
        return specialty;
    }



    public void setSpecialty(ArrayList<String> specialty)
    {
        put("schedule",schedule);
    }



}
