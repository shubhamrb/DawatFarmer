package com.dawat.farmer.mamits.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.R;

import java.util.List;

public class DropdownListAdapter extends RecyclerView.Adapter<DropdownListAdapter.DashboardListViewHolder> {

    private Context mContext;
    private View root;
    private List<String> list;
    private int SELECTED_ITEM = -1;
    private ItemClickListener listener;

    public DropdownListAdapter(Context mContext, List<String> farmersTypeList, ItemClickListener listener) {
        this.mContext = mContext;
        list = farmersTypeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DashboardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        root = LayoutInflater.from(mContext).inflate(R.layout.dropdown_item, parent, false);
        return new DashboardListViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardListViewHolder holder, int position) {
        if (list != null && list.size() > 0) {
            if (position == list.size() - 1) {
                holder.view.setVisibility(View.GONE);
            }
            holder.radio.setChecked(position == SELECTED_ITEM);
            holder.txt_item.setText(list.get(position));

            holder.itemView.setOnClickListener(v -> {
                SELECTED_ITEM = position;
                listener.onItemClick(list.get(position));
                notifyDataSetChanged();
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

    public interface ItemClickListener {
        void onItemClick(String item);
    }

    public static class DashboardListViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private AppCompatTextView txt_item;
        private RadioButton radio;

        public DashboardListViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view);
            txt_item = itemView.findViewById(R.id.txt_item);
            radio = itemView.findViewById(R.id.radio);
        }
    }
}
