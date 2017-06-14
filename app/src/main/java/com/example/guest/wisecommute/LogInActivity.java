package com.example.guest.wisecommute;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guest.wisecommute.interfaces.SearchForMatch;
import com.example.guest.wisecommute.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = LogInActivity.class.getSimpleName();
    @Bind(R.id.btnLogIn) Button btnLogIn;
    @Bind(R.id.btnRegister) Button btnRegister;
    @Bind(R.id.etEmail) EditText etEmail;
    @Bind(R.id.etPassword) EditText etPassword;

    /** Declaring Firebase variable */
    private DatabaseReference userAccounts;

    /** Value Event Listener (Firebase queries) */
    private ValueEventListener searchedUsersReferenceListener;

    /** Firebase Auth */
    private FirebaseAuth mAuth;

    /** Firebase AuthStateListener */
    private FirebaseAuth.AuthStateListener mAuthListener;

    /** Progress Dialog */
    private ProgressDialog mAuthProgressDialog;

    String email;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);

        /** Assigning the Firebase Reference, basically we are telling Firebase where are
         * starting point should be inside our database. For this use case, we want the
         * userAccount variable to hold the reference to the users section of our database. */
        userAccounts = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("users");

        createAuthProgressDialog();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    /** Get the users preferences and save in shared preferences */

                    Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };

        btnLogIn.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
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

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: starts");
        super.onDestroy();
        if(searchedUsersReferenceListener != null) {
            /** Remove listener on destroy so it's not using apps resources */
            userAccounts.removeEventListener(searchedUsersReferenceListener);
        }
        Log.d(TAG, "onDestroy: ends");
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
    }

    @Override
    public void onClick(View v) {
        if(v == btnLogIn) {
            Log.d(TAG, "onClick: btnLogIn clicked");
            loginWithPassword();
//            getUsersFromFirebase(userAccounts, this);
        } else if (v == btnRegister) {
            Log.d(TAG, "onClick: btnRegister clicked");
            Intent intent = new Intent(LogInActivity.this, RegisterActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }

    /** Login Firebase User */
    private void loginWithPassword() {
        email = etEmail.getText().toString().trim();
        pass = etPassword.getText().toString().trim();

        if(email.equals("")) {
            etEmail.setError("Please enter your email");
            return;
        }
        if(pass.equals("")) {
            etPassword.setError("Please enter your password");
            return;
        }
        mAuthProgressDialog.show();
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "onSignInComplete: " + task.isSuccessful());
                        mAuthProgressDialog.dismiss();
                        if(!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(LogInActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    } 

//    @Override
//    public void searchForMatch(ArrayList<User> users) {
//        boolean isFound = false;
//        for (User user : users) {
//            if (user.getEmail().equals(email) && user.getPass().equals(pass)) {
//                isFound = true;
//            }
//        }
//
//        if(!isFound) {
//            // triggered due to other user not matching
//            Toast.makeText(this, "Email or Password don't match records", Toast.LENGTH_LONG).show();
//        } else {
//            Intent intent = new Intent(LogInActivity.this, TrainColorActivity.class);
//            startActivity(intent);
//        }
//    }

    private void getUsersFromFirebase(DatabaseReference reference, final SearchForMatch callback) {

         searchedUsersReferenceListener = reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: starts");
                ArrayList<User> users = new ArrayList<>();
                for(DataSnapshot userSnapShot : dataSnapshot.getChildren()) {
                    /** How to pull out individual fields */
//                    String firstName = (String) userSnapShot.child("firstName").getValue();
//                    String lastName = (String) userSnapShot.child("lastName").getValue();
//                    String email = (String) userSnapShot.child("email").getValue();
//                    String phoneNumber = (String) userSnapShot.child("phoneNumber").getValue();
//                    User user = new User(firstName, lastName, email, phoneNumber);
//                    Log.d(TAG, "onDataChange: user is " + user);

                    /** Pulling out the entire object */
                    User user = userSnapShot.getValue(User.class);

                    /** Adding to users ArrayList */
                    users.add(user);
                }

                Log.d(TAG, "onDataChange: ends");
                callback.searchForMatch(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: Firebase read failed " + databaseError.getMessage());
            }
        });
    }
}
