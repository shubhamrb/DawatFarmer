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
import com.dawat.farmer.mamits.model.PlantProtectionModel;
import com.dawat.farmer.mamits.utils.BeautifyDate;

import java.util.ArrayList;
import java.util.List;

public class PlantProtectionAdapter extends RecyclerView.Adapter<PlantProtectionAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    List<PlantProtectionModel> list;

    public PlantProtectionAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.plant_protection_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            PlantProtectionModel model = list.get(position);
            holder.txt_label.setText("Plant Protection Details   |\nField " + model.getFarm_field());

            holder.txt_year.setText(model.getCultivation_year());
            holder.txt_season.setText(model.getSeason());
            holder.txt_farm_code.setText(model.getFarm_name());
            holder.txt_field_code.setText(model.getFarm_field());
            String date = new BeautifyDate().beautifyDate(model.getDate(), "yyyy-MM-dd", "dd MMM, yyyy");
            holder.txt_date.setText(date);
            holder.txt_disease.setText(model.getType());
            holder.txt_description.setText(model.getPesticide_name());
            holder.txt_treatment_type.setText(model.getTreatment_type());
            holder.txt_treatment.setText(model.getChemical_name());
            holder.txt_dose.setText(model.getDose());
            holder.txt_cost_per_unit.setText(model.getCost_unit());
            holder.txt_cost_per_acre.setText(model.getCost_per_acre());
            holder.txt_total_cost.setText(model.getTotal_cost());
            holder.txt_measure.setText("" + model.getMeasurement());

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

    public void setList(List<PlantProtectionModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView txt_label, txt_year, txt_season, txt_farm_code, txt_field_code,
                txt_date, txt_disease, txt_description, txt_treatment_type,
                txt_treatment, txt_dose, txt_cost_per_unit, txt_cost_per_acre, txt_total_cost, txt_measure;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_label = itemView.findViewById(R.id.txt_label);
            txt_year = itemView.findViewById(R.id.txt_year);
            txt_season = itemView.findViewById(R.id.txt_season);
            txt_farm_code = itemView.findViewById(R.id.txt_farm_code);
            txt_field_code = itemView.findViewById(R.id.txt_field_code);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_disease = itemView.findViewById(R.id.txt_disease);
            txt_description = itemView.findViewById(R.id.txt_description);
            txt_treatment_type = itemView.findViewById(R.id.txt_treatment_type);
            txt_treatment = itemView.findViewById(R.id.txt_treatment);
            txt_dose = itemView.findViewById(R.id.txt_dose);
            txt_cost_per_unit = itemView.findViewById(R.id.txt_cost_per_unit);
            txt_cost_per_acre = itemView.findViewById(R.id.txt_cost_per_acre);
            txt_total_cost = itemView.findViewById(R.id.txt_total_cost);
            txt_measure = itemView.findViewById(R.id.txt_measure);
        }
    }

}