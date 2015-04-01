package main.java.cz2006project.mojojo.Boundary;

/**
 * Created by srishti on 31/3/15.
 */

        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.widget.SwipeRefreshLayout;
        import android.support.v7.widget.CardView;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.RelativeLayout;
        import android.widget.ScrollView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.parse.DeleteCallback;
        import com.parse.FindCallback;
        import com.parse.ParseException;
        import com.parse.ParseObject;
        import com.parse.ParseQuery;
        import com.parse.ParseUser;

        import java.util.List;

        import cz2006project.mojojo.R;
        import main.java.cz2006project.mojojo.Control.ParseTables;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduledAppointmentsFragment extends Fragment {

    RecyclerView appointmentsList;
    RecyclerView.Adapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    private boolean refresh = false;
    //private boolean check_my_appointments=false;
    View v;
    LinearLayout appointmentsMainLayout;
    ScrollView emptyAppointment;

    public ScheduledAppointmentsFragment(){

    }

    public static ScheduledAppointmentsFragment newInstance(){
        ScheduledAppointmentsFragment scheduledAppointmentsFragment = new ScheduledAppointmentsFragment();
        //Bundle b = new Bundle();
        //b.putBoolean("check", check);
        //scheduledAppointmentsFragment.setArguments(b);
        return scheduledAppointmentsFragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        /*if(this.getArguments() != null){
            check_my_appointments = getArguments().getBoolean("check");
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragments_appointments_list, container, false);
        appointmentsList = (RecyclerView) v.findViewById(R.id.listviewappointments);
        appointmentsMainLayout = (LinearLayout) v.findViewById(R.id.appointments_main_list);
        emptyAppointment = (ScrollView) v.findViewById(R.id.empty_appointments);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        appointmentsList.setLayoutManager(layoutManager);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh = true;
                fetchData();
            }
        });
        fetchData();

        return v;
    }

    public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.ViewHolder> implements View.OnClickListener {

        private int expandedPosition = -1;
        private List<ParseObject> appointments;

        public AppointmentsAdapter(List<ParseObject> appointments) {
            this.appointments = appointments;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CardView cd = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_appointments, parent, false);
            ViewHolder viewHolder = new ViewHolder(cd);
            viewHolder.itemView.setOnClickListener(AppointmentsAdapter.this);
            viewHolder.itemView.setTag(viewHolder);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.appointment_number.setText((String)appointments.get(position).get(ParseTables.Appointment.APPOINTMENTNUMBER));
            holder.patient.setText((String)appointments.get(position).get(ParseTables.Appointment.PATIENT));
            holder.clinic.setText((String)appointments.get(position).get(ParseTables.Appointment.CLINIC));
            holder.appointment_date.setText(appointments.get(position).get(ParseTables.Appointment.DATE)+" "+appointments.get(position).get(ParseTables.Appointment.TIME));
            holder.notes.setText((String)appointments.get(position).get(ParseTables.Appointment.NOTES));
            holder.medicalissue.setText((String)appointments.get(position).get(ParseTables.Appointment.MEDICALISSUE));


                holder.appointment_creator.setVisibility(View.GONE);
                holder.appointment_delete.setVisibility(View.VISIBLE);


            if (position == expandedPosition) {
                holder.expanded_area.setVisibility(View.VISIBLE);
            } else {
                holder.expanded_area.setVisibility(View.GONE);
            }
        }

        @Override
        public int getItemCount() {
            return appointments.size();
        }

        @Override
        public void onClick(View v) {
            final ViewHolder holder = (ViewHolder) v.getTag();
            if (holder.getPosition() == expandedPosition) {
                holder.expanded_area.setVisibility(View.GONE);
                expandedPosition = -1;
            } else {
                if (expandedPosition >= 0) {
                    int prev = expandedPosition;
                    notifyItemChanged(prev);
                }
                expandedPosition = holder.getPosition();
                notifyItemChanged(expandedPosition);
            }

        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView appointment_number;
            TextView clinic;
            TextView patient;
            TextView medicalissue;
            RelativeLayout expanded_area;
            TextView notes;
            TextView appointment_date;
            TextView appointment_time;
            Button appointment_delete;
            TextView appointment_creator;


            public ViewHolder(View itemView) {
                super(itemView);
                this.appointment_number = (TextView) itemView.findViewById(R.id.appointment_number);
                this.clinic = (TextView) itemView.findViewById(R.id.clinic);
                this.patient = (TextView) itemView.findViewById(R.id.patient);
                this.medicalissue = (TextView) itemView.findViewById(R.id.medicalissue);
                this.expanded_area = (RelativeLayout) itemView.findViewById(R.id.expanded_area);
                this.appointment_creator = (TextView) itemView.findViewById(R.id.appointment_creator);

                this.notes = (TextView) itemView.findViewById(R.id.notes);
                this.appointment_date = (TextView) itemView.findViewById(R.id.appointment_date);
                this.appointment_time = (Button) itemView.findViewById(R.id.appointment_time);

                appointment_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ParseObject appt = appointments.get(getPosition());
                        appt.deleteInBackground(new DeleteCallback() {
                            @Override
                            public void done(ParseException e) {
                                if(e == null){
                                    fetchData();
                                }
                                else{
                                    Toast.makeText(getActivity().getApplicationContext(), "Internet Connection Problem", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });

            }
        }
    }

    public void fetchData(){
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                "Appointments");
        query.orderByAscending(ParseTables.Appointment.DATE);

            query.whereEqualTo(ParseTables.Appointment.DOCTOR, ParseUser.getCurrentUser().getString("NAME"));

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                doneFetching(parseObjects);
            }
        });
    }

    public void doneFetching(List<ParseObject> appointments){
        adapter = new AppointmentsAdapter(appointments);
        appointmentsList.setAdapter(adapter);
        if (refresh == true) {
            swipeRefreshLayout.setRefreshing(false);
            refresh = false;
        }

            appointmentsMainLayout.setVisibility(View.GONE);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);
    }



}

