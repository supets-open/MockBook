package com.supets.pet.mock.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.supets.commons.widget.CommonHeader;
import com.supets.lib.supetscontext.App;
import com.supets.pet.mock.bean.LocalMockData;
import com.supets.pet.mock.bean.MockData;
import com.supets.pet.mock.bean.MockExampleData;
import com.supets.pet.mock.core.FormatLogProcess;
import com.supets.pet.mock.dao.LocalMockDataDB;
import com.supets.pet.mock.dao.MockDataDB;
import com.supets.pet.mock.dao.MockExampleDataDB;
import com.supets.pet.mockui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * supets_shopmail
 *
 * @user lihongjiang
 * @description
 * @date 2017/6/19
 * @updatetime 2017/6/19
 */

public class MockConfigJsonActivity extends Activity {

    private ListView mListView;
    private EditText content;
    private MockAdapter adapter;
    private LocalMockData localMockData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_configjson);
        localMockData = LocalMockDataDB.queryAllMockData(getIntent().getStringExtra("url")).get(0);
        initView();
        updateData();
    }

    private void updateData() {
        List<MockData> datas = MockDataDB.
                queryAllMockData(localMockData.getUrl());
        if (datas != null) {
            adapter.setData(localMockData);
            adapter.setData(datas);
            adapter.setData(MockExampleDataDB.queryAll());
            adapter.notifyDataSetChanged();
        }
    }

    private void initView() {
        CommonHeader header = (CommonHeader) findViewById(R.id.header);
        header.getTitleTextView().setText("接口数据配置详情");
        header.getLeftButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        header.getRightButton().setText("保存");
        header.getRightButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = content.getText().toString();
                localMockData.setData(data);
                LocalMockDataDB.updateMockData(localMockData);
                App.toast("配置成功");
            }

        });

        mListView = (ListView) findViewById(R.id.datalist);
        adapter = new MockAdapter();
        mListView.setAdapter(adapter);

        content = (EditText) findViewById(R.id.content);
        content.setText(localMockData.getData());

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(localMockData.getUrl());
    }

    private class MockAdapter<T> extends BaseAdapter {

        public List<T> data = new ArrayList<>();

        public void setData(List<T> data) {
            if (data != null)
                this.data.addAll(data);
        }

        public void setData(T data) {
            if (data != null)
                this.data.add(data);
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
                        .inflate(R.layout.mock_list_config_item, viewGroup, false);
            }


            if (data.get(position) instanceof LocalMockData) {
                final LocalMockData temp = (LocalMockData) data.get(position);
                ((TextView) view.findViewById(R.id.name)).setText("原始" + position);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        content.setText(temp.getData());
                    }
                });
                if (FormatLogProcess.isJson(temp.getData())) {
                    (view.findViewById(R.id.status)).setBackgroundColor(Color.parseColor("#00ff00"));
                } else {
                    (view.findViewById(R.id.status)).setBackgroundColor(Color.parseColor("#ff0000"));
                }
            }

            if (data.get(position) instanceof MockData) {
                final MockData temp = (MockData) data.get(position);
                ((TextView) view.findViewById(R.id.name)).setText("抓取" + position);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        content.setText(temp.getData());
                    }
                });
                if (FormatLogProcess.isJson(temp.getData())) {
                    (view.findViewById(R.id.status)).setBackgroundColor(Color.parseColor("#00ff00"));
                } else {
                    (view.findViewById(R.id.status)).setBackgroundColor(Color.parseColor("#ff0000"));
                }
            }

            if (data.get(position) instanceof MockExampleData) {
                final MockExampleData temp = (MockExampleData) data.get(position);
                ((TextView) view.findViewById(R.id.name)).setText(temp.getName());
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        content.setText(temp.getData());
                    }
                });
                if (FormatLogProcess.isJson(temp.getData())) {
                    (view.findViewById(R.id.status)).setBackgroundColor(Color.parseColor("#00ff00"));
                } else {
                    (view.findViewById(R.id.status)).setBackgroundColor(Color.parseColor("#ff0000"));
                }
            }

            return view;
        }
    }

}
