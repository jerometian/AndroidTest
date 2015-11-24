package com.example.jerometian.networktest;

/**
 * Created by jjtian on 2015/11/23.
 */
public interface HttpCallbackListener {

    void onFinish(String resposne);

    void onError(Exception e);

}
