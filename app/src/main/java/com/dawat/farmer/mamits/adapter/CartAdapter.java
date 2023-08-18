package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.CartModel;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    private List<CartModel> list;
    private OnClickQuantityUpdateListener listener;

    public CartAdapter(Context mContext, OnClickQuantityUpdateListener listener) {
        this.mContext = mContext;
        this.listener = listener;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.cart_list_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            if (position == list.size() - 1) {
                holder.view.setVisibility(View.GONE);
            }
            CartModel model = list.get(position);
            holder.txt_title.setText(Html.fromHtml(model.getName_hi()));
            holder.txt_des.setText(Html.fromHtml(model.getCat_title_hi()));
            holder.txt_quantity.setText(model.getQuantity());
            holder.txt_price.setText("₹ " + model.getSelling_price());
            Glide.with(mContext).load(model.getImage()).into(holder.product_image);

            if (Integer.parseInt(model.getQuantity()) > 0) {
                holder.min_card.setCardBackgroundColor(ColorStateList.valueOf(mContext.getResources().getColor(R.color.teal, null)));
            } else {
                holder.min_card.setCardBackgroundColor(ColorStateList.valueOf(mContext.getResources().getColor(R.color.teal_dim, null)));
            }

            holder.min_card.setOnClickListener(v -> {
                int quantity = Integer.parseInt(model.getQuantity());
                if (quantity > 1) {
                    quantity--;
                    listener.onUpdate(position, model.getCategory_id(), model.getProduct_id(), quantity);
                } else {
                    listener.onDelete(model.getId());

                }
            });
            holder.plus_card.setOnClickListener(v -> {
                int quantity = Integer.parseInt(model.getQuantity());
                quantity++;
                listener.onUpdate(position, model.getCategory_id(), model.getProduct_id(), quantity);
            });
        }
    }

    public interface OnClickQuantityUpdateListener {
        void onUpdate(int position, String category_id, String product_id, int quantity);

        void onDelete(String id);
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

    public void setList(List<CartModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private ImageView product_image;
        private AppCompatTextView txt_title, txt_des, txt_price, txt_quantity;
        private CardView min_card, plus_card;
        private View view;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.product_image);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_des = itemView.findViewById(R.id.txt_des);
            txt_price = itemView.findViewById(R.id.txt_price);
            txt_quantity = itemView.findViewById(R.id.txt_quantity);
            min_card = itemView.findViewById(R.id.min_card);
            plus_card = itemView.findViewById(R.id.plus_card);
            view = itemView.findViewById(R.id.view);
        }
    }
}
