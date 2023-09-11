package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.FarmerProfileTabs;

import java.util.List;

public class FarmerProfileTabListAdapter extends RecyclerView.Adapter<FarmerProfileTabListAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    private TabClickListener listener;
    private List<FarmerProfileTabs> list;
    private int SELECTED_TAB = 0;

    public FarmerProfileTabListAdapter(Context mContext, TabClickListener listener, List<FarmerProfileTabs> list) {
        this.mContext = mContext;
        this.listener = listener;
        this.list = list;
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.farmer_profile_tab_layout, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        FarmerProfileTabs tab = list.get(position);
        holder.txt_tab.setText(tab.getName());
        holder.icon_img.setImageDrawable(ResourcesCompat.getDrawable(mContext.getResources(), tab.getIcon(), null));

        if (SELECTED_TAB == position) {
            holder.card_view.setCardBackgroundColor(mContext.getResources().getColor(R.color.teal, null));
            holder.txt_tab.setTextColor(mContext.getResources().getColor(R.color.white, null));
            holder.icon_img.setImageTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.white, null)));
        } else {
            holder.card_view.setCardBackgroundColor(mContext.getResources().getColor(R.color.white_80_color, null));
            holder.txt_tab.setTextColor(mContext.getResources().getColor(R.color.black, null));
            holder.icon_img.setImageTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.black, null)));
        }

        holder.itemView.setOnClickListener(v -> {
            onclick(position);
        });
    }

    public void onclick(int position) {
        SELECTED_TAB = position;
        listener.onClick(position);
        notifyDataSetChanged();
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
        private CardView card_view;
        private ImageView icon_img;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_tab = itemView.findViewById(R.id.txt_tab);
            card_view = itemView.findViewById(R.id.card_view);
            icon_img = itemView.findViewById(R.id.icon_img);
        }
    }

    public interface TabClickListener {
        void onClick(int position);
    }
}
