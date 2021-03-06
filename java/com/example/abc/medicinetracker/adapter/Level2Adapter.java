package com.example.abc.medicinetracker.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.abc.medicinetracker.entities.Level1;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;


import static android.content.Context.MODE_PRIVATE;


public class Level2Adapter extends   RecyclerView.Adapter<Level2ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<Level1> itemObjectList;
    private int layoutResource;
    private View layoutView;
    private CheckBox checkBox;
    JSONArray arrlist;
    public Level2Adapter(Context context, List<Level1> itemObjectList, int layoutResource) {
        this.context = context;
        this.itemObjectList = itemObjectList;
        this.layoutResource = layoutResource;
        arrlist=new JSONArray();

    }




    @Override
    public Level2ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        layoutView = LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false);
        return new Level2ViewHolder(layoutView);
    }



    @Override
    public void onBindViewHolder(final Level2ViewHolder holder, int position) {
        final Level1 singleItem = itemObjectList.get(position);
        final String id = String.valueOf(singleItem.getLevel1_id());

        // use Glide to download and display the category image.

        holder.name.setText(singleItem.getLevel1_name());


    /*    Gson gson = ((CustomApplication)((Activity)context).getApplication()).getGsonObject();
        final String objectToString = gson.toJson(singleItem);*/

/*

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);




                }
        });
*/
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("checkbox",singleItem.getLevel1_id());
                    //jsonObject.put("name",singleItem.getLevel1_name());


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



        layoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("schedule_id", schedule_id);
                context.startActivity(intent);
*/
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

