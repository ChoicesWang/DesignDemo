/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.support.design.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.support.design.Activitiy.CheeseDetailActivity;
import com.support.design.R;
import com.support.design.bean.Photo;
import com.support.design.common.Constants;

/**
 * 列表页
 *
 * @author choices
 */
public class CheeseListFragment extends Fragment {

    public static final int LIST = 0;
    public static final int GRID = 1;
    public static final int STAGGERED = 2;

    public static final String KEY = "Mode";

    public static CheeseListFragment newInstance(int type) {
        CheeseListFragment fragment = new CheeseListFragment();
        Bundle b = new Bundle();
        b.putInt(KEY, type);
        fragment.setArguments(b);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.fragment_cheese_list, container, false);
        setupRecyclerView(rv);
        return rv;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {

        RecyclerView.LayoutManager layoutManager = null;
        RecyclerView.Adapter adapter = null;
        switch (getArguments().getInt(KEY)) {
            case LIST:
                layoutManager = new LinearLayoutManager(getActivity());
                adapter = new MagicAdapter(initData(), LIST);
                break;
            case GRID:
                layoutManager = new GridLayoutManager(getActivity(), 2);
                adapter = new MagicAdapter(initData(), GRID);
                break;
            case STAGGERED:
                layoutManager = new LinearLayoutManager(getActivity());
                adapter = new MagicAdapter(initData(), LIST);
                break;
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    private Photo[] initData() {
        int length = 60;
        Photo[] mPhotos = new Photo[length];

        for (int i = 0; i < length; i++) {
            mPhotos[i] = new Photo();
            mPhotos[i].name = Constants.CHEESE_STRINGS[i % Constants.CHEESE_STRINGS.length];
            mPhotos[i].url = Constants.IMAGES[i % Constants.IMAGES.length];
        }
        return mPhotos;
    }

    public static class MagicAdapter extends RecyclerView.Adapter<MeiziViewHolder> {

        private int cellType;
        private Photo[] photos;

        public MagicAdapter(Photo[] photos, int cellType) {
            this.photos = photos;
            this.cellType = cellType;
        }

        @Override
        public int getItemViewType(int position) {
            return cellType;
        }

        @Override
        public MeiziViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            int layoutId = R.layout.list_item;
            switch (viewType) {
                case LIST:
                    layoutId = R.layout.list_item;
                    break;
                case GRID:
                    layoutId = R.layout.grid_item;
                    break;
                case STAGGERED:
                    layoutId = R.layout.list_item;
                    break;
            }
            View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new MeiziViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MeiziViewHolder holder, int position) {
            String title = photos[position].name;
            holder.mName = title;
            holder.mTextView.setText(title);
            holder.mImageUrl = photos[position].url;
            holder.mDraweeView.setImageURI(Uri.parse(holder.mImageUrl));
        }

        @Override
        public int getItemCount() {
            return photos.length;
        }
    }

    public static class MeiziViewHolder extends RecyclerView.ViewHolder {
        public String mName;
        public String mImageUrl;

        public final SimpleDraweeView mDraweeView;
        public final TextView mTextView;

        public MeiziViewHolder(View view) {
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

    }

    // 备注

//    void fun() {
//        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
//        mBackground = mTypedValue.resourceId;
//        view.setBackgroundResource(mBackground);
//
//        return new ArrayList<>(Arrays.asList(Constants.IMAGES));
//
//        int size = mValues.size();
//        for (int i = 0; i < size; i++) {
//            int pos = i % Constants.IMAGES.length;
//            mImageMap.put(mValues.get(i), Constants.IMAGES[pos]);
//        }
//
//        Random random = new Random();
//        for (String key : mValues) {
//            // 随机绑定图片
//            mImageMap.put(key, Constants.IMAGES[random.nextInt(Constants.IMAGES.length)]);
//        }
//    }

}
