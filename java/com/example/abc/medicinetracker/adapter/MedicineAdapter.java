package com.example.abc.medicinetracker.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abc.medicinetracker.entities.Medicine;
import com.google.gson.Gson;

import java.util.List;



/**
 * Created by developer on 16/3/17.
 */

public class MedicineAdapter extends RecyclerView.Adapter<MedicineViewHolder>{

    private Context context;
    private List<Medicine> itemObjectList;
    private int layoutResource;
    private View layoutView;

    public MedicineAdapter(Context context, List<Medicine> itemObjectList, int layoutResource) {
        this.context = context;
        this.itemObjectList = itemObjectList;
        this.layoutResource = layoutResource;
    }




    @Override
    public MedicineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        layoutView = LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false);
        return new MedicineViewHolder(layoutView);
    }



    @Override
    public void onBindViewHolder(MedicineViewHolder holder, int position) {
        Medicine singleItem = itemObjectList.get(position);
        final String id = String.valueOf(singleItem.getMedicine_id());

        // use Glide to download and display the category image.



       // holder.name.setText(singleItem.getDisease_name());

        holder.bg.setText(singleItem.getMedicine_name());


       /* Gson gson = ((CustomApplication)((Activity)context).getApplication()).getGsonObject();
        final String objectToString = gson.toJson(singleItem);*/

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

}

