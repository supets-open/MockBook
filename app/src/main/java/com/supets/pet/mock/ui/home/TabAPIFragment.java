package com.supets.pet.mock.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.supets.pet.mock.bean.LocalMockData;
import com.supets.pet.mock.dao.LocalMockDataDB;
import com.supets.pet.mock.ui.BaseFragment;
import com.supets.pet.mock.ui.MockConfigJsonActivity;
import com.supets.pet.mockui.R;

import java.util.ArrayList;
import java.util.List;


public class TabAPIFragment extends BaseFragment {

    private ListView mList;
    private MockTestAdapter adapter;
    private SmartRefreshLayout smartRefreshLayout;

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
        smartRefreshLayout = view.findViewById(R.id.refreshLayout);
        smartRefreshLayout.setEnableLoadmore(false);
        smartRefreshLayout.setEnableRefresh(false);
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

    private class MockTestAdapter extends BaseAdapter {


        public List<LocalMockData> data = new ArrayList<>();


        public void setData(List<LocalMockData> data) {
            this.data.addAll(data);
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
