package com.youarenotin.dragrecylerview.CallBack;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.youarenotin.dragrecylerview.Adapter.ViewAdapter;


/**
 * Created by dell on 5/25 0025.
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
        int fromPositon = viewHolder.getAdapterPosition();
        int toPosition =target.getAdapterPosition();
        ((ViewAdapter) adapter).onMove(fromPositon,toPosition);
        return true;//True if the viewHolder has been moved to the adapter position of target.
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        ((ViewAdapter) adapter).onSwipe(viewHolder.getAdapterPosition());
    }
}
