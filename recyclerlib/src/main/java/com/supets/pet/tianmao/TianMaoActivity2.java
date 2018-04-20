package com.supets.pet.tianmao;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.recyclerlib.R;
import com.supets.pet.pull.adapter.LoadMoreAdapter;
import com.supets.pet.suspendbar.MultiFeedAdapter;

import java.util.ArrayList;
import java.util.List;

public class TianMaoActivity2 extends FragmentActivity {

    private MultiFeedAdapter loadMoreAdapter;
    private List<String> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView header;
    private LoadMoreAdapter loadMoreAdapter2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tianmao2);
        TabLayout  tabLayout=findViewById(R.id.tab);
        //tab可滚动
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //tab居中显示
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        //tab的字体选择器,默认黑色,选择时红色
        tabLayout.setTabTextColors(Color.BLACK, Color.RED);
        //tab的下划线颜色,默认是粉红色
        tabLayout.setSelectedTabIndicatorColor(Color.BLUE);
        for (int i = 0; i < 20; i++) {
            //添加tab
            tabLayout.addTab(tabLayout.newTab().setText("TAB" + i));
        }
        final Toast toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        //设置tab的点击监听器
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                toast.setText(tab.getPosition() + ":" + tab.getText());
                toast.show();

              recyclerView.getLayoutManager().scrollToPosition(0);
                loadMoreAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        header = findViewById(R.id.header);
        header.setNestedScrollingEnabled(false);

        recyclerView = findViewById(R.id.list);

        // 模拟获取数据
        getData();

        loadMoreAdapter = new MultiFeedAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL,false));
        recyclerView.setAdapter(loadMoreAdapter);

//        PagingScrollHelper  pagingScrollHelper=new PagingScrollHelper();
//        pagingScrollHelper.setUpRecycleView(recyclerView);
//        pagingScrollHelper.setOnPageChangeListener(new PagingScrollHelper.onPageChangeListener() {
//            @Override
//            public void onPageChange(int index) {
//                toast.setText(index+"------");
//                toast.show();
//            }
//        });

        loadMoreAdapter2 = new LoadMoreAdapter(dataList);
        header.setLayoutManager(new LinearLayoutManager(this));
        header.setAdapter(loadMoreAdapter2);

    }


    private void getData() {
        char letter = 'A';
        for (int i = 0; i < 24; i++) {
            dataList.add(String.valueOf(letter));
            letter++;
        }
    }
}
