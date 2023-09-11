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
import com.dawat.farmer.mamits.model.FertilizationModel;
import com.dawat.farmer.mamits.utils.BeautifyDate;

import java.util.ArrayList;
import java.util.List;

public class FertilizationAdapter extends RecyclerView.Adapter<FertilizationAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    List<FertilizationModel> list;

    public FertilizationAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.fertilization_layout_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            FertilizationModel model = list.get(position);
            holder.txt_label.setText("Fertilization Details   |   Field " + model.getFarm_field());

            holder.txt_year.setText(model.getCultivation_year());
            holder.txt_season.setText(model.getSeason());
            holder.txt_farm_code.setText(model.getFarm_name());
            holder.txt_field_code.setText(model.getFarm_field());
            String fertilization_date = new BeautifyDate().beautifyDate(model.getFertilization_date(), "yyyy-MM-dd", "dd MMM, yyyy");
            holder.txt_fertilization_date.setText(fertilization_date);
            holder.txt_fertilization_type.setText(model.getFertilization_type());
            holder.txt_fertilization.setText(model.getFertilization());
            holder.txt_quantity.setText(model.getQuantity());
            holder.txt_measure.setText(model.getMeasure());
            holder.txt_cost.setText(model.getCost());
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

    public void setList(List<FertilizationModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView txt_label, txt_year, txt_season, txt_farm_code, txt_field_code,
                txt_fertilization_date, txt_fertilization_type, txt_fertilization, txt_quantity,
                txt_measure, txt_cost, txt_cost_per_acre, txt_total_cost;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_label = itemView.findViewById(R.id.txt_label);
            txt_year = itemView.findViewById(R.id.txt_year);
            txt_season = itemView.findViewById(R.id.txt_season);
            txt_farm_code = itemView.findViewById(R.id.txt_farm_code);
            txt_field_code = itemView.findViewById(R.id.txt_field_code);
            txt_fertilization_date = itemView.findViewById(R.id.txt_fertilization_date);
            txt_fertilization_type = itemView.findViewById(R.id.txt_fertilization_type);
            txt_fertilization = itemView.findViewById(R.id.txt_fertilization);
            txt_quantity = itemView.findViewById(R.id.txt_quantity);
            txt_measure = itemView.findViewById(R.id.txt_measure);
            txt_cost = itemView.findViewById(R.id.txt_cost);
            txt_cost_per_acre = itemView.findViewById(R.id.txt_cost_per_acre);
            txt_total_cost = itemView.findViewById(R.id.txt_total_cost);
        }
    }
}
