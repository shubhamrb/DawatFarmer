package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.AttachmentModel;
import com.dawat.farmer.mamits.model.CommentsModel;
import com.dawat.farmer.mamits.model.CropModel;
import com.dawat.farmer.mamits.ui.messages.PlayerActivity;

import java.util.ArrayList;
import java.util.List;

public class CropsAdapter extends RecyclerView.Adapter<CropsAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    List<CropModel> list;

    public CropsAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.crop_layout_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            CropModel model = list.get(position);
            holder.txt_label.setText("Crop Details   |   Field " + model.getFarm_field());
            holder.txt_year.setText(model.getCultivation_year());
            holder.txt_season.setText(model.getSeason());
            holder.txt_farm_code.setText(model.getFarm_name());
            holder.txt_field_code.setText(model.getFarm_field());
            holder.txt_crop_name.setText(model.getName());
            holder.txt_total_area.setText(model.getCropped_area());
            showAttachments(holder, model.getComments());
            holder.rl_img1.setOnClickListener(v -> {
                try {
                    mContext.startActivity(new Intent(mContext, PlayerActivity.class)
                            .putExtra("file_type", model.getComments().getAttachment().get(0).getFile_type())
                            .putExtra("filepath", model.getComments().getAttachment().get(0).getAttachment()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            holder.rl_img2.setOnClickListener(v -> {
                try {
                    mContext.startActivity(new Intent(mContext, PlayerActivity.class)
                            .putExtra("file_type", model.getComments().getAttachment().get(1).getFile_type())
                            .putExtra("filepath", model.getComments().getAttachment().get(1).getAttachment()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            holder.rl_img3.setOnClickListener(v -> {
                try {
                    mContext.startActivity(new Intent(mContext, PlayerActivity.class)
                            .putExtra("file_type", model.getComments().getAttachment().get(2).getFile_type())
                            .putExtra("filepath", model.getComments().getAttachment().get(2).getAttachment()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            holder.rl_img4.setOnClickListener(v -> {
                try {
                    mContext.startActivity(new Intent(mContext, PlayerActivity.class)
                            .putExtra("file_type", model.getComments().getAttachment().get(3).getFile_type())
                            .putExtra("filepath", model.getComments().getAttachment().get(3).getAttachment()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
    private void showAttachments(DashboardListViewHolder holder, CommentsModel commentsModel) {
        if (commentsModel != null) {
            if (commentsModel.getComment().size() != 0) {
                holder.txt_comment.setText(commentsModel.getComment().get(0).getComment());
            }
            if (commentsModel.getAttachment() != null) {
                holder.imageArray = commentsModel.getAttachment();
            }
        }
        holder.txt_file1.setImageResource(R.drawable.image_24);
        holder.txt_file2.setImageResource(R.drawable.image_24);
        holder.txt_file3.setImageResource(R.drawable.image_24);
        holder.txt_file4.setImageResource(R.drawable.image_24);
        holder.rl_img1.setVisibility(View.GONE);
        holder.rl_img2.setVisibility(View.GONE);
        holder.rl_img3.setVisibility(View.GONE);
        holder.rl_img4.setVisibility(View.GONE);

        for (int i = 0; i < holder.imageArray.size(); i++) {
            if (i == 0) {
                if (holder.imageArray.get(i).getFile_type().equalsIgnoreCase("png")
                        || holder.imageArray.get(i).getFile_type().equalsIgnoreCase("jpg")
                        || holder.imageArray.get(i).getFile_type().equalsIgnoreCase("jpeg")) {
                    Glide.with(mContext).load(holder.imageArray.get(i).getAttachment()).into(holder.txt_file1);
                } else {
                    holder.txt_file1.setImageResource(R.drawable.video_24);
                }
                holder.rl_img1.setVisibility(View.VISIBLE);
            } else if (i == 1) {
                if (holder.imageArray.get(i).getFile_type().equalsIgnoreCase("png")
                        || holder.imageArray.get(i).getFile_type().equalsIgnoreCase("jpg")
                        || holder.imageArray.get(i).getFile_type().equalsIgnoreCase("jpeg")) {
                    Glide.with(mContext).load(holder.imageArray.get(i).getAttachment()).into(holder.txt_file2);
                } else {
                    holder.txt_file2.setImageResource(R.drawable.video_24);
                }
                holder.rl_img2.setVisibility(View.VISIBLE);
            } else if (i == 2) {
                if (holder.imageArray.get(i).getFile_type().equalsIgnoreCase("png")
                        || holder.imageArray.get(i).getFile_type().equalsIgnoreCase("jpg")
                        || holder.imageArray.get(i).getFile_type().equalsIgnoreCase("jpeg")) {
                    Glide.with(mContext).load(holder.imageArray.get(i).getAttachment()).into(holder.txt_file3);
                } else {
                    holder.txt_file3.setImageResource(R.drawable.video_24);
                }
                holder.rl_img3.setVisibility(View.VISIBLE);
            } else if (i == 3) {
                if (holder.imageArray.get(i).getFile_type().equalsIgnoreCase("png")
                        || holder.imageArray.get(i).getFile_type().equalsIgnoreCase("jpg")
                        || holder.imageArray.get(i).getFile_type().equalsIgnoreCase("jpeg")) {
                    Glide.with(mContext).load(holder.imageArray.get(i).getAttachment()).into(holder.txt_file4);
                } else {
                    holder.txt_file4.setImageResource(R.drawable.video_24);
                }
                holder.rl_img4.setVisibility(View.VISIBLE);
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

    public void setList(List<CropModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView txt_label, txt_year, txt_season, txt_farm_code, txt_field_code, txt_crop_name, txt_total_area;
        private AppCompatTextView txt_comment;
        private ImageView txt_file1, txt_file2, txt_file3, txt_file4;
        private LinearLayout rl_img1, rl_img2, rl_img3, rl_img4;
        public List<AttachmentModel> imageArray;
        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_label = itemView.findViewById(R.id.txt_label);
            txt_year = itemView.findViewById(R.id.txt_year);
            txt_season = itemView.findViewById(R.id.txt_season);
            txt_farm_code = itemView.findViewById(R.id.txt_farm_code);
            txt_field_code = itemView.findViewById(R.id.txt_field_code);
            txt_crop_name = itemView.findViewById(R.id.txt_crop_name);
            txt_total_area = itemView.findViewById(R.id.txt_total_area);
            imageArray = new ArrayList<>();
            txt_comment = itemView.findViewById(R.id.txt_comment);
            txt_file1 = itemView.findViewById(R.id.txt_file1);
            txt_file2 = itemView.findViewById(R.id.txt_file2);
            txt_file3 = itemView.findViewById(R.id.txt_file3);
            txt_file4 = itemView.findViewById(R.id.txt_file4);
            rl_img1 = itemView.findViewById(R.id.rl_img1);
            rl_img2 = itemView.findViewById(R.id.rl_img2);
            rl_img3 = itemView.findViewById(R.id.rl_img3);
            rl_img4 = itemView.findViewById(R.id.rl_img4);
        }
    }
}
