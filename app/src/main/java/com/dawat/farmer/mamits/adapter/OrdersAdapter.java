package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.MainActivity;
import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    private List<OrderModel> list;

    public OrdersAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.order_list_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            if (position == list.size() - 1) {
                holder.view.setVisibility(View.GONE);
            }
            OrderModel model = list.get(position);
            holder.txt_order_id.setText("ORDID" + model.getOrderId());
            holder.txt_name.setText("₹ " + model.getFinal_price() + " - " + model.getPayment_channel());
            switch (model.getStatus()) {
                case "1":
                    holder.txt_time.setText("Order Placed");
                    break;
                case "2":
                    holder.txt_time.setText("Order Accepted");
                    break;
                case "3":
                    holder.txt_time.setText("Order Cancelled");
                    break;
                case "4":
                    holder.txt_time.setText("Order Rejected");
                    break;
                case "5":
                    holder.txt_time.setText("Order on hold");
                    break;
                case "6":
                    holder.txt_time.setText("Order Delivered");
                    break;
            }
            holder.itemView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString("order_id", model.getId());
                Navigation.findNavController(((MainActivity) mContext).findViewById(R.id.nav_host_fragment))
                        .navigate(R.id.navigation_orders_detail, bundle);
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

    public void setList(List<OrderModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView txt_order_id, txt_name, txt_time;
        private View view;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_order_id = itemView.findViewById(R.id.txt_order_id);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_time = itemView.findViewById(R.id.txt_time);
            view = itemView.findViewById(R.id.view);
        }
    }
}
