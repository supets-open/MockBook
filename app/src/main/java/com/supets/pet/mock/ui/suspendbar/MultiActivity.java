package com.supets.pet.mock.ui.suspendbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.supets.pet.mockui.R;

public class MultiActivity extends AppCompatActivity {
  private RecyclerView mFeedList;
  private RelativeLayout mSuspensionBar;
  private TextView mSuspensionTv;
  private int mCurrentPosition = 0;

  private int mSuspensionHeight;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_multi);

    mSuspensionBar = findViewById(R.id.suspension_bar);
    mSuspensionTv = findViewById(R.id.tv_time);

    final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    final MultiFeedAdapter adapter = new MultiFeedAdapter();

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
    mSuspensionTv.setText(getTime(mCurrentPosition));
  }

  private String getTime(int position) {
    return "NOVEMBER " + (1 + position / 4);
  }
}
