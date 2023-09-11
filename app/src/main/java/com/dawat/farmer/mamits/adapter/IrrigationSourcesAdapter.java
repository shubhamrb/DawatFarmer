package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.IrrigationSourcesModel;

import java.util.ArrayList;
import java.util.List;

public class IrrigationSourcesAdapter extends RecyclerView.Adapter<IrrigationSourcesAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    List<IrrigationSourcesModel> list;

    public IrrigationSourcesAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.detail_list_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            IrrigationSourcesModel model = list.get(position);
            holder.txt_label.setText("Source " + (position + 1));

            if (model.getOther_name() != null && !model.getOther_name().isEmpty()) {
                holder.txt_source.setText(model.getOther_name());
            } else {
                holder.txt_source.setText(model.getIrrigation_sources());
            }
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

    public void setList(List<IrrigationSourcesModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView txt_label, txt_source;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_label = itemView.findViewById(R.id.txt_label);
            txt_source = itemView.findViewById(R.id.txt_source);
        }
    }
}
