package main.java.cz2006project.mojojo.Boundary;

/**
 * Created by srishti on 3/4/15.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.parse.ParseUser;

import cz2006project.mojojo.R;
import main.java.cz2006project.mojojo.Boundary.Account.ProfileFragment;
import main.java.cz2006project.mojojo.Boundary.Appointments.AppointmentsFragment;
import main.java.cz2006project.mojojo.Boundary.MyAdapter;
import main.java.cz2006project.mojojo.Boundary.QwikSearch.QwikSearchFragment;
import main.java.cz2006project.mojojo.Control.SampleApplication;


public class MainNavigationActivity extends ActionBarActivity {

    public static final String TAG = "Appointments";
    public static final boolean DEBUG = SampleApplication.LOG_DEBUG;
    //First We Declare Titles And Icons For Our Navigation Drawer List View
    //This Icons And Titles Are holded in an Array as you can see

    String TITLES[] = {"Appointments","Search","My Account","Log Out",};
    int ICONS[] = {R.drawable.ic_action_appointment,R.drawable.ic_action_search,R.drawable.ic_action_myprofile,R.drawable.ic_action_logout};

    //Similarly we Create a String Resource for the name and email in the header view
    //And we also create a int resource for profile picture in the header view

    String NAME = ParseUser.getCurrentUser().getString("name");
    String EMAIL = ParseUser.getCurrentUser().getEmail();
    int PROFILE = R.drawable.mojojo;

    private Toolbar toolbar;                              // Declaring the Toolbar Object
    private ParseUser currentUser;


    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout

    private CharSequence mTitle;
    private String myTitle;
    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_exp);

    /* Assinging the toolbar object ot the view
    and setting the the Action bar to our toolbar
     *//*
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);*/


        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new MyAdapter(TITLES, ICONS, NAME, EMAIL, PROFILE, this);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture


        mRecyclerView.setAdapter(mAdapter);



        // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager


        Drawer = (DrawerLayout) findViewById(R.id.drawer_layout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }

        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync


        final GestureDetector mGestureDetector = new GestureDetector(MainNavigationActivity.this, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());
                int position = recyclerView.getChildPosition(child);
                FragmentManager fragmentManager = getSupportFragmentManager();

                switch (position) {

                    case 0:case 1:

                        if (DEBUG) Log.d(TAG, "appointment fragment");
                        mTitle = "Appointments";
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, new AppointmentsFragment())
                                .commit();
                        break;
                    case 2:
                        if (DEBUG) Log.d(TAG, "qwiksearch fragment");
                        mTitle = "QwikSearch";
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, new QwikSearchFragment())
                                .commit();
                        break;
                    case 3:
                        if (DEBUG) Log.d(TAG, "myprofile fragment");
                        mTitle = "Profile";
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, new ProfileFragment())
                                .commit();
                        break;
                    case 4:
                        if (DEBUG) Log.d(TAG, "logout");
                        new AlertDialog.Builder(mRecyclerView.getContext())
                                .setMessage("Are you sure you want to exit?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        MainNavigationActivity.this.finish();
                                        ParseUser.logOut();
                                        currentUser = null;
                                        Intent i = new Intent(MainNavigationActivity.this, SampleProfileActivity.class);
                                        MainNavigationActivity.this.startActivity(i);

                                    }
                                })
                                .setNegativeButton("No", null)
                                .show();
                        break;



                }




                if(child!=null && mGestureDetector.onTouchEvent(motionEvent)){
                    Drawer.closeDrawers();

                    return true;

                }


                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }
        });


        myTitle = getString(R.string.app_name);
        if (toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
                toolbar.setTitle(myTitle);
                toolbar.setTitleTextColor(getResources().getColor(R.color.white));

            }
        }
        mTitle = getResources().getString(R.string.appointments);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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



}