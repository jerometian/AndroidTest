package com.example.jerometian.networktest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by jjtian on 2015/11/24.
 */
public class JsonUtil {


    public static <T>  List<T> parseJSONWithGSON(String jsonData)
    {
        Gson gson = new Gson();
        List<T> tList =  gson.fromJson(jsonData,new TypeToken<List<T>>(){}.getType());
        return tList;

    }
}
