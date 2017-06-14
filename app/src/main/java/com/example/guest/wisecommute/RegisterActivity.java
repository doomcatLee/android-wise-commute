package com.example.guest.wisecommute;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.wisecommute.interfaces.Firebase;
import com.example.guest.wisecommute.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, Firebase {
    private static final String TAG = RegisterActivity.class.getSimpleName();
//    @Bind(R.id.btnRegister) Button btnRegister;
//    @Bind(R.id.etEmail) EditText etEmail;
//    @Bind(R.id.etPassword) EditText etPassword;
//    @Bind(R.id.etPasswordConfirm) EditText etPasswordConfirm;

    private DatabaseReference userAccounts;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    /** Progress Dialog */
    private ProgressDialog mAuthProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: starts");
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        createAuthStateListener();
        createAuthProgressDialog();

        /** Assigning the Firebase Reference, basically we are telling Firebase where are
         * starting point should be inside our database. For this use case, we want the
         * userAccount variable to hold the reference to the users section of our database. */
        userAccounts = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("users");

//        TextView[] textViews = {etEmail, etPassword}; //removed passwordConfim

//        for(TextView textView : textViews) {
//            // set text view font
//        }

//        btnRegister.setOnClickListener(this);

        Log.d(TAG, "onCreate: ends");
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
    }

    @Override
    public void onClick(View v) {
//    '
    }

    private void createNewUser() {
        final String email = "placerhiolder"; //etEmail.getText().toString().trim();
        String password = "placeholder"; //etPassword.getText().toString().trim();
        String password2 = "placeholder"; //etPasswordConfirm.getText().toString().trim();

        if(!email.equals("") && !password.equals("") && !password2.equals("")) {
            if (password.equals(password2)) {
                mAuthProgressDialog.show();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "Authentication is successful");
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    String userEmail = user.getEmail();
                                    String userName = user.getDisplayName();
                                    String userUid = user.getUid();
                                    Uri userProfileImage = user.getPhotoUrl();

                                    User newUser = new User(userEmail, userName, userUid, userProfileImage);
                                    saveUserToFirebase(newUser);
                                    mAuthProgressDialog.dismiss();
                                } else {
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                Toast.makeText(this, "Password's don't match", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "All fields must be filled out", Toast.LENGTH_SHORT).show();
        }
    }

    private void createAuthStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(RegisterActivity.this, TrainColorActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    public void saveUserToFirebase(User user) {
        /** Store the user object in our users section of our database */
        userAccounts.push().setValue(user);
    }
}
