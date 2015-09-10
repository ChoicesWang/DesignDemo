package com.support.design.holder;

import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.net.Uri;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.support.design.Activitiy.CheeseDetailActivity;
import com.support.design.R;
import com.support.design.bean.Photo;
import com.support.design.fragment.CheeseListFragment;


public class DraweeViewHolder extends BaseViewHolder<Photo> {
    public String mName;
    public String mImageUrl;

    public final SimpleDraweeView mDraweeView;
    public final TextView mTextView;

    private Animation animation;
    private View root;


    public DraweeViewHolder(View view) {
        super(view);

        root = view;
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

        animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.item_load);
    }

    @Override
    public void onBind(Photo photo) {
        root.startAnimation(animation);

        mName = photo.name;
        mImageUrl = photo.url;

        mTextView.setText(mName);

        int width = mDraweeView.getLayoutParams().width;
        int height = mDraweeView.getLayoutParams().height;
        switch (getItemViewType()) {
            case CheeseListFragment.LIST:
                break;
            case CheeseListFragment.GRID:
                width = 365;
                height = 268;
                break;
            case CheeseListFragment.STAGGERED:
                break;
            default:
                break;
        }

        ImageRequest imageRequest =
                ImageRequestBuilder.newBuilderWithSource(Uri.parse(mImageUrl))
                        .setResizeOptions(new ResizeOptions(width, height))
                        .setProgressiveRenderingEnabled(true)
                        .build();
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setOldController(mDraweeView.getController())
                .setAutoPlayAnimations(true)
                .build();
        mDraweeView.setController(draweeController);
    }
}