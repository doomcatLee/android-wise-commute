package com.example.guest.wisecommute.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.wisecommute.R;
import com.example.guest.wisecommute.RegisterActivity;

import butterknife.ButterKnife;


public class HomeFormFragment extends Fragment {

    private TextView mNextButton;
    private ImageView mBackButton;

    public HomeFormFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ButterKnife.bind(getActivity());
        View view = inflater.inflate(R.layout.fragment_home_form, container, false);
        mNextButton = (TextView) view.findViewById(R.id.btnNext2);
        mBackButton = (ImageView) view.findViewById(R.id.btnBack);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == mBackButton){
                    Intent intent = new Intent(getActivity(), RegisterActivity.class);
                    intent.putExtra("homeBackClicked", "1");
                    startActivity(intent);
                }
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == mNextButton) {
                    Intent intent = new Intent(getActivity(), RegisterActivity.class);
                    intent.putExtra("showWorkFragment", "1");
                    startActivity(intent);
                }
            }
        });
        return view;
    }

}
