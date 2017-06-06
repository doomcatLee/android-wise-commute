package com.example.guest.wisecommute;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

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
    @Bind(R.id.gridLayout) GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_color);
        ButterKnife.bind(this);

        Button[] trainNameArray = {greenLine, blueLine, redLine, yellowLine, orangeLine, greenToClackamas, greenToCityCenter, blueToGresham, blueToHillsboro,
                redToAirport, redToBeaverton, orangeToMilwaukie, orangeToCityCenter, yellowToCityCenter, yellowToExpo};
        setButtonOnClickListener(trainNameArray);
        setVisibilityOnInitialTrainButtonsOnLoad(trainNameArray);
    }

    public void setVisibilityOnInitialTrainButtonsOnLoad(Button[] trainLine) {
        for(int i = 0; i < trainLine.length; i++) {
            if(i < 5) {
                trainLine[i].setVisibility(View.VISIBLE);
            } else {
                trainLine[i].setVisibility(View.GONE);
            }
        }
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
            intent.putExtra("trainDirectionFullSign", "Green Line to Clackamas");
            intent.putExtra("trainShortSign", "green line to clackamas");
            startActivity(intent);
        } else if (v == greenToCityCenter) {
            Intent intent = new Intent(TrainColorActivity.this, StopListActivity.class);
            intent.putExtra("trainColor", "green");
            intent.putExtra("trainDirection", "city center");
            intent.putExtra("trainDirectionFullSign", "Green Line to City Center");
            intent.putExtra("trainShortSign", "green line to city ctr");
            startActivity(intent);
        } else if (v == blueLine) {
            blueLine.setVisibility(View.GONE);
            blueToGresham.setVisibility(View.VISIBLE);
            blueToHillsboro.setVisibility(View.VISIBLE);
        } else if (v == blueToHillsboro) {
            Intent intent = new Intent(TrainColorActivity.this, StopListActivity.class);
            intent.putExtra("trainColor", "blue");
            intent.putExtra("trainDirection", "hillsboro");
            intent.putExtra("trainDirectionFullSign", "Blue Line to Hillsboro");
            intent.putExtra("trainShortSign", "blue to hillsboro");
            startActivity(intent);
        } else if (v == blueToGresham) {
            Intent intent = new Intent(TrainColorActivity.this, StopListActivity.class);
            intent.putExtra("trainColor", "blue");
            intent.putExtra("trainDirection", "gresham");
            intent.putExtra("trainDirectionFullSign", "Blue Line to Gresham");
            intent.putExtra("trainShortSign", "blue to gresham");
            startActivity(intent);
        } else if (v == redLine) {
            redLine.setVisibility(View.GONE);
            redToAirport.setVisibility(View.VISIBLE);
            redToBeaverton.setVisibility(View.VISIBLE);
        } else if (v == redToAirport) {
            Intent intent = new Intent(TrainColorActivity.this, StopListActivity.class);
            intent.putExtra("trainColor", "red");
            intent.putExtra("trainDirection", "airport");
            intent.putExtra("trainDirectionFullSign", "Red Line to Airport");
            intent.putExtra("trainShortSign", "red to airport");
            startActivity(intent);
        } else if (v == redToBeaverton) {
            Intent intent = new Intent(TrainColorActivity.this, StopListActivity.class);
            intent.putExtra("trainColor", "red");
            intent.putExtra("trainDirection", "beaverton");
            intent.putExtra("trainDirectionFullSign", "Red Line to Beaverton");
            intent.putExtra("trainShortSign", "red line to beaverton");
            startActivity(intent);
        } else if (v == orangeLine) {
            orangeLine.setVisibility(View.GONE);
            orangeToCityCenter.setVisibility(View.VISIBLE);
            orangeToMilwaukie.setVisibility(View.VISIBLE);
        } else if (v == orangeToMilwaukie) {
            Intent intent = new Intent(TrainColorActivity.this, StopListActivity.class);
            intent.putExtra("trainColor", "orange");
            intent.putExtra("trainDirection", "milwaukie");
            intent.putExtra("trainDirectionFullSign", "Orange Line to Milwaukie");
            intent.putExtra("trainShortSign", "orange Line to milwaukie");
            startActivity(intent);
        } else if (v == orangeToCityCenter) {
            Intent intent = new Intent(TrainColorActivity.this, StopListActivity.class);
            intent.putExtra("trainColor", "orange");
            intent.putExtra("trainDirection", "city center");
            intent.putExtra("trainDirectionFullSign", "Orange Line to City Center/Expo");
            intent.putExtra("trainShortSign", "orange line to city ctr\\/expo"); // may cause issues
            startActivity(intent);
        } else if (v == yellowLine) {
            yellowLine.setVisibility(View.GONE);
            yellowToCityCenter.setVisibility(View.VISIBLE);
            yellowToExpo.setVisibility(View.VISIBLE);
        } else if (v == yellowToExpo) {
            Intent intent = new Intent(TrainColorActivity.this, StopListActivity.class);
            intent.putExtra("trainColor", "yellow");
            intent.putExtra("trainDirection", "expo");
            intent.putExtra("trainDirectionFullSign", "Yellow Line to Expo Center");
            intent.putExtra("trainShortSign", "yellow line to expo ctr");
            startActivity(intent);
        } else if (v == yellowToCityCenter) {
            Intent intent = new Intent(TrainColorActivity.this, StopListActivity.class);
            intent.putExtra("trainColor", "yellow");
            intent.putExtra("trainDirection", "city center");
            intent.putExtra("trainDirectionFullSign", "Yellow Line to City Center/Milwaukie");
            intent.putExtra("trainShortSign", "yellow line to city ctr\\/milw"); // may cause issues
            startActivity(intent);
        }
    }
}
