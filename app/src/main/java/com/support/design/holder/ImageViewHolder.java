package com.support.design.holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.support.design.Activitiy.CheeseDetailActivity;
import com.support.design.R;
import com.support.design.bean.Photo;


public class ImageViewHolder extends BaseViewHolder<Photo> {

    public String mName;
    public String mImageUrl;

    public final ImageView mImageVIew;
    public final TextView mTextView;

    public ImageViewHolder(View view) {
        super(view);
        mTextView = (TextView) view.findViewById(R.id.text);
        mImageVIew = (ImageView) view.findViewById(R.id.imageView);

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

        Glide.with(mImageVIew.getContext())
                .load(mImageUrl)
                .centerCrop()
                .placeholder(R.color.wait_color)
                .crossFade()
                .into(mImageVIew);

    }
}
