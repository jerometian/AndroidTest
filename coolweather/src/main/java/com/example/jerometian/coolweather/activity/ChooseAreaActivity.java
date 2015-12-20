package com.example.jerometian.coolweather.activity;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.view.Window;


import com.example.jerometian.coolweather.db.CoolWeatherDB;
import com.example.jerometian.coolweather.model.Province;

import java.util.ArrayList;
import java.util.List;

import test.example.com.coolweather.R;

/**
 * Created by jerometian on 2015/12/7.
 */
public class ChooseAreaActivity extends AppCompatActivity {

    private RecyclerView  recycler;
    private AppCompatTextView  textView;


    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;
    /**
     * 当前选中的级别
     */
    private int currentLevel;
    private CoolWeatherDB coolWeatherDB;
    /**
     * 省列表
     */
    private List<Province> provinceList;

    /**
     * 选中的省份
     */
    private Province selectedProvince;
    private List<String> dataList  = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.choose_area);
        recycler = (RecyclerView)findViewById(R.id.list_view);
        textView = (AppCompatTextView)findViewById(R.id.title_text);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recycler.setLayoutManager(linearLayoutManager);
        recycler.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recycler, new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if ( currentLevel == LEVEL_PROVINCE){
                    selectedProvince = provinceList.get(position);
//                    queryProvinces();
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
        RecyclerView.Adapter adapter = new ChooseAdapter(dataList);
        recycler.setAdapter(adapter);
        coolWeatherDB = CoolWeatherDB.getInstance(this);
        queryProvinces();
    }


    private void queryProvinces() {
        provinceList = coolWeatherDB.loadProvinces();
        if (provinceList.size() > 0) {
            dataList.clear();
            for (Province province : provinceList) {
                dataList.add(province.getProvinceName());
            }
//            adapter.notifyDataSetChanged();
//            recycler.setSelection(0);
           //  recycler.scrollToPosition(cursor);
            textView.setText("中国");
            currentLevel = LEVEL_PROVINCE;
        } else {
//            queryFromServer(null, "province");
        }
    }

}
