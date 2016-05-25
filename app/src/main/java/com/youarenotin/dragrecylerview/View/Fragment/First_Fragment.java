package com.youarenotin.dragrecylerview.View.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youarenotin.dragrecylerview.R;

/**
 * Created by x on 5/25 0025.
 */
public class First_Fragment extends Fragment implements View.OnClickListener {
    private volatile static Object obj = new Object();
    private View.OnClickListener listener;
    private static First_Fragment instance;

    public First_Fragment() {
    }

    public static First_Fragment getInstance() {
        if (instance == null) {
            synchronized (obj) {
                instance = new First_Fragment();
            }
        }
        return instance;
    }

    public void setOnclickListener(View.OnClickListener l) {
        if (l != null) {
            this.listener = l;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_main, null, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        ((MainActivity)getActivity()).tb.
        view.findViewById(R.id.btn_list).setOnClickListener(this);
        view.findViewById(R.id.btn_grid).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.listener.onClick(v);
    }
}
