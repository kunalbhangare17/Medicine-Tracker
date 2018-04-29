package com.example.abc.medicinetracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.abc.medicinetracker.adapter.VitaminAdapter1;
import com.example.abc.medicinetracker.entities.VitaminDeficiencyFoodPojo;
import com.example.abc.medicinetracker.network.GsonRequest;
import com.example.abc.medicinetracker.network.VolleySingleton;
import com.example.abc.medicinetracker.util.Helper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
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
import java.util.Map;



public class FoodDetailsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String ID, token;
    ProgressDialog loading;
    JSONObject info;
    JSONArray components;
    TextView title, nametv, sizetv, pricetv, manufacturertv;
    String name, price, manufacturer, type, size, constituents;
    private static final String TAG = FoodDetailsActivity.class.getSimpleName();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_food_details);


        title = (TextView) findViewById(R.id.title_rem);
        //    recyclerView = (RecyclerView) findViewById(R.id.List);


        listView = (ListView) findViewById(R.id.List);

        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        final String level1_vitamin_id = sharedPreferences.getString("level1_vitamin_id", null);


        // getVitaminDetails(level1_vitamin_id);

        FoodDetailsActivity.Fetcher fetcher = new FoodDetailsActivity.Fetcher();
        String method = "generate";
        fetcher.execute(method, level1_vitamin_id);


    }

    private void getVitaminDetails(String level1_vitamin_id) {

        Map<String, String> params = new HashMap<String, String>();
        params.put(Helper.level_1_vitamin_id, String.valueOf(level1_vitamin_id));


        GsonRequest<VitaminDeficiencyFoodPojo[]> serverRequest = new GsonRequest<VitaminDeficiencyFoodPojo[]>(
                Request.Method.POST,
                Helper.PATH_TO_LEVEL2_VITAMIN,
                VitaminDeficiencyFoodPojo[].class,
                params,
                createRequestSuccessListener(),
                createRequestErrorListener());

        serverRequest.setRetryPolicy(new DefaultRetryPolicy(
                Helper.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getInstance(this).addToRequestQueue(serverRequest);
    }

    private Response.Listener<VitaminDeficiencyFoodPojo[]> createRequestSuccessListener() {
        return new Response.Listener<VitaminDeficiencyFoodPojo[]>() {
            @Override
            public void onResponse(VitaminDeficiencyFoodPojo[] response) {
                try {
                    if (response.length > 0) {
                        Log.d(TAG, "Json Response " + response.toString());
                        List<VitaminDeficiencyFoodPojo> itemObject = new ArrayList<VitaminDeficiencyFoodPojo>();


                      /*  for (int i = 0; i < response.length; i++) {
                            itemObject.add(new VitaminDeficiencyFoodPojo(response[i].getFood_id(), response[i].getVitamin_deficiency(), response[i].getLevel_1_vitamin_id(), response[i].getFood_intake(), response[i].getLevel_1_id()));

                            //textView.setText(response[i].getFood_intake());


                        }

*/
                        VitaminAdapter1 mAdapter = new VitaminAdapter1(FoodDetailsActivity.this, itemObject, R.layout.disease_symptoms_list_layout1);
                        recyclerView.setAdapter(mAdapter);


                    } else {
                        Toast.makeText(FoodDetailsActivity.this, "No data found", Toast.LENGTH_LONG).show();
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

    public void back(View view) {
        onBackPressed();
    }

    private class Fetcher extends AsyncTask<String, String, String> {

        AlertDialog alertDialog;
        Context ctx;

        public Fetcher() {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {

        }//UploadPojo

        @Override
        protected String doInBackground(String... params) {

            String reg_url = Helper.DOMAIN +"SearchServlet";

            String method = params[0];
            if (method.equals("generate")) {

                String level_1_vitamin_id = params[1];
                try {
                    URL url = new URL(reg_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);

                    //httpURLConnection.setDoInput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data =
                            URLEncoder.encode("level_1_vitamin_id", "UTF-8") + "=" + URLEncoder.encode(level_1_vitamin_id, "UTF-8") + "&" +
                                    URLEncoder.encode("action", "UTF-8") + "=" + URLEncoder.encode("getVitaminFoods", "UTF-8");

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
                TypeReference<List<VitaminDeficiencyFoodPojo>> mapType = new TypeReference<List<VitaminDeficiencyFoodPojo>>() {
                };
                List<VitaminDeficiencyFoodPojo> jsonToPersonList = objectMapper.readValue(result, mapType);

                //List<OwnerPojo> jsonToPersonList = new ObjectMapper().readValue(result, new TypeReference<List<OwnerPojo>>() { });
                Log.i("jsonToPersonList","onPostExecute  jsonToPersonList="+jsonToPersonList);
                // objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);



                FoodDetailsActivity.CustomAdapter customList=new FoodDetailsActivity.CustomAdapter(FoodDetailsActivity.this, (List)jsonToPersonList);

                listView.setAdapter(customList);

                //System.out.println(jsonToPersonList);

                //System.out.println(jsonToPersonList);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public class CustomAdapter extends BaseAdapter implements View.OnClickListener {
        //,  CompoundButton.OnCheckedChangeListener

//********** Declare Used Variables ********

        private Activity activity;
        private List<VitaminDeficiencyFoodPojo> data;
        LayoutInflater inflater=null;
        // public Resources res;
        VitaminDeficiencyFoodPojo tempValues=null;
        int i=0;
        private View btnBooking;
        JSONArray arrlist;
        SparseBooleanArray mCheckStates;
//************  CustomAdapter Constructor ****************

        public CustomAdapter(Activity a, List<VitaminDeficiencyFoodPojo> d) {

//********* Take passed values *********

            activity = a;
            data=d;
            // res = resLocal;
            arrlist=new JSONArray();
//**********  Layout inflator to call external xml activity_faculty_info () **********

            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

//******* What is the size of Passed Arraylist Size ***********

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



//******** Create a holder Class to contain inflated xml file elements ********

        class ViewHolder{

            public TextView Name,date;
            public ImageView imageView;
            public CardView card;

            public TextView price,like,manuturing_date,expired_date;
            public ImageView addToCart,viewMore;
            RatingBar ratingBar;


        }

//***** Depends upon data size called for each row , Create each ListView row ****

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public View getView(final int position, View convertView, ViewGroup parent ) {

            View vi = convertView;
            final FoodDetailsActivity.CustomAdapter.ViewHolder holder;

            if(convertView==null){

//***** Inflate tabitem.xml file for each row ( Defined below ) ******

                vi = inflater.inflate(R.layout.disease_symptoms_list_layout1, null);

//***** View Holder Object to contain tabitem.xml file elements *****

                holder = new FoodDetailsActivity.CustomAdapter.ViewHolder();
                holder.Name = (TextView) vi.findViewById(R.id.name);










                //  holder.ratingBar=(RatingBar) vi.findViewById(R.id.ratingbar);




//***********  Set holder with LayoutInflater ***********

                vi.setTag( holder );
            }
            else
                holder=(FoodDetailsActivity.CustomAdapter.ViewHolder)vi.getTag();

            if(data.size()<=0)
            {
                holder.Name.setText("No Data");


            }
            else
            {
//**** Get each Model object from Arraylist *******

                tempValues=null;
                tempValues = (VitaminDeficiencyFoodPojo) data.get( position );

//***********  Set Model values in Holder elements **********



                holder.Name.setText( tempValues.getFood_intake());





                //holder.like.setText( tempValues.getReview_likes(like));

                // String x=holder.like.getText().toString();






              /*  String serverImagePath =  tempValues.getImage();
                Glide.with(activity).load(serverImagePath).diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().override(300, 300).into(holder.imageView);


*/

                vi.setOnClickListener(new FoodDetailsActivity.CustomAdapter.OnItemClickListener( position ));

            }
            return vi;
        }

        @Override
        public void onClick(View v) {


            Log.v("CustomAdapter", "=====Row button clicked=====");
        }

//******** Called when Item click in ListView ***********

        private class OnItemClickListener  implements View.OnClickListener {
            private int mPosition;

            OnItemClickListener(int position){
                mPosition = position;
            }

            @Override
            public void onClick(View arg0) {

                FoodDetailsActivity sct = (FoodDetailsActivity) activity;


//***  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )***
                sct.onItemClick(mPosition);
            }
        }
    }

    private void onItemClick(int mPosition) {
    }
}