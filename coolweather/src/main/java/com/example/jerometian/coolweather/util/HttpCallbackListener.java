package com.example.jerometian.coolweather.util;

/**
 * Created by jerometian on 2015/12/6.
 */
public interface HttpCallbackListener {
    void onFinished(String response);
    void onError(Exception e);
}
