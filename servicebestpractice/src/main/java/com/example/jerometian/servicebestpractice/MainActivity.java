package com.example.jerometian.servicebestpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by jjtian on 2015/11/6.
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this,LongRunningService.class);
        startService(intent);
    }
}
