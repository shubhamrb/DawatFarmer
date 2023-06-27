package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.NotificationModel;
import com.dawat.farmer.mamits.utils.BeautifyDate;

import java.util.ArrayList;
import java.util.List;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    private List<NotificationModel> list;

    public NotificationListAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.notifications_list_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            if (position == list.size() - 1) {
                holder.view.setVisibility(View.GONE);
            }
            NotificationModel model = list.get(position);
            holder.txt_title.setText(model.getTitle());
            holder.txt_sub_title.setText("");

            String date = new BeautifyDate().beautifyAgoDate(model.getCreated_at());
            holder.txt_hrs.setText(date);
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

    public void setList(List<NotificationModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private AppCompatTextView txt_title, txt_sub_title, txt_hrs;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_sub_title = itemView.findViewById(R.id.txt_sub_title);
            txt_hrs = itemView.findViewById(R.id.txt_hrs);
        }
    }
}
