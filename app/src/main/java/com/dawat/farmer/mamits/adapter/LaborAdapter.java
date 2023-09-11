package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.LaborModel;

import java.util.ArrayList;
import java.util.List;

public class LaborAdapter extends RecyclerView.Adapter<LaborAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    List<LaborModel> list;

    public LaborAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.labor_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            LaborModel model = list.get(position);
            holder.txt_label.setText("Labor Details   |\nField " + model.getFarm_field());

            holder.txt_year.setText(model.getCultivation_year());
            holder.txt_season.setText(model.getSeason());
            holder.txt_farm_code.setText(model.getFarm_name());
            holder.txt_field_code.setText(model.getFarm_field());
            holder.txt_operation.setText(model.getOperation());
            holder.txt_labor.setText(model.getLabour());
            holder.txt_gender.setText("" + model.getGender());
            holder.txt_days.setText(model.getDays());
            holder.txt_rate.setText(model.getRate());
            holder.txt_type.setText(model.getType());
            holder.txt_cost_per_acre.setText(model.getCost_per_acre());
            holder.txt_total_cost.setText(model.getTotal_cost());
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

    public void setList(List<LaborModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView txt_label, txt_year, txt_season, txt_farm_code, txt_field_code,
                txt_operation, txt_labor, txt_gender, txt_rate, txt_days, txt_type, txt_cost_per_acre, txt_total_cost;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_label = itemView.findViewById(R.id.txt_label);
            txt_year = itemView.findViewById(R.id.txt_year);
            txt_season = itemView.findViewById(R.id.txt_season);
            txt_farm_code = itemView.findViewById(R.id.txt_farm_code);
            txt_field_code = itemView.findViewById(R.id.txt_field_code);
            txt_operation = itemView.findViewById(R.id.txt_operation);
            txt_labor = itemView.findViewById(R.id.txt_labor);
            txt_gender = itemView.findViewById(R.id.txt_gender);
            txt_days = itemView.findViewById(R.id.txt_days);
            txt_rate = itemView.findViewById(R.id.txt_rate);
            txt_type = itemView.findViewById(R.id.txt_type);
            txt_cost_per_acre = itemView.findViewById(R.id.txt_cost_per_acre);
            txt_total_cost = itemView.findViewById(R.id.txt_total_cost);
        }
    }
}
