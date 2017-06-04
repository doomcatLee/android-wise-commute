package com.example.guest.wisecommute;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.guest.wisecommute.adapters.TrainListAdapter;
import com.example.guest.wisecommute.models.Train;
import com.example.guest.wisecommute.services.TrimetService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TrainArrivalActivity extends AppCompatActivity {
    private static final String TAG = TrainArrivalActivity.class.getSimpleName();
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private TrainListAdapter mAdapter;

    public ArrayList<Train> mTrains = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_arrival);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String trainColor = intent.getStringExtra("trainColor");
        String trainDirection = intent.getStringExtra("trainDirection");
        String trainStopID = intent.getStringExtra("trainStopID");
        String trainDirectionFullSign = intent.getStringExtra("trainDirectionFullSign");
        String trainShortSign = intent.getStringExtra("trainShortSign");

    }

    private void getTrains(String trainColor, String trainStopID, String trainDirection, String trainShortSign) {
        final TrimetService trimetService = new TrimetService();
        trimetService.findArrivals(trainColor, trainStopID, trainDirection, trainShortSign, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
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
                            Log.d(TAG, "Estimated: " + train.getEstimated());
                            Log.d(TAG, "Scheduled: " + train.getScheduled());
                            Log.d(TAG, "StopID: " + train.getLocID());
                        }
                    }
                });


                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}