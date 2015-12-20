package com.example.jerometian.coolweather.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by jerometian on 2015/12/19.
 */
public class RecyclerItemClickListener  implements RecyclerView.OnItemTouchListener {

    private  OnRecyclerViewItemClickListener recyclerViewItemClickListener;

    private GestureDetector  gestureDetector;

    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, final OnRecyclerViewItemClickListener recyclerViewItemClickListener) {
        this.recyclerViewItemClickListener = recyclerViewItemClickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public void onLongPress(MotionEvent e) {
//                super.onLongPress(e);
                View childview = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if ( childview != null && recyclerViewItemClickListener != null)
                {
                    recyclerViewItemClickListener.onItemClick(childview,recyclerView.getChildAdapterPosition(childview));

                }
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
//                return super.onSingleTapUp(e);
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());

        if(childView != null && recyclerViewItemClickListener != null && gestureDetector.onTouchEvent(e))
        {
            recyclerViewItemClickListener.onItemClick(childView, view.getChildPosition(childView));
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
