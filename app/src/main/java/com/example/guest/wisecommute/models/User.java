package com.example.guest.wisecommute.models;

import android.net.Uri;


public class User {
    private String email;
    private String name;
    private String uid;
    private Uri profileImage;
    private String pWorkStopName;
    private String pWorkStopID;
    private String pWorkTrainColor;
    private String pWorkDirection;
    private String pHomeStopName;
    private String pHomeStopID;
    private String pHomeTrainColor;
    private String pHomeDirection;

    public User(String email, String name, String uid, Uri profileImage) {
        this.email = email;
        this.name = name;
        this.uid = uid;
        this.profileImage = profileImage;
        this.pWorkStopName = "";
        this.pWorkStopID = "";
        this.pWorkTrainColor = "";
        this.pWorkDirection = "";
        this.pHomeStopName = "";
        this.pHomeStopID = "";
        this.pHomeTrainColor = "";
        this.pHomeDirection = "";
    }

    public void setWorkPreferences(String pWorkStopName, String pWorkStopID, String pWorkTrainColor, String pWorkDirection) {
        this.pWorkStopName = pWorkStopName;
        this.pWorkStopID = pWorkStopID;
        this.pWorkTrainColor = pWorkTrainColor;
        this.pWorkDirection = pWorkDirection;
    }

    public void setHomePreferences(String pHomeStopName, String pHomeStopID, String pHomeTrainColor, String pHomeDirection) {
        this.pHomeStopName = pHomeStopName;
        this.pHomeStopID = pHomeStopID;
        this.pHomeTrainColor = pHomeTrainColor;
        this.pHomeDirection = pHomeDirection;
    }

    public String getpWorkStopName() {
        return pWorkStopName;
    }

    public String getpWorkStopID() {
        return pWorkStopID;
    }

    public String getpWorkTrainColor() {
        return pWorkTrainColor;
    }

    public String getpWorkDirection() {
        return pWorkDirection;
    }

    public String getpHomeStopName() {
        return pHomeStopName;
    }

    public String getpHomeStopID() {
        return pHomeStopID;
    }

    public String getpHomeTrainColor() {
        return pHomeTrainColor;
    }

    public String getpHomeDirection() {
        return pHomeDirection;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public Uri getProfileImage() {
        return profileImage;
    }
}
