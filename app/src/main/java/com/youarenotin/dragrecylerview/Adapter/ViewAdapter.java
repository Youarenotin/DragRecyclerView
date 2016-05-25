package com.youarenotin.dragrecylerview.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youarenotin.dragrecylerview.Bean.ItemTest;
import com.youarenotin.dragrecylerview.IItemTouch;
import com.youarenotin.dragrecylerview.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by dell on 5/25 0025.
 */
public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.mViewholder> implements IItemTouch {
    private ArrayList<ItemTest> list;
    private int layoutId;
    public ViewAdapter(ArrayList<ItemTest> list,int layoutId) {
        this.list=list;
        this.layoutId=layoutId;
    }

    @Override
    public mViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent,false);
        return new  mViewholder(view);
    }

    @Override
    public void onBindViewHolder(mViewholder holder, int position) {
        ItemTest item = list.get(position);
        holder.id.setText(item.getId()+"");
        holder.des.setText(item.getDescription());
        holder.img.setImageResource(item.getImgResId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onMove(int from, int to) {
        if (from<to){
            for (int i=from ; i<to - from ; i++){
                Collections.swap(list,i,i+1);
            }
        }
        else{
            for (int i = from ; i -to>0 ; i--){
                Collections.swap(list,i,i-1);
            }
        }
        notifyItemMoved(from, to);
    }

    @Override
    public void onSwipe(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }


    public class mViewholder extends  RecyclerView.ViewHolder{
        public  TextView id ;
        public TextView des;
        public ImageView img;
        public mViewholder(View itemView) {
            super(itemView);
            this.id = (TextView) itemView.findViewById(R.id.item_id);
            this.des= (TextView) itemView.findViewById(R.id.item_des);
            this.img= (ImageView) itemView.findViewById(R.id.item_img);
        }
    }
}
