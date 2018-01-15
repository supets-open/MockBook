package com.supets.pet.mock.ui.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.supets.pet.mock.base.BaseFragment;
import com.supets.pet.mockui.R;

import java.util.ArrayList;
import java.util.List;


public class TabToolsFragment extends BaseFragment {

    private RecyclerView mList;
    private MockToolsAdapter adapter;
    private SwipeRefreshLayout mPull;

    public static TabToolsFragment newInstance(String content) {
        Bundle arguments = new Bundle();
        arguments.putString("content", content);
        TabToolsFragment tabContentFragment = new TabToolsFragment();
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
        mList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mPull = view.findViewById(R.id.swipe_refresh);
        mPull.setEnabled(false);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void process() {
        adapter = new MockToolsAdapter();
        mList.setAdapter(adapter);
        List<String> datas = new ArrayList<>();
        datas.add("JSON助手");
        datas.add("录屏工具");
        datas.add("二维码识别");
        datas.add("二维码生成");
        datas.add("图片裁剪");
        datas.add("拍照");
        adapter.setData(datas);
        adapter.notifyDataSetChanged();
    }


}
