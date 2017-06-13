package com.example.guest.wisecommute;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;
    private UserPreferenceArrivalFragment userPreferenceArrivalFragment;
    private LiveFeedFragment liveFeedFragment;
    private Intent navIntent;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fragmentManager = getSupportFragmentManager();
            switch (item.getItemId()) {
                case R.id.navigation_to_from:
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
                    transaction.commit();
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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
