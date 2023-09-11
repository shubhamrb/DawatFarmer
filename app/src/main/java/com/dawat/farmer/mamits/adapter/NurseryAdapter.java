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
import com.dawat.farmer.mamits.model.NurseryModel;
import com.dawat.farmer.mamits.utils.BeautifyDate;

import java.util.ArrayList;
import java.util.List;

public class NurseryAdapter extends RecyclerView.Adapter<NurseryAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    List<NurseryModel> list;

    public NurseryAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.nursery_layout_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            NurseryModel model = list.get(position);
            holder.txt_label.setText("Nursery Details   |   Field " + model.getFarm_field());

            holder.txt_crop_name.setText(model.getCrop_name());
            holder.txt_year.setText(model.getCultivation_year());
            holder.txt_season.setText(model.getSeason());
            holder.txt_farm_code.setText(model.getFarm_name());
            holder.txt_field_code.setText(model.getFarm_field());
            holder.txt_seed_type.setText(model.getSeed_type());
            holder.txt_variety.setText(model.getVariety());
            holder.txt_seed_source.setText(model.getSource_of_seed());
            if (model.getSource_of_seed().equalsIgnoreCase("own")) {
                holder.ll_company_name.setVisibility(View.GONE);
            } else {
                holder.ll_company_name.setVisibility(View.VISIBLE);
            }
            holder.txt_company.setText(model.getCompany_name());
            holder.txt_seed_used.setText(model.getSeed_used());
            holder.txt_seed_cost.setText(model.getSeed_cost());
            String sowing_date = new BeautifyDate().beautifyDate(model.getDate_of_sowing(), "yyyy-MM-dd", "dd MMM, yyyy");
            holder.txt_date_sowing.setText(sowing_date);
            holder.txt_sown_area.setText(model.getSown_area());
            holder.txt_field_area.setText(model.getField_area());
            String lc_date = new BeautifyDate().beautifyDate(model.getLc_date(), "yyyy-MM-dd", "dd MMM, yyyy");
            holder.txt_lc_date.setText(lc_date);
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

    public void setList(List<NurseryModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView txt_label, txt_crop_name, txt_year, txt_season, txt_farm_code, txt_field_code,
                txt_seed_type, txt_variety, txt_seed_source, txt_company, txt_seed_used, txt_seed_cost, txt_date_sowing,
                txt_sown_area, txt_field_area, txt_lc_date, txt_total_cost;
        private LinearLayout ll_company_name;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_label = itemView.findViewById(R.id.txt_label);
            txt_year = itemView.findViewById(R.id.txt_year);
            txt_season = itemView.findViewById(R.id.txt_season);
            txt_farm_code = itemView.findViewById(R.id.txt_farm_code);
            txt_field_code = itemView.findViewById(R.id.txt_field_code);
            txt_crop_name = itemView.findViewById(R.id.txt_crop_name);
            txt_seed_type = itemView.findViewById(R.id.txt_seed_type);
            txt_variety = itemView.findViewById(R.id.txt_variety);
            txt_seed_source = itemView.findViewById(R.id.txt_seed_source);
            txt_company = itemView.findViewById(R.id.txt_company);
            txt_seed_used = itemView.findViewById(R.id.txt_seed_used);
            txt_seed_cost = itemView.findViewById(R.id.txt_seed_cost);
            txt_date_sowing = itemView.findViewById(R.id.txt_date_sowing);
            txt_sown_area = itemView.findViewById(R.id.txt_sown_area);
            txt_field_area = itemView.findViewById(R.id.txt_field_area);
            txt_lc_date = itemView.findViewById(R.id.txt_lc_date);
            txt_total_cost = itemView.findViewById(R.id.txt_total_cost);
            ll_company_name = itemView.findViewById(R.id.ll_company_name);
        }
    }
}
