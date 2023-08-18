package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.OrderDetailModel;

import java.util.ArrayList;
import java.util.List;

public class OrderCartAdapter extends RecyclerView.Adapter<OrderCartAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    private List<OrderDetailModel.ProductModel> list;

    public OrderCartAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.order_cart_list_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            if (position == list.size() - 1) {
                holder.view.setVisibility(View.GONE);
            }
            OrderDetailModel.ProductModel model = list.get(position);
            holder.txt_title.setText(Html.fromHtml(model.getName_hi()));
            holder.txt_per_price.setText(model.getQty() + " x ₹ " + model.getFinal_price());

            float price = Float.parseFloat(model.getFinal_price().substring(0, model.getFinal_price().indexOf(".")));
            int quantity = Integer.parseInt(model.getQty());
            holder.txt_price.setText("₹ " + (quantity * price));
            Glide.with(mContext).load(model.getImage()).into(holder.product_image);
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

    public void setList(List<OrderDetailModel.ProductModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private ImageView product_image;
        private AppCompatTextView txt_title, txt_per_price, txt_price;
        private View view;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.product_image);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_per_price = itemView.findViewById(R.id.txt_per_price);
            txt_price = itemView.findViewById(R.id.txt_price);
            view = itemView.findViewById(R.id.view);
        }
    }
}
