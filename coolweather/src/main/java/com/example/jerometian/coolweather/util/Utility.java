package com.example.jerometian.coolweather.util;

import android.text.TextUtils;

import com.example.jerometian.coolweather.db.CoolWeatherDB;
import com.example.jerometian.coolweather.model.Province;

/**
 * Created by jerometian on 2015/12/6.
 */
public class Utility {
    public synchronized  static  boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,String response)
    {
        if ( !TextUtils.isEmpty(response))
        {
            String[] allProvinces = response.split(",");
            if ( allProvinces != null && allProvinces.length >0)
            {

                for(String p:allProvinces){
                    String[] array = p.split("\\|");
                    Province  province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    coolWeatherDB.saveProvince(province);
                }
                return  true;
            }

        }
        return  false;

    }
}
