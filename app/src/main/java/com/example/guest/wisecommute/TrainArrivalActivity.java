package com.example.guest.wisecommute;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.guest.wisecommute.adapters.TrainListAdapter;
import com.example.guest.wisecommute.fragments.DashboardFragment;
import com.example.guest.wisecommute.models.Train;
import com.example.guest.wisecommute.services.TrimetService;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TrainArrivalActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = TrainArrivalActivity.class.getSimpleName();
    @Bind(R.id.twitterRecyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.tvStopName) TextView tvStopName;
    private TrainListAdapter mAdapter;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private DashboardFragment dashboardFragment;

    private String trainColor;
    private String trainStopID;
    private String trainDirection;
    private String stopName;
    private String trainShortSign;

    public ArrayList<Train> mTrains = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_arrival);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate: starts");

        fragmentManager = getSupportFragmentManager();
        dashboardFragment = new DashboardFragment();

        Intent intent = getIntent();
        trainColor = intent.getStringExtra("trainColor");
        trainDirection = intent.getStringExtra("trainDirection");
        trainStopID = intent.getStringExtra("stopID");
        stopName = intent.getStringExtra("stopName");
        trainShortSign = intent.getStringExtra("trainShortSign");

        tvStopName.setText(stopName);

        getTrains(trainColor, trainStopID, trainDirection);

        Log.d(TAG, "onCreate: ends");
    }

    @Override
    public void onClick(View v) {

    }

    public void showDashboardFragment(boolean showDashboardFragment) {
        if(showDashboardFragment) {
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.content, dashboardFragment);
            transaction.commit();
        }
    }

    /** Menu Code */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_arrival, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /** Menu Code */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        } else if (id == R.id.action_settings) {
            Intent intent = new Intent(TrainArrivalActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.action_dashboard) {
            Intent intent = new Intent(TrainArrivalActivity.this, MainActivity.class);
            intent.putExtra("showDashboardFragment", "true");
            startActivity(intent);
            finish();
        } else if (id == R.id.action_refresh) {
            Log.d(TAG, "onOptionsItemSelected: clicked");
            // trigger api call
            getTrains(trainColor, trainStopID, trainDirection);
        } else if (id == R.id.action_alarm) {
            Intent openClockIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
            openClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(openClockIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    /** Logout Firebase User */
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(TrainArrivalActivity.this, LogInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void getTrains(String trainColor, String trainStopID, String trainDirection) {
        Log.d(TAG, "getTrains: starts");
        final TrimetService trimetService = new TrimetService();
        trimetService.findArrivals(trainColor, trainStopID, trainDirection, new Callback() {
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

//                        for(Train train : mTrains) {
//                            Log.d(TAG, "ShortSign: " + train.getShortSign());
//                            Log.d(TAG, "FullSign: " + train.getFullSign());
//                            Log.d(TAG, train.getEstimatedMinutes());
//
//                            Log.d(TAG, train.getScheduledTime());
//
//                            Log.d(TAG, "StopID: " + train.getLocID());
//                        }
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
