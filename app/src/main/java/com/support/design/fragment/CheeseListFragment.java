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

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.support.design.R;
import com.support.design.adapter.MyAdapter;
import com.support.design.bean.Photo;
import com.support.design.common.Constants;

import java.util.ArrayList;

/**
 * 列表页
 *
 * @author choices
 */
public class CheeseListFragment extends Fragment {

    public static final int GLIDE = 0;
    public static final int FRESCO = 1;

    public static final String UTIL = "Util";

    public static CheeseListFragment newInstance(int util) {
        CheeseListFragment fragment = new CheeseListFragment();
        Bundle b = new Bundle();
        b.putInt(UTIL, util);
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

        RecyclerView.Adapter adapter = null;
        Bundle bundle = getArguments();
        switch (bundle.getInt(UTIL)) {
            case GLIDE:
                adapter = new MyAdapter(initData(), GLIDE);
                break;
            case FRESCO:
                adapter = new MyAdapter(initData(), FRESCO);
                break;
        }

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        // recyclerView.addItemDecoration(new MyItemDecoration());
    }

    private ArrayList<Photo> initData() {
        int length = 100;
        ArrayList<Photo> mPhotos = new ArrayList<>();

        int strLength = Constants.CHEESE_STRINGS.length;
        int urlLength = Constants.IMAGES.length;

        for (int i = 0; i < length; i++) {
            Photo photo = new Photo();
            photo.name = Constants.CHEESE_STRINGS[i % strLength];
            photo.url = Constants.IMAGES[i % urlLength];
            mPhotos.add(photo);
        }
        return mPhotos;
    }


    public static class MyItemDecoration extends RecyclerView.ItemDecoration {

        int layerId;
        Paint paint = new Paint();
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, 100, new int[]{0, Color.BLACK}, null, Shader.TileMode.CLAMP);

        public MyItemDecoration() {
            paint.setAntiAlias(true);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
            // 主要为了提升性能
            layerId = c.saveLayer(0, 0, parent.getWidth(), parent.getHeight(), null, Canvas.ALL_SAVE_FLAG);

        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
            paint.setXfermode(xfermode);
            paint.setShader(linearGradient);
            c.drawRect(0, 0, parent.getWidth(), 100, paint);
            paint.setXfermode(null);
            c.restoreToCount(layerId);
        }
    }

}
