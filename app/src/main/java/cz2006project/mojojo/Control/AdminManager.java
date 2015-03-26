package main.java.cz2006project.mojojo.Control;

import com.parse.ParseUser;

import java.util.Date;
import java.util.List;

import main.java.cz2006project.mojojo.Entity.Appointment;
import main.java.cz2006project.mojojo.Entity.Doctor;
import main.java.cz2006project.mojojo.Entity.MedicalRecords;
import main.java.cz2006project.mojojo.Entity.Patient;
import main.java.cz2006project.mojojo.Entity.Schedule;

/**
 * Created by Mai and zx on 5/3/2015.
 */
public class AdminManager
{

    AppointmentManager appointmentManager = null;
    private String Reminders;
    private List<Patient> PatientList;
    private List<Doctor> DoctorList;
    private List<Schedule> DoctorScheduleList;
    private List<Appointment> AppointmentList;

    public AdminManager(List<Patient> PatientList,List<Doctor> DoctorList,List<Schedule> DoctorScheduleList,List<Appointment> AppointmentList)
    {
        this.PatientList = PatientList;
        this.DoctorList = DoctorList;
        this.DoctorScheduleList = DoctorScheduleList;
        this.AppointmentList = AppointmentList;
        Reminders = null;
    }

    public AdminManager()
    {
        //Admin admin = new Admin();
        appointmentManager = new AppointmentManager();
    }

   /* public boolean CreateAppointment(ParseUser PatientNRIC)
    {
        for(Patient person : PatientList)
        {
            if(person.getPatientNRIC() != PatientNRIC)
                return false;
        }
        appointmentManager.CreateAppointment();
        return true;
    }*/
/*
    public boolean CancelAppointment(ParseUser PatientNRIC,int AppointmentNo)
    {
        for(Patient person : PatientList)
        {
            if(person.getPatientNRIC() != PatientNRIC)
                return false;
        }
        appointmentManager.CancelAppointment(AppointmentNo);
        return true;
    }
    */
/*
    public boolean ModifyAppointment(ParseUser PatientNRIC,int AppointmentNo)
    {
        for(Patient person : PatientList)
        {
            if(person.getPatientNRIC() != PatientNRIC)
                return false;
        }
        appointmentManager.ModifyAppointment(AppointmentNo);
        return true;
    }
   */
/*
    public boolean CreateAppointment(ParseUser PatientNRIC,int AppointmentNo, List<Patient> ExistingFollowUpAppointment)
    {
        for(Patient person : PatientList)
        {
            if(person.getPatientNRIC() != PatientNRIC)
                return false;
        }
        appointmentManager.CreateAppointment(ExistingFollowUpAppointment,AppointmentNo);
        return true;
    }
*/

    public List<Appointment> getAppointmentsList(){return AppointmentList;}

    public void setAppointmentsList(List<Appointment> AppointmentsList)
    {
        this.AppointmentList = AppointmentsList;
    }

    public List<Patient> getPatientList()
    {
        return PatientList;
    }

    public void setPatientList(List<Patient> PatientList)
    {
        this.PatientList = PatientList;
    }

    public List<Doctor> getDoctorList()
    {
        return DoctorList;
    }

    public void setDoctorList(List<Doctor> DoctorList)
    {
        this.DoctorList = DoctorList;
    }

    public List<Schedule> getScheduleList()
    {
        return DoctorScheduleList;
    }

    public void setDoctorScheduleList(List<Schedule> DoctorScheduleList)
    {
        this.DoctorScheduleList = DoctorScheduleList;
    }

    public boolean SetReminders(String Reminder, Date Appointmentdate, String PatientNric)
    {
        for (int i = 0 ; i < AppointmentList.size(); i++)
        {
            //if (AppointmentList.get(i).getAppointmentDate() == Appointmentdate && AppointmentList.get(i).getPatientNRIC().equals(PatientNric))
            {
                //appointmentManager.SetReminders();
                return true;
            }

        }
        return false;

    }


    public void AddDoctor(Doctor doctor)
    {
        DoctorList.add(doctor);
    }


    public boolean RemoveDoctor(ParseUser DoctorNRIC)
    {
        for (int i = 0 ; i < DoctorList.size(); i++)
        {
            /*if (DoctorList.get(i).getDoctorNRIC() == DoctorNRIC)
            {
                DoctorList.remove(i);
                return true;
            }*/
        }
        return false;
    }




    public void UpdateMedicalRecords(ParseUser PatientNric, MedicalRecords Medicalrecord)
    {
        for (Patient patient : PatientList)
        {
            if (patient.getPatientNRIC() == PatientNric);
            {

            }

        }
    }
}




