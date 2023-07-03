package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.Tabs;

import java.util.List;

public class TabListAdapter extends RecyclerView.Adapter<TabListAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    private List<Tabs> list;
    private OnTabClickListener listener;

    public TabListAdapter(Context mContext, List<Tabs> list, OnTabClickListener listener) {
        this.mContext = mContext;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.tab_layout, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        Tabs tab = list.get(position);
        holder.txt_tab.setText(tab.getName());
        holder.icon_img.setImageDrawable(ResourcesCompat.getDrawable(mContext.getResources(), tab.getIcon(), null));
        holder.itemView.setOnClickListener(v -> {
            listener.onTabClick(tab.getName());
        });
    }

    public interface OnTabClickListener {
        void onTabClick(String tab);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView txt_tab;
        private ImageView icon_img;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_tab = itemView.findViewById(R.id.txt_tab);
            icon_img = itemView.findViewById(R.id.icon_img);
        }
    }
}
