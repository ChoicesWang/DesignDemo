package com.support.design.holder;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.support.design.bean.Photo;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBind(Photo object);
}
