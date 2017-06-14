package com.example.guest.wisecommute.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.guest.wisecommute.R;
import com.example.guest.wisecommute.RegisterActivity;

import butterknife.ButterKnife;

/**
 * Created by Gun Lee.
 */

public class EmailFormFragment extends Fragment implements View.OnClickListener{

    private TextView mNextButton1;
    private EditText mEmailEditText;
    private static final String TAG = EmailFormFragment.class.getSimpleName();

    public EmailFormFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: starts");
        ButterKnife.bind(getActivity());
        View view = inflater.inflate(R.layout.fragment_email_form, container, false);
        mNextButton1 = (TextView) view.findViewById(R.id.btnNext2);

        Log.d("Log the button",mNextButton1.getText().toString());
        mNextButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == mNextButton1) {
                    Intent intent = new Intent(getActivity(), RegisterActivity.class);
                    intent.putExtra("showPasswordFragment", "1");
                    startActivity(intent);
                }
            }
        });
        Log.d(TAG, "onCreateView: ends");
        return view;
    }


    @Override
    public void onClick(View v) {

    }

}
