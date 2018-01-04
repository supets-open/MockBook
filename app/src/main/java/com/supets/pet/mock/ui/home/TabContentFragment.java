package com.supets.pet.mock.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.supets.pet.mock.ui.BaseFragment;
import com.supets.pet.mockui.R;

import java.util.ArrayList;
import java.util.List;


public class TabContentFragment extends BaseFragment {

    private ListView mList;
    private MockAdapter adapter;

    public static TabContentFragment newInstance(String content) {
        Bundle arguments = new Bundle();
        arguments.putString("content", content);
        TabContentFragment tabContentFragment = new TabContentFragment();
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
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void process() {
        adapter = new MockAdapter();
        mList.setAdapter(adapter);
        List<String> datas = new ArrayList<>();
        datas.add("数据抓取");
        datas.add("接口测试");
        datas.add("数据模型");
        datas.add("测试配置");
        datas.add("邮件管理");
        datas.add("映射测试");
        datas.add("JSON助手");
        datas.add("异常管理");
        adapter.setData(datas);
        adapter.notifyDataSetChanged();
    }

}
