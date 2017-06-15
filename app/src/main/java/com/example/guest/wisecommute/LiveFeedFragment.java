package com.example.guest.wisecommute;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guest.wisecommute.adapters.TwitterAdapter;
import com.example.guest.wisecommute.models.Tweet;
import com.example.guest.wisecommute.services.TwitterService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LiveFeedFragment extends Fragment {
    private static final String TAG = LiveFeedFragment.class.getSimpleName();
    @Bind(R.id.tvTitle) TextView tvStopName;
    private TwitterAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Context mContext;

    public ArrayList<Tweet> mTweets = new ArrayList<>();

    public LiveFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ButterKnife.bind(getActivity());
        Log.d(TAG, "onCreateView: getActivity " + getActivity());

        getTweets("trimet", "10");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_live_feed, container, false);
    }

    private void getTweets(String screenName, String count) {
        Log.d(TAG, "getTweets: starts");
        final TwitterService twitterService = new TwitterService();
        twitterService.findTweets(screenName, count, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.e(TAG, "onFailure: API Call failed with " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, "onResponse: starts");
                mTweets = twitterService.processResults(response);
                Log.d(TAG, "onResponse: mTweets is " + mTweets);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.twitterRecyclerView);
                        mAdapter = new TwitterAdapter(getActivity(), mTweets);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        if(mRecyclerView != null) {
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setHasFixedSize(true);
                        } else {
                            Log.d(TAG, "run: RecyclerView is null bro..");
                        }

                        for(Tweet tweet : mTweets) {
                            Log.d(TAG, "Twitter - TimeStamp: " + tweet.getTimeStamp());
                            Log.d(TAG, "Twitter - ScreenName: " + tweet.getScreenName());
                            Log.d(TAG, "Twitter - Location: " + tweet.getLocation());
                            Log.d(TAG, "Twitter - Text: " + tweet.getText());
                            Log.d(TAG, "Twitter - Name: " + tweet.getUsername());
                        }
                    }
                });
            }
        });
        Log.d(TAG, "getTrains: ends");
    }

}
