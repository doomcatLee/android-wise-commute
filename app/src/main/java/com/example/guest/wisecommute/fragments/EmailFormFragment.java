package com.example.guest.wisecommute.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.guest.wisecommute.R;

/**
 * Created by Gun Lee.
 */

public class EmailFormFragment extends Fragment {

    private TextView mNextButton;
    private EditText mEmailEditText;
    private static final String TAG = EmailFormFragment.class.getSimpleName();

    public EmailFormFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_email_form, container, false);
    }




}
