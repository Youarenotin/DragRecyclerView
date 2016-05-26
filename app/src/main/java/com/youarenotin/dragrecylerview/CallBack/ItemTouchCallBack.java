package com.youarenotin.dragrecylerview.CallBack;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.youarenotin.dragrecylerview.Adapter.ViewAdapter;


/**
 * Created by Lubo on 5/25 0025.
 */
public class ItemTouchCallBack extends ItemTouchHelper.Callback {

    private RecyclerView.Adapter adapter;

    public ItemTouchCallBack(ViewAdapter adapter) {
        this.adapter=adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if(recyclerView.getLayoutManager() instanceof GridLayoutManager){
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
            int swipeFlags = 0;
            return makeMovementFlags(dragFlags,swipeFlags);
        }
        int dragFlags =ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags,swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //True if the viewHolder has been moved to the adapter position of target.
        int fromPositon = viewHolder.getAdapterPosition();
        int toPosition =target.getAdapterPosition();
        ((ViewAdapter) adapter).onMove(fromPositon,toPosition);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        ((ViewAdapter) adapter).onSwipe(viewHolder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //The type of interaction on the View. Is either ACTION_STATE_DRAG or ACTION_STATE_SWIPE.
        if (actionState==ItemTouchHelper.ACTION_STATE_SWIPE){
            Log.d("ItemTouchCallBack","ACTION_STATE_SWIPE");
            float alpha = 1- dX/viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setTranslationX(dX);
        }else if(actionState == ItemTouchHelper.ACTION_STATE_DRAG){
            Log.d("ItemTouchCallBack","ACTION_STATE_DRAG");
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#11a11b"));
        }
        else if (actionState==ItemTouchHelper.ACTION_STATE_IDLE){
            Log.d("ItemTouchCallBack", "ACTION_STATE_IDLE");

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        Log.d("ItemTouchCallBack", "clearview");
        viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
    }
}
