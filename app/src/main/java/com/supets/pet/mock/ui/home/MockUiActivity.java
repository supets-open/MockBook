package com.supets.pet.mock.ui.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.supets.commons.widget.CommonHeader;
import com.supets.pet.mock.ViewInjector;
import com.supets.pet.mockui.R;
import com.zhy.ioc.Bind;

import java.util.ArrayList;
import java.util.List;

public class MockUiActivity extends AppCompatActivity {

    @Bind(R.id.list)
    ListView mListView;
    @Bind(R.id.header)
    CommonHeader header;

    private MockAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_tab);
        ViewInjector.injectView(this);
        initView();
    }


    private void initView() {
        header.getTitleTextView().setText(R.string.debug_title);
        header.getLeftButton().setVisibility(View.GONE);

        adapter = new MockAdapter();
        mListView.setAdapter(adapter);

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
