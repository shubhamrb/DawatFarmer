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
import com.dawat.farmer.mamits.model.BlogModel;
import com.dawat.farmer.mamits.utils.BeautifyDate;

import java.util.ArrayList;
import java.util.List;

public class BlogsListAdapter extends RecyclerView.Adapter<BlogsListAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    private List<BlogModel> list;
    private OnClickListener listener;
    private boolean fullView;

    public BlogsListAdapter(Context mContext, OnClickListener listener, boolean fullView) {
        this.mContext = mContext;
        this.listener = listener;
        this.fullView = fullView;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(fullView ? R.layout.full_blog_list_item : R.layout.blog_list_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            BlogModel model = list.get(position);
            Glide.with(mContext).load(model.getAttachment()).into(holder.image);
            holder.txt_title.setText(Html.fromHtml(model.getTitle_hi()));
            holder.txt_des.setText("" + Html.fromHtml(model.getDescription_hi()));
//            String date =new BeautifyDate().beautifyAgoDate(model.getEvent_start_date());
            holder.txt_time.setText("");

            holder.itemView.setOnClickListener(v -> {
                listener.onBlogClick(model.getId(), model.getTitle_hi());
            });
        }
    }

    public interface OnClickListener {
        void onBlogClick(String blog_id, String title);
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

    public void setList(List<BlogModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private AppCompatTextView txt_title, txt_des, txt_time;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_des = itemView.findViewById(R.id.txt_des);
            txt_time = itemView.findViewById(R.id.txt_time);
        }
    }
}
