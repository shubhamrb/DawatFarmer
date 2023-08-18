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
import com.dawat.farmer.mamits.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    private List<ProductModel> list;
    private OnItemClickListener listener;
    private int type = 0;

    public ProductsAdapter(Context mContext, int type, OnItemClickListener listener) {
        this.listener = listener;
        this.mContext = mContext;
        this.type = type;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (type == 0) {
            root = LayoutInflater.from(mContext).inflate(R.layout.product_list_item, parent, false);
        } else {
            root = LayoutInflater.from(mContext).inflate(R.layout.product_small_item, parent, false);
        }
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            ProductModel model = list.get(position);
            holder.txt_name.setText(Html.fromHtml(model.getName_hi()));
            holder.txt_price.setText("₹ " + model.getSelling_price());
            Glide.with(mContext).load(model.getAttachment() + model.getImage()).into(holder.image);

            holder.itemView.setOnClickListener(v -> {
                listener.onItemClick(model.getId());
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

    public void setList(List<ProductModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(String product_id);
    }

    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private AppCompatTextView txt_name, txt_price;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_price = itemView.findViewById(R.id.txt_price);
        }
    }
}
