package com.supets.pet.mock.base;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public abstract  class BaseRecycleAdapter<T> extends RecyclerView.Adapter<BaseRecycleViewHolder> {

    public List<T> data = new ArrayList<>();

    public void setData(List<T> data) {
        this.data = data;
    }

    public void addHomeData(List<T> data) {
        this.data.clear();
        if (data != null) {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void addMoreData(List<T> data) {
        if(data != null) {
            int itemCount = data.size();
            int postionStart = this.getItemCount();
            this.data.addAll(data);
            this.notifyItemRangeInserted(postionStart, itemCount);
        }
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
