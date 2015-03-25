package main.java.cz2006project.mojojo.Boundary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cz2006project.mojojo.R;

public class Profile extends Activity {

    private TextView welcomeTextView;
    private TextView appointmentTextView;
    private View appointmentDetail;
    private Button consultADoctor;
    private Button bookMoreAppointment;
    private Button checkAppointmentRecord;
    private Button aboutUs;
    private Button viewMyRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        welcomeTextView = (TextView) findViewById(R.id.Welcome);
        appointmentTextView = (TextView) findViewById(R.id.Upcoming_Appoint);
        appointmentDetail = findViewById(R.id.listView);
        consultADoctor = (Button) findViewById(R.id.ConsultADoctor);
        checkAppointmentRecord = (Button) findViewById(R.id.CheckAppointRecords);
        bookMoreAppointment = (Button) findViewById(R.id.Book_More_Appointments);
        aboutUs = (Button) findViewById(R.id.About_Us);
        viewMyRecord = (Button)findViewById(R.id.View_My_Records);

        welcomeTextView.setText("Welcome");
        appointmentTextView.setText("Upcoming Appointment:");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Consult()
    {
        Intent intent = new Intent(this,Search_Doctor.class);
        startActivity(intent);
    }
}
