package com.example.guest.wisecommute;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TrainColorActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.greenLine) Button greenLine;
    @Bind(R.id.greenToClackamas) Button greenToClackamas;
    @Bind(R.id.greenToCityCenter) Button greenToCityCenter;
    @Bind(R.id.blueLine) Button blueLine;
    @Bind(R.id.blueToHillsboro) Button blueToHillsboro;
    @Bind(R.id.blueToGresham) Button blueToGresham;
    @Bind(R.id.redLine) Button redLine;
    @Bind(R.id.redToAirport) Button redToAirport;
    @Bind(R.id.redToBeaverton) Button redToBeaverton;
    @Bind(R.id.orangeLine) Button orangeLine;
    @Bind(R.id.orangeToMilwaukie) Button orangeToMilwaukie;
    @Bind(R.id.orangeToCityCenter) Button orangeToCityCenter;
    @Bind(R.id.yellowLine) Button yellowLine;
    @Bind(R.id.yellowToExpo) Button yellowToExpo;
    @Bind(R.id.yellowToCityCenter) Button yellowToCityCenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_color);
        ButterKnife.bind(this);

        Button[] trainNameArray = {greenLine, blueLine, redLine, yellowLine, orangeLine, greenToClackamas, greenToCityCenter, blueToGresham, blueToHillsboro,
                redToAirport, redToBeaverton, orangeToMilwaukie, orangeToCityCenter, yellowToCityCenter, yellowToExpo};
        setButtonOnClickListener(trainNameArray);

    }

    public void setButtonOnClickListener(Button[] trainNameArray) {
        for(int i = 0; i < trainNameArray.length; i++) {
            trainNameArray[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if(v == greenLine) {
            greenLine.setVisibility(View.GONE);
            greenToCityCenter.setVisibility(View.VISIBLE);
            greenToClackamas.setVisibility(View.VISIBLE);
        } else if (v == greenToClackamas) {
            Intent intent = new Intent(TrainColorActivity.this, StopListActivity.class);
            intent.putExtra("trainColor", "green");
            intent.putExtra("trainDirection", "clackamas");
            intent.putExtra("trainShortSign", "Green Line to Clackamas");
            startActivity(intent);
        } else if (v == greenToCityCenter) {
            Intent intent = new Intent(TrainColorActivity.this, StopListActivity.class);
            intent.putExtra("trainColor", "green");
            intent.putExtra("trainDirection", "city center");
            intent.putExtra("trainShortSign", "Green Line to City Ctr");
            startActivity(intent);
        } else if (v == blueLine) {
            blueLine.setVisibility(View.GONE);
            blueToGresham.setVisibility(View.VISIBLE);
            blueToHillsboro.setVisibility(View.VISIBLE);
        } else if (v == blueToHillsboro) {
            Intent intent = new Intent(TrainColorActivity.this, StopListActivity.class);
            intent.putExtra("trainColor", "blue");
            intent.putExtra("trainDirection", "hillsboro");
            intent.putExtra("trainShortSign", "Blue to Hillsboro");
            startActivity(intent);
        } else if (v == blueToGresham) {
            Intent intent = new Intent(TrainColorActivity.this, StopListActivity.class);
            intent.putExtra("trainColor", "blue");
            intent.putExtra("trainDirection", "gresham");
            intent.putExtra("trainShortSign", "Blue to Gresham");
            startActivity(intent);
        } else if (v == redLine) {
            redLine.setVisibility(View.GONE);
            redToAirport.setVisibility(View.VISIBLE);
            redToBeaverton.setVisibility(View.VISIBLE);
        } else if (v == redToAirport) {
            Intent intent = new Intent(TrainColorActivity.this, StopListActivity.class);
            intent.putExtra("trainColor", "red");
            intent.putExtra("trainDirection", "airport");
            intent.putExtra("trainShortSign", "Red to Airport");
            startActivity(intent);
        } else if (v == redToBeaverton) {
            Intent intent = new Intent(TrainColorActivity.this, StopListActivity.class);
            intent.putExtra("trainColor", "red");
            intent.putExtra("trainDirection", "beaverton");
            intent.putExtra("trainShortSign", "Red Line to Beaverton");
            startActivity(intent);
        } else if (v == orangeLine) {
            orangeLine.setVisibility(View.GONE);
            orangeToCityCenter.setVisibility(View.VISIBLE);
            orangeToMilwaukie.setVisibility(View.VISIBLE);
        } else if (v == orangeToMilwaukie) {
            Intent intent = new Intent(TrainColorActivity.this, StopListActivity.class);
            intent.putExtra("trainColor", "orange");
            intent.putExtra("trainDirection", "milwaukie");
            intent.putExtra("trainShortSign", "Orange Line to Milwaukie");
            startActivity(intent);
        } else if (v == orangeToCityCenter) {
            Intent intent = new Intent(TrainColorActivity.this, StopListActivity.class);
            intent.putExtra("trainColor", "orange");
            intent.putExtra("trainDirection", "city center");
            intent.putExtra("trainShortSign", "Orange Line to City Ctr\\/Expo"); // may cause issues
            startActivity(intent);
        } else if (v == yellowLine) {
            yellowLine.setVisibility(View.GONE);
            yellowToCityCenter.setVisibility(View.VISIBLE);
            yellowToExpo.setVisibility(View.VISIBLE);
        } else if (v == yellowToExpo) {
            Intent intent = new Intent(TrainColorActivity.this, StopListActivity.class);
            intent.putExtra("trainColor", "yellow");
            intent.putExtra("trainDirection", "expo");
            intent.putExtra("trainShortSign", "Yellow Line to Expo Ctr");
            startActivity(intent);
        } else if (v == yellowToCityCenter) {
            Intent intent = new Intent(TrainColorActivity.this, StopListActivity.class);
            intent.putExtra("trainColor", "yellow");
            intent.putExtra("trainDirection", "city center");
            intent.putExtra("trainShortSign", "Yellow Line to City Ctr\\/Milw"); // may cause issues
            startActivity(intent);
        }
    }
}
