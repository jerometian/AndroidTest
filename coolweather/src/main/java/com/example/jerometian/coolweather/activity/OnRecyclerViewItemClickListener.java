package com.example.jerometian.coolweather.activity;

import android.view.View;

/**
 * Created by jerometian on 2015/12/7.
 */
public  interface OnRecyclerViewItemClickListener {
    public void onItemClick(View view,int position);
    public  void onItemLongClick(View view,int position);
}
