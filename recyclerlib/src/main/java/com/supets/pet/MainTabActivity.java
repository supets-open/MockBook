package com.supets.pet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recyclerlib.R;
import com.supets.pet.banner.BannerMainActivity;
import com.supets.pet.gallery.GalleryActivity;
import com.supets.pet.pull.activity.LoadMoreWrapperActivity;
import com.supets.pet.suspendbar.SuspendActivity;
import com.supets.pet.tianmao.TianMaoActivity;

import java.util.ArrayList;

public class MainTabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainTabAdapter adapter = new MainTabAdapter();
        RecyclerView mFeedList = findViewById(R.id.feed_list);
        mFeedList.setLayoutManager(new LinearLayoutManager(this));
        mFeedList.setAdapter(adapter);
        mFeedList.setHasFixedSize(true);
    }

    private class MainTabAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private ArrayList<ClickType> data = new ArrayList<>();

        public MainTabAdapter() {
            data.add(new ClickType(0, "RecyclerView实现悬浮"));
            data.add(new ClickType(1, "RecyclerView实现Gallery"));
            data.add(new ClickType(2, "RecyclerView实现Banner"));
            data.add(new ClickType(3, "RecyclerView实现下拉刷新和上拉加载更多"));
            data.add(new ClickType(4, "RecyclerView实现天猫效果"));
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyclerView.ViewHolder(new MainViewHolder(parent).mWholeView) {
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            MainViewHolder mainViewHolder = (MainViewHolder) holder.itemView.getTag();
            mainViewHolder.updateData(data.get(position));
        }


        @Override
        public int getItemCount() {
            return data.size();
        }

        public class MainViewHolder implements View.OnClickListener {

            private View mWholeView;
            private TextView name;
            private ClickType mData;

            public MainViewHolder(ViewGroup parent) {
                mWholeView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
                name = mWholeView.findViewById(R.id.name);
                mWholeView.setOnClickListener(this);
                mWholeView.setTag(this);
            }

            public void updateData(ClickType data) {
                this.mData = data;
                name.setText(mData.name);
            }

            @Override
            public void onClick(View view) {
                if (mData.type == 0) {
                    Intent intent = new Intent(view.getContext(), SuspendActivity.class);
                    view.getContext().startActivity(intent);
                }

                if (mData.type == 1) {
                    Intent intent = new Intent(view.getContext(), GalleryActivity.class);
                    view.getContext().startActivity(intent);
                }

                if (mData.type == 2) {
                    Intent intent = new Intent(view.getContext(), BannerMainActivity.class);
                    view.getContext().startActivity(intent);
                }

                if (mData.type == 3) {
                    Intent intent = new Intent(view.getContext(), LoadMoreWrapperActivity.class);
                    view.getContext().startActivity(intent);
                }
                if (mData.type == 4) {
                    Intent intent = new Intent(view.getContext(), TianMaoActivity.class);
                    view.getContext().startActivity(intent);
                }
            }
        }


        public class ClickType {
            public int type;
            public String name;

            public ClickType(int type, String name) {
                this.type = type;
                this.name = name;
            }
        }
    }


}
