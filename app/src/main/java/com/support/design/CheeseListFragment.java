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

package com.support.design;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.support.design.common.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * 列表页
 * @author choices
 */
public class CheeseListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.fragment_cheese_list, container, false);
        setupRecyclerView(rv);
        return rv;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new SimpleStringRecyclerViewAdapter(getActivity(),
                getRandomSublist(Cheeses.sCheeseStrings, 40)));
    }

    private List<String> getRandomSublist(String[] array, int amount) {
        ArrayList<String> list = new ArrayList<>(amount);
        Random random = new Random();
        while (list.size() < amount) {
            list.add(array[random.nextInt(array.length)]);
        }
        return list;

        // return new ArrayList<>(Arrays.asList(Constants.IMAGES));
    }

    public static class SimpleStringRecyclerViewAdapter extends RecyclerView.Adapter<MeiziViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        private List<String> mValues;
        private HashMap<String, String> mImageMap;

        public SimpleStringRecyclerViewAdapter(Context context, List<String> items) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mValues = items;

            mImageMap = new HashMap<>();

//            int size = mValues.size();
//            for (int i = 0; i < size; i++) {
//                int pos = i % Constants.IMAGES.length;
//                mImageMap.put(mValues.get(i), Constants.IMAGES[pos]);
//            }

            Random random = new Random();
            for (String key : mValues) {
                mImageMap.put(key, Constants.IMAGES[random.nextInt(Constants.IMAGES.length)]);
            }

        }

        public String getValueAt(int position) {
            return mValues.get(position);
        }

        @Override
        public MeiziViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            view.setBackgroundResource(mBackground);
            return new MeiziViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MeiziViewHolder holder, int position) {
            holder.mName = mValues.get(position);
            holder.mImageUrl = mImageMap.get(holder.mName);
            holder.mTextView.setText(mValues.get(position));

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, CheeseDetailActivity.class);
                    intent.putExtra(CheeseDetailActivity.EXTRA_NAME, holder.mName);
                    intent.putExtra(CheeseDetailActivity.EXTRA_IMAGE_URL, holder.mImageUrl);

                    context.startActivity(intent);
                }
            });

            holder.mDraweeView.setImageURI(Uri.parse(holder.mImageUrl));
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }

    public static class MeiziViewHolder extends RecyclerView.ViewHolder {
        public String mName;
        public String mImageUrl;

        public final View mView;
        public final SimpleDraweeView mDraweeView;
        public final TextView mTextView;

        public MeiziViewHolder(View view) {
            super(view);
            mView = view;
            mDraweeView = (SimpleDraweeView) view.findViewById(R.id.draweeView);
            mTextView = (TextView) view.findViewById(android.R.id.text1);
        }

    }
}
