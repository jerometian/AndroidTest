package com.example.jerometian.servicebestpractice;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by jjtian on 2015/11/6.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i= new Intent(context,LongRunningService.class);
        context.startService(i);
    }
}
