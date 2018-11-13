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
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536232185126&di=ef1e4f07528cc04a7128f76b2f093aec&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019812554bd3b6000001bf72b5388a.jpg%401280w_1l_2o_100sh.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536232185126&di=ef1e4f07528cc04a7128f76b2f093aec&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019812554bd3b6000001bf72b5388a.jpg%401280w_1l_2o_100sh.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536232185126&di=ef1e4f07528cc04a7128f76b2f093aec&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019812554bd3b6000001bf72b5388a.jpg%401280w_1l_2o_100sh.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536232185126&di=ef1e4f07528cc04a7128f76b2f093aec&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019812554bd3b6000001bf72b5388a.jpg%401280w_1l_2o_100sh.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536232185126&di=ef1e4f07528cc04a7128f76b2f093aec&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019812554bd3b6000001bf72b5388a.jpg%401280w_1l_2o_100sh.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536232185126&di=ef1e4f07528cc04a7128f76b2f093aec&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019812554bd3b6000001bf72b5388a.jpg%401280w_1l_2o_100sh.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536232185126&di=ef1e4f07528cc04a7128f76b2f093aec&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019812554bd3b6000001bf72b5388a.jpg%401280w_1l_2o_100sh.jpg");
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
