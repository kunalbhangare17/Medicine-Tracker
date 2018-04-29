package com.example.abc.medicinetracker.adapter;/*
package adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.gson.Gson;

import com.inducesmile.androidfoodordering.androidfoodordering.Level2SymptomsActivity;
import com.inducesmile.androidfoodordering.androidfoodordering.MainActivity;
import com.inducesmile.androidfoodordering.androidfoodordering.entities.Level0;
import com.inducesmile.androidfoodordering.androidfoodordering.entities.Symptom;
import com.inducesmile.androidfoodordering.androidfoodordering.util.CustomApplication;

import java.util.ArrayList;
import java.util.List;


public class Level1Adapter extends   RecyclerView.Adapter<Level1ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<Level0> itemObjectList;
    private int layoutResource;
    private View layoutView;
    List arrlist;
    private  CheckBox checkBox;
    public Level1Adapter(Context context, List<Level0> itemObjectList, int layoutResource) {
        this.context = context;
        this.itemObjectList = itemObjectList;
        this.layoutResource = layoutResource;

        arrlist=new ArrayList();
    }




    @Override
    public Level1ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        layoutView = LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false);
        return new Level1ViewHolder(layoutView);
    }



    @Override
    public void onBindViewHolder(final Level1ViewHolder holder, int position) {
        final Level0 singleItem = itemObjectList.get(position);
*/
/*
final String id = String.valueOf(singleItem.getLevel0_id());

        // use Glide to download and display the category image.



        holder.name.setText(singleItem.getLevel0_name());
*//*




        Gson gson = ((CustomApplication)((Activity)context).getApplication()).getGsonObject();
        final String objectToString = gson.toJson(singleItem);

*/
/*

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);




                }
        });
*//*

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.name.setText(singleItem.getLevel0_name());

                holder.checkBox.setText(singleItem.getLevel0_id());
                //holder.checkBox.setText(singleItem.getLevel0_id());


                arrlist.add(singleItem.getLevel0_id());



                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                sharedPreferences.edit().putString("checkbox",arrlist.toString());





            }
        });



        layoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                */
/*Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("schedule_id", schedule_id);
                context.startActivity(intent);
*//*

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

*/
