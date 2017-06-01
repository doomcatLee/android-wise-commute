package com.example.guest.wisecommute;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guest.wisecommute.adapters.TrainListAdapter;
import com.example.guest.wisecommute.models.Train;
import com.example.guest.wisecommute.services.TrimetService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class StopListActivity extends AppCompatActivity {

    public static final String TAG = StopListActivity.class.getSimpleName();
    @Bind(R.id.stopListView) ListView mStopListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String trainColor = intent.getStringExtra("trainColor");
        String trainDirection = intent.getStringExtra("trainDirection");
        String trainShortSign = intent.getStringExtra("trainShortSign");


//        mStopListView.setAdapter();
    }
}
