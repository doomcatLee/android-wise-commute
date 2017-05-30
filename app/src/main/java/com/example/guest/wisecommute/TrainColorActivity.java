package com.example.guest.wisecommute;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TrainColorActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.greenTrain) Button greenTrain;
    @Bind(R.id.greenToClackamas) Button greenToClackamas;
    @Bind(R.id.greenToCityCenter) Button greenToCityCenter;
    @Bind(R.id.blueTrain) Button blueTrain;
    @Bind(R.id.blueToHillsboro) Button blueToHillsboro;
    @Bind(R.id.blueToGresham) Button blueToGresham;
    @Bind(R.id.redTrain) Button redTrain;
    @Bind(R.id.redToAirport) Button redToAirport;
    @Bind(R.id.redToBeaverton) Button redToBeaverton;
    @Bind(R.id.orangeTrain) Button orangeTrain;
    @Bind(R.id.orangeToMilwaukie) Button orangeToMilwaukie;
    @Bind(R.id.orangeToCityCenter) Button orangeToCityCenter;
    @Bind(R.id.yellowTrain) Button yellowTrain;
    @Bind(R.id.yellowToExpo) Button yellowToExpo;
    @Bind(R.id.yellowToCityCenter) Button yellowToCityCenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_color);
        ButterKnife.bind(this);

    }

    @Override
    public void onClick(View v) {
        if(v == greenTrain) {

        } else if (v == greenToClackamas) {

        } else if (v == greenToCityCenter) {

        } else if (v == blueTrain) {

        } else if (v == blueToHillsboro) {

        } else if (v == blueToGresham) {

        } else if (v == redTrain) {

        } else if (v == redToAirport) {

        } else if (v == redToBeaverton) {

        } else if (v == orangeTrain) {

        } else if (v == orangeToMilwaukie) {

        } else if (v == orangeToCityCenter) {

        } else if (v == yellowTrain) {

        } else if (v == yellowToExpo) {

        } else if (v == yellowToCityCenter) {

        }
    }
}
