package main.java.cz2006project.mojojo.Boundary;
/*
 *  Copyright (c) 2014, Parse, LLC. All rights reserved.
 *
 *  You are hereby granted a non-exclusive, worldwide, royalty-free license to use,
 *  copy, modify, and distribute this software in source code or binary form for use
 *  in connection with the web services and APIs provided by Parse.
 *
 *  As with any software that integrates with the Parse platform, your use of
 *  this software is subject to the Parse Terms of Service
 *  [https://www.parse.com/about/terms]. This copyright notice shall be
 *  included in all copies or substantial portions of the software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 *  FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 *  COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 *  IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *  CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.facebook.AppEventsLogger;
import com.parse.FindCallback;
import com.parse.FunctionCallback;
import com.parse.GetCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cz2006project.mojojo.R;

/**
 * Shows the user profile. This simple activity can function regardless of whether the user
 * is currently logged in.
 */
public class SampleProfileActivity extends Activity {
    private static final int LOGIN_REQUEST = 0;

    private TextView titleTextView;
    private TextView emailTextView;
    private TextView nameTextView;
    private Button loginOrLogoutButton;
    private List<String> patients = new ArrayList<>();
    private String pat;

    private ParseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ParseLoginBuilder loginBuilder = new ParseLoginBuilder(
                SampleProfileActivity.this);
        startActivityForResult(loginBuilder.build(), LOGIN_REQUEST);


    }

    @Override
    protected void onStart() {
        super.onStart();

        currentUser = ParseUser.getCurrentUser();
        sendMail();

        if (currentUser == null){
            ParseLoginBuilder loginBuilder = new ParseLoginBuilder(
                    SampleProfileActivity.this);
            startActivityForResult(loginBuilder.build(), LOGIN_REQUEST);


        }


        if (currentUser != null && currentUser.getString("type").equals("Patient") ) {

            Intent patientIntent = new Intent(this, MainActivity.class);
            startActivity(patientIntent);
            finish();


        }
        if (currentUser != null && currentUser.getString("type").equals("Doctor") ) {

            Intent doctorIntent = new Intent(this, DoctorActivity.class);
            startActivity(doctorIntent);
            finish();

        }

        }

    @Override
    protected void onResume() {
        super.onResume();
        sendMail();



        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }


    public void sendMail() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Appointment");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> appointmentList, ParseException e) {
                if (e == null) {
                    //Log.d("score", "Retrieved " + appointmentList.size() + " appointments");
                    for (ParseObject appointment : appointmentList) {
                        Date appointmentDate = appointment.getDate("Date");
                        Boolean reminderstatus = appointment.getBoolean("ReminderSent");
                        if (reminderstatus == false) {


                            //Log.d("score", "Retrieved " + appointment.getString("patient") + " appointments");

                        //Log.d("score", "Retrieved " + appointment.getDate("Date") + " appointments");

                        Calendar cal = Calendar.getInstance();
                        Calendar cal1 = Calendar.getInstance();
                        Date dt2 = cal1.getTime();

                        cal1.set(Calendar.YEAR, Calendar.MONTH, Calendar.DATE+ 1, Calendar.HOUR_OF_DAY, Calendar.MINUTE);
                        Date date = cal.getTime();
                        long duration  = dt2.getTime() - date.getTime();
                        long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
                        //Log.d("score", "Retrieved " + diffInHours + " lol");

                        long diffHours = diffInHours / (60 * 60 * 1000);
                        //Log.d("score", "Retrieved " + diffHours + " lol");

                        if (diffHours <= 24) {
                             pat = appointment.getString("patient");

                            //Log.d("score", "Retrieved " + pat + " huuuuh");

                        }
                            ParseQuery<ParseUser> pquery = ParseUser.getQuery();
                            pquery.whereEqualTo("name", pat);
                            pquery.getFirstInBackground(new GetCallback<ParseUser>() {
                                public void done(final ParseUser patient, ParseException e) {
                                    if (e == null) {
                                        //Log.d("patient", "Retrieved the USERUSERUSER.");

                                            Map<String, String> params = new HashMap<>();
                                            params.put("text", "Hello! Reminder for your upcoming appointment. Please be punctual for your appointment tomorrow! ");
                                            params.put("subject", "Appointment Reminder");
                                            params.put("fromEmail", "srishti_lal@hotmail.com");
                                            params.put("fromName", "QwikDoc");
                                            params.put("toEmail", patient.getEmail());
                                            //Log.d("patient", patient.getEmail());

                                            params.put("toName", "Target user");



                                            ParseCloud.callFunctionInBackground("sendMail", params, new FunctionCallback<Object>() {
                                                @Override
                                                public void done(Object response, ParseException exc) {



                                                    Log.e("cloud code example", "response: " + response);

                                                }


                                            });
                                        }

                                else {
                                        Log.d("patient", "The getFirst request failed.");

                                    }
                                }

                            });
                        }
                        appointment.put("ReminderSent", true);


                    }


                }else {
                    Log.d("patient", "Error: " + e.getMessage());
                }
            }
        });
    }






    /**
     * Shows the profile of the given user.
     */

    /**
     * Show a message asking the user to log in, toggle login/logout button text.

    private void showProfileLoggedOut() {
        titleTextView.setText(R.string.profile_title_logged_out);
        emailTextView.setText("");
        nameTextView.setText("");
        loginOrLogoutButton.setText(R.string.profile_login_button_label);
    }
     */
}
