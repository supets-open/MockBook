package com.supets.pet.banner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


import com.example.recyclerlib.R;
import com.supets.pet.banner.banner.BannerLayout;

import java.util.ArrayList;
import java.util.List;

public class BannerMainActivity extends AppCompatActivity implements BannerLayout.OnBannerItemClickListener {

    BannerLayout banner, bannerVertical;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        toolbar = findViewById(R.id.toolbar);
        // 使用Toolbar替换ActionBar
        setSupportActionBar(toolbar);

        banner = findViewById(R.id.recycler);
        bannerVertical = findViewById(R.id.recycler_ver);
        //解决recyclerView嵌套问题
        List<String> list = new ArrayList<>();
        list.add("http://oo6pz0u05.bkt.clouddn.com/17-12-13/69427561.jpg");
        list.add("http://oo6pz0u05.bkt.clouddn.com/17-12-13/23738150.jpg");
        list.add("http://oo6pz0u05.bkt.clouddn.com/17-12-13/30127126.jpg");
        list.add("http://oo6pz0u05.bkt.clouddn.com/17-12-13/36125492.jpg");

        banner.initBannerImageView(list);
        bannerVertical.initBannerImageView(list);
        banner.setOnBannerItemClickListener(this);
        bannerVertical.setOnBannerItemClickListener(this);
    }


    public void jump(View view) {
        startActivity(new Intent(BannerMainActivity.this, BannerNormalActivity.class));
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "点击了第  " + position+"  项", Toast.LENGTH_SHORT).show();
    }
}
