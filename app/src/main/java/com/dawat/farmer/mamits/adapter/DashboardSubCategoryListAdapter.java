package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.SrpCategoryModel;

import java.util.ArrayList;
import java.util.List;

public class DashboardSubCategoryListAdapter extends RecyclerView.Adapter<DashboardSubCategoryListAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    private List<SrpCategoryModel> list;
    private OnClickListener listener;

    public DashboardSubCategoryListAdapter(Context mContext, OnClickListener listener) {
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
        if (list.size() > 0) {
            SrpCategoryModel model = list.get(position);
            holder.txt_title.setText(model.getCat_title_hi());
            Glide.with(mContext).load(model.getIcon()).into(holder.icon);

            holder.itemView.setOnClickListener(v -> {
                listener.onSubCategoryClick(model);
            });
        }
    }

    public interface OnClickListener {
        void onSubCategoryClick(SrpCategoryModel model);
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

    public void setList(List<SrpCategoryModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView txt_title, txt_des;
        private ImageView icon;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_des = itemView.findViewById(R.id.txt_des);
            icon = itemView.findViewById(R.id.icon);
        }
    }
}
