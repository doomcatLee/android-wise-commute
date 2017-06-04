package com.example.guest.wisecommute;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guest.wisecommute.models.Train;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 5/31/17.
 */

public class TrainFragmentDetail extends Fragment {
    @Bind(R.id.tvStopName) TextView tvStopName;

    private Train mTrain;

    public static TrainFragmentDetail newInstance(Train train) {
        TrainFragmentDetail trainFragmentDetail = new TrainFragmentDetail();
        Bundle args = new Bundle();
        args.putParcelable("train", Parcels.wrap(train));
        trainFragmentDetail.setArguments(args);
        return trainFragmentDetail;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTrain = Parcels.unwrap(getArguments().getParcelable("train"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_train_detail, container, false);
        ButterKnife.bind(this, view);

        Intent intent = getActivity().getIntent();
        String stopName = intent.getStringExtra("stopName");
        tvStopName.setText(stopName);

        return view;
    }
}
