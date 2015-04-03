package main.java.cz2006project.mojojo.Boundary.QwikSearch;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cz2006project.mojojo.R;
import main.java.cz2006project.mojojo.CircularImageView;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.

/*public class ViewDoctor extends Fragment {

    TextView name, clinic, area, qualifications;
    String dname, dclinic, darea, dqualifications;
    CircularImageView pic;
    byte[] data;


    public ViewDoctor() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.view_person, container, false);

        Bundle i = getArguments();
        if (i != null) {
            dname = i.getString("name");
            darea = i.getString("area");
            dclinic = i.getString("clinic");
            dqualifications = i.getString("qualifications");
            data = i.getByteArray("pic");
            Log.e("pic", String.valueOf(data));
        }

        pic = (CircularImageView) rootView.findViewById(R.id.doctor_image);
        name = (TextView) rootView.findViewById(R.id.doctor_name);
        clinic = (TextView) rootView.findViewById(R.id.doctor_clinic);
        qualifications = (TextView) rootView.findViewById(R.id.doctor_qualifications);
        area = (TextView) rootView.findViewById(R.id.doctor_area);

        pic.setImageBitmap(BitmapFactory
                .decodeByteArray(
                        data, 0,
                        data.length));

        name.setText(" " + dname);
        clinic.setText(" " + dclinic);
        area.setText(" " + darea);
        qualifications.setText(" " + dqualifications);

        return rootView;

    }


}*/