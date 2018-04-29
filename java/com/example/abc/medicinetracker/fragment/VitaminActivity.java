package com.example.abc.medicinetracker.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.abc.medicinetracker.R;
import com.example.abc.medicinetracker.adapter.VitaminAdapter;
import com.example.abc.medicinetracker.entities.Level0;
import com.example.abc.medicinetracker.entities.VitaminMapPojo;
import com.example.abc.medicinetracker.network.GsonRequest;
import com.example.abc.medicinetracker.network.VolleySingleton;
import com.example.abc.medicinetracker.util.Helper;
import com.example.abc.medicinetracker.util.SimpleDividerItemDecoration;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by developer on 15/2/18.
 */

public class VitaminActivity extends Fragment implements View.OnClickListener   {

    private static final String TAG = Level1SymptomsActivity.class.getSimpleName();
    String symptom_name="";
    Button btnSubmit;
    private RecyclerView recyclerView;
    View.OnClickListener checkBoxListener;

    String level0_id="";

    private Level0 singleMenuItem;
    public VitaminActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // getActivity().setTitle(getString(R.string.restuarant_home));

        View view = inflater.inflate(R.layout.activity_disaese, container, false);

        btnSubmit=(Button)view.findViewById(R.id.btnSubmit) ;


        // symptoms=   (CheckBox)findViewById(R.id.symptomsChecker) ;

        recyclerView = (RecyclerView)view.findViewById(R.id.List);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));



        SharedPreferences sharedPreferences =getActivity(). getSharedPreferences("User", Context.MODE_PRIVATE);

        final String level1_id = sharedPreferences.getString("level1_id",null);








        getVitaminFromRemoteServer(level1_id);





        return  view;
    }



    private void getVitaminFromRemoteServer(String level1_id){
        Map<String, String> params = new HashMap<String,String>();
        params.put(Helper.level1_id, String.valueOf(level1_id));




        GsonRequest<VitaminMapPojo[]> serverRequest = new GsonRequest<VitaminMapPojo[]>(
                Request.Method.POST,
                Helper.PATH_TO_LEVEL2_MEDICINE,
                VitaminMapPojo[].class,
                params,
                createRequestSuccessListener1(),
                createRequestErrorListener1());

        serverRequest.setRetryPolicy(new DefaultRetryPolicy(
                Helper.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(serverRequest);
    }

    private Response.Listener<VitaminMapPojo[]> createRequestSuccessListener1() {
        return new Response.Listener<VitaminMapPojo[]>() {
            @Override
            public void onResponse(VitaminMapPojo[] response) {
                try {
                    if(response.length > 0){
                        Log.d(TAG, "Json Response " + response.toString());
                        //display  by category
                        List<VitaminMapPojo> itemObject = new ArrayList<VitaminMapPojo>();





                        for (int i = 0; i < response.length; i++) {
                            itemObject.add(new VitaminMapPojo(response[i].getLevel_1_vitamin_id(),response[i].getLevel_1_id(),  response[i].getLevel_1_name(), response[i].getVitamin_deficiency()));

                        }

                       /* LayoutInflater inflater = LayoutInflater.from(getActivity());
                        View subview = inflater.inflate(R.layout.user_login, null);

                        TextView textView = (TextView) subview.findViewById(R.id.word);



                        Log.d(TAG, "Json Response " + response.toString());
                        //display  by category
                        List<VitaminMapPojo> itemObject = new ArrayList<VitaminMapPojo>();





                        for (int i = 0; i < response.length; i++){
                            itemObject.add(new VitaminMapPojo(response[i].getLevel_1_id(), response[i].getLevel_1_vitamin_id(),response[i].getLevel_1_name(),response[i].getVitamin_deficiency()));

                      textView.setText(response[i].getVitamin_deficiency());



                        }



                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Vitamin");

                        builder.setView(subview);
                        builder.create();

                   *//* builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    });
*//*

                        builder.show();

*/








                        VitaminAdapter mAdapter = new VitaminAdapter(getActivity(), itemObject, R.layout.disease_symptoms_list_layout);
                        recyclerView.setAdapter(mAdapter);





                    }else{
                        Toast.makeText(getActivity(), "No data found", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private Response.ErrorListener createRequestErrorListener1() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };
    }




    @Override
    public void onClick(View v) {

    }














}