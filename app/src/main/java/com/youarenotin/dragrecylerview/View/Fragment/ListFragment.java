package com.youarenotin.dragrecylerview.View.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.youarenotin.dragrecylerview.Adapter.ViewAdapter;
import com.youarenotin.dragrecylerview.Bean.ItemTest;
import com.youarenotin.dragrecylerview.CallBack.ItemTouchCallBack;
import com.youarenotin.dragrecylerview.CallBack.OnItemTouchListenerImpl;
import com.youarenotin.dragrecylerview.R;

import java.util.ArrayList;

/**
 * Created by dell on 5/25 0025.
 */
public class ListFragment extends Fragment {

    private ArrayList<ItemTest> list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<ItemTest>();
        for (int i=0 ; i<8 ; i++){
            list.add(new ItemTest(1 + i * 8, R.drawable.takeout_ic_category_brand, "韭菜炒香干"));
            list.add(new ItemTest(2 + i * 8, R.drawable.takeout_ic_category_flower, "小炒土豆片"));
            list.add(new ItemTest(3 + i * 8, R.drawable.takeout_ic_category_fruit, "肉末粉丝"));
            list.add(new ItemTest(4 + i * 8, R.drawable.takeout_ic_category_medicine, "芹菜香干"));
            list.add(new ItemTest(5 + i * 8, R.drawable.takeout_ic_category_motorcycle, "西瓜"));
            list.add(new ItemTest(6 + i * 8, R.drawable.takeout_ic_category_public, "包菜炒粉丝"));
            list.add(new ItemTest(7 + i * 8, R.drawable.takeout_ic_category_sweet, "甜点"));
            list.add(new ItemTest(8 + i * 8, R.drawable.takeout_ic_category_store, "仓库"));
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_listview,null,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewAdapter listAdapter =new ViewAdapter(list,R.layout.item_list);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.listview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //拖动 & 滑动
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchCallBack(listAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        //点击 & 长按
        recyclerView.addOnItemTouchListener(new OnItemTouchListenerImpl(recyclerView)
                                            {
                                                @Override
                                                protected void onItemClick(RecyclerView.ViewHolder vh) {
                                                    if (vh instanceof ViewAdapter.mViewholder){
                                                        ViewAdapter.mViewholder vh_New = (ViewAdapter.mViewholder) vh;
                                                        Toast.makeText(getActivity(),""+vh_New.id.getText(),Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                @Override
                                                protected void onItemLongClick(RecyclerView.ViewHolder vh) {
                                                    if (vh instanceof ViewAdapter.mViewholder){
                                                        ViewAdapter.mViewholder vh_New = (ViewAdapter.mViewholder) vh;
                                                        Toast.makeText(getActivity(),"我能做出来->"+vh_New.des.getText(),Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
        );
    }
}
