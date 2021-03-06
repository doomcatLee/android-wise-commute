package com.example.guest.wisecommute.models;

public class Tweet {
    private String timeStamp;

    private String day;
    private String text;
    private String username;
    private String screenName;
    private String location;

    public Tweet(String timeStamp, String day, String text, String username, String screenName, String location) {
        this.timeStamp = timeStamp;
        this.day = day;
        this.text = text;
        this.username = username;
        this.screenName = screenName;
        this.location = location;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getText() {
        return text;
    }

    public String getUsername() {
        return username;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getLocation() {
        return location;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


    @Override
    public String toString() {
        return "Tweet{" +
                "timeStamp='" + timeStamp + '\'' +
                ", text='" + text + '\'' +
                ", username='" + username + '\'' +
                ", screenName='" + screenName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
