package com.example.guest.wisecommute.models;

/**
 * Created by Guest on 5/31/17.
 */

public class Train {
    private String shortSign;
    private String fullSign;
    private int estimated;
    private int scheduled;
    private String id;
    private int locID;

    public Train(String shortSign, String fullSign, int estimated, int scheduled, String id, int locID) {
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

    public int getEstimated() {
        return estimated;
    }

    public int getScheduled() {
        return scheduled;
    }

    public String getId() {
        return id;
    }

    public int getLocID() {
        return locID;
    }
}
