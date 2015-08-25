package com.support.design;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.support.design.adapter.CustomPagerAdapter;

/**
 * 消息
 * Created by Administrator on 2015/6/12.
 */
public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (viewPager != null) {
            setupViewPager(viewPager);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    private void setupViewPager(@NonNull ViewPager viewPager) {
        CustomPagerAdapter adapter = new CustomPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(CheeseListFragment.newInstance(CheeseListFragment.LIST), "曹成");
        adapter.addFragment(CheeseListFragment.newInstance(CheeseListFragment.LIST), "鲁野");
        adapter.addFragment(CheeseListFragment.newInstance(CheeseListFragment.LIST), "小明");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(MainActivity.DEFAULT_PAGES);
    }
}
