package com.example.abc.medicinetracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.abc.medicinetracker.entities.LocationMasterPojo;
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
import java.util.List;



public class ViewActivity extends AppCompatActivity {


    TextView camp_name,bank_description,date,time,conatct,area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);




area=(TextView)findViewById(R.id.tv_name) ;
        camp_name=(TextView)findViewById(R.id.name);
        bank_description=(TextView)findViewById(R.id.sub);
        conatct=(TextView)findViewById(R.id.sub1);

        date=(TextView)findViewById(R.id.sub2);
        time=(TextView)findViewById(R.id.sub3);
       // date=(TextView)findViewById(R.id.sub3);










        SharedPreferences sharedPreferences = getSharedPreferences("CarData", Context.MODE_PRIVATE);

        String location = sharedPreferences.getString("camp_name","");
        camp_name.setText(location);

        String location1 = sharedPreferences.getString("location","");
        area.setText(location1);
        String subLocation = sharedPreferences.getString("bank_description","");
        bank_description.setText(subLocation);
        String date1 = sharedPreferences.getString("date","");
        date.setText(date1);

        String time1 = sharedPreferences.getString("time","");
        time.setText(time1);

        String contact1 = sharedPreferences.getString("contact","");
        conatct.setText(contact1);




        ViewActivity.Fetcher fetcher = new ViewActivity.Fetcher();
        String method = "driverList";
        fetcher.execute(method,location1,location);

    }

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
            String faculty_list_url = Helper.DOMAIN+"AdminServlet";
            // String login_url = Constants.DOMAIN_URL+"LibraryManagementSystem/AdminServlet";
            String method = params[0];
            if (method.equals("driverList")) {
String location=params[1];
String blood_bank_name=params[2];

                try {
                    URL url = new URL(faculty_list_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    //httpURLConnection.setDoInput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                    String data =
                            URLEncoder.encode("location", "UTF-8") + "=" + URLEncoder.encode(location, "UTF-8") + "&" +

                                    URLEncoder.encode("blood_bank_name", "UTF-8") + "=" + URLEncoder.encode(blood_bank_name, "UTF-8") + "&" +

                                    URLEncoder.encode("action", "UTF-8") + "=" + URLEncoder.encode("getBloodBank", "UTF-8");

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
                TypeReference<List<LocationMasterPojo>> mapType = new TypeReference<List<LocationMasterPojo>>() {
                };
                List<LocationMasterPojo> jsonToPersonList = objectMapper.readValue(result, mapType);

                //List<OwnerPojo> jsonToPersonList = new ObjectMapper().readValue(result, new TypeReference<List<OwnerPojo>>() { });
                Log.i("jsonToPersonList","onPostExecute  jsonToPersonList="+jsonToPersonList);
                // objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);
/*
                Purchase1Activity.CustomAdapter customList=new Purchase1Activity.CustomAdapter(Purchase1Activity.this, (List)jsonToPersonList);

                listView.setAdapter(customList);*/


for(int i=0;i<jsonToPersonList.size();i++) {

    //TextView camp_name,bank_description,date,time,conatct;

    camp_name.setText(jsonToPersonList.get(i).getBlood_bank_name());
    bank_description.setText(jsonToPersonList.get(i).getBank_description());
    date.setText(jsonToPersonList.get(i).getDate());
            time.setText(jsonToPersonList.get(i).getTime());
    conatct.setText(jsonToPersonList.get(i).getContact_details());
    area.setText(jsonToPersonList.get(i).getLocation());
}



                //System.out.println(jsonToPersonList);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }





   /* ////////////////logout//////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.addtocart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.addToCart) {
            Intent intent = new Intent(ViewActivity.this, HomeActivity.class);
           *//* SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();*//*
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

*/

    //////////////backpressed///////////////
  /*  @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent=new Intent(getApplicationContext(),MainMapActivity.class);
        startActivity(intent);
    }



*/






}
