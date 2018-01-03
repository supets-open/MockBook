package com.supets.pet.mock.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.supets.commons.widget.CommonHeader;
import com.supets.pet.mock.bean.MockData;
import com.supets.pet.mock.utils.FormatLogProcess;
import com.supets.pet.mock.dao.MockDataDB;
import com.supets.pet.mockui.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MockDataListActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_tab);

        initView();
        update();

    }

    private void update() {
        List<MockData> datas = MockDataDB.queryAll();
        if (datas != null) {
            adapter.setData(datas);
            adapter.notifyDataSetChanged();
        }
    }

    ListView mListView;
    MockAdapter adapter;

    private void initView() {
        CommonHeader header = (CommonHeader) findViewById(R.id.header);
        header.getTitleTextView().setText("数据抓取列表");
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
                MockDataDB.deleteAll();
                update();
            }
        });

        mListView = (ListView) findViewById(R.id.list);
        adapter = new MockAdapter();
        mListView.setAdapter(adapter);

    }


    class MockAdapter extends BaseAdapter {


        public List<MockData> data = new ArrayList<>();


        public void setData(List<MockData> data) {
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
                        .inflate(R.layout.mock_list_datalist_item, viewGroup, false);
            }

            ((TextView) view.findViewById(R.id.name)).setText(data.get(position).getUrl());


            if (FormatLogProcess.isJson(data.get(position).getData())) {
                (view.findViewById(R.id.status)).setBackgroundColor(Color.parseColor("#00ff00"));
            } else {
                (view.findViewById(R.id.status)).setBackgroundColor(Color.parseColor("#ff0000"));
            }

            String time = dateToStrLong(data.get(position).getTime());
            ((TextView) view.findViewById(R.id.time)).setText(time);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), MockInfoActivity.class);
                    intent.putExtra("id", data.get(position).getId().toString());
                    view.getContext().startActivity(intent);
                }
            });

            return view;
        }
    }

    public static String dateToStrLong(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

}
