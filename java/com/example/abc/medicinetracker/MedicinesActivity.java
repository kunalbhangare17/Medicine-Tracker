package com.example.abc.medicinetracker;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abc.medicinetracker.fragment.CameraFragment;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MedicinesActivity extends AppCompatActivity {


    EditText drname, medsname;
    Spinner spinner1, spinner2, spinner3;
    Spinner type, number;
    Button submit;
    ListView list;
    String[] time = {"Yes", "No"};
    String[] stype = {"TYPE", "Tablet", "Syrup"};
    String[] tnumber = {"Days", "1", "2", "3", "4", "5", "6", "7"};
    String[] snumber = {"Syrup No.", "1", "2", "3", "4"};

    CheckBox c1, c2, c3;
    ImageView start;
    String morningg, afternoonn, nightt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);

        drname = (EditText) findViewById(R.id.doctorname);
        medsname = (EditText) findViewById(R.id.medsname);
        start = (ImageView) findViewById(R.id.start);


        type = (Spinner) findViewById(R.id.medtype);
        number = (Spinner) findViewById(R.id.medno);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Calendar ca = Calendar.getInstance();
                System.out.println("Current time =&gt; " + ca.getTime());

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = df.format(ca.getTime());
                SharedPreferences settings = getSharedPreferences("patientdata", MODE_PRIVATE);

                String email = settings.getString("patient_email", null);


                String method = "email";
                MedicinesActivity.BackgroundTask2 backgroundTask = new MedicinesActivity.BackgroundTask2(MedicinesActivity.this);
                backgroundTask.execute(method, email, formattedDate);


            }
        }, 60000);




      /*  start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar ca = Calendar.getInstance();
                System.out.println("Current time =&gt; " + ca.getTime());

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = df.format(ca.getTime());
                SharedPreferences settings = getSharedPreferences("patientdata", MODE_PRIVATE);

                String email = settings.getString("patient_email", null);


                String method = "email";
                MedicinesActivity.BackgroundTask2 backgroundTask = new MedicinesActivity.BackgroundTask2(MedicinesActivity.this);
                backgroundTask.execute(method, email, formattedDate);


            }
        });
*/






       /* spinner1=(Spinner) findViewById(R.id.medsmorn);
        spinner2=(Spinner) findViewById(R.id.medsaftr);
        spinner3=(Spinner) findViewById(R.id.medsnght);*/

        submit = (Button) findViewById(R.id.submit);

        list = (ListView) findViewById(R.id.listview);

        c1 = (CheckBox) findViewById(R.id.morning);
        c2 = (CheckBox) findViewById(R.id.afternoon);
        c3 = (CheckBox) findViewById(R.id.night);


        ArrayAdapter<String> dataadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, time);
        dataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       /* spinner1.setAdapter(dataadapter);
        spinner2.setAdapter(dataadapter);
        spinner3.setAdapter(dataadapter);*/

        ArrayAdapter<String> dataadaptertype = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stype);
        dataadaptertype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(dataadaptertype);

        ArrayAdapter<String> dataadapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tnumber);
        dataadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        number.setAdapter(dataadapter1);

        ArrayAdapter<String> dataadapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, snumber);
        dataadapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        String typeee = type.getSelectedItem().toString();
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

       /* if (typeee.contains("Tablet"))
        {
            number.setAdapter(dataadapter1);
        }else
        {
            number.setAdapter(dataadapter2);
        }*/

        SharedPreferences sharedrname = getApplicationContext().getSharedPreferences("drname", MODE_PRIVATE);
        String nm = sharedrname.getString("drname", null);
        medsname.setText(nm);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 52);
        calendar.set(Calendar.SECOND, 0);
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm");
// you can get seconds by adding  "...:ss" to it
        date.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));

        String localTime = date.format(currentLocalTime);

        AlarmManager am = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        PendingIntent pi = PendingIntent.getService(getApplicationContext(), 0, new Intent(getApplicationContext(), MedicinesActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pi);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (c1.isChecked()) {
                    morningg = "yes";
                } else {
                    morningg = "no";
                }
                if (c2.isChecked()) {
                    afternoonn = "yes";
                } else {
                    afternoonn = "no";
                }
                if (c3.isChecked()) {
                    nightt = "yes";
                } else {
                    nightt = "no";
                }

                String namee = drname.getText().toString();
                String medsnamee = medsname.getText().toString();

                String medtype = type.getSelectedItem().toString().trim();
                String mednumber = number.getSelectedItem().toString().trim();
               /* String morn=spinner1.getSelectedItem().toString().trim();
                String aftr=spinner2.getSelectedItem().toString().trim();
                String night=spinner3.getSelectedItem().toString().trim();*/

                SharedPreferences settings = getSharedPreferences("patientdata", MODE_PRIVATE);
                String patientemail = settings.getString("patient_email", null);
                String patientid = settings.getString("patient_id", null);
                String patientref = settings.getString("patient_ref_email", null);

                String method = "submit";
                MedicinesActivity.BackgroundTask1 backgroundTask = new MedicinesActivity.BackgroundTask1(MedicinesActivity.this);
                backgroundTask.execute(method, namee, medsnamee, morningg, afternoonn, nightt, patientid, patientemail, patientref, medtype, mednumber);
            }
        });


    }


    ////////////////web services to submit////////////

    public class BackgroundTask1 extends AsyncTask<String, Void, String> {
        AlertDialog alertDialog;
        Context ctx;

        public BackgroundTask1(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(ctx).create();
            alertDialog.setTitle("Login Information....");
        }

        @Override
        protected String doInBackground(String... params) {
            // String reg_url = "http://192.168.1.113:8080/LibraryManagementSystem/RegistrationServlet";
            String reg_url = Helper.DOMAIN + "MedicineServlet";

            String method = params[0];
            if (method.equals("submit")) {
                //Set pretty printing of json
                //objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

                String namee = params[1];
                String medsname = params[2];
                String mornn = params[3];
                String aftrr = params[4];
                String nightt = params[5];
                String patientidd = params[6];
                String patientemaill = params[7];
                String patientrefemaill = params[8];
                String typee = params[9];
                String number = params[10];


                try {
                    //String arrayToJson = objectMapper.writeValueAsString(videoMaster);
                    URL url = new URL(reg_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);

                    //httpURLConnection.setDoInput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("doctor_name", "UTF-8") + "=" + URLEncoder.encode(namee, "UTF-8") + "&" +
                            URLEncoder.encode("medicine_name", "UTF-8") + "=" + URLEncoder.encode(medsname, "UTF-8") + "&" +
                            URLEncoder.encode("morning_time_medicine", "UTF-8") + "=" + URLEncoder.encode(mornn, "UTF-8") + "&" +
                            URLEncoder.encode("afternoon_time_medicine", "UTF-8") + "=" + URLEncoder.encode(aftrr, "UTF-8") + "&" +
                            URLEncoder.encode("night_time_medicine", "UTF-8") + "=" + URLEncoder.encode(nightt, "UTF-8") + "&" +
                            URLEncoder.encode("patient_email", "UTF-8") + "=" + URLEncoder.encode(patientemaill, "UTF-8") + "&" +
                            URLEncoder.encode("patient_id", "UTF-8") + "=" + URLEncoder.encode(patientidd, "UTF-8") + "&" +
                            URLEncoder.encode("patient_ref_email", "UTF-8") + "=" + URLEncoder.encode(patientrefemaill, "UTF-8") + "&" +
                            URLEncoder.encode("role", "UTF-8") + "=" + URLEncoder.encode(typee, "UTF-8") + "&" +
                            URLEncoder.encode("days", "UTF-8") + "=" + URLEncoder.encode(number, "UTF-8") + "&" +
                            URLEncoder.encode("action", "UTF-8") + "=" + URLEncoder.encode("addMedicine", "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
                    IS.close();
                    //httpURLConnection.connect();
                    httpURLConnection.disconnect();
                    return "Registration Success...";
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("Registration Success...")) {
              /*  Intent intent= new Intent(MedicinesActivity.this,MedicinesActivity.class);
                startActivity(intent);*/

                SharedPreferences settings = getSharedPreferences("patientdata", MODE_PRIVATE);
                String patientemail = settings.getString("patient_email", null);

                MedicinesActivity.Fetcher fetcher = new MedicinesActivity.Fetcher();
                String method = "companyList";
                fetcher.execute(method, patientemail);


            } else {
                Toast.makeText(getApplicationContext(), "noregistered", Toast.LENGTH_SHORT).show();
            }
        }

      /*  public String getImeiName(Context context) {
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            return telephonyManager.getDeviceId();


        }*/
    }

    //////////////////custom adapter///////////////////////////////
    public class CustomAdapter extends BaseAdapter implements View.OnClickListener {

        /*********** Declare Used Variables *********/
        private Activity activity;
        private List<MedicinePojo> data;
        LayoutInflater inflater = null;
        // public Resources res;
        MedicinePojo tempValues = null;
        int i = 0;
        private View btnBooking;

        /*************  CustomAdapter Constructor *****************/
        public CustomAdapter(Activity a, List<MedicinePojo> d) {

            /********** Take passed values **********/
            activity = a;
            data = d;
            // res = resLocal;

            /***********  Layout inflator to call external xml activity_faculty_info () ***********/
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        /******** What is the size of Passed Arraylist Size ************/
        public int getCount() {

            if (data.size() <= 0)
                return 1;
            return data.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        @Override
        public void onClick(View view) {

        }

        /********* Create a holder Class to contain inflated xml file elements *********/
        class ViewHolder {

            public TextView Namet;
            public TextView mornt;
            public TextView aftrt;
            public TextView nghtt;


        }

        /****** Depends upon data size called for each row , Create each ListView row *****/
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public View getView(int position, View convertView, ViewGroup parent) {

            View vi = convertView;
            final MedicinesActivity.CustomAdapter.ViewHolder holder;

            if (convertView == null) {

                /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
                vi = inflater.inflate(R.layout.medicineslist, null);

                /****** View Holder Object to contain tabitem.xml file elements ******/
                holder = new MedicinesActivity.CustomAdapter.ViewHolder();
                holder.Namet = (TextView) vi.findViewById(R.id.namem);
                holder.mornt = (TextView) vi.findViewById(R.id.mornm);
                holder.aftrt = (TextView) vi.findViewById(R.id.aftrm);
                holder.nghtt = (TextView) vi.findViewById(R.id.nghtm);

                //holder.btnBooking=(Button) vi.findViewById(R.id.booking);
                /************  Set holder with LayoutInflater ************/
                vi.setTag(holder);
            } else
                holder = (MedicinesActivity.CustomAdapter.ViewHolder) vi.getTag();

            if (data.size() <= 0) {
                holder.Namet.setText("No Data");


            } else {
                /***** Get each Model object from Arraylist ********/
                tempValues = null;
                tempValues = (MedicinePojo) data.get(position);

                /************  Set Model values in Holder elements ***********/


                holder.Namet.setText(tempValues.getMedicine_name());
                holder.mornt.setText(tempValues.getMorning_time_medicine());
                holder.aftrt.setText(tempValues.getAfternoon_time_medicine());
                holder.nghtt.setText(tempValues.getNight_time_medicine());

            }
            return vi;
        }

    }


    //AsyncTask
    public class Fetcher extends AsyncTask<String, String, String> {

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
            String faculty_list_url = Helper.DOMAIN + "MedicineServlet";
            // String login_url = Constants.DOMAIN_URL+"LibraryManagementSystem/AdminServlet";
            String method = params[0];
            if (method.equals("companyList")) {

                String email = params[1];

                try {
                    URL url = new URL(faculty_list_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    //httpURLConnection.setDoInput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                    String data =
                            URLEncoder.encode("patient_email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                                    URLEncoder.encode("action", "UTF-8") + "=" + URLEncoder.encode("addMoreMedicines", "UTF-8");

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
                TypeReference<List<MedicinePojo>> mapType = new TypeReference<List<MedicinePojo>>() {
                };
                List<MedicinePojo> jsonToPersonList = objectMapper.readValue(result, mapType);

                //List<OwnerPojo> jsonToPersonList = new ObjectMapper().readValue(result, new TypeReference<List<OwnerPojo>>() { });
                Log.i("jsonToPersonList", "onPostExecute  jsonToPersonList=" + jsonToPersonList);
                // objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);

                MedicinesActivity.CustomAdapter customList = new MedicinesActivity.CustomAdapter(MedicinesActivity.this, (List) jsonToPersonList);

                list.setAdapter(customList);

               /* Intent intent= new Intent(MedicinesActivity.this,MedicinesActivity.class);
                startActivity(intent);*/

                //System.out.println(jsonToPersonList);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public class BackgroundTask2 extends AsyncTask<String, Void, String> {
        AlertDialog alertDialog;
        Context ctx;

        public BackgroundTask2(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(ctx).create();
            alertDialog.setTitle("Login Information....");
        }

        @Override
        protected String doInBackground(String... params) {
            // String reg_url = "http://192.168.1.113:8080/LibraryManagementSystem/RegistrationServlet";
            String reg_url = Helper.DOMAIN + "PatientServlet";

            String method = params[0];
            if (method.equals("email")) {
                //Set pretty printing of json
                //objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

                String namee = params[1];
                String medsname = params[2];


                try {
                    //String arrayToJson = objectMapper.writeValueAsString(videoMaster);
                    URL url = new URL(reg_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);

                    //httpURLConnection.setDoInput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(medsname, "UTF-8") + "&" +
                            URLEncoder.encode("patient_email", "UTF-8") + "=" + URLEncoder.encode(namee, "UTF-8") + "&" +

                            URLEncoder.encode("action", "UTF-8") + "=" + URLEncoder.encode("sendNotification", "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
                    IS.close();
                    //httpURLConnection.connect();
                    httpURLConnection.disconnect();
                    return "Registration Success...";
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("Registration Success...")) {
                Intent intent = new Intent(MedicinesActivity.this, CameraFragment.class);

                startActivity(intent);


            } else {
                Toast.makeText(getApplicationContext(), "noregistered", Toast.LENGTH_SHORT).show();
            }
        }

    }
}