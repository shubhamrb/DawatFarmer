package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.TransplantationModel;
import com.dawat.farmer.mamits.utils.BeautifyDate;

import java.util.ArrayList;
import java.util.List;

public class TransplantationAdapter extends RecyclerView.Adapter<TransplantationAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    List<TransplantationModel> list;

    public TransplantationAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.transplantation_layout_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            TransplantationModel model = list.get(position);
            holder.txt_label.setText("Transplantation Details   |\nField " + model.getFarm_field());

            holder.txt_year.setText(model.getCultivation_year());
            holder.txt_season.setText(model.getSeason());
            holder.txt_farm_code.setText(model.getFarm_name());
            holder.txt_field_code.setText(model.getFarm_field());
            holder.txt_transplanted_acres.setText(model.getTransplanted_acres());
            String from_date = new BeautifyDate().beautifyDate(model.getFrom_date(), "yyyy-MM-dd", "dd MMM, yyyy");
            holder.txt_from_date.setText(from_date);
            String to_date = new BeautifyDate().beautifyDate(model.getTo_date(), "yyyy-MM-dd", "dd MMM, yyyy");
            holder.txt_to_date.setText(to_date);
            holder.txt_cultivation_method.setText(model.getCultivation_method());
            String lc_date = new BeautifyDate().beautifyDate(model.getMain_field_lc_date(), "yyyy-MM-dd", "dd MMM, yyyy");
            holder.txt_lc_date.setText(lc_date);
            holder.txt_rate.setText("" + model.getTransplantation_rate());

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

    public void setList(List<TransplantationModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView txt_label, txt_year, txt_season, txt_farm_code, txt_field_code,
                txt_transplanted_acres, txt_from_date, txt_to_date, txt_cultivation_method, txt_lc_date, txt_rate;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_label = itemView.findViewById(R.id.txt_label);
            txt_year = itemView.findViewById(R.id.txt_year);
            txt_season = itemView.findViewById(R.id.txt_season);
            txt_farm_code = itemView.findViewById(R.id.txt_farm_code);
            txt_field_code = itemView.findViewById(R.id.txt_field_code);
            txt_transplanted_acres = itemView.findViewById(R.id.txt_transplanted_acres);
            txt_from_date = itemView.findViewById(R.id.txt_from_date);
            txt_to_date = itemView.findViewById(R.id.txt_to_date);
            txt_cultivation_method = itemView.findViewById(R.id.txt_cultivation_method);
            txt_lc_date = itemView.findViewById(R.id.txt_lc_date);
            txt_rate = itemView.findViewById(R.id.txt_rate);
        }
    }

}
