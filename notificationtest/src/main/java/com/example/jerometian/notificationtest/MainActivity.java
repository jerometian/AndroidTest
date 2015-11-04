package com.example.jerometian.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by jjtian on 2015/11/4.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button sendNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendNotice = (Button)findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.send_notice:
                NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

                Notification notification = new Notification.Builder(getBaseContext())
                        .setContentTitle("This is ticker title")
                        .setSmallIcon(R.drawable.avatar_enterprise_vip)
                        .setWhen(System.currentTimeMillis())
                        .setContentText("this is ticker text")
                        .build();
                Intent intent = new Intent(this,NotificationActivity.class);
                PendingIntent pi =  PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
                notification.contentIntent = pi;
                manager.notify(1,notification);

                break;
            default:
                break;

        }
    }
}
