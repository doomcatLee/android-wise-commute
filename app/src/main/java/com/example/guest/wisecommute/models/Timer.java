package com.example.guest.wisecommute.models;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.guest.wisecommute.R;

public class Timer {

    public int seconds;
    public boolean running;
    public boolean wasRunning;

    public Timer(int s){
        seconds = s;
    }

    public void runTimer(final TextView time, final TextView circle, Context context){

//        Resources res = time.getResources();
//        final Drawable greenCircle = res.getDrawable(R.drawable.greencircle);
//        final Drawable redCircle= res.getDrawable(R.drawable.redcircle);
//        final Drawable yellowCircle = getResources().getDrawable(R.drawable.yellowcircle);

        final Drawable greenCircle = ContextCompat.getDrawable(context, R.drawable.greencircle);
        final Drawable yellowCircle = ContextCompat.getDrawable(context, R.drawable.greencircle);
        final Drawable redCircle = ContextCompat.getDrawable(context, R.drawable.greencircle);

        final Handler handler = new Handler();
        handler.post(new Runnable(){

            @Override
            public void run() {
                int minutes = (seconds%3600)/60;
                int secs =  seconds%60;
                Log.d("mins", Integer.toString(minutes));
                Log.d("secs", Integer.toString(secs));
                String timeFormatted = String.format("%02d:%02d", minutes, secs);
                time.setText(timeFormatted);
                if (running){
                    if (seconds > 600 && seconds < 3600){
                        circle.setBackground(greenCircle);
                    }else if (seconds > 120 && seconds < 600){
                        circle.setBackground(yellowCircle);
                    }else if (seconds > 1 && seconds < 120){
                        circle.setBackground(redCircle);
                    }else{
                        running = false;
                    }
                    seconds--;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void onClickStart(View v){
        running = true;

    }

    public void onClickStop(View v){
        running = false;

    }

    public void onClickReset(View v){
        running = false;
        seconds = 0;

    }
}