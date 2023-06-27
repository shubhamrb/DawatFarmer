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
import com.dawat.farmer.mamits.model.AppealModel;
import com.dawat.farmer.mamits.utils.CustomLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class AppealListAdapter extends RecyclerView.Adapter<AppealListAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    private int tab;
    private List<AppealModel> list;

    public AppealListAdapter(Context mContext, int tab) {
        this.mContext = mContext;
        this.tab = tab;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.appeal_ticket_list_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list.size() > 0) {
            AppealModel model = list.get(position);
            holder.txt_username.setText(model.getFarmer_name());
            holder.txt_appeal_count.setText(model.getAppeals().size() + " अपीलें कतार में हैं");
            holder.itemView.setOnClickListener(v -> setUpAppealSubList(holder, model.getAppeals()));
        }
    }

    private void setUpAppealSubList(DashboardListViewHolder holder, List<AppealModel.Appeal> appeals) {
        if (holder.openSubList) {
            holder.view.setVisibility(View.GONE);
            holder.recycler_sub_list.setVisibility(View.GONE);
            holder.btn_dropdown.setRotation(360);
        } else {
            holder.btn_dropdown.setRotation(180);
            holder.view.setVisibility(View.VISIBLE);
            holder.recycler_sub_list.setVisibility(View.VISIBLE);
            CustomLinearLayoutManager manager = new CustomLinearLayoutManager(holder.itemView.getContext(), RecyclerView.VERTICAL, false);
            holder.recycler_sub_list.setLayoutManager(manager);
            holder.recycler_sub_list.setItemAnimator(null);
            holder.appealSubListAdapter = new AppealSubListAdapter(mContext, appeals);
            holder.recycler_sub_list.setAdapter(holder.appealSubListAdapter);
        }
        holder.openSubList = !holder.openSubList;
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

    public void setList(List<AppealModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recycler_sub_list;
        private View view;
        private AppealSubListAdapter appealSubListAdapter;
        private boolean openSubList;
        private ImageView btn_dropdown;
        private AppCompatTextView txt_username, txt_appeal_count;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            recycler_sub_list = itemView.findViewById(R.id.recycler_appeal_ticket_sublist);
            view = itemView.findViewById(R.id.view);
            btn_dropdown = itemView.findViewById(R.id.btn_dropdown);
            txt_username = itemView.findViewById(R.id.txt_username);
            txt_appeal_count = itemView.findViewById(R.id.txt_appeal_count);
        }
    }
}
