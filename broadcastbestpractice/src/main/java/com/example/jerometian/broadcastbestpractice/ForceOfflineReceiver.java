package com.example.jerometian.broadcastbestpractice;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
//import android.app.AlertDialog;
import android.view.ContextThemeWrapper;
import android.view.WindowManager;

/**
 * Created by jjtian on 2015/10/28.
 */
public class ForceOfflineReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(context,R.style.AppCompatAlertDialogStyle));
        dialogBuilder.setTitle("Warning");
        dialogBuilder.setMessage("You are Forced to be offline.Please try to login again.");
        dialogBuilder.setCancelable(true);
        dialogBuilder.setNegativeButton("Cancel",null);
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCollector.finishAll();
                Intent intent = new Intent(context, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        AlertDialog alertDialog=  dialogBuilder.create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
    }
}
