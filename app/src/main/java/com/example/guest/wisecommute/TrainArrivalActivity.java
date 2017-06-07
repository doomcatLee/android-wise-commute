package com.example.guest.wisecommute;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.guest.wisecommute.adapters.TrainListAdapter;
import com.example.guest.wisecommute.models.Train;
import com.example.guest.wisecommute.services.TrimetService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TrainArrivalActivity extends AppCompatActivity {
    private static final String TAG = TrainArrivalActivity.class.getSimpleName();
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.tvStopName) TextView tvStopName;
    private TrainListAdapter mAdapter;

    public ArrayList<Train> mTrains = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_arrival);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate: starts");

        Intent intent = getIntent();
        String trainColor = intent.getStringExtra("trainColor");
        String trainDirection = intent.getStringExtra("trainDirection");
        String trainStopID = intent.getStringExtra("stopID");
        String stopName = intent.getStringExtra("stopName");
        String trainDirectionFullSign = intent.getStringExtra("trainDirectionFullSign");
        String trainShortSign = intent.getStringExtra("trainShortSign");

        // set up the adapter..

        tvStopName.setText(stopName);

        getTrains(trainColor, trainStopID, trainDirection, trainShortSign);

        Log.d(TAG, "onCreate: ends");
    }

    private void getTrains(String trainColor, String trainStopID, String trainDirection, String trainShortSign) {
        Log.d(TAG, "getTrains: starts");
        final TrimetService trimetService = new TrimetService();
        trimetService.findArrivals(trainColor, trainStopID, trainDirection, trainShortSign, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.e(TAG, "onFailure: API Call failed with " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, "onResponse: starts");
                mTrains = trimetService.processResults(response);

                TrainArrivalActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mAdapter = new TrainListAdapter(getApplicationContext(), mTrains);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TrainArrivalActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        for(Train train : mTrains) {
                            Log.d(TAG, "ShortSign: " + train.getShortSign());
                            Log.d(TAG, "FullSign: " + train.getFullSign());
                            Long estimated = Long.parseLong(train.getEstimated());
                            Date startDate = new Date();
                            Date date = new Date(estimated);
                            long difference = date.getTime() - startDate.getTime();
                            Date differenceDate = new Date(difference);
                            DateFormat format = new SimpleDateFormat("mm");
                            format.setTimeZone(TimeZone.getTimeZone("UTC-8:00"));
                            String estimatedMinutes = format.format(differenceDate);
                            Log.d(TAG, "Estimated: " + estimatedMinutes + " minutes");

                            Long scheduled = Long.parseLong(train.getScheduled());
                            Date scheduledDate = new Date(scheduled);
                            DateFormat scheduledFormat = new SimpleDateFormat("hh:mm");
                            scheduledFormat.setTimeZone(TimeZone.getTimeZone("UTC-8:00"));
                            String scheduledMinutes = scheduledFormat.format(scheduledDate);
                            Log.d(TAG, "Scheduled to arrive at " + scheduledMinutes);

                            Log.d(TAG, "StopID: " + train.getLocID());
                        }
                    }
                });
            }
        });
        Log.d(TAG, "getTrains: ends");
    }

    public void setCustomAdapter(ArrayList<Train> trains) {
//        mAdapter = new TrainListAdapter(getApplicationContext(), trains);
    }
}
