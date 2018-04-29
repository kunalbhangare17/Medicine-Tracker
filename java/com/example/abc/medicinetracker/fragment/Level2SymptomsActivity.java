package com.example.abc.medicinetracker.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.example.abc.medicinetracker.R;
import com.example.abc.medicinetracker.adapter.Level2Adapter;
import com.example.abc.medicinetracker.adapter.VitaminAdapter;
import com.example.abc.medicinetracker.entities.Level1;
import com.example.abc.medicinetracker.entities.VitaminMapPojo;
import com.example.abc.medicinetracker.network.GsonRequest;
import com.example.abc.medicinetracker.network.VolleySingleton;
import com.example.abc.medicinetracker.util.Helper;
import com.example.abc.medicinetracker.util.SimpleDividerItemDecoration;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import static android.content.Context.MODE_PRIVATE;

public class Level2SymptomsActivity extends Fragment implements View.OnClickListener  {
    private static final String TAG = Level2SymptomsActivity.class.getSimpleName();
    Bundle level0_id;
    Button btnSubmit;
    private RecyclerView recyclerView;
    View.OnClickListener checkBoxListener;
    public Level2SymptomsActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // getActivity().setTitle(getString(R.string.restuarant_home));

        View view = inflater.inflate(R.layout.activity_level2_symptoms, container, false);




        btnSubmit=(Button)view.findViewById(R.id.btnSubmit) ;

        // symptoms=   (CheckBox)findViewById(R.id.symptomsChecker) ;

        recyclerView = (RecyclerView)view.findViewById(R.id.sym2List);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));



        SharedPreferences sharedPreferences =getActivity(). getSharedPreferences("User", Context.MODE_PRIVATE);

        final String level0_id = sharedPreferences.getString("level0_id",null);




        //Bundle level0_id = getIntent().getExtras().getBundle("level0_id");








        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                try {

                    SharedPreferences settings = getActivity().getSharedPreferences("Groceries", MODE_PRIVATE);
                    String var =settings.getString("checkbox","");
                    //String name =settings.getString("name","");




                 /*   Level2SymptomsActivity.Fetcher fetcher = new Level2SymptomsActivity.Fetcher();
                    String method = "companyList";
                    fetcher.execute(method,var);
*/



                    VitaminActivity rm1 = new VitaminActivity();

                    SharedPreferences sharedPreferences =getActivity(). getSharedPreferences("User", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    //Toast.makeText(getActivity(),tempValues.getFileName(file).toString(),Toast.LENGTH_LONG).show();
                    editor.putString("level1_id",var);
                    editor.commit();
                    getActivity().  getSupportFragmentManager().beginTransaction().replace(R.id.container, rm1).commit();




                   // getVitaminFromRemoteServer(var);












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


                  /*  Intent intent=new Intent(Level2SymptomsActivity.this,Disaese1Activity.class);




                    intent.putExtra("level1_id",var);



                    startActivity(intent);*/












                }



                catch (Exception e)
                {
                    e.printStackTrace();
                }










            }

        });



        getLevel2ListFromRemoteServer(level0_id);
        return  view;
    }

  /*  private void getVitaminFromRemoteServer(String var) {
    }
*/








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
                            itemObject.add(new VitaminMapPojo(response[i].getLevel_1_id(), response[i].getLevel_1_vitamin_id(), response[i].getLevel_1_name(), response[i].getVitamin_deficiency()));

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











    private void getLevel2ListFromRemoteServer(String level0_id){
        Map<String, String> params = new HashMap<String,String>();
        params.put(Helper.level0_id, String.valueOf(level0_id));




        GsonRequest<Level1[]> serverRequest = new GsonRequest<Level1[]>(
                Request.Method.POST,
                Helper.PATH_TO_LEVEL2_SYMTOMS,
                Level1[].class,
                params,
                createRequestSuccessListener(),
                createRequestErrorListener());

        serverRequest.setRetryPolicy(new DefaultRetryPolicy(
                Helper.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(serverRequest);
    }

    private Response.Listener<Level1[]> createRequestSuccessListener() {
        return new Response.Listener<Level1[]>() {
            @Override
            public void onResponse(Level1[] response) {
                try {
                    if(response.length > 0){


                        Log.d(TAG, "Json Response " + response.toString());
                        //display  by category
                        List<Level1> itemObject = new ArrayList<Level1>();
                        for (int i = 0; i < response.length; i++){
                            itemObject.add(new Level1(response[i].getLevel1_id(), response[i].getLevel1_name(),response[i].getLevel1_description()));

                        }
                        Level2Adapter mAdapter = new Level2Adapter(getActivity(), itemObject, R.layout.level2_symptoms_list_layout);
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


    private class Fetcher extends AsyncTask<String, String, String> {

        android.app.AlertDialog alertDialog;
        Context ctx;

        public Fetcher() {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            String faculty_list_url = Helper.DOMAIN + "SearchServlet";
            // String login_url = Constants.DOMAIN_URL+"LibraryManagementSystem/AdminServlet";
            String method = params[0];
            if (method.equals("companyList")) {
                String level1_id = params[1];

                try {
                    URL url = new URL(faculty_list_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    //httpURLConnection.setDoInput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                    String data =

                            URLEncoder.encode("level1_id", "UTF-8") + "=" + URLEncoder.encode(level1_id, "UTF-8") + "&" +

                                    URLEncoder.encode("action", "UTF-8") + "=" + URLEncoder.encode("getVitamin", "UTF-8");

                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String response = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return response;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            try {

                Log.i("jsonToPersonList", "onPostExecute  jsonToPersonList=" + result);
                ObjectMapper objectMapper = new ObjectMapper();
                TypeReference<List<VitaminMapPojo>> mapType = new TypeReference<List<VitaminMapPojo>>() {
                };
                List<VitaminMapPojo> jsonToPersonList = objectMapper.readValue(result, mapType);

                //List<OwnerPojo> jsonToPersonList = new ObjectMapper().readValue(result, new TypeReference<List<OwnerPojo>>() { });
                Log.i("jsonToPersonList", "onPostExecute  jsonToPersonList=" + jsonToPersonList);
                // objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);

                String calaries = null;

               /* int i = 0;*/
                //  if(result.isEmpty()||result.length()>0)

                if (jsonToPersonList.size() > 0)
                // {
                //   if(calaries!=null)
                {

                    for (int i = 0; i < jsonToPersonList.size(); i++) {

                        calaries = jsonToPersonList.get(i).getVitamin_deficiency().toString();
                        LayoutInflater inflater = LayoutInflater.from(getActivity());
                        View subview = inflater.inflate(R.layout.user_login, null);

                        TextView textView = (TextView) subview.findViewById(R.id.word);
                        textView.setText(calaries);
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Vitamin");

                        builder.setView(subview);
                        builder.create();

                   /* builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    });
*/

                        builder.show();


                    }


                }

                //System.out.println(jsonToPersonList);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
    }

