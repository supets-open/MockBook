package com.supets.pet.mock.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.supets.pet.mock.base.SupetRecyclerViewScrollListener;
import com.supets.pet.mock.bean.MockData;
import com.supets.pet.mock.dao.MockDataDB;
import com.supets.pet.mock.base.BaseFragment;
import com.supets.pet.mockui.R;

import java.util.List;


public class TabDataFragment extends BaseFragment {

    private RecyclerView mList;
    private MockDataAdapter adapter;
    private SwipeRefreshLayout mPull;

    public static TabDataFragment newInstance(String content) {
        Bundle arguments = new Bundle();
        arguments.putString("content", content);
        TabDataFragment tabContentFragment = new TabDataFragment();
        tabContentFragment.setArguments(arguments);
        return tabContentFragment;
    }


    @Override
    public int getContentLayout() {
        return R.layout.fragment_tab_content;
    }

    @Override
    public void findViews(View view) {
        mList = view.findViewById(R.id.list);
        mList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPull = view.findViewById(R.id.swipe_refresh);
        mPull.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                offset = 0;
                update();
            }
        });
        mList.addOnScrollListener(new SupetRecyclerViewScrollListener() {
            @Override
            public void onLoadNextPage(RecyclerView view) {
                update();
            }
        });
        mPull.setOnChildScrollUpCallback(new SwipeRefreshLayout.OnChildScrollUpCallback() {
            @Override
            public boolean canChildScrollUp(SwipeRefreshLayout parent, @Nullable View child) {
                if (mList == null) {
                    return false;
                }
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mList.getLayoutManager();
                return linearLayoutManager.findFirstCompletelyVisibleItemPosition() != 0;
            }
        });
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void process() {
        adapter = new MockDataAdapter();
        mList.setAdapter(adapter);
        offset = 0;
        update();
    }

    private int offset = 0;

    private void update() {
        List<MockData> datas = MockDataDB.queryAllPage(offset);
        boolean nomore = datas == null || datas.size() < 20;

        if (offset == 0) {
            if (!nomore) {
                adapter.addHomeData(datas);
                offset++;
                // smartRefreshLayout.setEnableLoadmore(true);
                //smartRefreshLayout.autoLoadmore();
            } else {
                adapter.addHomeData(null);
                // smartRefreshLayout.setEnableLoadmore(false);
            }
        } else {
            if (!nomore) {
                adapter.addMoreData(datas);
                offset++;
            }
            //smartRefreshLayout.finishLoadmore(100, true, nomore);
        }


    }


}
