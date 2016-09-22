package com.lastdown.homework.homework;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Martijn on 22/09/2016.
 */

public class AssignmentAdapter extends ArrayAdapter<Assignment> {

    private LayoutInflater inflater;

    public AssignmentAdapter(Context context, int resource, List<Assignment> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(getContext());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder;

        //Check if the row is new
        if (row == null) {

            //Inflate the layout if it didn't exist yet
            row = inflater.inflate(R.layout.list_item_assignment, parent, false);
            //Create a new view holder instance
            holder = new ViewHolder(row);
            //set the holder as a tag so we can get it back later
            row.setTag(holder);

        } else {

            //The row isn't new so we can reuse the view holder
            holder = (ViewHolder) row.getTag();
        }

        //Populate the row
        holder.populateRow(getItem(position));
        return row;

    }
}
