package main.java.cz2006project.mojojo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import cz2006project.mojojo.R;

/**
 * Created by srishti on 3/4/15.
 */
public class ClinicAdapter extends ParseQueryAdapter<ParseObject> {

    public ClinicAdapter(Context context) {
        // Use the QueryFactory to construct a PQA that will only show
        // Todos marked as high-pri
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("Clinic");
                return query;
            }
        });
    }

    // Customize the layout by overriding getItemView
    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.clinic_itemview, null);
        }

        super.getItemView(object, v, parent);

        // Add and download the image
        ParseImageView todoImage = (ParseImageView) v.findViewById(R.id.icon);
        ParseFile imageFile = object.getParseFile("image");
        if (imageFile != null) {
            todoImage.setParseFile(imageFile);
            todoImage.loadInBackground();
        }

        // Add the title view
        TextView titleTextView = (TextView) v.findViewById(R.id.text1);
        titleTextView.setText(object.getString("name"));

        TextView locationTextView = (TextView) v.findViewById(R.id.text2);
        locationTextView.setText(object.getString("Location"));

    /*    TextView doctorsTextView = (TextView) v.findViewById(R.id.text3);
        doctorsTextView.setText(object.getString("doctors"));

        TextView contactnumberTextView = (TextView) v.findViewById(R.id.text4);
        contactnumberTextView.setText(object.getString("contactnumber"));*/


        return v;
    }

}