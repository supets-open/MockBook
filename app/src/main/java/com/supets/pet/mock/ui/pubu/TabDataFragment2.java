package com.supets.pet.mock.ui.pubu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.supets.pet.mock.base.BaseFragment;
import com.supets.pet.mock.base.SupetRecyclerViewScrollListener;
import com.supets.pet.mock.bean.MockData;
import com.supets.pet.mock.dao.MockDataDB;
import com.supets.pet.mockui.R;

import java.util.List;


public class TabDataFragment2 extends BaseFragment {

    private RecyclerView mList;
    private MockPubuAdapter adapter;
    private SwipeRefreshLayout mPull;


    public static TabDataFragment2 newInstance(String content) {
        Bundle arguments = new Bundle();
        arguments.putString("content", content);
        TabDataFragment2 tabContentFragment = new TabDataFragment2();
        tabContentFragment.setArguments(arguments);
        return tabContentFragment;
    }


    @Override
    public int getContentLayout() {
        return R.layout.fragment_tab_data_list;
    }

    @Override
    public void findViews(View view) {

        mList = view.findViewById(R.id.list);
        mList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mList.setItemAnimator(null);//去掉动画

        mPull = view.findViewById(R.id.swipe_refresh);
        mPull.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                offset = 0;
                update();
                mPull.setRefreshing(false);
            }
        });
        mList.addOnScrollListener(new SupetRecyclerViewScrollListener() {
            @Override
            public void onLoadNextPage(RecyclerView view) {

                if (!mPull.isRefreshing()) {
                    update();
                }
            }
        });
//        mPull.setOnChildScrollUpCallback(new SwipeRefreshLayout.OnChildScrollUpCallback() {
//            @Override
//            public boolean canChildScrollUp(SwipeRefreshLayout parent, @Nullable View child) {
//                if (mList == null) {
//                    return false;
//                }
//                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mList.getLayoutManager();
//                return linearLayoutManager.findFirstCompletelyVisibleItemPosition() != 0;
//            }
//        });
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void process() {
        adapter = new MockPubuAdapter();
        mList.setAdapter(adapter);
        offset = 0;
        update();
    }

    private int offset = 0;

    private void update() {
        List<MockData> datas = MockDataDB.queryAllPage(offset);
        boolean nomore = datas == null || datas.size() < 20;

        if (!nomore) {
            offset++;
        }

        if (offset == 0) {
            adapter.addHomeData(datas);
        } else {
            adapter.addMoreData(datas);
        }

    }


}