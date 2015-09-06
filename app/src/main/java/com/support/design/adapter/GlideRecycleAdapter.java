package com.support.design.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.support.design.R;
import com.support.design.bean.Photo;
import com.support.design.fragment.CheeseListFragment;
import com.support.design.holder.ImageViewHolder;

import java.util.ArrayList;

public class GlideRecycleAdapter extends RecyclerView.Adapter<ImageViewHolder> {


    private int cellType;
    private ArrayList<Photo> mData;

    public GlideRecycleAdapter(ArrayList<Photo> mData, int cellType) {
        this.cellType = cellType;
        this.mData = mData;
    }

    @Override
    public int getItemViewType(int position) {
        return cellType;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.list_item;
        switch (viewType) {
            case CheeseListFragment.LIST:
                layoutId = R.layout.image_grid_item;
                break;
            case CheeseListFragment.GRID:
                layoutId = R.layout.image_grid_item;
                break;
            case CheeseListFragment.STAGGERED:
                layoutId = R.layout.image_grid_item;
                break;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.onBind(mData.get(position));
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }
}