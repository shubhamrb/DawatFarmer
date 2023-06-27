package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardCategoryListAdapter extends RecyclerView.Adapter<DashboardCategoryListAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    private List<String> list;
    private OnClickListener listener;

    public DashboardCategoryListAdapter(Context mContext, OnClickListener listener) {
        this.mContext = mContext;
        this.listener = listener;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.dashboard_category_list_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
//        if (list.size() > 0) {
            holder.itemView.setOnClickListener(v -> {
                listener.onCategoryClick("");
            });
//        }
    }

    public interface OnClickListener {
        void onCategoryClick(String appeal_id);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
