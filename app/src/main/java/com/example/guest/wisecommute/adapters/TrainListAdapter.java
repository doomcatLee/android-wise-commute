package com.example.guest.wisecommute.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guest.wisecommute.R;
import com.example.guest.wisecommute.models.Train;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TrainListAdapter extends RecyclerView.Adapter<TrainListAdapter.TrainViewHolder> {
    private ArrayList<Train> mTrains = new ArrayList<>();
    private Context mContext;

    public TrainListAdapter(Context context, ArrayList<Train> trains) {
        mContext = context;
        mTrains = trains;
    }

    // View Holder which holds the train list item (train arrival information format)
    @Override
    public TrainListAdapter.TrainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.train_list_item, parent, false);
        TrainViewHolder viewHolder = new TrainViewHolder(view);
        return viewHolder;
    }

    // updates the contents of the ItemView to reflect the restaurant in the given position
    @Override
    public void onBindViewHolder(TrainViewHolder holder, int position) {
        holder.bindTrain(mTrains.get(position));
    }

    @Override
    public int getItemCount() {
        return mTrains.size();
    }

    // ViewHolder will find the views and set their values for the item in the list
    public class TrainViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tvStopName) TextView tvStopName;
        private Context mContext;

        public TrainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindTrain(Train train) {
            tvStopName.setText(train.getShortSign());
        }
    }
}
