package com.supets.pet.mock.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.supets.pet.mock.bean.MockData;
import com.supets.pet.mock.dao.MockDataDB;
import com.supets.pet.mock.ui.BaseFragment;
import com.supets.pet.mock.ui.MockInfoActivity;
import com.supets.pet.mock.utils.FormatLogProcess;
import com.supets.pet.mockui.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class TabDataFragment extends BaseFragment {

    private ListView mList;
    private MockDataAdapter adapter;

    public static TabDataFragment newInstance(String content) {
        Bundle arguments = new Bundle();
        arguments.putString("content", content);
        TabDataFragment tabContentFragment = new TabDataFragment();
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
        //smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
         //   @Override
        //    public void onLoadmore(RefreshLayout refreshlayout) {
        //        update();
         //   }
        //});

       // smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
        //    @Override
        //    public void onRefresh(RefreshLayout refreshlayout) {
        //        offset = 0;
       //         update();
           //     refreshlayout.resetNoMoreData();
        //        refreshlayout.finishRefresh(true);
       //     }
      //  });
    }

    @Override
    public void process() {
        adapter = new MockDataAdapter();
        mList.setAdapter(adapter);
        offset = 0;
        update();
    }

    private int offset = 0;

    private void update() {
        List<MockData> datas = MockDataDB.queryAllPage(offset);
        boolean nomore = datas == null || datas.size()<20;

        if (offset == 0) {
            if (!nomore) {
                adapter.addHomeData(datas);
                offset++;
               // smartRefreshLayout.setEnableLoadmore(true);
                //smartRefreshLayout.autoLoadmore();
            } else {
                adapter.addHomeData(null);
               // smartRefreshLayout.setEnableLoadmore(false);
            }
        } else {
            if (!nomore) {
                adapter.addMoreData(datas);
                offset++;
            }
            //smartRefreshLayout.finishLoadmore(100, true, nomore);
        }


    }

    private class MockDataAdapter extends BaseAdapter {


        public List<MockData> data = new ArrayList<>();

        public void setData(List<MockData> data) {
            this.data = data;
        }

        public void addHomeData(List<MockData> data) {
            this.data.clear();
            if (data != null) {
                this.data.addAll(data);
            }
            notifyDataSetChanged();
        }

        public void addMoreData(List<MockData> data) {
            if (data != null) {
                this.data.addAll(data);
                notifyDataSetChanged();
            }
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
                (view.findViewById(R.id.status)).setBackgroundColor(getResources().getColor(R.color.appcolor));
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
