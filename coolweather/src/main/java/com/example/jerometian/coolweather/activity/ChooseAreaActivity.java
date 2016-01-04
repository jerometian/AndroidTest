package com.example.jerometian.coolweather.activity;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import android.view.Window;


import com.example.jerometian.coolweather.db.CoolWeatherDB;
import com.example.jerometian.coolweather.model.City;
import com.example.jerometian.coolweather.model.County;
import com.example.jerometian.coolweather.model.Province;
import com.example.jerometian.coolweather.util.HttpCallbackListener;
import com.example.jerometian.coolweather.util.HttpUtil;
import com.example.jerometian.coolweather.util.Utility;
import com.example.jerometian.coolweather.util.ZHttp;

import java.util.ArrayList;
import java.util.List;
import android.text.TextUtils;
import android.widget.Toast;

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
    private RecyclerView.Adapter adapter;
    /**
     * 当前选中的级别
     */
    private int currentLevel;
    private CoolWeatherDB coolWeatherDB;
    /**
     * 省列表
     */
    private List<Province> provinceList;

    private  List<City> cityList;

    private  List<County> countyList;

    private ProgressDialog progressDialog;
    /**
     * 选中的省份
     */
    private Province selectedProvince;
    private City selectedCity;
    private List<String> dataList  = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.choose_area);
        recycler = (RecyclerView)findViewById(R.id.list_view);
        textView = (AppCompatTextView)findViewById(R.id.title_text);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recycler.setLayoutManager(linearLayoutManager);

        adapter = new AreaListAdapter(this, dataList, new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int index,String data) {
                if (currentLevel == LEVEL_PROVINCE) {
                    selectedProvince = provinceList.get(index);
                    queryCities();
                } else if (currentLevel == LEVEL_CITY) {
                    selectedCity = cityList.get(index);
                    queryCounties();
                }
            }
        });

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
//                Log.d("province:",province.getProvinceName());
            }

            adapter.notifyDataSetChanged();
            recycler.setSelected(true);
            textView.setText("中国");
            currentLevel = LEVEL_PROVINCE;
        } else {
//            Log.d("debug:","not province data.");
           queryFromServer(null, "province");
        }
    }

    /**
     * 查询选中省内所有的市，优先从数据库查询，如果没有查询到再去服务器上查询。
     */
    private void queryCities() {
        cityList = coolWeatherDB.loadCities(selectedProvince.getId());
        if (cityList.size() > 0) {
            dataList.clear();
            for (City city : cityList) {
                dataList.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            recycler.setSelected(true);
            textView.setText(selectedProvince.getProvinceName());
            currentLevel = LEVEL_CITY;
        } else {
            queryFromServer(selectedProvince.getProvinceCode(), "city");
        }
    }

    /**
     * 查询选中市内所有的县，优先从数据库查询，如果没有查询到再去服务器上查询。
     */
    private void queryCounties() {
        countyList = coolWeatherDB.loadCounties(selectedCity.getId());
        if (countyList.size() > 0) {
            dataList.clear();
            for (County county : countyList) {
                dataList.add(county.getCountyName());
            }
            adapter.notifyDataSetChanged();
            recycler.setSelected(true);
            textView.setText(selectedCity.getCityName());
            currentLevel = LEVEL_COUNTY;
        } else {
            queryFromServer(selectedCity.getCityCode(), "county");
        }
    }

    /**
     * 根据传入的代号和类型从服务器上查询省市县数据。
     */
    private void queryFromServer(final String code, final String type) {
        String address;
        if (!TextUtils.isEmpty(code)) {
            address = "http://www.weather.com.cn/data/list3/city" + code +
                    ".xml";
        } else {
            address = "http://www.weather.com.cn/data/list3/city.xml";
        }

 /*        address= String.format("%/s%/s/","http://wcf.open.cnblogs.com/blog","TenDaysTopDiggPosts");
        String  result = ZHttp.getString1(address);
        Log.d("data:",result);*/

        ZHttp.getString(address, new HttpCallbackListener() {
            @Override
            public void onFinished(String response) {
                boolean result = false;
                if ("province".equals(type)) {
//                    Log.d("debug:","equal province?");
                    result = Utility.handleProvincesResponse(coolWeatherDB,
                            response);
                } else if ("city".equals(type)) {
                    result = Utility.handleCitiesResponse(coolWeatherDB,
                            response, selectedProvince.getId());
                } else if ("county".equals(type)) {
                    result = Utility.handleCountiesResponse(coolWeatherDB,
                            response, selectedCity.getId());
                }
                if (result) {

                    // 通过runOnUiThread()方法回到主线程处理逻辑
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //closeProgressDialog();

                            Toast.makeText(ChooseAreaActivity.this, "加载成功", Toast.LENGTH_SHORT).show();
                            if ("province".equals(type)) {
                                queryProvinces();
                                Toast.makeText(ChooseAreaActivity.this, "加载成功1", Toast.LENGTH_SHORT).show();
                            } else if ("city".equals(type)) {
                                queryCities();
                            } else if ("county".equals(type)) {
                                queryCounties();
                            }
                        }
                    });
                }
            }

            @Override
            public void onError(Exception e) {
                // 通过runOnUiThread()方法回到主线程处理逻辑
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeProgressDialog();
                        Toast.makeText(ChooseAreaActivity.this,
                                "加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    /**
     * 显示进度对话框
     */
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }
    /**
     * 关闭进度对话框
     */
    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public void onBackPressed() {
        if (currentLevel == LEVEL_COUNTY) {
            queryCities();
        } else if (currentLevel == LEVEL_CITY) {
            queryProvinces();
        } else {
            finish();
        }
    }
}
