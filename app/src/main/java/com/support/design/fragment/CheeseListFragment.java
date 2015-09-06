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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.support.design.R;
import com.support.design.adapter.FrescoRecycleAdapter;
import com.support.design.adapter.GlideRecycleAdapter;
import com.support.design.bean.Photo;
import com.support.design.common.Constants;

import java.util.ArrayList;

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
                layoutManager = new GridLayoutManager(getActivity(), 2);
                adapter = new GlideRecycleAdapter(initData(), LIST);
                break;
            case GRID:
                layoutManager = new GridLayoutManager(getActivity(), 2);
                adapter = new FrescoRecycleAdapter(initData(), GRID);
                break;
            case STAGGERED:
                layoutManager = new LinearLayoutManager(getActivity());
                adapter = new FrescoRecycleAdapter(initData(), LIST);
                break;
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    private ArrayList<Photo> initData() {
        int length = 100;
        ArrayList<Photo> mPhotos = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            Photo photo = new Photo();
            photo.name = Constants.CHEESE_STRINGS[i % Constants.CHEESE_STRINGS.length];
            photo.url = Constants.IMAGES[i % Constants.IMAGES.length];
            mPhotos.add(photo);
        }
        return mPhotos;
    }

}
