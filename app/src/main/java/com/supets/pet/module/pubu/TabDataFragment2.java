package com.supets.pet.module.pubu;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.supets.commons.widget.CommonHeader;
import com.supets.pet.base.BaseFragment;
import com.supets.pet.base.SupetRecyclerViewScrollListener;
import com.supets.pet.bean.MockData;
import com.supets.pet.mockui.R;

import java.util.ArrayList;
import java.util.List;


public class TabDataFragment2 extends BaseFragment {

    private RecyclerView mList;
    private MockPubuAdapter adapter;
    private SwipeRefreshLayout mPull;
    private CommonHeader header;


    public static TabDataFragment2 newInstance(String content) {
        Bundle arguments = new Bundle();
        arguments.putString("content", content);
        TabDataFragment2 tabContentFragment = new TabDataFragment2();
        tabContentFragment.setArguments(arguments);
        return tabContentFragment;
    }


    @Override
    public int getContentLayout() {
        return R.layout.fragment_tab_data_list;
    }

    @Override
    public void findViews(View view) {

        mList = view.findViewById(R.id.list);
        mList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mList.setItemAnimator(null);//去掉动画

        mPull = view.findViewById(R.id.swipe_refresh);
        mPull.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                offset = 0;
                update();
                mPull.setRefreshing(false);
            }
        });
        mList.addOnScrollListener(new SupetRecyclerViewScrollListener() {
            @Override
            public void onLoadNextPage(RecyclerView view) {

                if (!mPull.isRefreshing()) {
                    update();
                }
            }
        });
//        mPull.setOnChildScrollUpCallback(new SwipeRefreshLayout.OnChildScrollUpCallback() {
//            @Override
//            public boolean canChildScrollUp(SwipeRefreshLayout parent, @Nullable View child) {
//                if (mList == null) {
//                    return false;
//                }
//                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mList.getLayoutManager();
//                return linearLayoutManager.findFirstCompletelyVisibleItemPosition() != 0;
//            }
//        });
        header = view.findViewById(R.id.header);
        initView();
    }

    private void initView() {
        header.getTitleTextView().setText(R.string.app_name);
        header.getTitleTextView().setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
        header.getWholeView().setBackgroundResource(R.color.appcolor);
        header.getLeftButton().setVisibility(View.GONE);
        header.getRightButton().setText("插入数据");
        header.getRightButton().setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                 ArrayList<MockData> temp = new ArrayList<MockData>() {};
                temp.add(new MockData(new Long(122), "CHARU", null, null, new java.util.Date()));
                temp.add(new MockData(new Long(122), "CHARU", null, null, new java.util.Date()));
                temp.add(new MockData(new Long(122), "CHARU", null, null, new java.util.Date()));
                temp.add(new MockData(new Long(122), "CHARU", null, null, new java.util.Date()));
                temp.add(new MockData(new Long(122), "CHARU", null, null, new java.util.Date()));
                adapter.insert(3, temp);
            }
        });
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void process() {
        adapter = new MockPubuAdapter();
        mList.setAdapter(adapter);
        offset = 0;
        update();
    }

    private int offset = 0;

    private void update() {
       // List<MockData> datas = MockDataDB.queryAllPage(offset);
        List<MockData> datas =new ArrayList<>();

        datas.add(new MockData());
        datas.add(new MockData());
        datas.add(new MockData());
        datas.add(new MockData());
        datas.add(new MockData());
        datas.add(new MockData());
        datas.add(new MockData());
        datas.add(new MockData());

        boolean nomore = datas == null || datas.size() < 20;

        if (!nomore) {
            offset++;
        }

        if (offset == 0) {
            adapter.addHomeData(datas);
        } else {
            adapter.addMoreData(datas);
        }

    }


}
