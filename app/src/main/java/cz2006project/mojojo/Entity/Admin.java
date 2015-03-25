package main.java.cz2006project.mojojo.Entity;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

/**
 * Created by ZX&SL on 2/24/2015.
 */
public class Admin extends ParseObject
{
    public void setAppointmentDate(Date appointmentDate)
    {
        put("appointmentDate",appointmentDate);
    }


    public String getName()
    {
        return getString ("name");
    }



    public void setName(String name)
    {
        put("name",name);
    }



    ParseUser getAdminNRIC()
    {
        return getParseUser("AdminNRIC");
    }



    public void setAdminNRIC(String AdminNRIC)
    {
        put("AdminNRIC",AdminNRIC);
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



    public boolean isGender()
    {
        return getBoolean("Gender");
    }



    public void setGender(boolean gender)
    {
        put("gender",gender);
    }



    public Date getDateOfBirth()
    {
        return getDate ("dateOfBirth");
    }



    public void setDateOfBirth(Date dateOfBirth)
    {
        put("DateOfBirth", dateOfBirth);
    }


}
