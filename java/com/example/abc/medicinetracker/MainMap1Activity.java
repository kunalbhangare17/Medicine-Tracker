package com.example.abc.medicinetracker;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abc.medicinetracker.entities.LocationMasterPojo;
import com.example.abc.medicinetracker.util.Helper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import org.json.JSONObject;

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
import java.util.Locale;




//ParkingPojo
public class MainMap1Activity extends AppCompatActivity
        implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMarkerClickListener,

        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,

        LocationListener, ResultCallback<Status> {
    String camp_name,bank_description,date,time,contact;

    List<LocationMasterPojo> mapPojos = new ArrayList<>();

    List mMarkers = new ArrayList();
    TextView textLat, textLong, textlatlong;
    Button start;
    LatLng p1 = null;
    LatLng p2 = null;
    private Marker marker;
    String resultLat, resultLon;
    AutoCompleteTextView autoDropLocation;
    LatLng latLng;
    private static final String TAG = null;
    private static final int REQ_PERMISSION = 1;
    private MapFragment mapFragment;
    private GoogleMap map;


    private GoogleApiClient googleApiClient;
    List<LocationMasterPojo> jsonToPersonList = null;
    private Location lastLocation;

    int PROXIMITY_RADIUS = 10000;
    double latitude, longitude;
    double end_latitude, end_longitude;
    float distance;
    String HttpURL = "";

    String lat, lon;
    String currentDateTimeString = "";
    LatLng latLng3;
    Button button;
    TextView textView;
    JSONObject jsonObject;
    String StringHolder = "";
    ProgressBar progressBar;
    String subLocation = "";

    // selectedItem;


    String available_slot;
    String Available_slot = "";
String parking_id;

    // selectedItem;

    List<HashMap<String, String>> dataglobal;
    String location;
    String subLocality;
    Address location1;
    AutoCompleteTextView acTextView;
    String subArea = "";
    String sub_area;
    MarkerOptions markerOptions;
    private ViewGroup infoWindow;
    private TextView infoTitle;
    private TextView infoSnippet;
    private Button infoButton;
    String message;
    String usermailid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map1);


        textLat = (TextView) findViewById(R.id.lat);
        textLong = (TextView) findViewById(R.id.lon);

        initGMaps();
        createGoogleApi();


        SharedPreferences settings =getSharedPreferences("CarData",MODE_PRIVATE);

        usermailid=settings.getString("email",null);


    }


    private LatLng getLocationFromAddress(Context applicationContext, String location) {
        Geocoder coder = new Geocoder(applicationContext);
        List<Address> address;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(location, 5);
            if (address == null) {
                return null;
            }
            location1 = address.get(0);
            location1.getLatitude();
            location1.getLongitude();


            // p1 = new LatLng(location1.getLatitude(), location1.getLongitude());
            resultLat = String.valueOf(location1.getLatitude());
            resultLon = String.valueOf(location1.getLongitude());

            Toast.makeText(getApplicationContext(), "Location Based Lat/Lon" + resultLat + resultLon, Toast.LENGTH_SHORT).show();


            GoogleMap map = null;
            // ... get a map.
            // Add a thin red line from London to New York.

            // Log.i("location", "onPostExecute  location=" + re);

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;


    }
