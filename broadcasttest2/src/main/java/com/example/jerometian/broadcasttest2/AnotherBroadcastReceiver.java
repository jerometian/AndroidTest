package com.example.jerometian.broadcasttest2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by jjtian on 2015/10/26.
 */
public class AnotherBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Retrieved in AnotherBroadcastReceiver.",Toast.LENGTH_LONG).show();
    }
}
