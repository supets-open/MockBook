package com.supets.pet.mock.ui.home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.supets.pet.mock.base.BaseRecycleAdapter;
import com.supets.pet.mock.base.BaseRecycleViewHolder;
import com.supets.pet.mock.bean.LocalMockData;
import com.supets.pet.mock.dao.LocalMockDataDB;
import com.supets.pet.mock.ui.MockConfigJsonActivity;
import com.supets.pet.mockui.R;

public class MockTestAdapter extends BaseRecycleAdapter<LocalMockData> {

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseRecycleViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mock_list_test_item, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseRecycleViewHolder holder, final int position) {

        ((TextView) holder.itemView.findViewById(R.id.name)).setText(data.get(position).getUrl());

        final CheckBox checkBox = holder.itemView.findViewById(R.id.select);
        checkBox.setChecked(data.get(position).getSelected());
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.get(position).setSelected(checkBox.isChecked());
                LocalMockDataDB.updateMockData(data.get(position));
            }
        });

        holder.itemView.findViewById(R.id.configjson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MockConfigJsonActivity.class);
                intent.putExtra("url", data.get(position).getUrl());
                view.getContext().startActivity(intent);
            }
        });
    }
}