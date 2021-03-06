package com.example.jerometian.servicebestpractice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Date;

/**
 * Created by jjtian on 2015/11/6.
 */
public class LongRunningService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("LongRunningService","executed at:" + new Date().toString());
            }
        }).start();

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        int anHour = 1* 60 *1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;

        Intent intent1 = new Intent(this,AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this,0,intent1,0);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        return super.onStartCommand(intent1, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
