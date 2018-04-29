package com.example.abc.medicinetracker;

import android.*;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import static android.content.Context.MODE_PRIVATE;

public class MapActivity extends Fragment {
    String address="";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Nearby Medical Shop");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_map, container, false);

        WebView mywebview = (WebView)v. findViewById(R.id.map);



        String location ="";/*request.getParameter("location");*/


        SharedPreferences settings =getActivity().getSharedPreferences("patientdata",MODE_PRIVATE);

        location=settings.getString("enteredLocation", "");


        String vaccination_center ="medical center";

    String address=location+","+vaccination_center;




        String html =   "<iframe width='700' height='700' frameborder='0' scrolling='no'  marginheight='0' marginwidth='0'   src='https://maps.google.com/maps?&amp;q="+address+"&amp;output=embed'></iframe>";





        mywebview.clearCache(true);
        mywebview.clearHistory();
        mywebview.getSettings().setJavaScriptEnabled(true);
        mywebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        mywebview.loadDataWithBaseURL (null, html, "text/html", "utf-8",   "about:blank");


return  v;



    }


}
