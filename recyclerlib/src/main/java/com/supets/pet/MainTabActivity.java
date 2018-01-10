package com.supets.pet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recyclerlib.R;
import com.supets.pet.banner.BannerMainActivity;
import com.supets.pet.gallery.GalleryActivity;
import com.supets.pet.pull.activity.LoadMoreWrapperActivity;
import com.supets.pet.suspendbar.SuspendActivity;

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

        private ArrayList<String> data = new ArrayList<>();

        public MainTabAdapter() {
            data.add("RecyclerView实现悬浮");
            data.add("RecyclerView实现Gallery");
            data.add("RecyclerView实现Banner");
            data.add("RecyclerView实现下拉刷新和上拉加载更多");
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyclerView.ViewHolder(new TextView(parent.getContext())) {
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            TextView name = (TextView) holder.itemView;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    qq(view, position);
                }
            });
            name.setText(data.get(position));
        }

        private void qq(View view, int position) {
            if (position == 0) {
                Intent intent = new Intent(view.getContext(), SuspendActivity.class);
                view.getContext().startActivity(intent);
            }

            if (position == 1) {
                Intent intent = new Intent(view.getContext(), GalleryActivity.class);
                view.getContext().startActivity(intent);
            }

            if (position == 2) {
                Intent intent = new Intent(view.getContext(), BannerMainActivity.class);
                view.getContext().startActivity(intent);
            }

            if (position == 3) {
                Intent intent = new Intent(view.getContext(), LoadMoreWrapperActivity.class);
                view.getContext().startActivity(intent);
            }
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }


}
