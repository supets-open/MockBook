package com.supets.pet.module.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.supets.pet.mockui.R;
import com.supets.pet.module.pubu.TabDataFragment2;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutBottomActivity extends AppCompatActivity {

    private TabLayout mTabTl;
    private ViewPager mContentVp;

    private List<String> tabIndicators;
    private List<Fragment> tabFragments;
    private ContentPagerAdapter contentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_home);

        mTabTl = findViewById(R.id.tl_tab);
        mContentVp = findViewById(R.id.vp_content);

        initContent();
        initTab();
    }

    private void initTab() {
        mTabTl.setTabMode(TabLayout.MODE_FIXED);
        mTabTl.setSelectedTabIndicatorHeight(0);
        ViewCompat.setElevation(mTabTl, 10);
        mTabTl.setupWithViewPager(mContentVp);
        for (int i = 0; i < tabIndicators.size(); i++) {
            TabLayout.Tab itemTab = mTabTl.getTabAt(i);
            if (itemTab != null) {
                itemTab.setCustomView(R.layout.item_tab_layout_custom);
                TextView itemTv = itemTab.getCustomView().findViewById(R.id.tv_menu_item);
                itemTv.setText(tabIndicators.get(i));
            }
        }
        mTabTl.getTabAt(0).getCustomView().setSelected(true);
    }

    private void initContent() {
        tabIndicators = new ArrayList<>();
        tabIndicators.add("列表");
        tabIndicators.add("图像");
        tabIndicators.add("视频");
        tabIndicators.add("布局");

        tabFragments = new ArrayList<>();
        tabFragments.add(TabDataFragment2.newInstance("列表"));
        tabFragments.add(TabToolsFragment.newInstance("图像"));
        tabFragments.add(TabToolsFragment.newInstance("视频"));
        tabFragments.add(TabToolsFragment.newInstance("布局"));

        contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        mContentVp.setAdapter(contentAdapter);
    }

    class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabIndicators.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicators.get(position);
        }
    }

}
