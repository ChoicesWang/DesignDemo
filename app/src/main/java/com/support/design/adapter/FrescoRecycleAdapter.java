package com.support.design.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.support.design.R;
import com.support.design.bean.Photo;
import com.support.design.fragment.CheeseListFragment;
import com.support.design.holder.DraweeViewHolder;

import java.util.ArrayList;


public class FrescoRecycleAdapter extends RecyclerView.Adapter<DraweeViewHolder> {

    private int cellType;
    private ArrayList<Photo> mData;

    public FrescoRecycleAdapter(ArrayList<Photo> mData, int cellType) {
        this.cellType = cellType;
        this.mData = mData;
    }

    @Override
    public int getItemViewType(int position) {
        return cellType;
    }

    @Override
    public DraweeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.list_item;
        switch (viewType) {
            case CheeseListFragment.LIST:
                layoutId = R.layout.list_item;
                break;
            case CheeseListFragment.GRID:
                layoutId = R.layout.grid_item;
                break;
            case CheeseListFragment.STAGGERED:
                layoutId = R.layout.list_item;
                break;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new DraweeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DraweeViewHolder holder, int position) {
        holder.onBind(mData.get(position));
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

}
