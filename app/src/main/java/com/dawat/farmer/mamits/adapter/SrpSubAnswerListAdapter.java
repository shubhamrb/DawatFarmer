package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.model.SrpAnswerModel;

import java.util.List;

public class SrpSubAnswerListAdapter extends RecyclerView.Adapter<SrpSubAnswerListAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    public List<SrpAnswerModel.SubAnswer> list;
    private String ques_pos;

    public SrpSubAnswerListAdapter(Context mContext, String ques_pos, List<SrpAnswerModel.SubAnswer> subquestion) {
        this.mContext = mContext;
        list = subquestion;
        this.ques_pos = ques_pos;
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.srp_sub_question_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            SrpAnswerModel.SubAnswer model = list.get(position);
            holder.txt_sr.setText(ques_pos + "." + (position + 1));
            holder.txt_question.setText(model.getSubquestion() != null ? model.getSubquestion() : "");

            if (model.getPoint_for_yes().equalsIgnoreCase("0.00") && model.getPoint_for_no().equalsIgnoreCase("0.00")) {
                holder.btn_yes.setBackgroundTintList(null);
                holder.btn_yes.setBackground(AppCompatResources.getDrawable(mContext, R.drawable.srp_button_border));
                holder.btn_yes.setTextColor(mContext.getColorStateList(R.color.black));

                holder.btn_no.setBackgroundTintList(null);
                holder.btn_no.setBackground(AppCompatResources.getDrawable(mContext, R.drawable.srp_button_border));
                holder.btn_no.setTextColor(mContext.getColorStateList(R.color.black));

                holder.btn_yes.setAlpha(0.4f);
                holder.btn_no.setAlpha(0.4f);
            } else {
                if (model.getPoint_for_yes().equalsIgnoreCase("0.00")) {
                    holder.btn_no.setBackgroundTintList(mContext.getColorStateList(R.color.primary_color));
                    holder.btn_no.setTextColor(mContext.getColorStateList(R.color.white));

                    holder.btn_yes.setBackgroundTintList(null);
                    holder.btn_yes.setBackground(AppCompatResources.getDrawable(mContext, R.drawable.srp_button_border));
                    holder.btn_yes.setTextColor(mContext.getColorStateList(R.color.black));

                    holder.btn_yes.setAlpha(0.4f);
                } else {
                    holder.btn_yes.setBackgroundTintList(mContext.getColorStateList(R.color.primary_color));
                    holder.btn_yes.setTextColor(mContext.getColorStateList(R.color.white));

                    holder.btn_no.setBackgroundTintList(null);
                    holder.btn_no.setBackground(AppCompatResources.getDrawable(mContext, R.drawable.srp_button_border));
                    holder.btn_no.setTextColor(mContext.getColorStateList(R.color.black));

                    holder.btn_no.setAlpha(0.4f);
                }
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

    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView txt_sr, txt_question, btn_yes, btn_no;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_sr = itemView.findViewById(R.id.txt_sr);
            txt_question = itemView.findViewById(R.id.txt_question);
            btn_yes = itemView.findViewById(R.id.btn_yes);
            btn_no = itemView.findViewById(R.id.btn_no);
        }
    }
}
