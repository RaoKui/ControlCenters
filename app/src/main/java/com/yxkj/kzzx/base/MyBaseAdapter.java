package com.yxkj.kzzx.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaoKui on 2017/7/12.
 */

public abstract class MyBaseAdapter<Holder, T> extends BaseAdapter {

    protected List<T> mDataList = new ArrayList<>();

    protected LayoutInflater mInflater;

    protected Context mContext;

    public MyBaseAdapter(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public T getItem(int position) {
        return mDataList == null ? null : mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        T t = getItem(position);
        if (convertView == null) {
            convertView = mInflater.inflate(getLayoutId(), parent, false);
            holder = onCreateViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        onBindViewHolder(holder, t, position, convertView);
        return convertView;
    }

    protected abstract void onBindViewHolder(Holder holder, T t, int position, View convertView);

    protected abstract Holder onCreateViewHolder(View convertView);

    @LayoutRes
    protected abstract int getLayoutId();

}
