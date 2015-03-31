package main.java.cz2006project.mojojo.Boundary;

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
public class QwikSearchFragment extends Fragment {


    ViewPager peoplePager;
    FragmentPagerAdapter fragmentPagerAdapter;

    public QwikSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        int p = getActivity().getResources().getColor(R.color.peopleColorPrimary);
        int s = getActivity().getResources().getColor(R.color.peopleColorPrimaryDark);
        SampleApplication.setCustomTheme((ActionBarActivity) getActivity(), p, s);

        fragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return (new SearchNearMe());
                    /*case 1:
                        return (new SearchByDoctor());
                    case 2:
                        return (new SearchByClinic());*/

                }
                return new editAppointment();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Near Me";
                    case 1:
                        return "Search By Doctor";
                    case 2:
                        return "Search by Clinic";
                }

                return null;
            }

            @Override
            public int getCount() {
                return 3;
            }
        };

        peoplePager = (ViewPager) view.findViewById(R.id.people_pager);
        peoplePager.setAdapter(fragmentPagerAdapter);
        peoplePager.setOffscreenPageLimit(3);
        return view;
    }


}