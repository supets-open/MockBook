package com.supets.pet.mock.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.supets.pet.mock.base.BaseRecycleAdapter;
import com.supets.pet.mock.base.BaseRecycleViewHolder;
import com.supets.pet.mock.bean.MockData;
import com.supets.pet.mock.ui.MockInfoActivity;
import com.supets.pet.mock.utils.FormatLogProcess;
import com.supets.pet.mockui.R;

import java.text.SimpleDateFormat;

public class MockDataAdapter extends BaseRecycleAdapter<MockData> {


    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mock_list_datalist_item, parent, false);
        return new BaseRecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecycleViewHolder holder, final int position) {

        ((TextView) holder.itemView.findViewById(R.id.name)).setText(data.get(position).getUrl());


        if (FormatLogProcess.isJson(data.get(position).getData())) {
            (holder.itemView.findViewById(R.id.status)).setBackgroundColor(holder.itemView.getResources().getColor(R.color.appcolor));
        } else {
            (holder.itemView.findViewById(R.id.status)).setBackgroundColor(Color.parseColor("#ff0000"));
        }

        String time = dateToStrLong(data.get(position).getTime());
        ((TextView) holder.itemView.findViewById(R.id.time)).setText(time);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MockInfoActivity.class);
                intent.putExtra("id", data.get(position).getId().toString());
                view.getContext().startActivity(intent);
            }
        });

    }

    public static String dateToStrLong(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }
}