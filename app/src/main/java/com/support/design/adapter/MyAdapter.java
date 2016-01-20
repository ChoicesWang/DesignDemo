package com.support.design.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.support.design.R;
import com.support.design.bean.Photo;
import com.support.design.fragment.CheeseListFragment;
import com.support.design.holder.BaseViewHolder;
import com.support.design.holder.DraweeViewHolder;
import com.support.design.holder.ImageViewHolder;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private int type;
    private ArrayList<Photo> mData;

    public MyAdapter(ArrayList<Photo> mData, int type) {
        this.mData = mData;
        this.type = type;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater factory = LayoutInflater.from(context);
        switch (viewType) {
            case CheeseListFragment.GLIDE:
                return new ImageViewHolder(factory.inflate(R.layout.image_grid_item, parent, false));
            case CheeseListFragment.FRESCO:
                return new DraweeViewHolder(factory.inflate(R.layout.drawee_grid_item, parent, false));
        }

        return new DraweeViewHolder(factory.inflate(R.layout.image_grid_item, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }
}
