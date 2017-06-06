package com.example.guest.wisecommute.models;

/**
 * Created by administrator on 6/6/17.
 */

public class Stop {

    private String stopName;
    private String stopId;

    public Stop() { super(); }

    public Stop(String stopName, String stopId) {
        this.stopName = stopName;
        this.stopId = stopId;
    }

    public String getStopName() {
        return stopName;
    }

    public String getStopId() {
        return stopId;
    }

    @Override
    public String toString() {
        return "Stop{" +
                "stopName='" + stopName + '\'' +
                ", stopId='" + stopId + '\'' +
                '}';
    }
}
