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
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536232185126&di=ef1e4f07528cc04a7128f76b2f093aec&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019812554bd3b6000001bf72b5388a.jpg%401280w_1l_2o_100sh.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536232185126&di=ef1e4f07528cc04a7128f76b2f093aec&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019812554bd3b6000001bf72b5388a.jpg%401280w_1l_2o_100sh.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536232185126&di=ef1e4f07528cc04a7128f76b2f093aec&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019812554bd3b6000001bf72b5388a.jpg%401280w_1l_2o_100sh.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536232185126&di=ef1e4f07528cc04a7128f76b2f093aec&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019812554bd3b6000001bf72b5388a.jpg%401280w_1l_2o_100sh.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536232185126&di=ef1e4f07528cc04a7128f76b2f093aec&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019812554bd3b6000001bf72b5388a.jpg%401280w_1l_2o_100sh.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536232185126&di=ef1e4f07528cc04a7128f76b2f093aec&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019812554bd3b6000001bf72b5388a.jpg%401280w_1l_2o_100sh.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536232185126&di=ef1e4f07528cc04a7128f76b2f093aec&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019812554bd3b6000001bf72b5388a.jpg%401280w_1l_2o_100sh.jpg");

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