/*
    Polyline line = map.addPolyline(new PolylineOptions()
            .add(new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude()), new LatLng(location1.getLatitude(), location1.getLongitude()))
            .width(5)
            .color(Color.RED));*/


    //To call Fragment
    private void initGMaps() {
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //Calls GoogleApi's
    private void createGoogleApi() {
        //  Log.d(TAG, "createGoogleApi()");
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

     /*google map methods*/

    @Override
    public void onMapClick(LatLng latLng) {
        Log.d(TAG, "onMapClick(" + latLng + ")");
        //markerForGeofence(latLng);
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.d(TAG, "onMarkerClickListener: " + marker.getPosition());
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Log.d(TAG, "onMapReady()");
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        map.setOnMapClickListener(this);
        map.setOnMarkerClickListener(this);
    }

    //////////// // Create a Location Marker

    private Marker locationMarker;

    private void markerLocation(LatLng latLng) {
        Log.i(TAG, "markerLocation(" + latLng + ")");
        //String title = "My Locatio";

        markerOptions  = new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation))
                .position(latLng).title("My Location");


        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            List<Address> listAddresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (listAddresses != null && listAddresses.size() > 0) {

//addressList != null && addressList.size() > 0
//
//
//
//      Here we are finding , whatever we want our marker to show when clicked



              /*  String state = listAddresses.get(0).getAdminArea();
                String country = listAddresses.get(0).getCountryName();*/
                subLocality = listAddresses.get(0).getAddressLine(0);

                Toast.makeText(getApplicationContext(), subLocality, Toast.LENGTH_SHORT).show();

                SharedPreferences sharedpreferences = getSharedPreferences("UserLogin", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedpreferences.edit();


                editor.putString("subLocality", subLocality);
                editor.putString("lattitude", lat);
                editor.putString("longitude", lon);

                editor.commit();


                //  markerOptions.title("" + latLng + "," + state + "," + state + "," + country);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (map != null) {
            marker=map.addMarker(markerOptions);
            LatLng mCustomerLatLng = new LatLng(latitude, longitude);
             markerOptions = new MarkerOptions();
            markerOptions.position(mCustomerLatLng);
            markerOptions.title("MyLocation");

            /*map.addMarker(markerOptions);
            float zoom = 10f;
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
            map.animateCamera(cameraUpdate);*/
        }
    }

    //////////////////// Create a marker for the geofence creation


    private Marker geoFenceMarker;

    private void markerForGeofence(LatLng latLng) {
        Log.i(TAG, "markerForGeofence(" + latLng + ")");
        String title = "GEOFENCE";

        end_latitude = latLng.latitude;
        end_longitude = latLng.longitude;

        textLat.setText("" + end_latitude);
        textLong.setText("" + end_longitude);


        // Define marker options
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
                .title(title);

        if (map != null) {
            // Remove last geoFenceMarker
            if (geoFenceMarker != null)
                geoFenceMarker.remove();

            geoFenceMarker = map.addMarker(markerOptions);
            float zoom = 17f;
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
            map.animateCamera(cameraUpdate);


        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Call GoogleApiClient connection when starting the Activity

        googleApiClient.connect();

    }

    @Override
    protected void onStop() {
        super.onStop();

        // Disconnect GoogleApiClient when stopping Activity
        googleApiClient.disconnect();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        // Log.i(TAG, "onConnected()");

        getLastKnownLocation();
    }

    private void getLastKnownLocation() {

        // Log.d(TAG, "getLastKnownLocation()");
        if (checkPermission()) {
            lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (lastLocation != null) {
                Log.i(TAG, "LasKnown location. " +
                        "Long: " + lastLocation.getLongitude() +
                        " | Lat: " + lastLocation.getLatitude());
                //writeLastLocation();
                startLocationUpdates();

            } else {
                Log.w(TAG, "No location retrieved yet");
                startLocationUpdates();
            }
        } else askPermission();
    }

    private LocationRequest locationRequest;
    // Defined in mili seconds.
    // This number in extremely low, and should be used only for debug
    // private final int UPDATE_INTERVAL =  3 * 60 * 1000; // 3 minutes
    //private final int FASTEST_INTERVAL = 30 * 1000;  // 30 secs

    private final int UPDATE_INTERVAL = 10 * 1000; // 10 secs
    private final int FASTEST_INTERVAL = 5000;  // 5 secs

    private void startLocationUpdates() {
        Log.i(TAG, "startLocationUpdates()");
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL);

        if (checkPermission())
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        // LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    /*location on the text views*/
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void writeActualLocation(Location location) {


        //showNotification("Stayed", " you resided here" );

        latitude = location.getLatitude();
        longitude = location.getLongitude();
        textLat.setText("lat :" + (double) latitude);
        textLong.setText("long :" + (double) longitude);


        lat = String.valueOf(latitude);
        lon = String.valueOf(longitude);

        /*lat = String.valueOf(location.getLatitude());
        lon = String.valueOf(location.getLongitude());*/
        // Toast.makeText(getApplicationContext(), "nayana"+nayana+"Sonone"+p1, Toast.LENGTH_SHORT).show();
        markerLocation(new LatLng(location.getLatitude(), location.getLongitude()));



/*
        String notify="notification";

        if(notify.equals("notification"))

        {
          */

/*
        }


else {*/

        MainMap1Activity.Fetcher fetcher = new MainMap1Activity.Fetcher();
        String method = "driverList";
        fetcher.execute(method, lat, lon,usermailid);




        //   }


        //      addMarkersToMap();


    }


    // Check for permission to access Location
    private boolean checkPermission() {
        Log.d(TAG, "checkPermission()");
        // Ask for permission if it wasn't granted yet
        return (android.support.v4.content.ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED);
    }

    // Asks for permission
    private void askPermission() {
        Log.d(TAG, "askPermission()");

        android.support.v4.app.ActivityCompat.requestPermissions(
                this,

                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                REQ_PERMISSION
        );
    }

    // Verify user's response of the permission requested
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult()");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQ_PERMISSION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted
                    getLastKnownLocation();

                } else {
                    // Permission denied
                    permissionsDenied();
                }
                break;
            }
        }
    }

    // App cannot work without the permissions
    private void permissionsDenied() {
        Log.w(TAG, "permissionsDenied()");
    }


    @Override
    public void onConnectionSuspended(int i) {
        //Log.w(TAG, "onConnectionSuspended()");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        //   Log.w(TAG, "onConnectionFailed()");
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged [" + location + "]");
        lastLocation = location;
        writeActualLocation(location);


    }


    //////////////////////////*START GEOFENCE*/
    private void startGeofence() {
        Log.i(TAG, "startGeofence()");
        if (geoFenceMarker != null) {
            Geofence geofence = createGeofence(geoFenceMarker.getPosition(), GEOFENCE_RADIUS);
            GeofencingRequest geofenceRequest = createGeofenceRequest(geofence);
            // addGeofence(geofenceRequest);
        } else {
            Log.e(TAG, "Geofence marker is null");
        }
        //Toast.makeText(getApplicationContext(),"geofence started",Toast.LENGTH_SHORT).show();
    }

    //////////////////*creating geofence*/

    private static final long GEO_DURATION = 60 * 60 * 1000;
    private static final String GEOFENCE_REQ_ID = "My Geofence";
    private static final float GEOFENCE_RADIUS = 100.0f; // in meters

    private Geofence createGeofence(LatLng latLng, float radius) {
        Log.d(TAG, "createGeofence");
        //Toast.makeText(getApplicationContext(),"geofence created",Toast.LENGTH_SHORT).show();
        return new Geofence.Builder()
                .setRequestId(GEOFENCE_REQ_ID)
                .setCircularRegion(latLng.latitude, latLng.longitude, radius)
                .setExpirationDuration(GEO_DURATION)
                .setLoiteringDelay(400)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER
                        | Geofence.GEOFENCE_TRANSITION_EXIT | Geofence.GEOFENCE_TRANSITION_DWELL)
                .build();

    }


      /*creating geofence request*/

    private GeofencingRequest createGeofenceRequest(Geofence geofence) {
        Log.d(TAG, "createGeofenceRequest");

        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofence(geofence);
        return builder.build();

    }


            /*creating pending intent*/

    private PendingIntent geoFencePendingIntent;
    private final int GEOFENCE_REQ_CODE = 0;


