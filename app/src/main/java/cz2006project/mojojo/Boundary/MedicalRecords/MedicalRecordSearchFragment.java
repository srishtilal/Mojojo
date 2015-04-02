package main.java.cz2006project.mojojo.Boundary.MedicalRecords;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.internal.widget.TintCheckBox;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import cz2006project.mojojo.R;
import main.java.cz2006project.mojojo.Control.ParseTables;
import main.java.cz2006project.mojojo.DividerItemDecoration;
import main.java.cz2006project.mojojo.ParseCircularImageView;
import main.java.cz2006project.mojojo.ProgressBarCircular;
import main.java.cz2006project.mojojo.Utilities;


public class MedicalRecordSearchFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBarCircular loader;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View rootView;
    private boolean onRefresh = false;

    private SharedPreferences filterPrefs;
    private SharedPreferences.Editor editor;

    private ParseGeoPoint location;

    public MedicalRecordSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        filterPrefs = getActivity().getSharedPreferences("filterdetails", 0);
        editor = filterPrefs.edit();
        editor.putBoolean("books", true);
        editor.putBoolean("apparatus", true);
        editor.putBoolean("misc", true);
        editor.putString("sortby", "nearest");
        editor.commit();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_medicalrecords_search, container, false);
        loader = (ProgressBarCircular) rootView.findViewById(R.id.progressBar);
        loader.setBackgroundColor(getResources().getColor(R.color.listingsColorPrimaryDark));
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.listing_recycler_view);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        EditText search = (EditText) rootView.findViewById(R.id.listing_search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                fetchListings(true, editable.toString());
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (Utilities.isNetworkAvailable(getActivity())) {
                    onRefresh = true;
                    fetchListings(false, null);
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "Please connect to the Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (Utilities.isNetworkAvailable(getActivity()))
            fetchListings(false, null);
        else
            fetchListings(true, null);

        return rootView;
    }


    private void fetchListings(final boolean cache, String text) {
        final ArrayList<String> medicalrecord = new ArrayList<>();

        ParseQuery<ParseObject> query = new ParseQuery<>(
                ParseTables.Patient.MEDICALRECORDS);
        if (cache)
            query.fromLocalDatastore();
        query.whereContainedIn(ParseTables.Patient.MEDICALRECORDS, medicalrecord);
        if (text == null) {
            //if (filterPrefs.getString("sortby", "nearest").equalsIgnoreCase("recent"))
            query.orderByDescending(ParseTables.MedicalRecord.CREATED_AT);
            //else
            //query.whereNear(ParseTables.Listings.LOCATION,location);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(final List<ParseObject> parseObjects, ParseException e) {
                    Log.d("Objects: ", "" + parseObjects);
                    if (e == null) {
                        if (medicalrecord.size() == 3 && !cache) {
                            ParseObject.unpinAllInBackground("medicalrecord", new DeleteCallback() {
                                @Override
                                public void done(ParseException e) {
                                    ParseObject.pinAllInBackground("medicalrecord", parseObjects);
                                    doneFetching(parseObjects, cache);
                                }
                            });
                        } else
                            doneFetching(parseObjects, cache);
                    } else {
                        e.printStackTrace();
                        if (onRefresh) {
                            onRefresh = false;
                            swipeRefreshLayout.setRefreshing(false);
                        }
                        loader.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "Please connect to the Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if (text != null) {
            query.whereMatches(ParseTables.MedicalRecord.PATIENT, "(" + text + ")", "i");
            ParseQuery<ParseObject> descQuery = new ParseQuery<>(
                    "Medical Records");
            descQuery.fromLocalDatastore();
            List<ParseQuery<ParseObject>> queries = new ArrayList<>();
            queries.add(query);
            queries.add(descQuery);
            ParseQuery<ParseObject> mainQuery = ParseQuery.or(queries);
            //if (filterPrefs.getString("sortby", "nearest").compareTo("recent") == 0)
            //else
            //   mainQuery.whereNear("location",location);
            mainQuery.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> results, ParseException e) {
                    if (e == null)
                        doneFetching(results, cache);
                    else
                        e.printStackTrace(); // shouldn't happen
                }
            });
        }
    }

    private void doneFetching(List<ParseObject> parseObjects, boolean cache) {
        mAdapter = new ListingAdapter(parseObjects, cache);
        if (onRefresh) {
            onRefresh = false;
            swipeRefreshLayout.setRefreshing(false);
        } else
            loader.setVisibility(View.GONE);
        mRecyclerView.setAdapter(mAdapter);
    }

    public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.ViewHolder> {
        private List<ParseObject> mDataset;
        private boolean mCache;

        public ListingAdapter(List<ParseObject> dataSet, boolean cache) {
            mDataset = dataSet;
            mCache = cache;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LinearLayout v = (LinearLayout) LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.medicalrecords_card_view, viewGroup, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
            viewHolder.patient_name.setText(mDataset.get(i).getString(ParseTables.MedicalRecord.PATIENT));
            viewHolder.patient_lastcheckup.setText(mDataset.get(i).getString(ParseTables.MedicalRecord.LASTCHECKUP));
            viewHolder.patient_healthstatus.setText(mDataset.get(i).getString(ParseTables.MedicalRecord.HEALTHSTATUS));
            viewHolder.patient_medication.setText(mDataset.get(i).getString(ParseTables.MedicalRecord.MEDICATION));
            viewHolder.patient_allergies.setText(mDataset.get(i).getString(ParseTables.MedicalRecord.ALLERGIES));
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView patient_name;
            TextView patient_lastcheckup;
            TextView patient_healthstatus;
            TextView patient_medication;
            TextView patient_allergies;


            public ViewHolder(LinearLayout v) {
                super(v);
                this.patient_name = (TextView) v.findViewById(R.id.patient_name);
                this.patient_lastcheckup = (TextView) v.findViewById(R.id.patient_lastcheckup);
                this.patient_healthstatus = (TextView) v.findViewById(R.id.patient_healthstatus);
                this.patient_medication = (TextView) v.findViewById(R.id.patient_medication);
                this.patient_allergies = (TextView) v.findViewById(R.id.patient_allergies);

            }
        }
    }/*

    @SuppressLint("ValidFragment")
    public class FilterDialog extends DialogFragment implements AdapterView.OnItemSelectedListener {
        private View v;
        private CheckBox books;
        private CheckBox apparatus;
        private CheckBox misc;



        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            filterPrefs = getActivity().getSharedPreferences("filterdetails", 0);
            editor = filterPrefs.edit();
            List<String> spinnerList = new ArrayList<>();
            spinnerList.add("Nearest");
            spinnerList.add("Recent");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), R.layout.simple_filter_textview, spinnerList);
            AlertDialog.Builder filterDialog = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            v = inflater.inflate(R.layout.listing_filter_dialog, null);
            books = (TintCheckBox) v.findViewById(R.id.cb_books);
            apparatus = (TintCheckBox) v.findViewById(R.id.cb_apparatus);
            misc = (TintCheckBox) v.findViewById(R.id.cb_misc);
            books.setChecked(filterPrefs.getBoolean("books", true));
            apparatus.setChecked(filterPrefs.getBoolean("apparatus", true));
            misc.setChecked(filterPrefs.getBoolean("misc", true));
            Spinner spinner = (Spinner) v.findViewById(R.id.spinner);
            spinner.setAdapter(dataAdapter);
            spinner.setOnItemSelectedListener(this);
            if (filterPrefs.getString("sortby", "nearest").compareTo("nearest") == 0)
                spinner.setSelection(0);
            else
                spinner.setSelection(1);
            filterDialog.setView(v);
            filterDialog.setTitle("Filter");
            filterDialog.setCancelable(false);
            filterDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (books.isChecked())
                        editor.putBoolean("books", true);
                    else
                        editor.putBoolean("books", false);

                    if (apparatus.isChecked())
                        editor.putBoolean("apparatus", true);
                    else
                        editor.putBoolean("apparatus", false);

                    if (misc.isChecked())
                        editor.putBoolean("misc", true);
                    else
                        editor.putBoolean("misc", false);

                    editor.commit();
                    fetchListings(true, null);
                }
            });
            return filterDialog.create();
        }


*/

}
