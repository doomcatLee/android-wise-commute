package com.example.guest.wisecommute;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.guest.wisecommute.fragments.DashboardFragment;
import com.example.guest.wisecommute.fragments.LiveFeedFragment;
import com.example.guest.wisecommute.fragments.UserPreferenceArrivalFragment;
import com.example.guest.wisecommute.models.Train;
import com.example.guest.wisecommute.services.TrimetService;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;
    private UserPreferenceArrivalFragment userPreferenceArrivalFragment;
    private LiveFeedFragment liveFeedFragment;
    private Intent navIntent;
    private DashboardFragment dashboardFragment;

    private String timeLeft;

    ArrayList<Train> mTrains = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            getTrainTime("green","7787", "clackamas");
            fragmentManager = getSupportFragmentManager();
            switch (item.getItemId()) {
                case R.id.navigation_to_from:
                    Bundle bundle = new Bundle();
                    bundle.putString("timeLeft", timeLeft);
                    userPreferenceArrivalFragment.setArguments(bundle);

                    Log.d(TAG, "onNavigationItemSelected:" +timeLeft);
                    Log.d(TAG, "onNavigationItemSelected:");
                    fragmentManager = getSupportFragmentManager();
                    transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.content, userPreferenceArrivalFragment);
                    transaction.commit();
                    return true;
                case R.id.navigation_all:
                    navIntent = new Intent(MainActivity.this, TrainColorActivity.class);
                    startActivity(navIntent);
                    return true;
                case R.id.navigation_live_feed:
                    transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.content, liveFeedFragment);
                    transaction.commitNow();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userPreferenceArrivalFragment = new UserPreferenceArrivalFragment();
        liveFeedFragment = new LiveFeedFragment();
        dashboardFragment = new DashboardFragment();
        fragmentManager = getSupportFragmentManager();


        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, userPreferenceArrivalFragment);
        transaction.commit();

        /** need to preload the users preferences from firebase into shared preferences */
//        getTrains(trainColor, trainStopID, trainDirection, trainShortSign);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String showDashboard = (String) intent.getStringExtra("showDashboardFragment");
        Log.d(TAG, "onCreate: showDashboard variable is " + showDashboard);
        if(showDashboard != null) {
            if (showDashboard.equals("true")) {
                fragmentManager = getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content, dashboardFragment);
                transaction.commit();
                showDashboard = "false";
            }
        }
    }

    /** Menu Code */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
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
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.action_dashboard) {
            showDashboardFragment(true);
        }
        return super.onOptionsItemSelected(item);
    }

    public void showDashboardFragment(boolean showDashboardFragment) {
        Intent intent = getIntent();
        String showDashboard = intent.getStringExtra("showDashboardFragment");

        if(showDashboardFragment || showDashboard.equals("true")) {
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.content, dashboardFragment);
            transaction.commit();
            showDashboard = "false";
        }
    }

    /** Logout Firebase User */
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LogInActivity.class);
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

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

//                        mAdapter = new TrainListAdapter(getApplicationContext(), mTrains);
//                        mRecyclerView.setAdapter(mAdapter);
//                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
//                        mRecyclerView.setLayoutManager(layoutManager);
//                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
        Log.d(TAG, "getTrains: ends");
    }


    private void getTrainTime(String trainColor, String trainStopID, String trainDirection) {
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

                try {
                    // we convert the response to a string, to make sure the response is in JSON format
                    String json = response.body().string();
                    if(response.isSuccessful()) {
                        JSONObject trimetJSON = new JSONObject(json);
                        JSONArray arrivalJSON = trimetJSON.getJSONObject("resultSet").getJSONArray("arrival");
                        JSONObject obj = arrivalJSON.getJSONObject(0);

                        long now = System.currentTimeMillis();
                        long end = Long.parseLong(obj.getString("estimated"));
                        String output = Long.toString((end-now)/1000);
                        Log.d(TAG, "onResponse: TRAIN ARRAY" + output);
                        timeLeft = output;


                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        Log.d(TAG, "getTrains: ends");
    }

}
