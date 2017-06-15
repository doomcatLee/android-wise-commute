package com.example.guest.wisecommute;

public class Constants {
    /** Trimet API */
    public static final String API_KEY = BuildConfig.Trimet_API_KEY;
    public static final String API_BASE_URL = "https://developer.trimet.org/ws/v2/arrivals?";
    public static final String STOP_QUERY_ID = "locIDs"; //Example: "location"
    public static final String API_FORMAT = "json";
    public static final String API_KEY_QUERY_PARAMETER = "appID";

    /** Twitter API */
    /** Example Request: https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=twitterapi&count=2 */
    public static final String TWITTER_CONSUMER_KEY = BuildConfig.Twitter_CONSUMER_KEY;
    public static final String TWITTER_CONSUMER_SECRET = BuildConfig.Twitter_CONSUMER_SECRET;
    public static final String TWITTER_TOKEN_KEY = BuildConfig.Twitter_TOKEN_KEY;
    public static final String TWITTER_TOKEN_SECRET = BuildConfig.Twitter_TOKEN_SECRET;
    public static final String TWITTER_BASE_URL = "https://api.twitter.com/1.1/statuses/user_timeline.json";
    public static final String SCREEN_NAME_QUERY = "screen_name";
    public static final String TWITTER_COUNT_QUERY = "count";
}
