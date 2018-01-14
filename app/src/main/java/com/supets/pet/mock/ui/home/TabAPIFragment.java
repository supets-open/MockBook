package com.supets.pet.mock.ui.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.supets.pet.mock.bean.LocalMockData;
import com.supets.pet.mock.dao.LocalMockDataDB;
import com.supets.pet.mock.base.BaseFragment;
import com.supets.pet.mockui.R;

import java.util.List;


public class TabAPIFragment extends BaseFragment {

    private RecyclerView mList;
    private MockTestAdapter adapter;
    private SwipeRefreshLayout mPull;

    public static TabAPIFragment newInstance(String content) {
        Bundle arguments = new Bundle();
        arguments.putString("content", content);
        TabAPIFragment tabContentFragment = new TabAPIFragment();
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
        mPull.setEnabled(false);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void process() {
        adapter = new MockTestAdapter();
        mList.setAdapter(adapter);
        updateData();
    }

    private void updateData() {
        List<LocalMockData> datas = LocalMockDataDB.queryAll();
        if (datas != null) {
            adapter.data.clear();
            adapter.setData(datas);
            adapter.notifyDataSetChanged();
        }
    }


}
