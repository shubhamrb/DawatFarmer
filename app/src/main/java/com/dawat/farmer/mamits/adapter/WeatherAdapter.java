package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.WeatherModel;
import com.dawat.farmer.mamits.utils.BeautifyDate;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    private List<WeatherModel.ForecastModel.ForecastDayModel> list;

    public WeatherAdapter(Context mContext) {
        this.mContext = mContext;
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.weather_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        WeatherModel.ForecastModel.ForecastDayModel forecastModel = list.get(position);
        int current_temp = (int) forecastModel.getDay().getAvgtemp_c();
        holder.txt_current_temp.setText(current_temp + "' C");
        Glide.with(mContext).load("https:" + forecastModel.getDay().getCondition().getIcon()).into(holder.icon);
        String date = new BeautifyDate().beautifyDate(forecastModel.getDate(), "yyyy-MM-dd", "dd MMMM, yyyy");
        holder.txt_date.setText(date);
        holder.txt_wind.setText(((int) forecastModel.getDay().getMaxwind_kph()) + " kph");
        holder.txt_humidity.setText(((int)forecastModel.getDay().getAvghumidity()) + " %");
        int min_temp = (int) forecastModel.getDay().getMintemp_c();
        int max_temp = (int) forecastModel.getDay().getMaxtemp_c();
        holder.txt_min_temp.setText(min_temp + "' C");
        holder.txt_max_temp.setText(max_temp + "' C");
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

    public void setList(List<WeatherModel.ForecastModel.ForecastDayModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView txt_current_temp, txt_date, txt_max_temp, txt_min_temp, txt_wind, txt_humidity;
        private ImageView icon;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_current_temp = itemView.findViewById(R.id.txt_current_temp);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_max_temp = itemView.findViewById(R.id.txt_max_temp);
            txt_min_temp = itemView.findViewById(R.id.txt_min_temp);
            txt_wind = itemView.findViewById(R.id.txt_wind);
            txt_humidity = itemView.findViewById(R.id.txt_humidity);
            icon = itemView.findViewById(R.id.icon);
        }
    }
}
