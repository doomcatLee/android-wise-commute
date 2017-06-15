package com.example.guest.wisecommute.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guest.wisecommute.R;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserPreferenceArrivalFragment extends Fragment {
    @Bind(R.id.tvTimerText) TextView tvTimerText;
    @Bind(R.id.tvCircle) TextView tvCircle;
    @Bind(R.id.tvTrainName) TextView tvTrainName;
    @Bind(R.id.tvStopName) TextView tvStopName;
    @Bind(R.id.preferenceArrivalRecyclerView) RecyclerView mRecyclerView;

    public UserPreferenceArrivalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ButterKnife.bind(getActivity());
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_preference_arrival, container, false);
    }

}
