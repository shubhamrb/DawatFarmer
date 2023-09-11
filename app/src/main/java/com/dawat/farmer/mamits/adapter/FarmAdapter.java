package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.FarmModel;

import java.util.ArrayList;
import java.util.List;

public class FarmAdapter extends RecyclerView.Adapter<FarmAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    private FarmClickListener listener;
    List<FarmModel> list;

    public FarmAdapter(Context mContext, FarmClickListener listener) {
        this.mContext = mContext;
        this.listener = listener;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.farm_list_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            FarmModel model = list.get(position);
            holder.txt_farm_code.setText(model.getCode());
            if (model.getStatus().equals("1")) {
                holder.txt_field_count.setText(model.getFormfield() + " Fields");
                holder.txt_farm_size.setText(model.getTotal() + " Acres");
                holder.farm_card.setCardBackgroundColor(mContext.getResources().getColor(R.color.yellow_dim));
            } else {
                holder.txt_field_count.setText("No Fields Added");
                holder.txt_farm_size.setText("N/A");
                holder.farm_card.setCardBackgroundColor(mContext.getResources().getColor(R.color.cream_color));
            }
            holder.itemView.setOnClickListener(v -> listener.onClick(model));
        }
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

    public void setList(List<FarmModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView txt_farm_code, txt_field_count, txt_farm_size;
        private CardView farm_card;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            farm_card = itemView.findViewById(R.id.farm_card);
            txt_farm_code = itemView.findViewById(R.id.txt_farm_code);
            txt_field_count = itemView.findViewById(R.id.txt_field_count);
            txt_farm_size = itemView.findViewById(R.id.txt_farm_size);
        }
    }

    public interface FarmClickListener {
        void onClick(FarmModel model);
    }
}
