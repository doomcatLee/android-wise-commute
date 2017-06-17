package com.example.guest.wisecommute;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.guest.wisecommute.models.Timer;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener{

    Button mRegisterButton;
    TextView mLoginButton;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        mRegisterButton = (Button) findViewById(R.id.registerButton);
        mRegisterButton.setOnClickListener(this);

        mLoginButton = (TextView) findViewById(R.id.loginTextView);
        mLoginButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if (v == mRegisterButton){
            Intent intent = new Intent(IntroActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
        if(v == mLoginButton){
            Intent intent = new Intent(IntroActivity.this,LogInActivity.class);
            startActivity(intent);
        }
    }
}
