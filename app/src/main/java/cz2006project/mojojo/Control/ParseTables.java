package main.java.cz2006project.mojojo.Control;

/**
 * Created by srishti on 27/3/15.
 */
public class ParseTables {


        /**
         * Created by championswimmer on 26/1/15.
         */
        public static class Users {
            public static final String NAME = "name";
            public static final String USERNAME = "email";
            public static final String PASSWORD = "password";
            public static final String TYPE = "type";

        }
    public static class Schedule {
        public static final String COMMENTS = "comments";
        public static final String DATE = "date";
        public static final String DOCTOR = "doctor";
        public static final String TIME = "time";

    }
    public static class Appointment {
        public static final String APPOINTMENTNUMBER = "name";
        public static final String CLINIC = "clinic";
        public static final String DATE = "date";
        public static final String DOCTOR = "doctor";
        public static final String MEDICALISSUE = "followup";
        public static final String FOLLOWUP = "followup";
        public static final String PATIENT = "patient";
        public static final String NOTES = "notes";
        public static final String REMINDER = "reminder";
        public static final String TIME = "time";


    }
    public static class Doctor {
        public static final String NAME = "name";
        public static final String GENDER = "gender";
        public static final String SPECIALTY = "specialty";
        public static final String SCHEDULE = "schedule";
        public static final String DATEOFBIRTH = "dateofbirth";
        public static final String APPOINTMENTS = "appointments";
        public static final String CLINIC = "clinic";


    }
    public static class Patient {
        public static final String NAME = "name";
        public static final String NRIC = "nric";
        public static final String DATEOFBIRTH = "dateofbirth";
        public static final String GENDER = "gender";
        public static final String MEDICALRECORDS = "medicalrecords";
        public static final String APPOINTMENTS = "appointments";


    }
    public static class MedicalRecord {
        public static final String ALLERGIES = "allergies";
        public static final String HEALTHSTATUS = "healthstatus";
        public static final String LASTCHECKUP = "lastcheckup";
        public static final String MEDICATION = "medication";
        public static final String PATIENT = "patient";


    }
    public static class Clinic {
        public static final String ADMIN = "admin";
        public static final String DOCTOR = "doctor";
        public static final String LOCATION = "location";
        public static final String OPENINGHOURS = "openinghours";

    }
}
