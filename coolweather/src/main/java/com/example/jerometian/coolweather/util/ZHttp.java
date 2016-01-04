package com.example.jerometian.coolweather.util;

import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2015/8/28.
 */
public class ZHttp {
    private static OkHttpClient mOkHttpClient;

    public static OkHttpClient getHttpClient() {

        if (mOkHttpClient == null) {
            synchronized (ZHttp.class) {
                mOkHttpClient = new OkHttpClient();
                mOkHttpClient.setConnectTimeout(12, TimeUnit.SECONDS);
                mOkHttpClient.setReadTimeout(12, TimeUnit.SECONDS);
            }
        }
        return mOkHttpClient;
    }

    public static Response execute(String url) {
        Request request = new Builder().url(url).build();
        try {
            Response response = getHttpClient().newCall(request).execute();

            if (response.isSuccessful())
                return response;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过get请求，获取json实例
     *
     * @param urlStr 请求地址
     */
    public static String getString(final  String urlStr,final  HttpCallbackListener listener) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("debug:","start get request...");
                try {
                    Response response = execute(urlStr);


                    if ( listener != null)
                    {
                        String result = response.body().string();
//                        Log.d("debug:",result);
                        listener.onFinished(result);
                    }
                    /*if (response == null)
                        return null;
                    return response.body().string();*/

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        return null;
    }

    /**
     * 通过get请求，获取json实例
     *
     * @param urlStr 请求地址
     */
    public static String getString1(final  String urlStr) {
                try {
                    Response response = execute(urlStr);

                    if (response == null)
                        return null;
                    return response.body().string();

                } catch (IOException e) {
                    e.printStackTrace();
                }



        return null;
    }

    public static  void getString2(final  String urlStr) {

        OkHttpClient client = getHttpClient();
        Request request = new Request.Builder()
                .url(urlStr)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("okHttp",e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                 response.body().string();
            }
        });
    }

    public static byte[] getBytes(String url) {
        try {
            Response response = execute(url);

            if (null == response)
                return null;

            return response.body().bytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
