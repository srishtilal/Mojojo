package main.java.cz2006project.mojojo.Control;

import junit.framework.TestCase;

public class AppointmentManagerTest extends TestCase {

    public void testCreateAppointment() throws Exception {

        AppointmentManager appt = new AppointmentManager();
        assertFalse(appt.ValidateAppointment(1));
    }
}