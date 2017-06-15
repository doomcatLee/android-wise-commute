package com.example.guest.wisecommute.services;

import android.util.Log;

import com.example.guest.wisecommute.Constants;
import com.example.guest.wisecommute.models.Tweet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;


public class TwitterService {
    private static final String TAG = TwitterService.class.getSimpleName();

    public static void findTweets(String screenName, String count, Callback callback) {

        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(Constants.TWITTER_CONSUMER_KEY, Constants.TWITTER_CONSUMER_SECRET);
        consumer.setTokenWithSecret(Constants.TWITTER_TOKEN_KEY, Constants.TWITTER_TOKEN_SECRET);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.TWITTER_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.SCREEN_NAME_QUERY, screenName).addQueryParameter(Constants.TWITTER_COUNT_QUERY, count);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Log.d(TAG, "findTweets: request is " + request);

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Tweet> processResults(Response response) {
        Log.d(TAG, "processResults: working");
        ArrayList<Tweet> tweets = new ArrayList<>();

        try {
            String json = response.body().string();
            if(response.isSuccessful()) {
                JSONArray twitterJSON = new JSONArray(json);
                for(int i = 0; i <= twitterJSON.length(); i++) {
                    JSONObject tweetJSON = twitterJSON.getJSONObject(i);

                    String timeStamp = timeFilter(tweetJSON.getString("created_at"));
                    String text = tweetJSON.getString("text");
                    String name = tweetJSON.getJSONObject("user").getString("name");
                    String screenName = tweetJSON.getJSONObject("user").getString("screen_name");
                    String location = tweetJSON.getJSONObject("user").getString("location");

                    Tweet tweet = new Tweet(timeStamp, text, name, screenName, location);
                    Log.d(TAG, "TIMEstamp = " +timeStamp);
                    tweets.add(tweet);
                }
            } else {
                Log.d(TAG, "processResults: Response not successful!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tweets;
    }

    public String timeFilter(String t){
        String output="";
        String hour = t.substring(11, 13);
        String min = t.substring(14, 16);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
            Date dateObj = sdf.parse(hour + ":" + min);
            output = new SimpleDateFormat("h:mm a").format(dateObj);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return output;
    }


}
