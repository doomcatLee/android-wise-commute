package com.example.guest.wisecommute.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guest.wisecommute.MainActivity;
import com.example.guest.wisecommute.R;
import com.example.guest.wisecommute.models.Timer;
import com.example.guest.wisecommute.models.Train;
import com.example.guest.wisecommute.services.TrimetService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserPreferenceArrivalFragment extends Fragment {

    Timer timer;

    TextView tvTimerText;
    TextView tvCircle;
    TextView tvTrainName;
    TextView tvStopName;
    @Bind(R.id.preferenceArrivalRecyclerView) RecyclerView mRecyclerView;


    private String timeLeft;



    public UserPreferenceArrivalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ButterKnife.bind(getActivity());

        View view = inflater.inflate(R.layout.fragment_user_preference_arrival, container, false);
        tvTimerText = (TextView) view.findViewById(R.id.tvTimerText);
        tvCircle = (TextView) view.findViewById(R.id.tvCircle);
        tvStopName = (TextView) view.findViewById(R.id.tvStopName);
        tvTrainName = (TextView) view.findViewById(R.id.tvTrainName);

        Typeface fontThin = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Thin.ttf");
        Typeface fontRegular = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        tvTimerText.setTypeface(fontThin);
        tvTrainName.setTypeface(fontRegular);
        tvStopName.setTypeface(fontRegular);



        if (getArguments() != null) {
            timeLeft = getArguments().getString("timeLeft");
            Log.d(TAG, "onCreateView: TIME LEFT " +timeLeft);
            timer = new Timer(Integer.parseInt(timeLeft));
            timer.runTimer(tvTimerText,tvCircle,getActivity());
            timer.onClickStart(view);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        getTrains("green", "7787", "clackamas");
//        timer = new Timer(Integer.parseInt(timeLeft));
    }



}
