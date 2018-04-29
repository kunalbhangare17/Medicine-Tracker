package com.example.abc.medicinetracker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.abc.medicinetracker.R;


/**
 * Created by developer on 16/3/17.
 */

public class MedicineViewHolder extends RecyclerView.ViewHolder {

    public TextView name;

    public TextView phone;
    public TextView bg;


    public MedicineViewHolder(View itemView) {
        super(itemView);


        bg = (TextView) itemView.findViewById(R.id.bg);

    }
}