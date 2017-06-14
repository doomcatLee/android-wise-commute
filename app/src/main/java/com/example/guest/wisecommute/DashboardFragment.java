package com.example.guest.wisecommute;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {
    @Bind(R.id.sColor) Spinner sColor;
    @Bind(R.id.sDirection) Spinner sDirection;
    @Bind(R.id.sHome) Spinner sHome;
    @Bind(R.id.sWork) Spinner sWork;


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ButterKnife.bind(getActivity());

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

}
