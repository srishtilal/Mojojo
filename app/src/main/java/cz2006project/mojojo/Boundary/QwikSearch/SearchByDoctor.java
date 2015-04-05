package main.java.cz2006project.mojojo.Boundary.QwikSearch;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import cz2006project.mojojo.R;
import main.java.cz2006project.mojojo.ClinicAdapter;
import main.java.cz2006project.mojojo.Control.ParseTables;
import main.java.cz2006project.mojojo.ParseCircularImageView;
import main.java.cz2006project.mojojo.ProgressBarCircular;


public class SearchByDoctor extends Fragment {

   /* ProgressBarCircular progressBar;
    Dialog dialogPeople;

    String currentuseremail = "";
    String currentusername = "";
    String currentuser = "";
    ParseGeoPoint userlocation = new ParseGeoPoint(0, 0);

    ArrayList<ParseObject> interests = new ArrayList<>() ;

    EditText search;

    ArrayList<EachRow3> list3 = new ArrayList<EachRow3>();
    EachRow3 each;
    MyAdapter3 q;
    ListView listview;
    private ClinicAdapter clinicadapter;
    private ListView listView;*/
   private ClinicAdapter clinicadapter;
    private ListView listView;


    public SearchByDoctor() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_searchnearby, container, false);


            // Initialize the subclass of ParseQueryAdapter
            clinicadapter = new ClinicAdapter(getActivity());

            // Initialize ListView and set initial view to mainAdapter
            listView = (ListView) view.findViewById(R.id.list);
            listView.setAdapter(clinicadapter);
            clinicadapter.loadObjects();

        return view;


        }

    }
