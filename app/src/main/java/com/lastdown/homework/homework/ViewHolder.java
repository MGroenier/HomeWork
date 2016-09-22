package com.lastdown.homework.homework;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Martijn on 22/09/2016.
 */

public class ViewHolder {

    private TextView title;

    //Initialize the variables
    public ViewHolder(View view) {
        title = (TextView) view.findViewById(R.id.text_view_assignment_title);
    }

    //Method to set the values in a row
    public void populateRow(Assignment item) {
        //Set the title for this row
        title.setText(item.getTitle());
    }

}
