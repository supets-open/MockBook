package com.supets.pet.module.home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.supets.pet.base.BaseRecycleAdapter;
import com.supets.pet.base.BaseRecycleViewHolder;
import com.supets.pet.mockui.R;
import com.supets.pet.module.crop.MainCrop2Activity;
import com.supets.pet.module.crop.MaincROPActivity;
import com.supets.pet.module.live.TestLiveCycleActivity;
import com.supets.pet.module.paste.MainPasteActivity;
import com.supets.pet.module.zxing.TestGeneratectivity;
import com.supets.pet.module.zxing.TestScanActivity;

public class MockToolsAdapter extends BaseRecycleAdapter<String> {


    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseRecycleViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mock_list_home_tool_item, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseRecycleViewHolder holder, final int position) {
        ((TextView) holder.itemView.findViewById(R.id.name)).setText(data.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (position == 2) {
                    Intent intent = new Intent(view.getContext(), TestScanActivity.class);
                    view.getContext().startActivity(intent);
                }
                if (position == 3) {
                    Intent intent = new Intent(view.getContext(), TestGeneratectivity.class);
                    view.getContext().startActivity(intent);
                }

                if (position == 4) {
                    Intent intent = new Intent(view.getContext(), MaincROPActivity.class);
                    view.getContext().startActivity(intent);
                }

                if (position == 5) {
                    Intent intent = new Intent(view.getContext(), MainCrop2Activity.class);
                    view.getContext().startActivity(intent);
                }
                if (position == 7) {
                    Intent intent = new Intent(view.getContext(), MainPasteActivity.class);
                    view.getContext().startActivity(intent);
                }

                if (position == 0) {
                    Intent intent = new Intent(view.getContext(), TestLiveCycleActivity.class);
                    view.getContext().startActivity(intent);
                }

            }
        });
    }


}