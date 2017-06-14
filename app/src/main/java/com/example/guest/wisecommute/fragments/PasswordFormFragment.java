package com.example.guest.wisecommute.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.wisecommute.R;

public class PasswordFormFragment extends Fragment {

    private static final String TAG = EmailFormFragment.class.getSimpleName();
    private TextView mNextButton;
    private ImageView mBackButton;
    private EditText mPassword;
    private EditText mPasswordConfrim;

    public PasswordFormFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_password_form, container, false);
    }
}
