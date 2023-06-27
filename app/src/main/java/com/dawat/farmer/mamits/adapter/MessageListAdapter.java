package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.MessageModel;

import java.util.ArrayList;
import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    private String current_user_id;
    private List<MessageModel> list;

    public MessageListAdapter(Context mContext, String current_user_id) {
        this.mContext = mContext;
        this.current_user_id = current_user_id;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            root = LayoutInflater.from(mContext).inflate(R.layout.from_message_item, parent, false);
        } else {
            root = LayoutInflater.from(mContext).inflate(R.layout.to_message_item, parent, false);
        }
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        /*if (list.size() > 0) {
            MessageModel model = list.get(position);
            holder.txt_message.setText(model.getDescription());
            if (model.getFile() != null && !model.getFile().isEmpty()) {
                Glide.with(mContext).load(model.getFile()).into(holder.img);
                holder.img_card.setVisibility(View.VISIBLE);
            } else {
                holder.img_card.setVisibility(View.GONE);
            }

            //15 March, 2023  |  10 : 39 AM
            String date = new BeautifyDate().beautifyDate(model.getDate(), "yyyy-MM-dd HH:mm:ss", "dd MMM, yyyy | HH : mm aa");
            holder.txt_date.setText(date);
        }*/
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getFrom_user().equals(current_user_id) ? 0 : 1;
    }

    public void setList(List<MessageModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView txt_message, txt_date;
        private CardView img_card;
        private ImageView img;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_message = itemView.findViewById(R.id.txt_message);
            img_card = itemView.findViewById(R.id.img_card);
            img = itemView.findViewById(R.id.img);
            txt_date = itemView.findViewById(R.id.txt_date);
        }
    }
}
