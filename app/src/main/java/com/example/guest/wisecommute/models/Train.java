package com.example.guest.wisecommute.models;

/**
 * Created by Guest on 5/31/17.
 */

public class Train {
    private String shortSign;
    private String fullSign;
    private String estimated;
    private String scheduled;
    private String id;
    private int locID;

    public Train(String shortSign, String fullSign, String estimated, String scheduled, String id, int locID) {
        this.shortSign = shortSign;
        this.fullSign = fullSign;
        this.estimated = estimated;
        this.scheduled = scheduled;
        this.id = id;
        this.locID = locID;
    }

    public String getShortSign() {
        return shortSign;
    }

    public String getFullSign() {
        return fullSign;
    }

    public String getEstimated() {
        return estimated;
    }

    public String getScheduled() {
        return scheduled;
    }

    public String getId() {
        return id;
    }

    public int getLocID() {
        return locID;
    }

    @Override
    public String toString() {
        return "Train{" +
                "shortSign='" + shortSign + '\'' +
                ", fullSign='" + fullSign + '\'' +
                ", estimated=" + estimated +
                ", scheduled=" + scheduled +
                ", id='" + id + '\'' +
                ", locID=" + locID +
                '}';
    }

    public void getDelay() {
        // do math between estimated and scheduled time
    }
}
