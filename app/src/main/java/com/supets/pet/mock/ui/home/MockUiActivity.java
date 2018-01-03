package com.supets.pet.mock.ui.home;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ryan.rv_gallery.AnimManager;
import com.ryan.rv_gallery.GalleryRecyclerView;
import com.supets.commons.widget.CommonHeader;
import com.supets.pet.mock.ViewInjector;
import com.supets.pet.mock.ui.MockConfigActivity;
import com.supets.pet.mock.ui.MockCrashListActivity;
import com.supets.pet.mock.ui.MockDataListActivity;
import com.supets.pet.mock.ui.MockEmailListActivity;
import com.supets.pet.mock.ui.MockModelActivity;
import com.supets.pet.mock.ui.MockTestActivity;
import com.supets.pet.mock.ui.MockToolActivity;
import com.supets.pet.mock.ui.MockUrlRuleActivity;
import com.supets.pet.mockui.R;
import com.zhy.ioc.Bind;

import java.util.ArrayList;
import java.util.List;

public class MockUiActivity extends AppCompatActivity implements GalleryRecyclerView.OnItemClickListener {

    //    @Bind(R.id.rv_list)
//    ListView mListView;
    @Bind(R.id.header)
    CommonHeader header;

    private MockAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewInjector.injectView(this);
        onCreate2();
        initView();

    }


    private void initView() {
        header.getTitleTextView().setText(R.string.debug_title);
        header.getLeftButton().setVisibility(View.GONE);

        adapter = new MockAdapter();
        //mListView.setAdapter(adapter);

        //List<String> datas = new ArrayList<>();
        //datas.add("数据抓取");
        //datas.add("接口测试");
        //datas.add("数据模型");
        //datas.add("测试配置");
        // datas.add("邮件管理");
        // datas.add("映射测试");
        // datas.add("JSON助手");
        // datas.add("异常管理");
        //adapter.setData(datas);
        //adapter.notifyDataSetChanged();
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
//                    if (position == 7) {
//                        Intent intent = new Intent(view.getContext(), MockKeyActivity.class);
//                        view.getContext().startActivity(intent);
//                    }

                    if (position == 7) {
                        Intent intent = new Intent(view.getContext(), MockCrashListActivity.class);
                        view.getContext().startActivity(intent);
                    }
                }
            });
            return view;
        }
    }

    /////////
    private GalleryRecyclerView mRecyclerView;


    protected void onCreate2() {

        mRecyclerView = findViewById(R.id.rv_list);

        final RecyclerAdapter adapter = new RecyclerAdapter(getApplicationContext(), getDatas());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.initFlingSpeed(9000)                                   // 设置滑动速度（像素/s）
                .initPageParams(0, 60)     // 设置页边距和左右图片的可见宽度，单位dp
                .setAnimFactor(0.15f)                                   // 设置切换动画的参数因子
                .setAnimType(AnimManager.ANIM_BOTTOM_TO_TOP)            // 设置切换动画类型，目前有AnimManager.ANIM_BOTTOM_TO_TOP和目前有AnimManager.ANIM_TOP_TO_BOTTOM
                .setOnItemClickListener(this);                          // 设置点击事件
    }


    /***
     * 测试数据
     * @return
     */
    public List<Integer> getDatas() {
        TypedArray ar = getResources().obtainTypedArray(R.array.test_arr);
        final int[] resIds = new int[ar.length()];
        for (int i = 0; i < ar.length(); i++) {
            resIds[i] = ar.getResourceId(i, 0);
        }
        ar.recycle();
        List<Integer> tDatas = new ArrayList<>();
        for (int resId : resIds) {
            tDatas.add(resId);
        }
        return tDatas;
    }


    @Override
    public void onItemClick(View view, int position) {

    }
}
