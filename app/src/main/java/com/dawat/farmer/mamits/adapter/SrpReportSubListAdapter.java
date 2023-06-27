package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.SrpReportModel;
import com.dawat.farmer.mamits.utils.BeautifyDate;

import java.util.ArrayList;
import java.util.List;

public class SrpReportSubListAdapter extends RecyclerView.Adapter<SrpReportSubListAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    private List<SrpReportModel> list;

    public SrpReportSubListAdapter(Context mContext) {
        this.mContext = mContext;
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.srp_report_sub_list_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            if (position == list.size() - 1) {
                holder.view.setVisibility(View.GONE);
            }
            SrpReportModel model = list.get(position);
            holder.txt_name.setText(model.getTitle());
            holder.txt_point.setText(model.getTotalpoints() + " Points");
            String date = new BeautifyDate().beautifyDate(model.getCreated_at(), "yyyy-MM-dd", "dd MMM, yyyy");
            holder.txt_date.setText("Generated on " + date);
            holder.itemView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString("report_id", model.getId());
                Navigation.findNavController(v).navigate(R.id.navigation_srp_report_detail, bundle);
            });
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

    public void setList(List<SrpReportModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private AppCompatTextView txt_name, txt_point, txt_date;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_point = itemView.findViewById(R.id.txt_point);
            txt_date = itemView.findViewById(R.id.txt_date);
        }
    }
}
