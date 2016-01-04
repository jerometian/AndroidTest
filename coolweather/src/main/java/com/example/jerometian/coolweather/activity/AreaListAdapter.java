package com.example.jerometian.coolweather.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import test.example.com.coolweather.R;

/**
 * Created by jerometian on 2016/1/1.
 */
public class AreaListAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<String> dataList;
    private LayoutInflater layoutInflater;
    public   OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    public AreaListAdapter(Context context, List<String> dataList, final  OnRecyclerViewItemClickListener onRecyclerViewItemClickListener)
    {
        this.context = context;
        this.dataList = dataList;
        layoutInflater = layoutInflater.from(this.context);
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new AreaViewHolder(layoutInflater.inflate(R.layout.recyclerview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final String data = dataList.get(position);
        if (null==data)
            return;
        AreaViewHolder areaViewHolder = (AreaViewHolder)holder;
        if ( !TextUtils.isEmpty(data)){
            ((AreaViewHolder) holder).mTextView.setText(Html.fromHtml(data));
        }
        areaViewHolder.itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( onRecyclerViewItemClickListener != null)
                {
                    onRecyclerViewItemClickListener.onItemClick(position,data);
                }
            }
        });
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class AreaViewHolder extends  RecyclerView.ViewHolder{
        View itemContainer;
        TextView mTextView;
        public  AreaViewHolder(View iteView)
        {
            super(iteView);
            itemContainer = iteView.findViewById(R.id.item_container);
            mTextView = (TextView)itemView.findViewById(R.id.area_title);

        }
    }
}
