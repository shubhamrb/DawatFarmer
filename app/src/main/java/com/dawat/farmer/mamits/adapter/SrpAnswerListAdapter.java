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
import com.dawat.farmer.mamits.utils.CustomLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class SrpAnswerListAdapter extends RecyclerView.Adapter<SrpAnswerListAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    public List<SrpAnswerModel> list;

    public SrpAnswerListAdapter(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.generate_report_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            SrpAnswerModel model = list.get(position);
            String sr = ((position + 1) > 9) ? "" + (position + 1) : ("0" + (position + 1));
            holder.txt_sr.setText(sr);
            holder.txt_question.setText(model.getQuestion());

            CustomLinearLayoutManager manager = new CustomLinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
            holder.recycler_sub_question.setLayoutManager(manager);
            holder.recycler_sub_question.setItemAnimator(null);
            holder.srpSubAnswerListAdapter = new SrpSubAnswerListAdapter(mContext, sr, model.getSubquestion());
            holder.recycler_sub_question.setAdapter(holder.srpSubAnswerListAdapter);
            holder.recycler_sub_question.setVisibility(View.VISIBLE);

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

    public void setList(List<SrpAnswerModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recycler_sub_question;
        public SrpSubAnswerListAdapter srpSubAnswerListAdapter;
        private AppCompatTextView txt_sr, txt_question, btn_yes, btn_no;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            recycler_sub_question = itemView.findViewById(R.id.recycler_sub_question);
            txt_sr = itemView.findViewById(R.id.txt_sr);
            txt_question = itemView.findViewById(R.id.txt_question);
            btn_yes = itemView.findViewById(R.id.btn_yes);
            btn_no = itemView.findViewById(R.id.btn_no);
        }
    }
}
