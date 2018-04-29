package com.example.abc.medicinetracker.adapter;

/**
 * Created by developer on 17/2/18.
 */
/*

public class Vitamin1ViewHolder {
}
*/

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.abc.medicinetracker.R;


/**
 * Created by developer on 18/3/17.
 */

public class Vitamin1ViewHolder extends RecyclerView.ViewHolder {


    TextView name;
    CheckBox checkBox;



    public Vitamin1ViewHolder(View itemView) {
        super(itemView);


        name = (TextView)itemView.findViewById(R.id.name);





    }
}
