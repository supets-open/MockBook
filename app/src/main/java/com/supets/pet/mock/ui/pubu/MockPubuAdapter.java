package com.supets.pet.mock.ui.pubu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.supets.pet.mock.base.BaseRecycleAdapter;
import com.supets.pet.mock.base.BaseRecycleViewHolder;
import com.supets.pet.mock.bean.MockData;
import com.supets.pet.mockui.R;

public class MockPubuAdapter extends BaseRecycleAdapter<MockData> {


    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_new_recommend_product_view, parent, false);
        return new BaseRecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecycleViewHolder holder, final int position) {
        ((TextView) holder.itemView.findViewById(R.id.commission_proportion)).setText(position + "==");
        (holder.itemView.findViewById(R.id.commission_proportion)).setVisibility(position % 3 == 0 ? View.VISIBLE : View.GONE);
        (holder.itemView.findViewById(R.id.product_county_flag)).setVisibility(position % 2 == 0 ? View.VISIBLE : View.GONE);
        ImageView hed = holder.itemView.findViewById(R.id.product_image_view);

        Glide.with(holder.itemView.getContext())
                .load(getContentResId(position))
                .into(hed);

        hed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.remove(position);
               notifyItemRemoved(position);
            }
        });

    }


    private int getContentResId(int position) {
        switch (position % 4) {
            case 0:
                return R.drawable.taeyeon_one;
            case 1:
                return R.drawable.taeyeon_two;
            case 2:
                return R.drawable.taeyeon_three;
            case 3:
                return R.drawable.taeyeon_four;
        }
        return 0;
    }
}