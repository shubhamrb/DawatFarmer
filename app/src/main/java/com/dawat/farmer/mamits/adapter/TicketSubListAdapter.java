package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.MainActivity;
import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.TicketModel;
import com.dawat.farmer.mamits.utils.BeautifyDate;

import java.util.List;

public class TicketSubListAdapter extends RecyclerView.Adapter<TicketSubListAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    private List<TicketModel.Tickets> list;

    public TicketSubListAdapter(Context mContext, List<TicketModel.Tickets> tickets) {
        this.mContext = mContext;
        list = tickets;
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
            TicketModel.Tickets model = list.get(position);
            holder.txt_title.setText(model.getTitle());
            String dat = new BeautifyDate().beautifyDate(model.getCreated_at(), "yyyy-MM-dd", "dd MMM, yyyy");
            holder.txt_date.setText("TICKETID" + model.getId() + " | Requested on " + dat);
            holder.img.setImageResource(R.drawable.headphone_icon);
            holder.itemView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString("ticket_id", model.getId());
                bundle.putString("farmer_id", model.getFarmer_id());
                Navigation.findNavController(((MainActivity) mContext).findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_messenger, bundle);
            });
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
        private AppCompatTextView txt_title, txt_date;
        private ImageView img;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_date = itemView.findViewById(R.id.txt_date);
            img = itemView.findViewById(R.id.img);
        }
    }
}