/*

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                loaddataAfterSearch( editable.toString());
            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final ViewDoctor newFragment = new ViewDoctor();

                String tname = list3.get(i).doctorname;
                String tclinic = list3.get(i).doctorclinic;
                String tqualifications = list3.get(i).doctorqualifications;
                String tarea = list3.get(i).doctorarea;
                ParseFile tfile = list3.get(i).fileObject;

                final Bundle in = new Bundle();


                if (tname == null)
                    tname = " - ";

                if (tclinic == null)
                    tclinic = " - ";
                if (tqualifications == null)
                    tqualifications = " - ";
                if (tarea == null)
                    tarea = " - ";


                in.putString("name", tname);
                in.putString("clinic", tclinic);
                in.putString("qualifications", tqualifications);
                in.putString("area", tarea);

                newFragment.setArguments(in);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.anim_signin_enter, R.anim.anim_signin_exit);


                if (tfile != null) {
                    tfile
                            .getDataInBackground(new GetDataCallback() {

                                public void done(byte[] data,
                                                 ParseException e) {
                                    if (e == null) {
                                        Log.d("test",
                                                "We've got data in data.");

                                        in.putByteArray("pic", data);
                                        System.out.print("pic3" + String.valueOf(data));
                                        transaction.replace(R.id.container, newFragment).addToBackStack("PeopleNearMe").commit();


                                    } else {
                                        Log.d("test", "There was a problem downloading the data.");
                                    }
                                }
                            });
                } else {


                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_person);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] bitmapdata = stream.toByteArray();

                    in.putByteArray("pic", bitmapdata);
                    System.out.print("pic2" + String.valueOf(bitmapdata));

                    transaction.replace(R.id.container, newFragment).addToBackStack("PeopleNearMe").commit();

                }

            }
        });


        return view;
    }

    private void loaddata() {

        list3.clear();

        currentuser = ParseUser.getCurrentUser().getUsername();
        currentuseremail = ParseUser.getCurrentUser().getString(ParseTables.Users.USERNAME);
        currentusername = ParseUser.getCurrentUser().getString(ParseTables.Users.NAME);
        userlocation = ParseUser.getCurrentUser().getParseGeoPoint(ParseTables.Users.LOCATION);



        // DUMMY DATA SO THAT IT DISPLAYS SOMETHING
        if (userlocation==null ||  userlocation.getLatitude() == 0)
        {
            userlocation = new ParseGeoPoint(28.7434552 , 77.1205612);
        }



        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNear(ParseTables.Users.LOCATION, userlocation);

        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {


                    for (ParseUser pu : objects) {
                        //access the data associated with the ParseUser using the get method
                        //pu.getString("key") or pu.get("key")

                        if (!pu.getUsername().equals(currentuser)) {

                            each = new EachRow3();
                            each.doctorname = pu.getString(ParseTables.Doctor.NAME);

                            ArrayList<ParseObject> doctorQualifications = (ArrayList<ParseObject>) pu.get(ParseTables.Doctor.SPECIALTY);

                            if(!doctorQualifications.isEmpty()) {
                                StringBuilder stringBuilder = new StringBuilder("");
                                for (ParseObject parseObject : doctorQualifications) {
                                    try {
                                        stringBuilder.append(parseObject.fetchIfNeeded().getString("name")).append(", ");
                                    } catch (ParseException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                                    stringBuilder.setLength(stringBuilder.length() - 2);
                                    each.doctorqualifications = stringBuilder.toString();
                            }

                            each.doctorqualifications = pu.getString(ParseTables.Doctor.SPECIALTY);
                            each.doctorclinic = pu.getString(ParseTables.Doctor.CLINIC);
//                                          each.cdistance = pu.getString(ParseTables.Users.NAME);
                            each.doctorname = pu.getString(ParseTables.Doctor.NAME);
                            ParseGeoPoint temploc = pu.getParseGeoPoint(ParseTables.Users.LOCATION);
                            if (temploc != null && temploc.getLatitude() != 0) {
                                if (userlocation != null) {
                                    each.doctorarea = String.valueOf((int) temploc.distanceInKilometersTo(userlocation)) + " km";
                                } else {
                                    each.doctorarea = "N/A";
                                }
                            } else {
                                each.doctorarea = "N/A";
                            }



                            list3.add(each);


                        }
                    }

                    // The query was successful.
                } else {
                    // Something went wrong.
                }

                q = new MyAdapter3(getActivity(), 0, list3);
                q.notifyDataSetChanged();

                lv.setAdapter(q);
                progressBar.setVisibility(View.GONE);
                lv.setVisibility(View.VISIBLE);
            }
        });

    }

    private void loaddataAfterSearch(String textSearch) {

        list3.clear();

        currentuser = ParseUser.getCurrentUser().getUsername();
        currentuseremail = ParseUser.getCurrentUser().getString(ParseTables.Users.USERNAME);
        currentusername = ParseUser.getCurrentUser().getString(ParseTables.Users.NAME);
        userlocation = ParseUser.getCurrentUser().getParseGeoPoint(ParseTables.Users.LOCATION);

        // DUMMY DATA SO THAT IT DISPLAYS SOMETHING
        if (userlocation==null ||  userlocation.getLatitude() == 0)
        {
           userlocation = new ParseGeoPoint(28.7434552 , 77.1205612);
        }


        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNear(ParseTables.Users.LOCATION, userlocation);
        query.whereMatches(ParseTables.Users.NAME, "(" + textSearch + ")", "i");


        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {


                    for (ParseUser pu : objects) {
                        //access the data associated with the ParseUser using the get method
                        //pu.getString("key") or pu.get("key")

                        if (!pu.getUsername().equals(currentuser)) {

                            each = new EachRow3();
                            each.doctorname = pu.getString(ParseTables.Users.NAME);


                            ArrayList<ParseObject> doctorQualifications = (ArrayList<ParseObject>) pu.get(ParseTables.Doctor.SPECIALTY);

                            if(!doctorQualifications.isEmpty()) {
                                StringBuilder stringBuilder = new StringBuilder("");
                                for (ParseObject parseObject : doctorQualifications) {
                                    try {
                                        stringBuilder.append(parseObject.fetchIfNeeded().getString("name")).append(", ");
                                    } catch (ParseException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                                stringBuilder.setLength(stringBuilder.length() - 2);
                                each.doctorqualifications = stringBuilder.toString();
                            }


                            each.doctorqualifications = pu.getString(ParseTables.Doctor.SPECIALTY);
                            each.doctorclinic = pu.getString(ParseTables.Doctor.CLINIC);
//                                          each.doctorarea = pu.getString(ParseTables.Users.NAME);
                            each.doctorusername = pu.getString(ParseTables.Users.USERNAME);
                            ParseGeoPoint temploc = pu.getParseGeoPoint(ParseTables.Users.LOCATION);
                            if (temploc != null && temploc.getLatitude() != 0) {
                                if (userlocation != null) {
                                    each.doctorarea = String.valueOf((int) temploc.distanceInKilometersTo(userlocation)) + " km";
                                } else {
                                    each.doctorarea = "N/A";
                                }
                            } else {
                                each.doctorarea = "N/A";
                            }



                            list3.add(each);


                        }
                    }

                    // The query was successful.
                } else {
                    // Something went wrong.
                }

                q = new MyAdapter3(getActivity(), 0, list3);
                q.notifyDataSetChanged();

                lv.setAdapter(q);
                progressBar.setVisibility(View.GONE);
                lv.setVisibility(View.VISIBLE);
            }
        });

    }


    class MyAdapter3 extends ArrayAdapter<EachRow3> {
        LayoutInflater inflat;
        ViewHolder holder;

        public MyAdapter3(Context context, int textViewResourceId,
                          ArrayList<EachRow3> objects) {
            super(context, textViewResourceId, objects);
            // TODO Auto-generated constructor stub
            inflat = LayoutInflater.from(context);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            final int pos = position;

            if (convertView == null) {
                convertView = inflat.inflate(R.layout.listview_doctors, null);
                holder = new ViewHolder();
                holder.textname = (TextView) convertView.findViewById(R.id.doctor_name);
                holder.textclinic = (TextView) convertView.findViewById(R.id.doctor_clinic);
                holder.textarea = (TextView) convertView.findViewById(R.id.doctor_area);
                holder.textqualifications = (TextView) convertView.findViewById(R.id.doctor_qualifications);
                holder.userimg = (ParseCircularImageView) convertView.findViewById(R.id.people_userimg);


                convertView.setTag(holder);
            }
            holder = (ViewHolder) convertView.getTag();
            EachRow3 row = getItem(position);

            holder.textname.setText(row.doctorname);
            holder.textclinic.setText(row.doctorclinic);
            holder.textarea.setText(row.doctorarea);
            holder.textqualifications.setText(row.doctorqualifications);


            if (row.fileObject != null) {
                row.fileObject
                        .getDataInBackground(new GetDataCallback() {

                            public void done(byte[] data,
                                             ParseException e) {
                                if (e == null) {
                                    Log.d("test",
                                            "We've got data in data.");

                                    holder.userimg.setImageBitmap(BitmapFactory
                                            .decodeByteArray(
                                                    data, 0,
                                                    data.length));

                                } else {
                                    Log.d("test", "There was a problem downloading the data.");
                                }
                            }
                        });
            } else {
                holder.userimg.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_person));
            }

            return convertView;
        }

        @Override
        public EachRow3 getItem(int position) {
            // TODO Auto-generated method stub
            return list3.get(position);
        }

        private class ViewHolder {

            TextView textname;
            TextView textqualifications;
            TextView textclinic;
            TextView textarea;
            TextView textusername;
            ParseImageView userimg;
        }

    }

    private class EachRow3 {
        String doctorname;
        String doctorqualifications;
        String doctorclinic;
        String doctorarea;
        String doctorusername;
        Bitmap cbmp;
        ParseFile fileObject;
    }


}
*/
