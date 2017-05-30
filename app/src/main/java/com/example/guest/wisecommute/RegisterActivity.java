package com.example.guest.wisecommute;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.btnRegister) Button btnRegister;
    @Bind(R.id.btnLogIn) Button btnLogIn;
    @Bind(R.id.etUsername) EditText etUsername;
    @Bind(R.id.etPassword) EditText etPassword;
    @Bind(R.id.etPasswordConfirm) EditText etPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        TextView[] textViews = {etUsername, etPassword, etPasswordConfirm};

        for(TextView textView : textViews) {
            // set text view font
        }

        btnLogIn.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnRegister) {
            if(!etUsername.getText().toString().equals("") && !etPassword.getText().toString().equals("") && !etPasswordConfirm.getText().toString().equals("")) {
                if(etPassword.getText().toString().equals(etPasswordConfirm.getText().toString())) {
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Passwords don't match", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "All fields must be filled out", Toast.LENGTH_LONG).show();
            }
        } else if (v == btnLogIn) {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
