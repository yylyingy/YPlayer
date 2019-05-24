package com.github.yylyingy.common.adapter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * <br> ClassName:
 * <br> Description: todo(这里用一句话描述这个类的作用)
 * <br>
 * <br> Author:      Yangyl
 * <br> Date:        2019/5/23 0:38
 */
public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected Context mContext;
    protected List<T> mList;
//    protected OnSelectStateListener<T> mListener;

    public BaseAdapter(Context ctx, List<T> list) {
        mContext = ctx;
        mList = list;
    }

    public void add(List<T> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void add(T file) {
        mList.add(file);
        notifyDataSetChanged();
    }

    public void add(int index, T file) {
        mList.add(index, file);
        notifyDataSetChanged();
    }

    public void refresh(List<T> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void refresh(T file) {
        mList.clear();
        mList.add(file);
        notifyDataSetChanged();
    }

    public List<T> getDataSet() {
        return mList;
    }

//    public void setOnSelectStateListener(OnSelectStateListener<T> listener) {
//        mListener = listener;
//    }
}

