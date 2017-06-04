package com.example.guest.wisecommute;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StopListActivity extends AppCompatActivity {

    public static final String TAG = StopListActivity.class.getSimpleName();
    @Bind(R.id.stopListView) ListView lvStopList;
    @Bind(R.id.tvTrainDirectionFullSign) TextView tvTrainDirectionFullSign;

    // hashmap (northbound/southbound -> stopList array)
    String[] stopNames = {"Gateway", "Lents/Foster", "Holgate"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        final String trainColor = intent.getStringExtra("trainColor");
        final String trainDirection = intent.getStringExtra("trainDirection");
        final String trainDirectionFullSign = intent.getStringExtra("trainDirectionFullSign");
        final String trainShortSign = intent.getStringExtra("trainShortSign");

        tvTrainDirectionFullSign.setText(trainDirectionFullSign);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stopNames);
        lvStopList.setAdapter(adapter);

        lvStopList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(StopListActivity.this, TrainFragmentDetail.class);
                intent.putExtra("trainColor", trainColor);
                intent.putExtra("stopName", ((TextView)view).getText());
                intent.putExtra("trainDirection", trainDirection);
                intent.putExtra("trainDirectionFullSign", trainDirectionFullSign);
                intent.putExtra("trainShortSign", trainShortSign);
                startActivity(intent);
            }
        });
    }
}