/*    private PendingIntent createGeofencePendingIntent() {
        Log.d(TAG, "createGeofencePendingIntent");
        if (geoFencePendingIntent != null)
            return geoFencePendingIntent;

        Intent intent = new Intent(this, GeofenceTrasitionService.class);
        return PendingIntent.getService(
                this, GEOFENCE_REQ_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }*/

    // Add the created GeofenceRequest to the device's monitoring list

   /* private void addGeofence(GeofencingRequest request) {
        Log.d(TAG, "addGeofence");
        if (checkPermission()) {
            LocationServices.GeofencingApi.addGeofences(
                    googleApiClient,
                    request,
                    createGeofencePendingIntent()
            ).setResultCallback(this);
            // Toast.makeText(getApplicationContext(),"geofence added",Toast.LENGTH_LONG).show();
        }
    }*/

    // Draw Geofence circle on GoogleMap
    private Circle geoFenceLimits;

    private void drawGeofence() {
        Log.d(TAG, "drawGeofence()");

        if (geoFenceLimits != null)
            geoFenceLimits.remove();

        CircleOptions circleOptions = new CircleOptions()
                .center(geoFenceMarker.getPosition())
                .strokeColor(Color.RED)
                .fillColor(Color.argb(100, 150, 150, 150))
                .radius(GEOFENCE_RADIUS);
        geoFenceLimits = map.addCircle(circleOptions);
    }


    public void onResult(@NonNull Status status) {
        Log.i(TAG, "onResult: " + status);
        if (status.isSuccess()) {
            drawGeofence();
        } else {
            // inform about fail
            Toast.makeText(getApplicationContext(), "failed to create geofence", Toast.LENGTH_LONG).show();
        }


    }

