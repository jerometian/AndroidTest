package com.example.jerometian.networktest;

import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


/**
 * Created by jjtian on 2015/11/23.
 */
public class MainActivityNew extends AppCompatActivity  implements View.OnClickListener{
    private TextView sv;
    public static final  int SHOW_RESPONSE = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch ((msg.what))
            {
                case SHOW_RESPONSE:
                    String response = (String)msg.obj;
//                    sv.setText(response);

                    List<Book> books =   JsonUtil.parseJSONWithGSON(response);

                    int size = books.size();
                    Log.d("MainActivity" ,  Integer.toString(size));
                   /* for (Book b : books)
                    {
                        Log.d("MainActivity", "id is " + b.getId());
                        Log.d("MainActivity", "name is " + b.getName());
                        Log.d("MainActivity", "version is " + b.getVersion());
                    }*/
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sv = (TextView)findViewById(R.id.myTextView);
        Button button1 = (Button)findViewById(R.id.GetCommand);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.GetCommand:
                String address = "http://192.168.41.45:8001/default.aspx";
                HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String resposne) {
                        Message message = new Message();
                        message.what = SHOW_RESPONSE;
                        message.obj = resposne.toString();
                        handler.sendMessage(message);



                    }

                    @Override
                    public void onError(Exception e) {
                        Message message = new Message();
                        message.what = SHOW_RESPONSE;
                        message.obj = e.getMessage();
                        handler.sendMessage(message);
                    }
                });
                break;
            default:
                break;

        }
    }
}
