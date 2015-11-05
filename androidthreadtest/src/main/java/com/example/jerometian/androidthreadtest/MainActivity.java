package com.example.jerometian.androidthreadtest;

import android.app.Activity;
import android.os.Bundle;

import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.os.Handler;

/**
 * Created by jjtian on 2015/11/5.
 */
public class MainActivity  extends Activity implements View.OnClickListener {

   public static    final  int UPDATE_TEXT = 1;
    private TextView text;
    private Button changeText;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    text.setText("Nice to meet you");
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        text = (TextView) findViewById(R.id.text);
        changeText = (Button) findViewById(R.id.change_text);
        changeText.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            default:
                break;
        }
    }
}
