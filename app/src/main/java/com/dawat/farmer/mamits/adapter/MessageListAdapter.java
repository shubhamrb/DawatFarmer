package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dawat.farmer.mamits.ui.messages.PlayerActivity;
import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.MessageModel;
import com.dawat.farmer.mamits.utils.BeautifyDate;

import java.util.ArrayList;
import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    private String current_user_id;
    private List<MessageModel> list;
    private OnDownloadListener listener;

    public MessageListAdapter(Context mContext, String current_user_id, OnDownloadListener listener) {
        this.mContext = mContext;
        this.current_user_id = current_user_id;
        this.listener = listener;
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
        if (list.size() > 0) {
            MessageModel model = list.get(position);
            holder.txt_message.setText(model.getDescription());
            if (model.getFile() != null && !model.getFile().isEmpty()) {
                if (model.getFile().toLowerCase().contains(".jpg")
                        || model.getFile().toLowerCase().contains(".jpeg")
                        || model.getFile().toLowerCase().contains(".png")) {
                    Glide.with(mContext).load(model.getFile()).into(holder.img);
                    holder.img.setVisibility(View.VISIBLE);
                    holder.img_media_type.setVisibility(View.GONE);
                } else if (model.getFile().toLowerCase().contains(".mp4")
                        || model.getFile().toLowerCase().contains(".avi")
                        || model.getFile().toLowerCase().contains(".mkv")
                        || model.getFile().toLowerCase().contains(".mov")
                        || model.getFile().toLowerCase().contains(".wmv")
                        || model.getFile().toLowerCase().contains(".flv")
                        || model.getFile().toLowerCase().contains(".mpg")
                        || model.getFile().toLowerCase().contains(".mpeg")
                        || model.getFile().toLowerCase().contains(".3gp")
                        || model.getFile().toLowerCase().contains(".webm")
                ) {
                    holder.img.setVisibility(View.GONE);
                    holder.img_media_type.setImageResource(R.drawable.play_icon);
                    holder.img_media_type.setVisibility(View.VISIBLE);
                } else if (model.getFile().toLowerCase().contains(".mp3")) {
                    holder.img.setVisibility(View.GONE);
                    holder.img_media_type.setImageResource(R.drawable.mic_icon);
                    holder.img_media_type.setVisibility(View.VISIBLE);
                } else if (model.getFile().toLowerCase().contains(".pdf")
                        || model.getFile().toLowerCase().contains(".doc")
                        || model.getFile().toLowerCase().contains(".odt")
                        || model.getFile().toLowerCase().contains(".rtf")
                        || model.getFile().toLowerCase().contains(".txt")
                        || model.getFile().toLowerCase().contains(".xls")
                        || model.getFile().toLowerCase().contains(".ods")
                        || model.getFile().toLowerCase().contains(".ppt")
                ) {
                    holder.img.setVisibility(View.GONE);
                    holder.img_media_type.setImageResource(R.drawable.download_icon);
                    holder.img_media_type.setVisibility(View.VISIBLE);
                }
                holder.img_card.setVisibility(View.VISIBLE);
            } else {
                holder.img_card.setVisibility(View.GONE);
            }
            String date = new BeautifyDate().beautifyDate(model.getDate(), "yyyy-MM-dd HH:mm:ss", "dd MMM, yyyy | HH : mm aa");
            holder.txt_date.setText(date);

            holder.img_media_type.setOnClickListener(v -> {
                if (model.getFile().toLowerCase().contains(".pdf")
                        || model.getFile().toLowerCase().contains(".doc")
                        || model.getFile().toLowerCase().contains(".odt")
                        || model.getFile().toLowerCase().contains(".rtf")
                        || model.getFile().toLowerCase().contains(".txt")
                        || model.getFile().toLowerCase().contains(".xls")
                        || model.getFile().toLowerCase().contains(".ods")
                        || model.getFile().toLowerCase().contains(".ppt")
                ) {
                    listener.onDownload(model.getFile(), model.getFile().substring(model.getFile().lastIndexOf("/") + 1));
                } else {
                    mContext.startActivity(new Intent(mContext, PlayerActivity.class).putExtra("filepath", model.getFile()));
                }
            });
        }
    }

    public interface OnDownloadListener {
        void onDownload(String url, String file_name);
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
        return list.get(position).getFrom_user().equals(current_user_id) ? 0 : 1;
    }

    public void setList(List<MessageModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView txt_message, txt_date;
        private CardView img_card;
        private ImageView img, img_media_type;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_message = itemView.findViewById(R.id.txt_message);
            img_card = itemView.findViewById(R.id.img_card);
            img = itemView.findViewById(R.id.img);
            txt_date = itemView.findViewById(R.id.txt_date);
            img_media_type = itemView.findViewById(R.id.img_media_type);
        }
    }
}