/*



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @SuppressLint("WrongConstant")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);


            SharedPreferences sharedpreferences = getSharedPreferences("CarData",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.commit();
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/


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
            String faculty_list_url = Helper.DOMAIN +"MedicineServlet";
            // String login_url = Constants.DOMAIN_URL+"LibraryManagementSystem/AdminServlet";
            String method = params[0];
            if (method.equals("driverList")) {
                String latitude = params[1];
                String longitude = params[2];


                try {
                    URL url = new URL(faculty_list_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    //httpURLConnection.setDoInput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                    String data =

                            URLEncoder.encode("latitude", "UTF-8") + "=" + URLEncoder.encode(latitude, "UTF-8") + "&" +
                                    URLEncoder.encode("longitude", "UTF-8") + "=" + URLEncoder.encode(longitude, "UTF-8") + "&" +

                                    URLEncoder.encode("action", "UTF-8") + "=" + URLEncoder.encode("getParkingLocation", "UTF-8");

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
            // mResult = result;


            Log.i("jsonToPersonList", "onPostExecute  jsonToPersonList=" + result);
            ObjectMapper objectMapper = new ObjectMapper();
            final TypeReference<List<LocationMasterPojo>> mapType = new TypeReference<List<LocationMasterPojo>>() {
            };

            try {
                jsonToPersonList = objectMapper.readValue(result, mapType);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < jsonToPersonList.size(); i++) {

                latLng = new LatLng(jsonToPersonList.get(i).getLocation_latitude(), jsonToPersonList.get(i).getLocation_longitude());

                if (i<=2)
                {
                    map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).position(latLng).title(jsonToPersonList.get(i).getBlood_bank_name()));





                    // .icon(BitmapDescriptorFactory.fromResource(R.drawable.mainimageasd)).position(latLng).title(jsonToPersonList.get(i).getBlood_bank_name()));

                }else
                {
                    map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).position(latLng).title(jsonToPersonList.get(i).getBlood_bank_name()));


                    //map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).position(latLng).title(jsonToPersonList.get(i).getBlood_bank_name()));

                }

                float zoom = 15f;
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
                map.animateCamera(cameraUpdate);

                camp_name=jsonToPersonList.get(i).getBlood_bank_name();



                // subLocation=jsonToPersonList.get(i).getBlood_bank_name();
                bank_description=jsonToPersonList.get(i).getBank_description();
                date=jsonToPersonList.get(i).getDate();
                time=  jsonToPersonList.get(i).getTime();
                location=  jsonToPersonList.get(i).getLocation();

                // location2=jsonToPersonList.get(i).getLocation();
                contact=jsonToPersonList.get(i).getContact_details();



                CustomInfoWindowAdapter adapter = new CustomInfoWindowAdapter(MainMap1Activity.this);
                map.setInfoWindowAdapter(adapter);

                //  String title = "This is Title";


            }


            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {


                    marker.showInfoWindow();


                    // marker click here
                   /* Toast.makeText(MainMapActivity.this, ""+marker.getSnippet(), Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(MainMapActivity.this,UserLoginActivity.class);
                    startActivity(intent);
*/


                    //  LatLng latLng3 = new LatLng(driverLat,driverLon);


                    //  map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.autoneargreen)).position(latLng3).title(jsonToPersonList.get(0).getDriver_name()));


                    //  map.addMarker(new MarkerOptions().position(latLng3).title(title).snippet(subTitle)).showInfoWindow();


                    return false;


                }
            });


            map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                @Override
                public void onInfoWindowClick(Marker marker) {

                    //  addNotification();

                   /* T
                    latLng = marker.getPosition();
*/
                    // Toast.makeText(getApplicationContext(),marker.getTitle().toString(),Toast.LENGTH_SHORT).show();


                    Intent i = new Intent(MainMap1Activity.this, ViewActivity.class);

                    // Intent i=new Intent(MainMapActivity.this,ParkingListActivity.class);

                    // String ti=marker.getTitle();


                    // String parkingid = marker.get("parking_id").toString();

                    //   location=marker.getTitle().toString();


                    subLocation=marker.getTitle().toString();
                   /* subArea=marker.getTitle().toString();
                            contact=marker.getTitle().toString();*/






                    SharedPreferences settings = getSharedPreferences("CarData", MODE_PRIVATE);
                    SharedPreferences.Editor editor = settings.edit();

                    editor.putString("camp_name", ""+camp_name);
                    editor.putString("bank_description",""+bank_description);
                    editor.putString("location",""+location);

                    editor.putString("date",""+date);
                    editor.putString("time",""+time);

                    editor.putString("conatct",""+contact);




                    editor.commit();


                    startActivity(i);






                }

            });


            Log.i("jsonToPersonList", "onPostExecute2233  jsonToPersonList=" + jsonToPersonList.size());


        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent=new Intent(getApplicationContext(),MainMap1Activity.class);
        startActivity(intent);
    }
}