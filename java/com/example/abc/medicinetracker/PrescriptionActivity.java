package com.example.abc.medicinetracker;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.abc.medicinetracker.util.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;



public class PrescriptionActivity extends AppCompatActivity {

    private Button submit;
    private EditText name;

    private EditText meds1;
    private EditText meds2;
    private EditText meds3;
    private EditText meds4;
    private EditText meds5;
    private EditText syrup1;
    private EditText syrup2;

    private Spinner spinner11,spinner12,spinner13;
    private Spinner spinner21,spinner22,spinner23;
    private Spinner spinner31,spinner32,spinner33;
    private Spinner spinner41,spinner42,spinner43;
    private Spinner spinner51,spinner52,spinner53;

    private Spinner spinner61,spinner62,spinner63;
    private Spinner spinner71,spinner72,spinner73;

    String[] time ={"Yes","No"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);
        setTitle("Prescription");

        meds1 = (EditText)findViewById(R.id.meds1);
        meds2 = (EditText)findViewById(R.id.meds2);
        meds3 = (EditText)findViewById(R.id.meds3);
        meds4 = (EditText)findViewById(R.id.meds4);
        meds5 = (EditText)findViewById(R.id.meds5);

        syrup1 = (EditText)findViewById(R.id.syrup1);
        syrup2 = (EditText)findViewById(R.id.syrup2);

        spinner11=(Spinner) findViewById(R.id.meds1morn);
        spinner12=(Spinner) findViewById(R.id.meds1aftr);
        spinner13=(Spinner) findViewById(R.id.meds1nght);

        spinner21=(Spinner) findViewById(R.id.meds2morn);
        spinner22=(Spinner) findViewById(R.id.meds2aftr);
        spinner23=(Spinner) findViewById(R.id.meds2nght);

        spinner31=(Spinner) findViewById(R.id.meds3morn);
        spinner32=(Spinner) findViewById(R.id.meds3aftr);
        spinner33=(Spinner) findViewById(R.id.meds3nght);

        spinner41=(Spinner) findViewById(R.id.meds4morn);
        spinner42=(Spinner) findViewById(R.id.meds4aftr);
        spinner43=(Spinner) findViewById(R.id.meds4nght);

        spinner51=(Spinner) findViewById(R.id.meds5morn);
        spinner52=(Spinner) findViewById(R.id.meds5aftr);
        spinner53=(Spinner) findViewById(R.id.meds5nght);

        spinner61=(Spinner) findViewById(R.id.syrup1morn);
        spinner62=(Spinner) findViewById(R.id.syrup1aftr);
        spinner63=(Spinner) findViewById(R.id.syrup1nght);

        spinner71=(Spinner) findViewById(R.id.syrup2morn);
        spinner72=(Spinner) findViewById(R.id.syrup2aftr);
        spinner73=(Spinner) findViewById(R.id.syrup2nght);


        submit=(Button)findViewById(R.id.submit);

