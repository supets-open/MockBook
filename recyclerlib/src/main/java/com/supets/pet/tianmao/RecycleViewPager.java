package com.supets.pet.tianmao;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.supets.pet.suspendbar.MultiFeedAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewPager extends LinearLayout {

    private ViewPager viewPager;
    private List<RecyclerView> views = new ArrayList<>();
    private MyViewPagerAdapter adapter;

    public RecycleViewPager(Context context) {
        super(context);
        init();
    }

    public RecycleViewPager(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        for (int i = 0; i < 2; i++) {
            RecyclerView recyclerView = new RecyclerView(getContext());
            recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            MultiFeedAdapter loadMoreAdapter = new MultiFeedAdapter();
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(loadMoreAdapter);
            views.add(recyclerView);
        }

        viewPager = new ViewPager(getContext()) {
        };
        viewPager.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        adapter = new MyViewPagerAdapter(views, getContext());
        viewPager.setAdapter(adapter);
        addView(viewPager);
    }

    public class MyViewPagerAdapter extends PagerAdapter {
        private List<RecyclerView> views;
        private Context context;

        //有参构造
        public MyViewPagerAdapter(List<RecyclerView> views, Context context) {
            super();
            this.views = views;
            this.context = context;
        }

        //获得长度
        @Override
        public int getCount() {

            return views.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {

            return arg0 == arg1;
        }

        //展示的view
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //获得展示的view
            View view = views.get(position);
            //添加到容器
            container.addView(view);
            //返回显示的view
            return view;
        }

        //销毁view
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //从容器中移除view
            container.removeView((View) object);
        }

    }
}
