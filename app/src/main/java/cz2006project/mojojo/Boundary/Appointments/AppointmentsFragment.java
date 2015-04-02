package main.java.cz2006project.mojojo.Boundary.Appointments;

/**
 * Created by srishti on 30/3/15.
 */


        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentPagerAdapter;
        import android.support.v4.view.ViewPager;
        import android.support.v7.app.ActionBarActivity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import cz2006project.mojojo.R;
        import main.java.cz2006project.mojojo.Control.SampleApplication;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppointmentsFragment extends Fragment {

    ViewPager appointmentsPager;
    FragmentPagerAdapter fragmentPagerAdapter;


    public AppointmentsFragment() {
        // Required empty public constructor
    }

    public static AppointmentsFragment newInstance() {
        AppointmentsFragment fragment = new AppointmentsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_appointments, container, false);
        int p = getActivity().getResources().getColor(R.color.eventsColorPrimary);
        int s = getActivity().getResources().getColor(R.color.eventsColorPrimaryDark);
        SampleApplication.setCustomTheme((ActionBarActivity) getActivity(), p, s);

        fragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return (new editAppointment());
                    case 1:
                        return (new changeAppointment());
                    case 2:
                        return MyAppointmentsFragment.newInstance(true);

                }
                return new MyAppointmentsFragment();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Create Appointment";
                    case 1:
                        return "Change Appointment";
                    case 2:
                        return "My Appointments";
                }

                return null;
            }

            @Override
            public int getCount() {
                return 3;
            }
        };

        appointmentsPager = (ViewPager) view.findViewById(R.id.appointments_pager);
        appointmentsPager.setAdapter(fragmentPagerAdapter);
        appointmentsPager.setOffscreenPageLimit(2);
        return view;
    }

    public void goToOtherFragment(int position) {
        appointmentsPager.setCurrentItem(position);
    }





}
