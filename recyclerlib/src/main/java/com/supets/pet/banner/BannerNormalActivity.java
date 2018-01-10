package com.supets.pet.banner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;


import com.example.recyclerlib.R;
import com.supets.pet.banner.banner.RecyclerViewBannerBase;
import com.supets.pet.banner.banner.RecyclerViewBannerNormal;

import java.util.ArrayList;
import java.util.List;

public class BannerNormalActivity extends AppCompatActivity {
    RecyclerViewBannerNormal banner, banner2;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner2);
        toolbar = findViewById(R.id.toolbar);
        // 使用Toolbar替换ActionBar
        setSupportActionBar(toolbar);


        banner = findViewById(R.id.banner);
        banner2 = findViewById(R.id.banner2);
        List<String> list = new ArrayList<>();
        list.add("http://oo6pz0u05.bkt.clouddn.com/17-12-13/69427561.jpg");
        list.add("http://oo6pz0u05.bkt.clouddn.com/17-12-13/23738150.jpg");
        list.add("http://oo6pz0u05.bkt.clouddn.com/17-12-13/30127126.jpg");
        list.add("http://oo6pz0u05.bkt.clouddn.com/17-12-13/36125492.jpg");
        banner.initBannerImageView(list, new RecyclerViewBannerBase.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(BannerNormalActivity.this, "clicked:" + position, Toast.LENGTH_SHORT).show();
            }
        });
        banner2.initBannerImageView(list, new RecyclerViewBannerBase.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(BannerNormalActivity.this, "clicked:" + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
