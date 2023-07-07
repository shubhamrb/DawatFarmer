package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.AnsQuesModel;

import java.util.ArrayList;
import java.util.List;

public class AnsQuesListAdapter extends RecyclerView.Adapter<AnsQuesListAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    private List<AnsQuesModel> list;

    public AnsQuesListAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.ans_ques_list_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {

            AnsQuesModel model = list.get(position);
            holder.txt_ques.setText(model.getTitle_hi());
            holder.txt_ans.setText(model.getDescription_hi());

            holder.itemView.setOnClickListener(v -> {
                if (holder.isVisible) {
                    holder.txt_ans.setVisibility(View.GONE);
                } else {
                    holder.txt_ans.setVisibility(View.VISIBLE);
                }
                holder.isVisible = !holder.isVisible;
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

    public void setList(List<AnsQuesModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private ImageView btn_dropdown;
        private AppCompatTextView txt_ques, txt_ans;
        private boolean isVisible;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            btn_dropdown = itemView.findViewById(R.id.btn_dropdown);
            txt_ques = itemView.findViewById(R.id.txt_ques);
            txt_ans = itemView.findViewById(R.id.txt_ans);
        }
    }
}
