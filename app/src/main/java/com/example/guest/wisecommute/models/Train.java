package com.example.guest.wisecommute.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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

    public String getScheduledTime() {
        /** Scheduled Time (hours) */
        Long scheduled = Long.parseLong(this.getScheduled());
        Date scheduledDate = new Date(scheduled);
        DateFormat scheduledHoursFormat = new SimpleDateFormat("hh");
        scheduledHoursFormat.setTimeZone(TimeZone.getTimeZone("PST"));
        String scheduledHours = scheduledHoursFormat.format(scheduledDate);
        Integer convertedHours = Integer.parseInt(scheduledHours) + 5;

        /** Scheduled Time (minutes) */
        DateFormat scheduledMinutesFormat = new SimpleDateFormat("mm");
        scheduledMinutesFormat.setTimeZone(TimeZone.getTimeZone("PST"));
        String scheduledMinutes = scheduledMinutesFormat.format(scheduledDate);

        return convertedHours + ":" + scheduledMinutes;
    }

    public String getEstimatedMinutes() {
        Long estimated = Long.parseLong(this.getEstimated());
        Date startDate = new Date();
        Date date = new Date(estimated);
        long difference = date.getTime() - startDate.getTime();
        Date differenceDate = new Date(difference);
        DateFormat format = new SimpleDateFormat("mm");
        format.setTimeZone(TimeZone.getTimeZone("UTC-8:00"));
        String estimatedMinutes = format.format(differenceDate);

        String result = "";
        if(Integer.parseInt(estimatedMinutes) > 1) {
            result = "minutes";
        } else {
            result = "minute";
        }
        return estimatedMinutes + " " + result;
    }
}
