package com.supets.pet.mock.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.supets.commons.widget.CommonHeader;
import com.supets.pet.mock.bean.LocalMockData;
import com.supets.pet.mock.dao.LocalMockDataDB;
import com.supets.pet.mock.dao.MockDataDB;
import com.supets.pet.mockui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试接口选择列表
 */
public class MockTestActivity extends AppCompatActivity {

    private ListView mListView;
    private MockAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_tab);

        initView();
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


    private void initView() {
        CommonHeader header = (CommonHeader) findViewById(R.id.header);
        header.getTitleTextView().setText("接口测试");
        header.getLeftButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        header.getRightButton().setText("刷新");
        header.getRightButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                resetLocalMockData();

                updateData();
            }

        });

        mListView = (ListView) findViewById(R.id.list);
        adapter = new MockAdapter();
        mListView.setAdapter(adapter);
    }

    private void resetLocalMockData() {
        List<String> datas = MockDataDB.queryAllUrl();
        if (datas != null) {
            LocalMockDataDB.deleteAll();
            for (String temp : datas) {
                LocalMockDataDB.insertMockData(new LocalMockData(null, temp, null, false));
            }
        }
    }

  private   class MockAdapter extends BaseAdapter {


        public List<LocalMockData> data = new ArrayList<>();


        public void setData(List<LocalMockData> data) {
            this.data .addAll(data);
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
                        .inflate(R.layout.mock_list_test_item, viewGroup, false);
            }

            ((TextView) view.findViewById(R.id.name)).setText(data.get(position).getUrl());

            final CheckBox checkBox = (CheckBox) view.findViewById(R.id.select);
            checkBox.setChecked(data.get(position).getSelected());
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    data.get(position).setSelected(checkBox.isChecked());
                    LocalMockDataDB.updateMockData(data.get(position));
                }
            });

            view.findViewById(R.id.configjson).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), MockConfigJsonActivity.class);
                    intent.putExtra("url", data.get(position).getUrl());
                    view.getContext().startActivity(intent);
                }
            });
            return view;
        }
    }

}
