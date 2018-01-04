package com.supets.pet.mock.ui.home;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ListView;

import com.supets.commons.widget.CommonHeader;
import com.supets.pet.mock.ViewInjector;
import com.supets.pet.mockui.R;
import com.zhy.ioc.Bind;

import java.util.ArrayList;
import java.util.List;

public class MockUiActivity extends TabLayoutBottomActivity {

    @Bind(R.id.left_drawer2)
    ListView mListMenuView;
    @Bind(R.id.header)
    CommonHeader header;
    @Bind(R.id.drawer_layout)
    DrawerLayout mSlideMenu;

    private MockAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjector.injectView(this);
        initView();
    }


    private void initView() {
        header.getTitleTextView().setText(R.string.debug_title);
        header.getTitleTextView().setTextColor(ContextCompat.getColor(this,R.color.white));
        header.getWholeView().setBackgroundResource(R.color.appcolor);
        header.getLeftButton().setVisibility(View.GONE);
        header.getTitleTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideMenu.openDrawer(GravityCompat.START);
            }
        });

        adapter = new MockAdapter();
        mListMenuView.setAdapter(adapter);

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
