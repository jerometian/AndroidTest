package com.example.jerometian.broadcastbestpractice;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by jjtian on 2015/10/27.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onDestroy() {
        super.onDestroy();

        ActivityCollector.removeActivity(this);
       // Fresco.shutDown();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        //Fresco.initialize(this);
    }
}
