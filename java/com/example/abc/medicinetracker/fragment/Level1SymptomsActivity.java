package com.example.abc.medicinetracker.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.abc.medicinetracker.R;
import com.example.abc.medicinetracker.adapter.Level1Adapter;
import com.example.abc.medicinetracker.entities.Level0;
import com.example.abc.medicinetracker.network.GsonRequest;
import com.example.abc.medicinetracker.network.VolleySingleton;
import com.example.abc.medicinetracker.util.Helper;
import com.example.abc.medicinetracker.util.SimpleDividerItemDecoration;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import static android.content.Context.MODE_PRIVATE;

/**
 * Created by developer on 14/2/18.
 */

public class Level1SymptomsActivity  extends Fragment implements View.OnClickListener   {

    private static final String TAG = Level1SymptomsActivity.class.getSimpleName();
    String symptom_name="";
    Button btnSubmit;
    private RecyclerView recyclerView;
    View.OnClickListener checkBoxListener;

    String level0_id="";

    private Level0 singleMenuItem;
    public Level1SymptomsActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // getActivity().setTitle(getString(R.string.restuarant_home));

        View view = inflater.inflate(R.layout.activity_level1, container, false);



        btnSubmit=(Button)view.findViewById(R.id.btnSubmit) ;




        final List<CheckBox> optionCheckBox = new ArrayList<CheckBox>();

        // symptoms=   (CheckBox)findViewById(R.id.symptomsChecker) ;

        recyclerView = (RecyclerView)view.findViewById(R.id.symList);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));


        SharedPreferences sharedPreferences =getActivity(). getSharedPreferences("User", Context.MODE_PRIVATE);

        final String symptom_name = sharedPreferences.getString("enteredDepartCity",null);





        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //String enteredDepartCity = symptomsChecker.getSelectedItem().toString();

                //String enteredEmail = level0_id.getText().toString().trim();



                //  Log.d(TAG, enteredDepartCity );
                //Add new user to the server
                // addNewUserToRemoteServer(enteredDepartCity,enteredDestination, enteredDate);


                // getBookDetailListFromRemoteServer(ownerId);


                try {

                    SharedPreferences settings = getActivity().getSharedPreferences("Groceries", MODE_PRIVATE);
                    String var =settings.getString("checkbox","");


//                  Toast.makeText(activity,"Var "+var,Toast.LENGTH_LONG).show();
//
//
//                    JSONArray jsonArray = new JSONArray(var);
//                    String arr[]= new String[jsonArray.length()];
//                    for(int i=0;i<jsonArray.length();i++)
//
//                    {
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        String checkboxId = jsonObject.get("checkbox").toString();
//
//                        arr[i]=checkboxId;
//
//                    }
//
                    //  Bundle b=new Bundle();

                    //   b.putStringArray("level0_id", arr);

                    //b.putString("level0_id", var);


                   /* Intent intent=new Intent(Level1SymptomsActivity.this,Level2SymptomsActivity.class);




                    intent.putExtra("level0_id",var);



                    startActivity(intent);*/



                    Level2SymptomsActivity rm1 = new Level2SymptomsActivity();

                    SharedPreferences sharedPreferences =getActivity(). getSharedPreferences("User", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    //Toast.makeText(getActivity(),tempValues.getFileName(file).toString(),Toast.LENGTH_LONG).show();
                    editor.putString("level0_id",var);
                    editor.commit();
                    getActivity().  getSupportFragmentManager().beginTransaction().replace(R.id.container, rm1).commit();







                }



                catch (Exception e)
                {
                    e.printStackTrace();
                }



            }

        });



        getLevel1ListFromRemoteServer(symptom_name,level0_id);

        return  view;
    }


    private void getLevel1ListFromRemoteServer(String symptom_name , String level0_id){
        Map<String, String> params = new HashMap<String,String>();
        params.put(Helper.symptom_name,symptom_name);
        params.put(Helper.level0_id,level0_id);




        GsonRequest<Level0[]> serverRequest = new GsonRequest<Level0[]>(
                Request.Method.POST,
                Helper.PATH_TO_LEVEL1_SYMTOMS,
                Level0[].class,
                params,
                createRequestSuccessListener(),
                createRequestErrorListener());

        serverRequest.setRetryPolicy(new DefaultRetryPolicy(
                Helper.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(serverRequest);
    }

    private CheckBox createDynamicCheckBox(int index, String textContent){
        CheckBox checkBox = new CheckBox(getActivity());
        checkBox.setId(index);
        checkBox.setText(textContent);
        LinearLayout.LayoutParams params;
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        checkBox.setLayoutParams(params);
        return checkBox;
    }

    private Response.Listener<Level0[]> createRequestSuccessListener() {
        return new Response.Listener<Level0[]>() {
            @Override
            public void onResponse(Level0[] response) {
                try {
                    if(response.length > 0){
                        Log.d(TAG, "Json Response " + response.length);
                        //display  by category
                        List<Level0> itemObject = new ArrayList<Level0>();
                        for (int i = 0; i < response.length; i++){
                            itemObject.add(new Level0(response[i].getLevel0_id(), response[i].getLevel0_name(),response[i].getLevel0_description()));

                        }
                        Level1Adapter mAdapter = new Level1Adapter(getActivity(), itemObject, R.layout.level1_symptoms_list_layout);
                        recyclerView.setAdapter(mAdapter);
                    }else{
                        Toast.makeText(getActivity(), "", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private Response.ErrorListener createRequestErrorListener() {
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