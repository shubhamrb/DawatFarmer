package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.IrrigationModel;
import com.dawat.farmer.mamits.utils.BeautifyDate;

import java.util.ArrayList;
import java.util.List;

public class IrrigationAdapter extends RecyclerView.Adapter<IrrigationAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    List<IrrigationModel> list;

    public IrrigationAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.irrigation_layout_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            IrrigationModel model = list.get(position);
            holder.txt_label.setText("Irrigation Details   |   Field " + model.getFarm_field());

            holder.txt_year.setText(model.getCultivation_year());
            holder.txt_season.setText(model.getSeason());
            holder.txt_farm_code.setText(model.getFarm_name());
            holder.txt_field_code.setText(model.getFarm_field());
            holder.txt_crop_stage.setText(model.getCrop_stage());
            String irrigation_date = new BeautifyDate().beautifyDate(model.getIrrigation_date(), "yyyy-MM-dd", "dd MMM, yyyy");
            holder.txt_irrigation_date.setText(irrigation_date);
            holder.txt_irrigation_source.setText(model.getIrrigation_sources());
            holder.txt_application_place.setText(model.getApplication_place());
            holder.txt_application_method.setText(model.getApplication_method());
            holder.txt_tensio_meter.setText(model.getTensio_meter());
            holder.txt_action_taken.setText(model.getAction_taken());

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

    public void setList(List<IrrigationModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView txt_label, txt_year, txt_season, txt_farm_code, txt_field_code,
                txt_crop_stage, txt_irrigation_date, txt_irrigation_source, txt_application_place,
                txt_application_method, txt_tensio_meter, txt_action_taken;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_label = itemView.findViewById(R.id.txt_label);
            txt_year = itemView.findViewById(R.id.txt_year);
            txt_season = itemView.findViewById(R.id.txt_season);
            txt_farm_code = itemView.findViewById(R.id.txt_farm_code);
            txt_field_code = itemView.findViewById(R.id.txt_field_code);
            txt_crop_stage = itemView.findViewById(R.id.txt_crop_stage);
            txt_irrigation_date = itemView.findViewById(R.id.txt_irrigation_date);
            txt_irrigation_source = itemView.findViewById(R.id.txt_irrigation_source);
            txt_application_place = itemView.findViewById(R.id.txt_application_place);
            txt_application_method = itemView.findViewById(R.id.txt_application_method);
            txt_tensio_meter = itemView.findViewById(R.id.txt_tensio_meter);
            txt_action_taken = itemView.findViewById(R.id.txt_action_taken);
        }
    }

}
