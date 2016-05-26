package com.youarenotin.dragrecylerview.CallBack;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by dell on 5/25 0025.
 */
public class OnItemTouchListenerImpl implements RecyclerView.OnItemTouchListener {
    private GestureDetectorCompat gestureDetectorCompat;
    private View view;

    public OnItemTouchListenerImpl(View view) {
        this.view = view;
        gestureDetectorCompat = new GestureDetectorCompat(view.getContext(), new MGestureListener());
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetectorCompat.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//        gestureDetectorCompat.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public class MGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if (view instanceof RecyclerView) {
                View childViewUnder = ((RecyclerView) view).findChildViewUnder(e.getX(), e.getY());
                if ( childViewUnder==null)
                    return false;
                RecyclerView.ViewHolder childViewHolder = ((RecyclerView) view).getChildViewHolder(childViewUnder);
                if (childViewHolder!=null){
                    onItemClick(childViewHolder);
                    return true;
                }
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            if (view instanceof RecyclerView) {
                View childViewUnder = ((RecyclerView) view).findChildViewUnder(e.getX(), e.getY());
                if (childViewUnder==null)
                    return;
                RecyclerView.ViewHolder childViewHolder = ((RecyclerView) view).getChildViewHolder(childViewUnder);
                if (childViewHolder!=null)
                onItemLongClick(childViewHolder);
            }
        }
    }

    protected void onItemClick(RecyclerView.ViewHolder vh){};
    protected void onItemLongClick(RecyclerView.ViewHolder vh){};
}
