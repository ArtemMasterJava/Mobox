package com.anohin.formobex.view.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

///**
// * Created by Artem on 16.09.2016.
// */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent background = new Intent(context, BackgroundService.class);
        context.startService(background);
    }
}
