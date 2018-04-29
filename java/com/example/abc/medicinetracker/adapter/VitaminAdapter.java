package com.example.abc.medicinetracker.adapter;

/**
 * Created by developer on 18/3/17.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.abc.medicinetracker.FoodDetailsActivity;
import com.example.abc.medicinetracker.entities.VitaminMapPojo;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.List;


public class VitaminAdapter extends   RecyclerView.Adapter<VitaminViewHolder> implements View.OnClickListener {

    private Context context;
    private List<VitaminMapPojo> itemObjectList;
    private int layoutResource;
    private View layoutView;
    private CheckBox checkBox;

    JSONArray arrlist;
    public VitaminAdapter(Context context, List<VitaminMapPojo> itemObjectList, int layoutResource) {
        this.context = context;
        this.itemObjectList = itemObjectList;
        this.layoutResource = layoutResource;
        arrlist=new JSONArray();
    }




    @Override
    public VitaminViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        layoutView = LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false);
        return new VitaminViewHolder(layoutView);


    }



    @Override
    public void onBindViewHolder(final VitaminViewHolder holder, final int position) {
        final VitaminMapPojo singleItem = itemObjectList.get(position);
        //  final Level0 p = itemObjectList.get(position);
       final String level_id = String.valueOf(singleItem.getLevel_1_vitamin_id());

        // use Glide to download and display the category image.



        holder.name.setText(singleItem.getVitamin_deficiency());


/*
        Gson gson = ((CustomApplication)((Activity)context).getApplication()).getGsonObject();
        final String objectToString = gson.toJson(singleItem);*/


/*

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
*/
/*
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);*//*





                }
        });
*/

     /*   holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               try {
                    JSONObject jsonObject = new JSONObject();
                    //jsonObject.put("checkbox",singleItem.getDisease_id());
                   jsonObject.put("checkbox",singleItem.getLevel_1_id());

                    arrlist.put(jsonObject);
                    SharedPreferences settings = context.getSharedPreferences("Groceries", MODE_PRIVATE);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("checkbox", ""+arrlist);
                    editor.commit();






                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }









            }
        });
*/


        layoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final VitaminMapPojo singleItem = itemObjectList.get(position);
         String nayana=  singleItem.getVitamin_deficiency();
                final String level_id = String.valueOf(singleItem.getLevel_1_vitamin_id());










                Intent categoryIntent = new Intent(context, FoodDetailsActivity.class);


        // String level1_vitamin_id= String.valueOf(singleItem.getLevel_1_vitamin_id());






                SharedPreferences sharedPreferences =context.getSharedPreferences("User", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //Toast.makeText(getActivity(),tempValues.getFileName(file).toString(),Toast.LENGTH_LONG).show();
                editor.putString("level1_vitamin_id",level_id);
                editor.commit();

context.startActivity(categoryIntent);



               // Toast.makeText(context,"Hello"+level_id,Toast.LENGTH_LONG).show();

            }
        });





    }

    @Override
    public int getItemCount() {
        return itemObjectList.size();
    }


    @Override
    public void onClick(View v) {

    }
}