        ArrayAdapter<String> dataadapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,time);
        dataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner11.setAdapter(dataadapter);
        spinner12.setAdapter(dataadapter);
        spinner13.setAdapter(dataadapter);

        spinner21.setAdapter(dataadapter);
        spinner22.setAdapter(dataadapter);
        spinner23.setAdapter(dataadapter);

        spinner31.setAdapter(dataadapter);
        spinner32.setAdapter(dataadapter);
        spinner33.setAdapter(dataadapter);

        spinner41.setAdapter(dataadapter);
        spinner42.setAdapter(dataadapter);
        spinner43.setAdapter(dataadapter);

        spinner51.setAdapter(dataadapter);
        spinner52.setAdapter(dataadapter);
        spinner53.setAdapter(dataadapter);

        spinner61.setAdapter(dataadapter);
        spinner62.setAdapter(dataadapter);
        spinner63.setAdapter(dataadapter);

        spinner71.setAdapter(dataadapter);
        spinner72.setAdapter(dataadapter);
        spinner73.setAdapter(dataadapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emeds1 =meds1.getText().toString();
                String emeds2 = meds2.getText().toString();
                String emeds3 = meds3.getText().toString();
                String emeds4 = meds4.getText().toString();
                String emeds5 = meds5.getText().toString();
                String esyrup1 = syrup1.getText().toString();
                String esyrup2 = syrup2.getText().toString();

                String sp11=spinner11.getSelectedItem().toString();
                String sp12=spinner12.getSelectedItem().toString();
                String sp13=spinner13.getSelectedItem().toString();

                String sp21=spinner21.getSelectedItem().toString();
                String sp22=spinner22.getSelectedItem().toString();
                String sp23=spinner23.getSelectedItem().toString();

                String sp31=spinner31.getSelectedItem().toString();
                String sp32=spinner32.getSelectedItem().toString();
                String sp33=spinner33.getSelectedItem().toString();

                String sp41=spinner41.getSelectedItem().toString();
                String sp42=spinner42.getSelectedItem().toString();
                String sp43=spinner43.getSelectedItem().toString();

                String sp51=spinner51.getSelectedItem().toString();
                String sp52=spinner52.getSelectedItem().toString();
                String sp53=spinner53.getSelectedItem().toString();

                String sp61=spinner61.getSelectedItem().toString();
                String sp62=spinner62.getSelectedItem().toString();
                String sp63=spinner63.getSelectedItem().toString();

                String sp71=spinner71.getSelectedItem().toString();
                String sp72=spinner72.getSelectedItem().toString();
                String sp73=spinner73.getSelectedItem().toString();

                String method = "submit";
                PrescriptionActivity.BackgroundTask1 backgroundTask = new PrescriptionActivity.BackgroundTask1(PrescriptionActivity.this);
                backgroundTask.execute(method, emeds1, emeds2, emeds3, emeds4, emeds5, esyrup1, esyrup2);

            }
        });


    }


    ////////////////web services to submit////////////

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
            // String reg_url = "http://192.168.1.113:8080/LibraryManagementSystem/RegistrationServlet";
            String reg_url = Helper.DOMAIN+"MedicineServlet";

            String method = params[0];
            if (method.equals("submit")) {
                //Set pretty printing of json
                //objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

                String smeds1 = params[1];
                String smeds2 = params[2];
                String smeds3 = params[3];
                String smeds4 = params[4];
                String smeds5 = params[5];
                String syrup1 = params[6];
                String syrup2 = params[7];

                JSONObject user = new JSONObject();
                JSONObject syrups=new JSONObject();

                try {
                    user.put("meds1",smeds1);
                    user.put("meds2",smeds2);
                    user.put("meds3",smeds3);
                    user.put("meds4",smeds4);
                    user.put("meds5",smeds5);

                    syrups.put("syrup1",syrup1);
                    syrups.put("syrup2",syrup2);


                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                JSONArray notebookUsers = new JSONArray();
                JSONArray notebookUsers2 = new JSONArray();

                notebookUsers.put(user);
                notebookUsers2.put(syrups);


                try {
                    //String arrayToJson = objectMapper.writeValueAsString(videoMaster);
                    URL url = new URL(reg_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);

                    //httpURLConnection.setDoInput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data =  URLEncoder.encode("medicines", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(notebookUsers), "UTF-8")+ "&" +
                            URLEncoder.encode("syrup", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(notebookUsers2), "UTF-8")+ "&" +

                           /* URLEncoder.encode("patient_password", "UTF-8") + "=" + URLEncoder.encode(password,  "UTF-8")+ "&" +
                            URLEncoder.encode("patient_ref_name", "UTF-8") + "=" + URLEncoder.encode(refname, "UTF-8")+ "&" +
                            URLEncoder.encode("patient_contact_no", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8")+ "&" +
                            URLEncoder.encode("patient_ref_email", "UTF-8") + "=" + URLEncoder.encode(refemail, "UTF-8")+ "&" +
                            URLEncoder.encode("patient_reference", "UTF-8") + "=" + URLEncoder.encode(role, "UTF-8")+ "&" +*/

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
           /* if(result.equals("Registration Success..."))
            {
                Toast.makeText(PrescriptionActivity.this, result, Toast.LENGTH_LONG).show();
                Intent i=new Intent(PrescriptionActivity.this,PrescriptionActivity.class);
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
*/
        }

      /*  public String getImeiName(Context context) {
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            return telephonyManager.getDeviceId();


        }*/
    }







}
