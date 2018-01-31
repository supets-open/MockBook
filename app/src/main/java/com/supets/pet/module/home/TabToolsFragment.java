package com.supets.pet.module.home;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.supets.commons.widget.CommonHeader;
import com.supets.pet.base.BaseFragment;
import com.supets.pet.mockui.R;

import java.util.ArrayList;
import java.util.List;


public class TabToolsFragment extends BaseFragment {

    private RecyclerView mList;
    private MockToolsAdapter adapter;
    private SwipeRefreshLayout mPull;
    private CommonHeader header;

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
        header = view.findViewById(R.id.header);
        initView();
    }

    private void initView() {
        header.getTitleTextView().setText(R.string.app_name);
        header.getTitleTextView().setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
        header.getWholeView().setBackgroundResource(R.color.appcolor);
        header.getLeftButton().setVisibility(View.GONE);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void process() {
        adapter = new MockToolsAdapter();
        mList.setAdapter(adapter);
        List<String> datas = new ArrayList<>();
        datas.add("测试代码");
        datas.add("录屏工具");
        datas.add("二维码识别");
        datas.add("二维码生成");
        datas.add("图片裁剪");
        datas.add("图片裁剪");
        datas.add("拍照");
        datas.add("图片添加贴纸");
        adapter.setData(datas);
        adapter.notifyDataSetChanged();
    }


}
