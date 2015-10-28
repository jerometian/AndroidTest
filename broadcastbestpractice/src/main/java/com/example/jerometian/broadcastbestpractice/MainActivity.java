package com.example.jerometian.broadcastbestpractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;

/**
 * Created by jjtian on 2015/10/28.
 */
public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        AppCompatButton forceoffline = (AppCompatButton)findViewById(R.id.Button1);
        forceoffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.jerometian.broadcastbestpractice.FORCE_OFFLINE");
                Log.d("broad","starting...");
                sendBroadcast(intent);
                Log.d("broad", "started...");
            }
        });

    }
}
