package com.example.abc.medicinetracker.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.abc.medicinetracker.R;


public class Level1ViewHolder extends RecyclerView.ViewHolder {


    TextView name;
    CheckBox checkBox;



    public Level1ViewHolder(View itemView) {
        super(itemView);


        name = (TextView)itemView.findViewById(R.id.name);
        checkBox=(CheckBox)itemView.findViewById(R.id.sym);




    }
}
