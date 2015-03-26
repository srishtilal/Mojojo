package main.java.cz2006project.mojojo.Control;

/**
 * Created by Mai and zx on 5/3/2015.
 */
/**public class AdminManager

{ AppointmentManager appointmentManager = null;
    private String Reminders;
    private ArrayList<Patient> PatientList;
    private ArrayList<Doctor> DoctorList;
    //private ArrayList<Schedule> DoctorScheduleList;
    private ArrayList<Appointment> AppointmentList;
    public AdminManager(ArrayList<Patient> PatientList,ArrayList<Doctor> DoctorList,ArrayList<Schedule> DoctorScheduleList,ArrayList<Appointments> AppointmentList)
    {
        this.PatientList = PatientList;
        this.DoctorList = DoctorList;
        this.DoctorScheduleList = DoctorScheduleList;
        this.AppointmentList = AppointmentList;
        Reminders = null;
    }

    public AdminManager()
    {
        Admin admin = new Admin();
        appointmentManager = new AppointmentManager();
    }

    public boolean CreateAppointment(String PatientNRIC)
    {
        for(Patient person : PatientList)
        {
            if(person.getNRIC() != PatientNRIC)
                return false;
        }
        appointmentManager.CreateAppointment();
        return true;
    }

    public boolean CancelAppointment(String PatientNRIC,int AppointmentNo)
    {
        for(Patient person : PatientList)
        {
            if(person.getNRIC() != PatientNRIC)
                return false;
        }
        appointmentManager.CancelAppointment(AppointmentNo);
        return true;
    }

    public boolean ModifyAppointment(String PatientNRIC,int AppointmentNo)
    {
        for(Patient person : PatientList)
        {
            if(person.getNRIC() != PatientNRIC)
                return false;
        }
        appointmentManager.ModifyAppointment(AppointmentNo);
        return true;
    }

    public boolean CreateAppointment(String PatientNRIC,int AppointmentNo, ArrayList<Patient> ExistingFollowUpAppointment)
    {
        for(Patient person : PatientList)
        {
            if(person.getNRIC() != PatientNRIC)
                return false;
        }
        appointmentManager.CreateAppointment(ExistingFollowUpAppointment,AppointmentNo);
        return true;
    }


    public ArrayList<Appointments> getAppointmentsList(){return AppointmentList;}

    public void setAppointmentsList(ArrayList<Appointments> AppointmentsList)
    {
        this.AppointmentList = AppointmentsList;
    }

    public ArrayList<Patient> getPatientList()
    {
        return PatientList;
    }

    public void setPatientList(ArrayList<Patient> PatientList)
    {
        this.PatientList = PatientList;
    }

    public ArrayList<Doctor> getDoctorList()
    {
        return DoctorList;
    }

    public void setDoctorList(ArrayList<Doctor> DoctorList)
    {
        this.DoctorList = DoctorList;
    }

    public ArrayList<Schedule> getScheduleList()
    {
        return DoctorScheduleList;
    }

    public void setDoctorScheduleList(ArrayList<Schedule> DoctorScheduleList)
    {
        this.DoctorScheduleList = DoctorScheduleList;
    }

    public boolean SetReminders(String Reminder, Date Appointmentdate, String PatientNric)
    {
        for (int i = 0 ; i < AppointmentList.size(); i++)
        {
            if (AppointmentList.get(i).getAppointmentDate() == Appointmentdate && AppointmentList.get(i).getPatientNRIC().equals(PatientNric))
            {
                appointmentManager.SetReminder();
                return true;
            }

        }
        return false;

    }


    public void AddDoctor(Doctor doctor)
    {
        DoctorList.add(doctor);
    }


    public boolean RemoveDoctor(String DoctorNRIC)
    {
        for (int i = 0 ; i < DoctorList.size(); i++)
        {
            if (DoctorList.get(i).getNRIC() == DoctorNRIC)
            {
                DoctorList.remove(i);
                return true;
            }
        }
        return false;
    }




    public void UpdateMedicalRecords(Srting PatientNric, MedicalRecords Medicalrecord)
    {
        for (Patient patient : PatientList)
        {
            if (patient.getNRIC() == PatientNric);
            {

            }

        }
    }
}
*/



