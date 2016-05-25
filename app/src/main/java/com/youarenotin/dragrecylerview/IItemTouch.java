package com.youarenotin.dragrecylerview;

/**
 * Created by dell on 5/25 0025.
 */
public interface IItemTouch {
    void onMove(int from, int to);
    void onSwipe(int position );
}
