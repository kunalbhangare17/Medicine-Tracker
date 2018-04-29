package com.example.abc.medicinetracker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.abc.medicinetracker.fragment.CameraFragment;
import com.example.abc.medicinetracker.fragment.ReportFragment;
import com.example.abc.medicinetracker.fragment.SymtomsFragment;
import com.example.abc.medicinetracker.fragment.UserReportFragment;

import java.util.Timer;
import java.util.TimerTask;




/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    GridView grid;
  //  String[] text = { "Know Your Med","Health Tips","Med Reminder","Nearby Hospitals" };


    String[] text = { "Know Your Medicine","Health Tips","Med Reminder","Nearby Hospitals","View Report"};
    int[] imageId = { R.drawable.med,R.drawable.health_tips,R.drawable.med_remind,R.drawable.hospitals,R.drawable.med};

    private OnFragmentInteractionListener mListener;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Care For U");
        View v=inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        viewPager = (ViewPager)v.findViewById(R.id.viewPager);
        final PagerAdapter adapter = new CustomAdapter(getContext());

        final Handler mHandler = new Handler();

        // Create runnable for posting
        final Runnable mUpdateResults = new Runnable() {
            public void run() {

                viewPager.setAdapter(adapter);

            }
        };

        int delay = 1000; // delay for 1 sec.
        int period = 5000; // repeat every 5 sec.
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {

                mHandler.post(mUpdateResults);

            }

        }, delay, period);

        //Setting grid view
        CustomGrid gridAdapter = new CustomGrid(getContext(), text, imageId);
        grid=(GridView) v.findViewById(R.id.grid_home);
        grid.setAdapter(gridAdapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch(position)
                {
                    case 0 :
                    {
                        CameraFragment rm=new CameraFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();


                       /* FragmentReminder rm=new FragmentReminder();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();*/
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.container,rm).commit();
                        break;
                    }
                    case 1 :
                    {
                        HealthTips rm=new HealthTips();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.container,rm).commit();
                        break;
                    }
                    case 2 :
                    {
                          FragmentReminder rm=new FragmentReminder();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();


                      /*  Knowmed rm=new Knowmed();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();*/
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.container,rm).commit();
                        break;
                    }
                    case 4 :
                    {

                       UserReportFragment rm=new UserReportFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.container,rm).commit();
                        break;
                    }
                    case 3 :
                    {
                      /*  MapActivity rm=new MapActivity();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.container,rm).commit();*/
                        Intent intent=new Intent(getActivity(),MainMapMainActivity.class);

startActivity(intent);

                        break;
                    }
                }

            }
        });

        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
