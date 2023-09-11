package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.HarvestingModel;
import com.dawat.farmer.mamits.utils.BeautifyDate;

import java.util.ArrayList;
import java.util.List;

public class HarvestingAdapter extends RecyclerView.Adapter<HarvestingAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    List<HarvestingModel> list;

    public HarvestingAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.harvesting_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            HarvestingModel model = list.get(position);
            holder.txt_label.setText("Harvesting Details   |\nField " + model.getFarm_field());

            holder.txt_year.setText(model.getCultivation_year());
            holder.txt_season.setText(model.getSeason());
            holder.txt_farm_code.setText(model.getFarm_name());
            holder.txt_field_code.setText(model.getFarm_field());
            String from_date = new BeautifyDate().beautifyDate(model.getFrom_date(), "yyyy-MM-dd", "dd MMM, yyyy");
            holder.txt_from_date.setText(from_date);
            String to_date = new BeautifyDate().beautifyDate(model.getTo_date(), "yyyy-MM-dd", "dd MMM, yyyy");
            holder.txt_to_date.setText(to_date);
            holder.txt_method_type.setText(model.getMethod_type());
            holder.txt_done_by.setText(model.getDone_by());

            if (model.getDone_by().equalsIgnoreCase("Individual")) {
                holder.txt_no_of_labor.setText(model.getNo_of_labor());
                holder.txt_cost_per_labor.setText(model.getPer_labor_cost());
                holder.ll_individual_options.setVisibility(View.VISIBLE);
            } else if (model.getDone_by().equalsIgnoreCase("Contract")) {
                holder.txt_contract_cost.setText(model.getContract_cost());
                holder.ll_contract_options.setVisibility(View.VISIBLE);
            }
            holder.txt_est_area.setText(model.getEstimated_area());
            holder.txt_thresher_cost.setText(model.getThresher_cost());
            holder.txt_cleaning_cost.setText(model.getCleaning_cost());
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

    public void setList(List<HarvestingModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView txt_label, txt_year, txt_season, txt_farm_code, txt_field_code,
                txt_from_date, txt_to_date, txt_method_type, txt_done_by, txt_no_of_labor, txt_cost_per_labor, txt_contract_cost,
                txt_thresher_cost, txt_cleaning_cost, txt_est_area, txt_total_cost;
        private LinearLayout ll_individual_options, ll_contract_options;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_label = itemView.findViewById(R.id.txt_label);
            txt_year = itemView.findViewById(R.id.txt_year);
            txt_season = itemView.findViewById(R.id.txt_season);
            txt_farm_code = itemView.findViewById(R.id.txt_farm_code);
            txt_field_code = itemView.findViewById(R.id.txt_field_code);
            txt_from_date = itemView.findViewById(R.id.txt_from_date);
            txt_to_date = itemView.findViewById(R.id.txt_to_date);
            txt_method_type = itemView.findViewById(R.id.txt_method_type);
            txt_done_by = itemView.findViewById(R.id.txt_done_by);
            txt_no_of_labor = itemView.findViewById(R.id.txt_no_of_labor);
            txt_cost_per_labor = itemView.findViewById(R.id.txt_cost_per_labor);
            txt_contract_cost = itemView.findViewById(R.id.txt_contract_cost);
            txt_thresher_cost = itemView.findViewById(R.id.txt_thresher_cost);
            txt_cleaning_cost = itemView.findViewById(R.id.txt_cleaning_cost);
            txt_est_area = itemView.findViewById(R.id.txt_est_area);
            txt_total_cost = itemView.findViewById(R.id.txt_total_cost);
            ll_individual_options = itemView.findViewById(R.id.ll_individual_options);
            ll_contract_options = itemView.findViewById(R.id.ll_contract_options);
        }
    }

}
