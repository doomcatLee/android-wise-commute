package com.example.guest.wisecommute.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.guest.wisecommute.R;
import com.example.guest.wisecommute.models.Stop;

import java.util.ArrayList;

/**
 * Created by administrator on 6/6/17.
 */

public class StopAdapter extends ArrayAdapter<Stop> {
    private static final String TAG = StopAdapter.class.getSimpleName();

    public StopAdapter(Context context, ArrayList<Stop> stops) {
        super(context, 0, stops);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // grab current stop object
        Stop stop = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_stop_list, parent, false);
        }
        /** Connect widgets to getView, you have to connect them in this way..
         * due to the convertView being required to properly reference the widgets
         * without the convertView.findView.. you will get a null object reference */
        TextView tvStopName = (TextView) convertView.findViewById(R.id.tvStopName);
        TextView tvStopID = (TextView) convertView.findViewById(R.id.tvStopID);

        /** Using the stop object values, set custom_stop_list layout TextView's */
        tvStopName.setText(stop.getStopName());
        tvStopID.setText(stop.getStopId());

        return convertView;
    }
}
