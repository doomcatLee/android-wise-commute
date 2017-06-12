package com.example.guest.wisecommute;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guest.wisecommute.adapters.StopAdapter;
import com.example.guest.wisecommute.models.Stop;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StopListActivity extends AppCompatActivity {

    public static final String TAG = StopListActivity.class.getSimpleName();
    @Bind(R.id.stopListView) ListView lvStopList;
    @Bind(R.id.tvTrainDirectionFullSign) TextView tvTrainDirectionFullSign;
    ArrayList<Stop> stopList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        final String trainColor = intent.getStringExtra("trainColor");
        final String trainDirection = intent.getStringExtra("trainDirection");
        final String trainDirectionFullSign = intent.getStringExtra("trainDirectionFullSign");
        final String trainShortSign = intent.getStringExtra("trainShortSign");

        if (trainColor.equals("green") && trainDirection.equals("clackamas")) {
            setStopList(Constants.greenToClackamasStops);
        } else if (trainColor.equals("green") && trainDirection.equals("city center")) {
            setStopList(Constants.greenToCityCenterStops);
        } else if (trainColor.equals("blue") && trainDirection.equals("hillsboro")) {
            setStopList(Constants.blueToHillsboroStops);
        } else if (trainColor.equals("blue") && trainDirection.equals("gresham")) {
            setStopList(Constants.blueToGreshamStops);
        } else if (trainColor.equals("red") && trainDirection.equals("airport")) {
            setStopList(Constants.redToAirportStops);
        } else if (trainColor.equals("red") && trainDirection.equals("beaverton")) {
            setStopList(Constants.redToBeavertonStops);
        } else if (trainColor.equals("orange") && trainDirection.equals("milwaukie")) {
            setStopList(Constants.orangeToMilwaukieStops);
        } else if (trainColor.equals("orange") && trainDirection.equals("city center")) {
            setStopList(Constants.orangeToCityCenterStops);
        } else if (trainColor.equals("yellow") && trainDirection.equals("expo")) {
            setStopList(Constants.yellowToExpoStops);
        } else if (trainColor.equals("yellow") && trainDirection.equals("city center")) {
            setStopList(Constants.yellowToCityCenterStops);
        }

        tvTrainDirectionFullSign.setText(trainDirectionFullSign);

        StopAdapter stopAdapter = new StopAdapter(this, stopList);
        lvStopList.setAdapter(stopAdapter);

        lvStopList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Stop data = (Stop) parent.getItemAtPosition(position);
                String stopID = data.getStopId();
                String stopName = data.getStopName();

                Intent intent = new Intent(StopListActivity.this, TrainArrivalActivity.class);
                intent.putExtra("trainColor", trainColor);
                intent.putExtra("stopName", stopName);
                intent.putExtra("stopID", stopID);
                intent.putExtra("trainDirection", trainDirection);
                intent.putExtra("trainDirectionFullSign", trainDirectionFullSign);
                intent.putExtra("trainShortSign", trainShortSign);
                startActivity(intent);
            }
        });
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
            Intent intent = new Intent(StopListActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.action_dashboard) {
            Intent intent = new Intent(StopListActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /** Logout Firebase User */
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(StopListActivity.this, LogInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void setStopList(Stop[] stops) {
        for(int i = 0; i < stops.length; i++) {
            stopList.add(stops[i]);
        }
    }
}
