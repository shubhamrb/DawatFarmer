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
import com.dawat.farmer.mamits.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    private List<NewsModel> list;
    private OnClickListener listener;

    public NewsListAdapter(Context mContext, OnClickListener listener) {
        this.mContext = mContext;
        this.listener = listener;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.category_detail_list_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            NewsModel model = list.get(position);
            Glide.with(mContext).load(model.getAttachment()).into(holder.image);
            holder.txt_time.setText("15 April, 2023");
            holder.txt_title.setText(model.getTitle_hi());
        }
    }

    public interface OnClickListener {
        void onNewsClick(String news_id);
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

    public void setList(List<NewsModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private AppCompatTextView txt_time, txt_title;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            txt_time = itemView.findViewById(R.id.txt_time);
            txt_title = itemView.findViewById(R.id.txt_title);
        }
    }
}
