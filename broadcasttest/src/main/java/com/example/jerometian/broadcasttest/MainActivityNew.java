package com.example.jerometian.broadcasttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by jjtian on 2015/10/26.
 */
public class MainActivityNew extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.Button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("com.example.jerometian.broadcasttest.MY_BROADCAST");
                /*sendBroadcast(intent);*/  // same receiver can received too.
                sendBroadcast(intent,null);
            }
        });
    }
}
