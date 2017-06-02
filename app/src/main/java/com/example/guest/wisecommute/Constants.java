package com.example.guest.wisecommute;

/**
 * Created by Guest on 5/31/17.
 */

public class Constants {
    public static final String API_KEY = BuildConfig.Trimet_API_KEY;
    public static final String API_BASE_URL = "https://developer.trimet.org/ws/v2/arrivals?locIDs=";
    public static final String STOP_QUERY_ID = "locIDs"; //Example: "location"
    public static final String API_FORMAT = "json";
    public static final String API_KEY_QUERY_PARAMETER = "appID";
}
