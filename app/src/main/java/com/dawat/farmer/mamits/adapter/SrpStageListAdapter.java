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
import com.dawat.farmer.mamits.model.SrpStageModel;
import com.dawat.farmer.mamits.utils.CustomLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class SrpStageListAdapter extends RecyclerView.Adapter<SrpStageListAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    private List<SrpStageModel> list;
    private OnClickExpandListener listener;

    public SrpStageListAdapter(Context mContext, OnClickExpandListener listener) {
        this.mContext = mContext;
        list = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.srp_report_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            SrpStageModel model = list.get(position);
            holder.txt_name.setText(model.getStage());
            holder.txt_point.setText(model.getTotals() + " Points");

            CustomLinearLayoutManager manager = new CustomLinearLayoutManager(holder.itemView.getContext(), RecyclerView.VERTICAL, false);
            holder.recycler_srp_sublist.setLayoutManager(manager);
            holder.recycler_srp_sublist.setItemAnimator(null);
            holder.srpReportSubListAdapter = new SrpReportSubListAdapter(mContext,model.getStage());
            holder.recycler_srp_sublist.setAdapter(holder.srpReportSubListAdapter);

            holder.itemView.setOnClickListener(v -> {
                if (holder.openSubList) {
                    holder.view.setVisibility(View.GONE);
                    holder.recycler_srp_sublist.setVisibility(View.GONE);
                    holder.btn_dropdown.setRotation(360);
                } else {
                    holder.btn_dropdown.setRotation(180);
                    holder.view.setVisibility(View.VISIBLE);
                    holder.recycler_srp_sublist.setVisibility(View.VISIBLE);
                    listener.onExpand(model.getId(), holder);
                }
                holder.openSubList = !holder.openSubList;

            });
        }
    }

    public interface OnClickExpandListener {
        void onExpand(String stage_id, DashboardListViewHolder holder);
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

    public void setList(List<SrpStageModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recycler_srp_sublist;
        private View view;
        public SrpReportSubListAdapter srpReportSubListAdapter;
        private boolean openSubList;
        private ImageView btn_dropdown;
        private AppCompatTextView txt_name, txt_point;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            recycler_srp_sublist = itemView.findViewById(R.id.recycler_srp_sublist);
            view = itemView.findViewById(R.id.view);
            btn_dropdown = itemView.findViewById(R.id.btn_dropdown);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_point = itemView.findViewById(R.id.txt_point);
        }
    }
}
