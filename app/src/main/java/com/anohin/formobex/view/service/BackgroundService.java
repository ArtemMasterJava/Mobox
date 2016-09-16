package com.anohin.formobex.view.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.ContentValues.TAG;

///**
// * Created by Artem on 16.09.2016.
// */
public class BackgroundService extends Service {
    private boolean isRunning;
    private Context context;
    private Thread backgroundThread;
    private Handler mHandler;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        this.context = this;
        this.isRunning = false;
        this.backgroundThread = new Thread(myTask);
        mHandler = new Handler();
        mHandler.post(myTask);
    }


    private Runnable myTask = new Runnable() {

        public void run() {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String currentDateandTime = sdf.format(new Date());
            Log.v(TAG, "Текущее время:" + " " + currentDateandTime);
//            Toast toast = Toast.makeText(, "Helloo world " + currentDateandTime, Toast.LENGTH_LONG);
//            toast.setGravity(Gravity.CENTER, 0, 0);
//            toast.show();
            mHandler.postDelayed(this, 12000);
        }
    };


    @Override
    public void onDestroy() {
        this.isRunning = false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!this.isRunning) {
            this.isRunning = true;
            this.backgroundThread.start();
        }
        return START_STICKY;
    }

}
