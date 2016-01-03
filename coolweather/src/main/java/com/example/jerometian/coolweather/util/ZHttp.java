package com.example.jerometian.coolweather.util;

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
                try {
                    Response response = execute(urlStr);


                    if ( listener != null)
                    {
                        listener.onFinished(response.body().string());
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
