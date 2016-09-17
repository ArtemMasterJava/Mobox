package com.anohin.formobex.view.service;
import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;


public class DateService extends IntentService {

    public static final String EXTRA_MESSAGE = "message";
    private Handler handler;

    public DateService() {
        super("DateService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler = new Handler();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        while (true) {
            synchronized (this) {
                {
                    try {
                        wait(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            String text = intent.getStringExtra(EXTRA_MESSAGE);
            showDate(text);
        }
    }

    private void showDate(final String text) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}