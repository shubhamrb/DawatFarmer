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
import com.dawat.farmer.mamits.model.AppealModel;
import com.dawat.farmer.mamits.utils.BeautifyDate;

import java.util.List;

public class AppealSubListAdapter extends RecyclerView.Adapter<AppealSubListAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    private List<AppealModel.Appeal> list;

    public AppealSubListAdapter(Context mContext, List<AppealModel.Appeal> appeals) {
        this.mContext = mContext;
        list = appeals;
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.appeal_ticket_sub_list_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            if (position == list.size() - 1) {
                holder.view.setVisibility(View.GONE);
            }
            holder.btn_next.setVisibility(View.GONE);
            AppealModel.Appeal model = list.get(position);
            holder.txt_title.setText(model.getResion());
            String dat = new BeautifyDate().beautifyDate(model.getCreated_at(), "yyyy-MM-dd", "dd MMM, yyyy");
            holder.txt_date.setText("Requested on " + dat);

            if (model.getStatus().equals("0")) {
                holder.btn_forward.setVisibility(View.GONE);
                /*holder.btn_forward.setVisibility(View.VISIBLE);
                holder.btn_forward.setText("Forward to Coordinator");
                holder.btn_forward.setBackgroundTintList(mContext.getColorStateList(R.color.primary_color));
                holder.btn_forward.setTextColor(mContext.getColor(R.color.white));*/
            } else if (model.getStatus().equals("1")) {
                holder.btn_forward.setVisibility(View.GONE);
                /*holder.btn_forward.setVisibility(View.VISIBLE);
                holder.btn_forward.setText("Forwarded to Coordinator");
                holder.btn_forward.setBackgroundTintList(mContext.getColorStateList(R.color.white));
                holder.btn_forward.setTextColor(mContext.getColor(R.color.teal));*/
            } else if (model.getStatus().equals("2")) {
                holder.btn_forward.setVisibility(View.VISIBLE);
                holder.btn_forward.setText("Approved");
                holder.btn_forward.setBackgroundTintList(mContext.getColorStateList(R.color.white));
                holder.btn_forward.setTextColor(mContext.getColor(R.color.secondary_color));
            } else if (model.getStatus().equals("3")) {
                holder.btn_forward.setVisibility(View.VISIBLE);
                holder.btn_forward.setText("Rejected");
                holder.btn_forward.setBackgroundTintList(mContext.getColorStateList(R.color.white));
                holder.btn_forward.setTextColor(mContext.getColor(R.color.red_color));
            } else {
                holder.btn_forward.setVisibility(View.GONE);
            }
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


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private AppCompatTextView txt_title, txt_date, btn_forward;
        private ImageView btn_next;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_date = itemView.findViewById(R.id.txt_date);
            btn_next = itemView.findViewById(R.id.btn_next);
            btn_forward = itemView.findViewById(R.id.btn_forward);
        }
    }
}
