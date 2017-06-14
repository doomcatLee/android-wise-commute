package com.example.guest.wisecommute.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.wisecommute.R;
import com.example.guest.wisecommute.RegisterActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PasswordFormFragment extends Fragment {

    private static final String TAG = EmailFormFragment.class.getSimpleName();
    private ImageView mBackButton;
    @Bind(R.id.btnNext2) TextView mNextButton;

    public PasswordFormFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ButterKnife.bind(getActivity());
        View view = inflater.inflate(R.layout.fragment_password_form, container, false);
        mNextButton = (TextView) view.findViewById(R.id.btnNext2);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == mNextButton) {
                    Intent intent = new Intent(getActivity(), RegisterActivity.class);
                    intent.putExtra("showHomeFragment", "1");
                    startActivity(intent);
                }
            }
        });
        return view;
    }
}
