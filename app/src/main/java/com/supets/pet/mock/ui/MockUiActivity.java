package com.supets.pet.mock.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.supets.commons.widget.CommonHeader;
import com.supets.pet.mock.ViewInjector;
import com.supets.pet.mockui.R;
import com.zhy.ioc.Bind;

import java.util.ArrayList;
import java.util.List;

public class MockUiActivity extends Activity {

    @Bind(R.id.list)
    ListView mListView;
    @Bind(R.id.header)
    CommonHeader header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_tab);
        ViewInjector.injectView(this);

        initView();

        List<String> datas = new ArrayList<>();
        datas.add("数据抓取");
        datas.add("接口测试");
        datas.add("数据模型");
        datas.add("测试配置");
        datas.add("邮件管理");
        datas.add("映射测试");
        datas.add("JSON助手");
        //datas.add("版本更新");
        adapter.setData(datas);
        adapter.notifyDataSetChanged();
    }

    MockAdapter adapter;

    private void initView() {
        header.getTitleTextView().setText(R.string.debug_title);
        header.getLeftButton().setVisibility(View.GONE);

        adapter = new MockAdapter();
        mListView.setAdapter(adapter);

    }


    class MockAdapter extends BaseAdapter {


        public List<String> data = new ArrayList<>();

        public void setData(List<String> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {

            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.mock_list_tab_item, viewGroup, false);
            }

            ((TextView) view.findViewById(R.id.name)).setText(data.get(position));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position == 0) {
                        Intent intent = new Intent(view.getContext(), MockDataListActivity.class);
                        view.getContext().startActivity(intent);
                    }

                    if (position == 1) {
                        Intent intent = new Intent(view.getContext(), MockTestActivity.class);
                        view.getContext().startActivity(intent);
                    }
                    if (position == 2) {
                        Intent intent = new Intent(view.getContext(), MockModelActivity.class);
                        view.getContext().startActivity(intent);
                    }
                    if (position == 3) {
                        Intent intent = new Intent(view.getContext(), MockConfigActivity.class);
                        view.getContext().startActivity(intent);
                    }

                    if (position == 4) {
                        Intent intent = new Intent(view.getContext(), MockEmailListActivity.class);
                        view.getContext().startActivity(intent);
                    }

                    if (position == 5) {
                        Intent intent = new Intent(view.getContext(), MockUrlRuleActivity.class);
                        view.getContext().startActivity(intent);
                    }

                    if (position == 6) {
                        Intent intent = new Intent(view.getContext(), MockToolActivity.class);
                        view.getContext().startActivity(intent);
                    }
                    if (position == 7) {
                        Intent intent = new Intent(view.getContext(), MockKeyActivity.class);
                        view.getContext().startActivity(intent);
                    }
                }
            });
            return view;
        }
    }

}
