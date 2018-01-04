package com.supets.pet.mock.ui.home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.supets.pet.mock.ui.MockConfigActivity;
import com.supets.pet.mock.ui.MockCrashListActivity;
import com.supets.pet.mock.ui.MockDataListActivity;
import com.supets.pet.mock.ui.MockEmailListActivity;
import com.supets.pet.mock.ui.MockModelActivity;
import com.supets.pet.mock.ui.MockTestActivity;
import com.supets.pet.mock.ui.MockToolActivity;
import com.supets.pet.mock.ui.MockUrlRuleActivity;
import com.supets.pet.mockui.R;

import java.util.ArrayList;
import java.util.List;

public class MockAdapter extends BaseAdapter {


    public List<String> data = new ArrayList<>();

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.mock_list_home_item, viewGroup, false);
        }

        ((TextView) view.findViewById(R.id.name)).setText(data.get(position));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == 0) {
                    Intent intent = new Intent(view.getContext(), MockDataListActivity.class);
                    view.getContext().startActivity(intent);
                }

                if (position == 1) {
                    Intent intent = new Intent(view.getContext(), MockTestActivity.class);
                    view.getContext().startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent(view.getContext(), MockModelActivity.class);
                    view.getContext().startActivity(intent);
                }
                if (position == 3) {
                    Intent intent = new Intent(view.getContext(), MockConfigActivity.class);
                    view.getContext().startActivity(intent);
                }

                if (position == 4) {
                    Intent intent = new Intent(view.getContext(), MockEmailListActivity.class);
                    view.getContext().startActivity(intent);
                }

                if (position == 5) {
                    Intent intent = new Intent(view.getContext(), MockUrlRuleActivity.class);
                    view.getContext().startActivity(intent);
                }

                if (position == 6) {
                    Intent intent = new Intent(view.getContext(), MockToolActivity.class);
                    view.getContext().startActivity(intent);
                }
                if (position == 7) {
                    Intent intent = new Intent(view.getContext(), MockCrashListActivity.class);
                    view.getContext().startActivity(intent);
                }
            }
        });
        return view;
    }
}
