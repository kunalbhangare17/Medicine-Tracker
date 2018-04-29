package com.example.abc.medicinetracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

public class RegistrationActivity extends AppCompatActivity {

    private Button create;
    private TextView signin;
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText phone_number;
    private EditText refname;
    private EditText refemail,address;
    private Spinner spinner;


    String[] role = {"Parent", "Relative", "Guide"};
    String spnr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        create = (Button) findViewById(R.id.sign_up_button);
        username = (EditText)findViewById(R.id.username);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        phone_number = (EditText)findViewById(R.id.phone_number);
        refname = (EditText)findViewById(R.id.refname);
        refemail=(EditText) findViewById(R.id.refemail);
        signin=(TextView) findViewById(R.id.txtview_login);
        spinner=(Spinner) findViewById(R.id.spinner);
        address = (EditText)findViewById(R.id.address);


        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, role);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter3);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 spnr=  adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(in);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String enteredUsername = username.getText().toString().trim();
                String enteredEmail = email.getText().toString().trim();
                String enteredPassword = password.getText().toString().trim();
                String enteredrefname = refname.getText().toString();
                String enteredPhoneNumber = phone_number.getText().toString();
                String enteredrefemail = refemail.getText().toString();
                String enteredrefadd = address.getText().toString();
                String enteredrole = spnr;

                if (enteredUsername.isEmpty() || enteredEmail.isEmpty() || enteredPassword.isEmpty() || enteredrefname.isEmpty()
                        || enteredPhoneNumber.isEmpty() || enteredrefemail.isEmpty() )
                {
                   Toast.makeText(getApplicationContext(),"ALL FIELD MUST BE FILLED",Toast.LENGTH_SHORT).show();
                }else
                {
                    String method = "register";
                    RegistrationActivity.BackgroundTask1 backgroundTask = new RegistrationActivity.BackgroundTask1(RegistrationActivity.this);
                    backgroundTask.execute(method, enteredUsername, enteredEmail, enteredPassword, enteredrefname, enteredPhoneNumber, enteredrefemail, enteredrole,enteredrefadd);
                }



            }
        });


    }

    public class BackgroundTask1 extends AsyncTask<String,Void,String> {
        AlertDialog alertDialog;
        Context ctx;
        public BackgroundTask1(Context ctx)
        {
            this.ctx =ctx;
        }
        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(ctx).create();
            alertDialog.setTitle("Login Information....");
        }

        @Override
        protected String doInBackground(String... params) {
            // String reg_url = "http://192.168.1.43:8003/MedicineTracker/PatientServlet";
            String reg_url = Helper.DOMAIN+"PatientServlet";

            String method = params[0];
            if (method.equals("register")) {
                //Set pretty printing of json
                //objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

                String owner_name = params[1];
                String email = params[2];
                String password = params[3];
                String refname = params[4];
                String phone = params[5];
                String refemail = params[6];
                String role = params[7];
                String address = params[8];

                try {
                    //String arrayToJson = objectMapper.writeValueAsString(videoMaster);
                    URL url = new URL(reg_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);

                    //httpURLConnection.setDoInput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("patient_name", "UTF-8") + "=" + URLEncoder.encode(owner_name, "UTF-8") + "&" +
                            URLEncoder.encode("patient_email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8")+ "&" +
                            URLEncoder.encode("patient_password", "UTF-8") + "=" + URLEncoder.encode(password,  "UTF-8")+ "&" +
                            URLEncoder.encode("patient_ref_name", "UTF-8") + "=" + URLEncoder.encode(refname, "UTF-8")+ "&" +
                            URLEncoder.encode("patient_contact_no", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8")+ "&" +
                            URLEncoder.encode("patient_ref_email", "UTF-8") + "=" + URLEncoder.encode(refemail, "UTF-8")+ "&" +
                            URLEncoder.encode("patient_reference", "UTF-8") + "=" + URLEncoder.encode(role, "UTF-8")+ "&" +
                            URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8")+ "&" +
                            URLEncoder.encode("action", "UTF-8") + "=" + URLEncoder.encode("patientRegister", "UTF-8");
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
            if(result.equals("Registration Success..."))
            {
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                Intent i=new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(i);

            }
            else
            {
                try {

                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }

        }

    }

}
