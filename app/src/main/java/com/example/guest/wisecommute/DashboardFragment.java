package com.example.guest.wisecommute;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.wisecommute.models.Stop;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements View.OnClickListener {
    private Spinner sColor;
    private Spinner sDirection;
    private Spinner sHome;
    private Spinner sWork;
    private String homeColor;
    private String workColor;
    private String homeDirection;
    private String workDirection;
    private String workTrainName;
    private String workStopId;
    private String homeTrainName;
    private String homeStopId;
    private String genericTrainColor;
    private String genericTrainDirection;
    private TextView tvSwitchValue;
    private Switch mySwitch;
    private boolean isHomeShowing = false;

    private Button btnSave;

    List<String> trainColorList;
    List<String> trainDirectionList;
    ArrayList<Stop> stopList = new ArrayList<>();
    ArrayList<String> stopNames;
    ArrayList<String> stopIds = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> directionAdapter;
    ArrayAdapter<String> stopAdapter;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onClick(View v) {
        if(v == btnSave) {
//            Log.d(TAG, "homeColor is " + homeColor);
//            Log.d(TAG, "homeDirection is " + homeDirection);
//            Log.d(TAG, "workColor is " + workColor);
//            Log.d(TAG, "workDirection is " + workDirection);
            /** Firebase Save method call and SharedPreferences */
            if(!isHomeShowing) {
                /** Home Saving */
                Toast.makeText(getActivity(), "Home preference saved.", Toast.LENGTH_LONG).show();
            } else {
                /** Work Saving */
                Toast.makeText(getActivity(), "Work preference saved.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        sColor = (Spinner) view.findViewById(R.id.sColor);
        sDirection = (Spinner) view.findViewById(R.id.sDirection);
        sHome = (Spinner) view.findViewById(R.id.sHome);
        tvSwitchValue = (TextView) view.findViewById(R.id.tvSeekBarValue);
        mySwitch = (Switch) view.findViewById(R.id.switch1);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    tvSwitchValue.setText("Work");
                    isHomeShowing = true;
                } else {
                    tvSwitchValue.setText("Home");
                    isHomeShowing = false;
                }
            }
        });


        trainColorList = new ArrayList<>();
        trainColorList.add("Choose a Train Line");
        trainColorList.add("Green Line");
        trainColorList.add("Blue Line");
        trainColorList.add("Red Line");
        trainColorList.add("Yellow Line");
        trainColorList.add("Orange Line");


        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, trainColorList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sColor.setAdapter(adapter);

        sColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stopNames = new ArrayList<>();
                String selected = sColor.getSelectedItem().toString();
                genericTrainColor = selected;
                if(!isHomeShowing) {
                    homeColor = selected;
                } else {
                    workColor = selected;
                }
                if (selected.equals("Green Line")) {
                    List<String> trainDirectionList = new ArrayList<>();
                    trainDirectionList.add("To Clackamas");
                    trainDirectionList.add("To City Center");

                    directionAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, trainDirectionList);
                    directionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sDirection.setAdapter(directionAdapter);
                } else if (selected.equals("Blue Line")) {
                    List<String> trainDirectionList = new ArrayList<>();
                    trainDirectionList.add("To Gresham");
                    trainDirectionList.add("To Hillsboro");

                    directionAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, trainDirectionList);
                    directionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sDirection.setAdapter(directionAdapter);
                } else if (selected.equals("Red Line")) {
                    List<String> trainDirectionList = new ArrayList<>();
                    trainDirectionList.add("To Beaverton");
                    trainDirectionList.add("To Airport");

                    directionAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, trainDirectionList);
                    directionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sDirection.setAdapter(directionAdapter);
                } else if (selected.equals("Yellow Line")) {
                    List<String> trainDirectionList = new ArrayList<>();
                    trainDirectionList.add("To Expo");
                    trainDirectionList.add("To City Center");

                    directionAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, trainDirectionList);
                    directionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sDirection.setAdapter(directionAdapter);
                } else if (selected.equals("Orange Line")) {
                    List<String> trainDirectionList = new ArrayList<>();
                    trainDirectionList.add("To Milwaukie");
                    trainDirectionList.add("To City Center");

                    directionAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, trainDirectionList);
                    directionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sDirection.setAdapter(directionAdapter);
                } else {
                    List<String> trainDirectionList = new ArrayList<>();

                    directionAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, trainDirectionList);
                    directionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sDirection.setAdapter(directionAdapter);

                    ArrayList<String> empty = new ArrayList<>();
                    stopAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, empty);
                    stopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sHome.setAdapter(stopAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sDirection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = sDirection.getSelectedItem().toString();
                genericTrainDirection = selected;
                if(!isHomeShowing) {
                    homeDirection = selected;
                } else {
                    workDirection = selected;
                }

                if (genericTrainColor.equals("Green Line") && genericTrainDirection.equals("To Clackamas")) {
                    setStopList(StopConstants.greenToClackamasStops);
                    stopAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, stopNames);
                    stopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sHome.setAdapter(stopAdapter);
                } else if (genericTrainColor.equals("Green Line") && genericTrainDirection.equals("To City Center")) {
                    setStopList(StopConstants.greenToCityCenterStops);
                    stopAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, stopNames);
                    stopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sHome.setAdapter(stopAdapter);
                } else if (genericTrainColor.equals("Blue Line") && genericTrainDirection.equals("To Gresham")) {
                    setStopList(StopConstants.blueToHillsboroStops);
                    stopAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, stopNames);
                    stopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sHome.setAdapter(stopAdapter);
                } else if (genericTrainColor.equals("Blue Line") && genericTrainDirection.equals("To Hillsboro")) {
                    setStopList(StopConstants.blueToGreshamStops);
                    stopAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, stopNames);
                    stopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sHome.setAdapter(stopAdapter);
                } else if (genericTrainColor.equals("Red Line") && genericTrainDirection.equals("To Beaverton")) {
                    setStopList(StopConstants.redToAirportStops);
                    stopAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, stopNames);
                    stopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sHome.setAdapter(stopAdapter);
                } else if (genericTrainColor.equals("Red Line") && genericTrainDirection.equals("To Airport")) {
                    setStopList(StopConstants.redToBeavertonStops);
                    stopAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, stopNames);
                    stopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sHome.setAdapter(stopAdapter);
                } else if (genericTrainColor.equals("Orange Line") && genericTrainDirection.equals("To Milwaukie")) {
                    setStopList(StopConstants.orangeToMilwaukieStops);
                    stopAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, stopNames);
                    stopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sHome.setAdapter(stopAdapter);
                } else if (genericTrainColor.equals("Orange Line") && genericTrainDirection.equals("To City Center")) {
                    setStopList(StopConstants.orangeToCityCenterStops);
                    stopAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, stopNames);
                    stopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sHome.setAdapter(stopAdapter);
                } else if (genericTrainColor.equals("Yellow Line") && genericTrainDirection.equals("To Expo")) {
                    setStopList(StopConstants.yellowToExpoStops);
                    stopAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, stopNames);
                    stopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sHome.setAdapter(stopAdapter);
                } else if (genericTrainColor.equals("Yellow Line") && genericTrainDirection.equals("To City Center")) {
                    setStopList(StopConstants.yellowToCityCenterStops);
                    stopAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, stopNames);
                    stopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sHome.setAdapter(stopAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /** Spinner for Stop List */
        sHome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = sHome.getSelectedItem().toString();

                if(!isHomeShowing) {
                    homeTrainName = selected;
                    homeStopId = searchForStopId(homeTrainName);
                } else {
                    workTrainName = selected;
                    workStopId = searchForStopId(workTrainName);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public String searchForStopId(String trainName) {
        int stopIndex = 0;
        for(int i = 0; i < stopNames.size(); i++) {
            if(trainName.equals(stopNames.get(i))) {
                stopIndex = i;
            }
        }
        String stopId = stopIds.get(stopIndex);
        return stopId;
    }

    public void setStopList(Stop[] stops) {
        for(int i = 0; i < stops.length; i++) {
            String stopName = stops[i].getStopName();
            String stopId = stops[i].getStopId();

            stopNames.add(stopName);
            stopIds.add(stopId);
            stopList.add(stops[i]);
        }
    }
}
