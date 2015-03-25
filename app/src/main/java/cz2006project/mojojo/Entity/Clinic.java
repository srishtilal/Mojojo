package main.java.cz2006project.mojojo.Entity;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dhruv on 2/24/2015.
 */
public class Clinic extends ParseObject {

    private ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
    private ArrayList<Admin> adminList = new ArrayList<Admin>();
    private String Location;


    public List<Doctor> getDoctorList()
    {
        return getList ("doctorList");
    }



    public void setDoctorList(List<Doctor> doctorList)

    {
        put("doctorList",doctorList);
    }



    public List<Admin> getAdminList()
    {
        return getList ("adminList");
    }



    public void setAdminList(List<Admin> adminList)

    {
        put("adminList",adminList);
    }



    public String getLocation()
    {
        return getString("Location");
    }



    public void setLocation(String Location)
    {
        put("Location",Location);
    }


}
