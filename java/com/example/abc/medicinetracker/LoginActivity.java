package com.example.abc.medicinetracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.abc.medicinetracker.network.GsonRequest;
import com.example.abc.medicinetracker.network.VolleySingleton;
import com.example.abc.medicinetracker.util.Helper;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    private Button login;
    private EditText emailInput;
    private EditText passwordInput;
    private TextView signup;

    String patient_ref_email="",patient_id="",patient_email1="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button)findViewById(R.id.sign_in_button);
      /*  signInformation = (TextView)findViewById(R.id.sign_in_notice);
        signInformation.setText(Helper.NEW_ACCOUNT);
*/
        emailInput = (EditText)findViewById(R.id.email);
        signup = (TextView) findViewById(R.id.sign_up);
        passwordInput = (EditText)findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                PatientPojo patient = new PatientPojo();

                patient.setPatient_email(email);
                patient.setPatient_password(password);

                authenticateUserInRemoteServer( email,password);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in=new Intent(getApplicationContext(),RegistrationActivity.class);
                startActivity(in );

            }
        });

    }

    private void authenticateUserInRemoteServer( String email, String password){
        Map<String, String> params = new HashMap<String,String>();
        params.put("patient_email", email);
        params.put("patient_password", password);


        GsonRequest<PatientPojo> serverRequest = new GsonRequest<PatientPojo>(
                Request.Method.POST,
                Helper.CLOGIN,
                PatientPojo.class,
                params,
                createRequestSuccessListener(),
                createRequestErrorListener());



        VolleySingleton.getInstance(LoginActivity.this).addToRequestQueue(serverRequest);
    }

    private Response.Listener<PatientPojo> createRequestSuccessListener() {
        return new Response.Listener<PatientPojo>() {
            @Override
            public void onResponse(PatientPojo response) {
                try {
                    // Log.d(TAG, "Json Response " + response.getLoggedIn());
                  //  String invalid=response.getLoggedIn();


                    if(response.getLoggedIn().equals("1")){
                        //save login data to a shared preference
                        // navigate to restaurant home


                        Toast.makeText(getApplicationContext(),"welcome",Toast.LENGTH_LONG).show();
                      /*  Intent loginIntent = new Intent(LoginActivity.this, CameraActivity.class);
                        startActivity(loginIntent);*/



                        Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(loginIntent);

                        SharedPreferences settings =getSharedPreferences("patientdata",MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("patient_id", ""+response.getPatient_id(patient_id));
                        editor.putString("patient_email", ""+response.getPatient_email());
                        editor.putString("patient_ref_email", ""+response.getPatient_ref_email(patient_ref_email));

                        editor.putString("enteredLocation", ""+response.getAddress());


                        editor.commit();

                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,"Invalid User", Toast.LENGTH_LONG).show();
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
}
