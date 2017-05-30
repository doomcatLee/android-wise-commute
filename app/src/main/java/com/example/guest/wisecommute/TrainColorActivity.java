package com.example.guest.wisecommute;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TrainColorActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.greenLine) Button greenLine;
//    @Bind(R.id.greenToClackamas) Button greenToClackamas;
//    @Bind(R.id.greenToCityCenter) Button greenToCityCenter;
//    @Bind(R.id.blueLine) Button blueLine;
//    @Bind(R.id.blueToHillsboro) Button blueToHillsboro;
//    @Bind(R.id.blueToGresham) Button blueToGresham;
//    @Bind(R.id.redLine) Button redLine;
//    @Bind(R.id.redToAirport) Button redToAirport;
//    @Bind(R.id.redToBeaverton) Button redToBeaverton;
//    @Bind(R.id.orangeLine) Button orangeLine;
//    @Bind(R.id.orangeToMilwaukie) Button orangeToMilwaukie;
//    @Bind(R.id.orangeToCityCenter) Button orangeToCityCenter;
//    @Bind(R.id.yellowLine) Button yellowLine;
//    @Bind(R.id.yellowToExpo) Button yellowToExpo;
//    @Bind(R.id.yellowToCityCenter) Button yellowToCityCenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_color);
        ButterKnife.bind(this);

        greenLine.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == greenLine) {
            Toast.makeText(this, "Working!", Toast.LENGTH_SHORT).show();
        }
//        else if (v == greenToClackamas) {
//
//        } else if (v == greenToCityCenter) {
//
//        } else if (v == blueLine) {
//
//        } else if (v == blueToHillsboro) {
//
//        } else if (v == blueToGresham) {
//
//        } else if (v == redLine) {
//
//        } else if (v == redToAirport) {
//
//        } else if (v == redToBeaverton) {
//
//        } else if (v == orangeLine) {
//
//        } else if (v == orangeToMilwaukie) {
//
//        } else if (v == orangeToCityCenter) {
//
//        } else if (v == yellowLine) {
//
//        } else if (v == yellowToExpo) {
//
//        } else if (v == yellowToCityCenter) {
//
//        }
    }
}
