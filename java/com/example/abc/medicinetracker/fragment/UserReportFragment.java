package com.example.abc.medicinetracker.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.abc.medicinetracker.LoginActivity;
import com.example.abc.medicinetracker.R;
import com.example.abc.medicinetracker.RegistrationActivity;
import com.example.abc.medicinetracker.util.Helper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by developer on 19/4/18.
 */

public class UserReportFragment  extends Fragment {
EditText syst,dai,gloco,aic,chrostol,bmi;

Button submit;
    public UserReportFragment() {
        // Required empty public constructor
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // getActivity().setTitle("Nearby Medical Shop");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_report1, container, false);

syst=(EditText)view.findViewById(R.id.sys);

        dai=(EditText)view.findViewById(R.id.dia);
        submit=(Button)view.findViewById(R.id.submit);


        gloco=(EditText)view.findViewById(R.id.gluco);

        aic=(EditText)view.findViewById(R.id.aic);
        chrostol=(EditText)view.findViewById(R.id.chro);

        bmi=(EditText)view.findViewById(R.id.bmi);

        ImageView imageView =(ImageView)view.findViewById(R.id.start);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DayListActivity rm1 = new DayListActivity();

                getActivity().  getSupportFragmentManager().beginTransaction().replace(R.id.container, rm1).commit();










            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
               String  formattedDate = df.format(c.getTime());

                String enteredUsername = syst.getText().toString().trim();
                String enteredEmail = dai.getText().toString().trim();
                String enteredPassword = gloco.getText().toString().trim();
                String enteredrefname = aic.getText().toString();
                String enteredPhoneNumber = chrostol.getText().toString();
                String enteredrefemail = bmi.getText().toString();

                SharedPreferences settings =getActivity().getSharedPreferences("patientdata",MODE_PRIVATE);
                String patient_email = settings.getString("patient_email",null);


                    String method = "register";
                    UserReportFragment.BackgroundTask1 backgroundTask = new UserReportFragment.BackgroundTask1(getActivity());
                    backgroundTask.execute(method, enteredUsername, enteredEmail, enteredPassword, enteredrefname, enteredPhoneNumber, enteredrefemail,formattedDate,patient_email);



            }
        });

        return  view;


    }

    public class BackgroundTask1 extends AsyncTask<String,Void,String> {
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
            String reg_url = Helper.DOMAIN + "PatientServlet";

            String method = params[0];
            if (method.equals("register")) {
                //Set pretty printing of json
                //objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

                String systolic = params[1];


                String diastolic = params[2];

                String glucose = params[3];
                String aic = params[4];
                String total_cholestrol = params[5];
                String bmi = params[6];
                String date = params[7];
                String user_email = params[8];


                try {
                    //String arrayToJson = objectMapper.writeValueAsString(videoMaster);
                    URL url = new URL(reg_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);

                    //httpURLConnection.setDoInput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("systolic", "UTF-8") + "=" + URLEncoder.encode(systolic, "UTF-8") + "&" +
                            URLEncoder.encode("diastolic", "UTF-8") + "=" + URLEncoder.encode(diastolic, "UTF-8") + "&" +
                            URLEncoder.encode("glucose", "UTF-8") + "=" + URLEncoder.encode(glucose, "UTF-8") + "&" +
                            URLEncoder.encode("aic", "UTF-8") + "=" + URLEncoder.encode(aic, "UTF-8") + "&" +
                            URLEncoder.encode("total_cholestrol", "UTF-8") + "=" + URLEncoder.encode(total_cholestrol, "UTF-8") + "&" +
                            URLEncoder.encode("bmi", "UTF-8") + "=" + URLEncoder.encode(bmi, "UTF-8") + "&" +
                            URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8") + "&" +
                            URLEncoder.encode("user_email", "UTF-8") + "=" + URLEncoder.encode(user_email, "UTF-8") + "&" +
                            URLEncoder.encode("action", "UTF-8") + "=" + URLEncoder.encode("addPatientReport", "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
                    IS.close();
                    //httpURLConnection.connect();
                    httpURLConnection.disconnect();
                    return "Data Submitted Successfully...";
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
            if (result.equals("Data Submitted Successfully...")) {
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();


            } else {
                try {



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    }