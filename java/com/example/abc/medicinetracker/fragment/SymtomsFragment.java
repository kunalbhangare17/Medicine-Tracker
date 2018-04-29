package com.example.abc.medicinetracker.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.abc.medicinetracker.R;


/**
 * Created by developer on 14/2/18.
 */

public class SymtomsFragment  extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String TAG = SymtomsFragment.class.getSimpleName();
    private Spinner symptomsChecker;


    private Button btnSearch;

    public SymtomsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // getActivity().setTitle(getString(R.string.restuarant_home));

        View view = inflater.inflate(R.layout.activity_checker, container, false);

getActivity().setTitle("Vitamin");

        String[] symptoms = { "Back Pain", "Drowsiness", "fever", "loose motion","stomach pain","nose pain","swollen tongue","Headache"};


        symptomsChecker=(Spinner)view.findViewById(R.id.sympChecker);




        // Spinner click listener
        symptomsChecker.setOnItemSelectedListener(this);



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, symptoms);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        symptomsChecker.setAdapter(dataAdapter);










        btnSearch = (Button) view.findViewById(R.id.symChecker);

        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String enteredDepartCity = symptomsChecker.getSelectedItem().toString();




                Log.d(TAG, enteredDepartCity );
                //Add new user to the server
                // addNewUserToRemoteServer(enteredDepartCity,enteredDestination, enteredDate);



                Level1SymptomsActivity rm = new Level1SymptomsActivity();

                SharedPreferences sharedPreferences =getActivity(). getSharedPreferences("User", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //Toast.makeText(getActivity(),tempValues.getFileName(file).toString(),Toast.LENGTH_LONG).show();
                editor.putString("enteredDepartCity",enteredDepartCity);
                editor.commit();
                getActivity().  getSupportFragmentManager().beginTransaction().replace(R.id.container, rm).commit();

/*

                Fragment fragment = new Level1SymptomsActivity();



                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.content_main, fragment);


                ft.commit();


*/










            }

        });
        return  view;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


      /*  // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();


*/

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }





    }


