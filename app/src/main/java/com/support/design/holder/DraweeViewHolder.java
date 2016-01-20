package com.support.design.holder;

import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.support.design.Activitiy.CheeseDetailActivity;
import com.support.design.R;
import com.support.design.bean.Photo;


public class DraweeViewHolder extends BaseViewHolder {
    public String mName;
    public String mImageUrl;

    public final SimpleDraweeView mDraweeView;
    public final TextView mTextView;


    public DraweeViewHolder(View view) {
        super(view);

        mTextView = (TextView) view.findViewById(R.id.text);
        mDraweeView = (SimpleDraweeView) view.findViewById(R.id.draweeView);
        mDraweeView.getHierarchy().setActualImageFocusPoint(new PointF(0.5f, 0.3f));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, CheeseDetailActivity.class);
                intent.putExtra(CheeseDetailActivity.EXTRA_NAME, mName);
                intent.putExtra(CheeseDetailActivity.EXTRA_IMAGE_URL, mImageUrl);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public void onBind(Photo photo) {
        mName = photo.name;
        mImageUrl = photo.url;
        mTextView.setText(mName);
        mDraweeView.setImageURI(Uri.parse(mImageUrl));
    }
}