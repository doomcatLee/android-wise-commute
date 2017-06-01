package com.example.guest.wisecommute;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.wisecommute.models.Train;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 5/31/17.
 */

public class TrainFragmentDetail {
    @Bind(R.id.tvTrainDirection) TextView tvTrainDirection;

    private Train mTrain;

    // returns new instance of RestaurantDetailFragment it takes a restaurant object as an argument
    // What is a bundle??
    public static TrainFragmentDetail newInstance(Train train) {
        TrainFragmentDetail trainFragmentDetail = new TrainFragmentDetail();
        Bundle args = new Bundle();
        args.putParcelable("restaurant", Parcels.wrap(train));
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

        tvTrainStop.setText(mTrain.getStopName());


        return view;
    }
}
