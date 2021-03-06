package com.example.jerometian.coolweather.activity;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import test.example.com.coolweather.R;

/**
 * Created by jerometian on 2015/12/7.
 */
public class ChooseAdapter    extends RecyclerView.Adapter<ChooseAdapter.ViewHolder> {

    private List<String> datasetList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public ChooseAdapter(List<String> myDataset) {
        datasetList = myDataset;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ChooseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()) .inflate(R.layout.recyclerview_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        v.setPadding(5,5,5,5);
        ViewHolder vh = new ViewHolder((TextView)v);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(datasetList.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return datasetList.size();
    }

}
