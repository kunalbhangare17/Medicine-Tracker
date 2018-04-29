package com.example.abc.medicinetracker;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
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
import android.support.v4.app.NotificationCompat;
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
public class MainMapActivity extends AppCompatActivity
        implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMarkerClickListener,

        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,

        LocationListener, ResultCallback<Status> {

    private static final String GOOGLE_API_KEY = "AIzaSyDw1UQMuXEpU7MUp4RVqekOoOKTsLvgNec";
    List mMarkers = new ArrayList();
    TextView textLat, textLong, textlatlong;
    Button start;
    LatLng p1 = null;
    LatLng p2 = null;

String resultLat,resultLon;
    AutoCompleteTextView autoDropLocation;
    LatLng latLng;
    private static final String TAG = null;
    private static final int REQ_PERMISSION = 1;
    private MapFragment mapFragment;
    private GoogleMap map;

    private GoogleApiClient googleApiClient;

    private Location lastLocation;

    int PROXIMITY_RADIUS = 10000;
    double latitude, longitude;
    double end_latitude, end_longitude;
    float distance;
    String HttpURL = "";

    String lat, lon;
    String currentDateTimeString = "";
    LatLng latLng3;
    //HttpResponse httpResponse;
    Button button;
    TextView textView;
    JSONObject jsonObject;
    String StringHolder = "";
    ProgressBar progressBar;
    String subLocation="";

    // selectedItem;

    List<HashMap<String, String>> dataglobal;
    String location;
    String subLocality;
    Address location1;
    AutoCompleteTextView acTextView;
String subArea="";
String sub_area;

    private ViewGroup infoWindow;
    private TextView infoTitle;
    private TextView infoSnippet;
    private Button infoButton;
    String message;
    String usermailid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);





        textLat = (TextView) findViewById(R.id.lat);
        textLong = (TextView) findViewById(R.id.lon);

        initGMaps();
        createGoogleApi();


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

            Toast.makeText(getApplicationContext(), "Location Based Lat/Lon" + resultLat+resultLon, Toast.LENGTH_SHORT).show();


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

       MarkerOptions markerOptions = new MarkerOptions()
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
               /* subLocality = listAddresses.get(0).getAddressLine(0);

                Toast.makeText(getApplicationContext(), subLocality, Toast.LENGTH_SHORT).show();

                SharedPreferences sharedpreferences = getSharedPreferences("UserLogin", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedpreferences.edit();


                editor.putString("subLocality", subLocality);
                editor.putString("lattitude",lat);
                editor.putString("longitude",lon);

                editor.commit();

*/



                //  markerOptions.title("" + latLng + "," + state + "," + state + "," + country);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (map != null) {
            map.addMarker(markerOptions);
            float zoom = 10f;
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
            map.animateCamera(cameraUpdate);
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

          /*  latLng = new LatLng(latitude, longitude);
            map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            map.animateCamera(CameraUpdateFactory.zoomTo(12));*/

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
       // conlist();


    }

    public void conlist()
    {

        latLng = new LatLng(latitude, longitude);
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        map.animateCamera(CameraUpdateFactory.zoomTo(12));
        //map.addMarker(new MarkerOptions().position(latLng));

        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
        googlePlacesUrl.append("&types=hospital");
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" +GOOGLE_API_KEY);

        GooglePlacesReadTask googlePlacesReadTask = new GooglePlacesReadTask(MainMapActivity.this);
        Object[] toPass = new Object[2];
        toPass[0] = map;
        toPass[1] = googlePlacesUrl.toString();
        googlePlacesReadTask.execute(toPass);
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

    private final int UPDATE_INTERVAL = 10* 1000; // 10 secs
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



      //  showNotification("Stayed", " you resided here" );

        latitude = location.getLatitude();
        longitude = location.getLongitude();
        textLat.setText("lat :" + (double) latitude);
        textLong.setText("long :" + (double) longitude);


         lat = String.valueOf(latitude);
         lon= String.valueOf(longitude);

        /*lat = String.valueOf(location.getLatitude());
        lon = String.valueOf(location.getLongitude());*/
       // Toast.makeText(getApplicationContext(), "nayana"+nayana+"Sonone"+p1, Toast.LENGTH_SHORT).show();
        markerLocation(new LatLng(location.getLatitude(), location.getLongitude()));


/*
        String notify="notification";

        if(notify.equals("notification"))

        {
          */ /* MainMapActivity.Fetcher fetcher1 = new MainMapActivity.Fetcher();
            String method1 = "UserNotification";
            fetcher1.execute(method1,usermailid);*/

/*
        }


else {*/
          /*  MainMapActivity.BackgroundTask1 fetcher = new MainMapActivity.BackgroundTask1(MainMapActivity.this);
            String method = "login";
            fetcher.execute(method, lat, lon, usermailid);*/



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
        conlist();


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





  /*  @Override
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


            Intent intent = new Intent(getApplicationContext(), NavigatinActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                intent.addFlags(0x8000);



            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/




}