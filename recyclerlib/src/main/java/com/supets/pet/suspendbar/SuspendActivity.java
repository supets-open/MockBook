package com.supets.pet.suspendbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.example.recyclerlib.R;

public class SuspendActivity extends AppCompatActivity {
    private RecyclerView mFeedList;
    private RelativeLayout mSuspensionBar;
    private TextView mSuspensionTv;
    private ImageView mSuspensionIv;
    private int mCurrentPosition = 0;

    private int mSuspensionHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspend);

        mSuspensionBar = findViewById(R.id.suspension_bar);
        mSuspensionTv = findViewById(R.id.tv_nickname);
        mSuspensionIv = findViewById(R.id.iv_avatar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.item_jump) {
                    Intent intent = new Intent(SuspendActivity.this, MultiActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        final FeedAdapter adapter = new FeedAdapter();

        mFeedList = findViewById(R.id.feed_list);
        mFeedList.setLayoutManager(linearLayoutManager);
        mFeedList.setAdapter(adapter);
        mFeedList.setHasFixedSize(true);

        mFeedList.addOnScrollListener(new SuspensionBarScrollListener(mSuspensionBar) {
            @Override
            int getItemViewType() {
                return 0;
            }

            @Override
            void updateSuspensionBar(int position) {
                mCurrentPosition=position;
                updateSuspensionBar2();
            }
        });

        updateSuspensionBar2();
    }

    private void updateSuspensionBar2() {
        Picasso.with(SuspendActivity.this)
                .load(getAvatarResId(mCurrentPosition))
                .centerInside()
                .fit()
                .into(mSuspensionIv);

        mSuspensionTv.setText("Taeyeon " + mCurrentPosition);
    }

    private int getAvatarResId(int position) {
        switch (position % 4) {
            case 0:
                return R.drawable.avatar1;
            case 1:
                return R.drawable.avatar2;
            case 2:
                return R.drawable.avatar3;
            case 3:
                return R.drawable.avatar4;
        }
        return 0;
    }
}
