package com.example.abc.medicinetracker.util;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {


   // public static final String DOMAIN= "http://5.189.132.169/MedicineTracker/";

    public static final String DOMAIN = "http://192.168.43.25:8002/MedicineTracker/";

    public static final String CLOGIN = DOMAIN + "PatientServlet?action=patientLogin";

    public static final String level0_id = "level0_id";
    public static final String level1_id = "level1_id";
    public static final String level1_name = "level1_name";
    public static final String PATH_TO_LIST = DOMAIN + "SearchServlet?action=getmedicine";
    public static final String level_1_vitamin_id = "level_1_vitamin_id";
    public static final String time = "slot_time";


    public static final String PATH_TO_HOSPITAL_HOME = DOMAIN + "HospitalServlet?action=getHospitalById&hospital_id=5";

    public static final String PATH_TO_LEVEL2_MEDICINE = DOMAIN + "SearchServlet?action=getVitamin";


    public static final String PATH_TO_SERVER_LOGIN = DOMAIN + "RegistrationServlet?action=userlogin";
    public static final String PATH_TO_LEVEL1_SYMTOMS = DOMAIN + "SearchServlet?action=getLevel0";
    public static final String PATH_TO_LEVEL2_SYMTOMS = DOMAIN + "SearchServlet?action=getLevel1";
    public static final String PATH_TO_Diseases = DOMAIN + "SearchServlet?action=getDiseases";
    public static final String PATH_TO_LEVEL2_VITAMIN = DOMAIN + "SearchServlet?action=getVitaminFoods";


    public static final String PATH_TO_ALL_VACCINATION = DOMAIN + "SearchServlet?action=getAllDiseases";

    public static final String PATH_TO_ALL_VACCINATION_INFO = DOMAIN + "SearchServlet?action=getPrevention";


    public static void displayErrorMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static final String RESTAURANT = "RESTAURANT";
    public static final String SHARED_PREF = "SHARED_PREFERENCE";
    public static final String USERNAME = "USERNAME";
    public static final String USER_DATA = "USER_DATA";

    public static final String ADDRESS = "ADDRESS";
    public static final String CART = "CART";
    public static final String CLIENT_ID = "ATaaBOrUq8z_sna8gH9XZeXtzvc_2ATLC5vpOn1ckSoNDxLqHflUdTubODa8lEsB4jXyhIjqbnS46Qvo";
    public static final String CREDIT_CARD = "CREDIT_CARD";
    public static final String DELIVERY_ADDRESS = "DELIVERY_ADDRESS";
    public static final String EMAIL = "EMAIL";
    public static final String disease_id = "disease_id";
    public static final String user_id = "id";
    public static final int MINIMUM_LENGTH = 5;
    public static final int MY_SOCKET_TIMEOUT_MS = 5000;
    public static final String NAME = "NAME";
    public static final String NEW_ACCOUNT = "Don't have an account yet?";
    public static final String PASSWORD = "PASSWORD";
    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    public static final String symptom_name = "symptom_name";
    public static final String doctor_id = "doctor_id";

    public static boolean isValidEmail(String email) {
        return email.contains("@");
    }
}
