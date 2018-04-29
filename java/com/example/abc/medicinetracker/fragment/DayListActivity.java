package com.example.abc.medicinetracker.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.abc.medicinetracker.R;
import com.example.abc.medicinetracker.entities.PatientReportPojo;
import com.example.abc.medicinetracker.util.Helper;
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
import java.util.Collections;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


//DaysPojo

public class DayListActivity extends Fragment implements AdapterView.OnItemClickListener {
    ListView listView,list;

    String days="";
    TextView dayText;

    String x="";
    public DayListActivity() {
        // Required empty public constructor
    }

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_list);*/

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // getActivity().setTitle("Nearby Medical Shop");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_day_list, container, false);


        listView=(ListView)view.findViewById(R.id.daylist);

        SharedPreferences settings =getActivity().getSharedPreferences("patientdata",MODE_PRIVATE);
        String patient_email = settings.getString("patient_email",null);


        DayListActivity.Fetcher fetcher = new DayListActivity.Fetcher();
        String method = "companyList";
        fetcher.execute(method,patient_email);


return  view;


    }

    public void onItemClick(int mPosition) {


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }


    public class CustomAdapter extends BaseAdapter implements View.OnClickListener {

        /*********** Declare Used Variables *********/
        private Activity activity;
       public List<PatientReportPojo> data;
        LayoutInflater inflater=null;
        // public Resources res;
        PatientReportPojo tempValues=null;
        int i=0;
        private View btnBooking;

        /*************  CustomAdapter Constructor *****************/
        public CustomAdapter(Activity a, List<PatientReportPojo> d) {

            /********** Take passed values **********/
            activity = a;
            data=d;
            // res = resLocal;

            /***********  Layout inflator to call external xml activity_faculty_info () ***********/
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        /******** What is the size of Passed Arraylist Size ************/
        public int getCount() {

            if(data.size()<=0)
                return 1;
            return data.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        /********* Create a holder Class to contain inflated xml file elements *********/
        class ViewHolder{
            TextView date,sys,dia,gluco,aic,chro,bmi;
        }

        /****** Depends upon data size called for each row , Create each ListView row *****/
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public View getView(final int position, View convertView, ViewGroup parent ) {

            View vi = convertView;
            final DayListActivity.CustomAdapter.ViewHolder holder;

            if(convertView==null){

                /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
                vi = inflater.inflate(R.layout.layout2, null);

                /****** View Holder Object to contain tabitem.xml file elements ******/
                holder = new DayListActivity.CustomAdapter.ViewHolder();
                holder.date = (TextView) vi.findViewById(R.id.tv_name);
                holder.sys=(TextView)vi.findViewById(R.id.name) ;
                holder.dia=(TextView)vi.findViewById(R.id.sub);
                holder.gluco=(TextView)vi.findViewById(R.id.sub1);
                holder.aic=(TextView)vi.findViewById(R.id.sub2);

                holder.chro=(TextView)vi.findViewById(R.id.sub3);
                holder.bmi=(TextView)vi.findViewById(R.id.sub4);




                /************  Set holder with LayoutInflater ************/
                vi.setTag( holder );
            }
            else
                holder=(DayListActivity.CustomAdapter.ViewHolder)vi.getTag();

            if(data.size()<=0)
            {
                holder.date.setText("No Data");


            }
            else
            {
                /***** Get each Model object from Arraylist ********/
                tempValues=null;
                tempValues = (PatientReportPojo) data.get( position );

                /************  Set Model values in Holder elements ***********/


                holder.date.setText( tempValues.getDate());




                holder.sys.setText( tempValues.getSystolic());
                holder.dia.setText( tempValues.getDiastolic());
                holder.gluco.setText( tempValues.getGlucose());
                holder.aic.setText( tempValues.getAic());
                holder.chro.setText( tempValues.getTotal_cholestrol());

                holder.bmi.setText( tempValues.getBmi());





                vi.setOnClickListener(new DayListActivity.CustomAdapter.OnItemClickListener( position ));



            }
            return vi;
        }

        @Override
        public void onClick(View v) {


            Log.v("CustomAdapter", "=====Row button clicked=====");
        }

        /********* Called when Item click in ListView ************/
        private class OnItemClickListener  implements View.OnClickListener {
            private int mPosition;

            OnItemClickListener(int position){
                mPosition = position;
            }

            @Override
            public void onClick(View arg0) {

                /*DayListActivity sct = (DayListActivity) activity;

                /*//****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****//**//*
                sct.onItemClick(mPosition);
           */ }
        }
    }
    //AsyncTask
    private class Fetcher extends AsyncTask<String, String, String> {

        AlertDialog alertDialog;
        Context ctx;

        public Fetcher() {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            String faculty_list_url = Helper.DOMAIN+"PatientServlet";
            // String login_url = Constants.DOMAIN_URL+"LibraryManagementSystem/AdminServlet";
            String method = params[0];
            if (method.equals("companyList")) {
                String patient_email=params[1];


                try {
                    URL url = new URL(faculty_list_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    //httpURLConnection.setDoInput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                    String data =
                            URLEncoder.encode("user_email", "UTF-8") + "=" + URLEncoder.encode(patient_email, "UTF-8") + "&" +

                            URLEncoder.encode("action", "UTF-8") + "=" + URLEncoder.encode("getPatientReport", "UTF-8");

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

                Log.i("jsonToPersonList","onPostExecute  jsonToPersonList="+result);
                ObjectMapper objectMapper = new ObjectMapper();
                TypeReference<List<PatientReportPojo>> mapType = new TypeReference<List<PatientReportPojo>>() {
                };
                List<PatientReportPojo> jsonToPersonList = objectMapper.readValue(result, mapType);

                //List<OwnerPojo> jsonToPersonList = new ObjectMapper().readValue(result, new TypeReference<List<OwnerPojo>>() { });
                Log.i("jsonToPersonList","onPostExecute  jsonToPersonList="+jsonToPersonList);
                // objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);

                DayListActivity.CustomAdapter customList=new DayListActivity.CustomAdapter(getActivity(), (List)jsonToPersonList);




                listView.setAdapter(customList);


                //System.out.println(jsonToPersonList);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
