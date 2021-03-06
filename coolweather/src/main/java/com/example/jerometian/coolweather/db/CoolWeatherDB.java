package com.example.jerometian.coolweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jerometian.coolweather.model.City;
import com.example.jerometian.coolweather.model.County;
import com.example.jerometian.coolweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jerometian on 2015/12/5.
 */
public class CoolWeatherDB {

    public static final String DB_NAME = "cool_weather";
    public static final int VERSION = 1;

    private static CoolWeatherDB coolWeatherDB;

    private SQLiteDatabase db;

    private CoolWeatherDB(Context context) {
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static CoolWeatherDB getInstance(Context context) {

        if (coolWeatherDB == null) {
            coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }

    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            Log.d("debug:","before save:");
            db.insert("Province", null, values);
            Log.d("debug:","finished saved:");
        }
    }

    /*load province*/
    public List<Province> loadProvinces() {

        Log.d("debug:","load province...");
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("Province", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            }
            while (cursor.moveToNext());
        }
        return list;
    }


    public void saveCity(City city)
    {
        if ( city != null)
        {
            ContentValues values = new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            values.put("province_id",city.getProvinceId());
            db.insert("City",null,values);
        }

    }


    public List<City> loadCities(int province_id)
    {
        List<City>  cities = new ArrayList<City>();
        Cursor cursor = db.query("City",null,null,null,null,null,null);
        if ( cursor.moveToFirst())
        {
            do {
                City  city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(province_id);
                cities.add((city));
            }
            while (cursor.moveToNext());

        }
        return cities;

    }

    public void saveCounty(County county)
    {
        if ( county != null)
        {
            ContentValues values = new ContentValues();
            values.put("county_name",county.getCountyName());
            values.put("county_code",county.getCountyCode());
            values.put("city_id", county.getCityId());
            db.insert("County",null,values);
        }

    }

    public List<County> loadCounties(int city_id)
    {
        List<County> counties = new ArrayList<County>();
        Cursor cursor =db.query("County",null,null,null,null,null,null);
        if (cursor.moveToFirst())
        {

            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cursor.getInt(cursor.getColumnIndex("city_id")));
                counties.add(county);

            }
            while(cursor.moveToNext());
        }
        return  counties;
    }


}
