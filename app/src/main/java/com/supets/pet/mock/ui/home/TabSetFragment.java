package com.supets.pet.mock.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.supets.pet.mock.ui.BaseFragment;
import com.supets.pet.mock.ui.MockConfigActivity;
import com.supets.pet.mock.ui.MockCrashListActivity;
import com.supets.pet.mock.ui.MockEmailListActivity;
import com.supets.pet.mock.ui.MockModelActivity;
import com.supets.pet.mock.ui.MockToolActivity;
import com.supets.pet.mock.ui.MockUrlRuleActivity;
import com.supets.pet.mockui.R;

import java.util.ArrayList;
import java.util.List;


public class TabSetFragment extends BaseFragment {

    private ListView mList;
    private MockMoreAdapter adapter;

    public static TabSetFragment newInstance(String content) {
        Bundle arguments = new Bundle();
        arguments.putString("content", content);
        TabSetFragment tabContentFragment = new TabSetFragment();
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
        adapter = new MockMoreAdapter();
        mList.setAdapter(adapter);
        List<String> datas = new ArrayList<>();
        datas.add("映射测试");
        datas.add("测试配置");
        datas.add("邮件管理");
        datas.add("数据模型");
        datas.add("异常管理");
        datas.add("JSON助手");
        adapter.setData(datas);
        adapter.notifyDataSetChanged();
    }


    public class MockMoreAdapter extends BaseAdapter {


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
                        .inflate(R.layout.mock_list_home_item, viewGroup, false);
            }

            ((TextView) view.findViewById(R.id.name)).setText(data.get(position));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position == 0) {
                        Intent intent = new Intent(view.getContext(), MockUrlRuleActivity.class);
                        view.getContext().startActivity(intent);
                    } else if (position == 1) {
                        Intent intent = new Intent(view.getContext(), MockConfigActivity.class);
                        view.getContext().startActivity(intent);
                    } else if (position == 2) {
                        Intent intent = new Intent(view.getContext(), MockEmailListActivity.class);
                        view.getContext().startActivity(intent);
                    } else if (position == 3) {
                        Intent intent = new Intent(view.getContext(), MockModelActivity.class);
                        view.getContext().startActivity(intent);
                    } else if (position == 4) {
                        Intent intent = new Intent(view.getContext(), MockCrashListActivity.class);
                        view.getContext().startActivity(intent);
                    } else if (position == 5) {
                        Intent intent = new Intent(view.getContext(), MockToolActivity.class);
                        view.getContext().startActivity(intent);
                    }

                }
            });
            return view;
        }
    }
}
