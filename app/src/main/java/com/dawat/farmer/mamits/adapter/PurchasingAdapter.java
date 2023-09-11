package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.PurchasingModel;
import com.dawat.farmer.mamits.utils.BeautifyDate;

import java.util.ArrayList;
import java.util.List;

public class PurchasingAdapter extends RecyclerView.Adapter<PurchasingAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    List<PurchasingModel> list;

    public PurchasingAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.purchasing_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            PurchasingModel model = list.get(position);
            holder.txt_label.setText("Purchasing Details   |\nField " + model.getFarm_field());

            holder.txt_year.setText(model.getCultivation_year());
            holder.txt_season.setText(model.getSeason());
            holder.txt_farm_code.setText(model.getFarm_name());
            holder.txt_field_code.setText(model.getFarm_field());
            String date = new BeautifyDate().beautifyDate(model.getDate(), "yyyy-MM-dd", "dd MMM, yyyy");
            holder.txt_date.setText(date);
            holder.txt_bill_no.setText(model.getBill_no());
            holder.txt_qty.setText(model.getQty());
            holder.txt_rate.setText(model.getRate());
            holder.txt_area.setText(model.getArea());
            holder.txt_total_other.setText(model.getTotal_other());
            holder.txt_total_income.setText(model.getTotal_income());

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

    public void setList(List<PurchasingModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView txt_label, txt_year, txt_season, txt_farm_code, txt_field_code,
                txt_date, txt_bill_no, txt_qty, txt_rate, txt_area, txt_total_other, txt_total_income;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_label = itemView.findViewById(R.id.txt_label);
            txt_year = itemView.findViewById(R.id.txt_year);
            txt_season = itemView.findViewById(R.id.txt_season);
            txt_farm_code = itemView.findViewById(R.id.txt_farm_code);
            txt_field_code = itemView.findViewById(R.id.txt_field_code);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_bill_no = itemView.findViewById(R.id.txt_bill_no);
            txt_qty = itemView.findViewById(R.id.txt_qty);
            txt_rate = itemView.findViewById(R.id.txt_rate);
            txt_area = itemView.findViewById(R.id.txt_area);
            txt_total_other = itemView.findViewById(R.id.txt_total_other);
            txt_total_income = itemView.findViewById(R.id.txt_total_income);
        }
    }

}
