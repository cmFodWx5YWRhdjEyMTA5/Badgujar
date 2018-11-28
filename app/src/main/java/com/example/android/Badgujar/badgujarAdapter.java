package com.example.android.Badgujar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.Badgujar.R;

import java.util.ArrayList;

public class badgujarAdapter extends ArrayAdapter<badgujar> {

    private Context mContext;

    public badgujarAdapter(Context context, ArrayList<badgujar> badgujars) {

        super(context, 0, badgujars);


    }

   /*  @Override
    public long getItemId(int position) {
        return 0;
    } */

    // create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridItemView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes

            gridItemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_items, parent, false);
            
           /* imageView.setLayoutParams(new ViewGroup.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8); */
         } else {
            gridItemView = (View) convertView;
        }

        badgujar currentBadgujar = getItem(position);

        ImageView gridImageView = (ImageView) gridItemView.findViewById(R.id.gridImageView);
        gridImageView.setImageResource(currentBadgujar.getGridImageResourceId());

        TextView gridTextView = (TextView) gridItemView.findViewById(R.id.gridTextView);
        gridTextView.setText(currentBadgujar.getGridNames());

                return gridItemView;
    }

    // references to our images
/*    private Integer[] mThumbIds = {
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0
    }; */
}