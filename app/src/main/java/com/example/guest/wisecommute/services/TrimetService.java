package com.example.guest.wisecommute.services;

import com.example.guest.wisecommute.Constants;
import com.example.guest.wisecommute.models.Train;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 5/31/17.
 */

public class TrimetService {

    public static void findArrivals(String trainColor, String trainStopID, String trainDirection, String trainShortSign, Callback callback) {

        // Set up our credentials
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        // Set up the URL
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.API_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.STOP_QUERY_ID, trainStopID);
        urlBuilder.addQueryParameter(Constants.API_FORMAT, "true");
        urlBuilder.addQueryParameter(Constants.API_KEY_QUERY_PARAMETER, Constants.API_KEY);
        String url = urlBuilder.build().toString();

        // put the url above into a request
        Request request = new Request.Builder()
                .url(url)
                .build();

        // make the API call (client = tools required, request = url + query)
        Call call = client.newCall(request);

        // .enqueue instead of .execute so we will make the api call when our resources are freed up (execute would fire right away)
        call.enqueue(callback);
    }

    public ArrayList<Train> processResults(Response response) {
        ArrayList<Train> trains = new ArrayList<>();

        try {
            // we convert the response to a string, to make sure the response is in JSON format
            String json = response.body().string();
            if(response.isSuccessful()) {
                JSONObject trimetJSON = new JSONObject(json);
                JSONArray arrivalJSON = trimetJSON.getJSONObject("resultSet").getJSONArray("arrival");
                for(int i = 0; i <= arrivalJSON.length(); i++) {
                    JSONObject trainJSON = arrivalJSON.getJSONObject(i);
                    // pull out all the train info
                    String shortSign = trainJSON.getString("shortSign");
                    String fullSign = trainJSON.getString("fullSign");
                    int estimated = trainJSON.getInt("estimated");
                    int scheduled = trainJSON.getInt("scheduled");
                    String id = trainJSON.getString("id");
                    int locID = trainJSON.getInt("locid");


                    // use the restaurant model to create a restaurant object then add to our array of restaurants
                    Train train = new Train(shortSign, fullSign, estimated, scheduled, id, locID);
                    trains.add(train);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return trains;
    }
}
