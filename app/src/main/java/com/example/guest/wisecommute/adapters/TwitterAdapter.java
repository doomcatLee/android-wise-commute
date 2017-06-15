package com.example.guest.wisecommute.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guest.wisecommute.R;
import com.example.guest.wisecommute.models.Tweet;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by administrator on 6/12/17.
 */

public class TwitterAdapter extends RecyclerView.Adapter<TwitterAdapter.TwitterViewHolder> {
    private ArrayList<Tweet> mTweets = new ArrayList<>();
    private Context mContext;

    public TwitterAdapter(Context context, ArrayList<Tweet> tweets) {
        mContext = context;
        mTweets = tweets;
    }

    // View Holder which holds the train list item (train arrival information format)
    @Override
    public TwitterAdapter.TwitterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.twitter_list_item, parent, false);
        TwitterViewHolder viewHolder = new TwitterViewHolder(view);
        return viewHolder;
    }

    // updates the contents of the ItemView to reflect the restaurant in the given position
    @Override
    public void onBindViewHolder(TwitterAdapter.TwitterViewHolder holder, int position) {
        holder.bindTweet(mTweets.get(position));
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    // ViewHolder will find the views and set their values for the item in the list
    public class TwitterViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tvText) TextView tvText;
        @Bind(R.id.tvTimeStamp) TextView tvTimeStamp;
        @Bind(R.id.tvScreenName) TextView tvScreenName;
//        @Bind(R.id.tvName) TextView tvName;

        public TwitterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindTweet(Tweet tweet) {
            tvText.setText(tweet.getText());
            tvScreenName.setText("@" + tweet.getScreenName());
//            tvName.setText(tweet.getUsername());
            tvTimeStamp.setText(tweet.getTimeStamp());
        }
    }
}
