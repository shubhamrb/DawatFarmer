package com.dawat.farmer.mamits.customDialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.adapter.DropdownListAdapter;
import com.dawat.farmer.mamits.databinding.BottomsheetAppealFilterBinding;
import com.dawat.farmer.mamits.utils.CustomLinearLayoutManager;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Arrays;
import java.util.List;


public class AppealFilterBottomSheet {
    Context mContext;
    public BottomSheetDialog bottomSheetMediaActionDialog;
    public BottomsheetAppealFilterBinding binding;
    private DropdownListAdapter resolvedStatusAdapter, typeAdapter;
    private boolean resolveStatusVisible, typeVisible;
    private OnClickListener listener;

    public AppealFilterBottomSheet(Context mContext, OnClickListener listener) {
        this.mContext = mContext;
        this.listener = listener;
    }

    public void openOption() {

        bottomSheetMediaActionDialog = new BottomSheetDialog(mContext, R.style.NoBackgroundDialogTheme);
        binding = BottomsheetAppealFilterBinding.inflate(LayoutInflater.from(mContext));
        bottomSheetMediaActionDialog.setContentView(binding.getRoot());
        bottomSheetMediaActionDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);

        getStatus();
        getType();

        binding.txtStatus.setOnClickListener(v -> {
            binding.recyclerType.setVisibility(View.GONE);
            typeVisible = false;

            if (resolveStatusVisible) {
                binding.recyclerResolveStatus.setVisibility(View.GONE);
            } else {
                binding.recyclerResolveStatus.setVisibility(View.VISIBLE);
            }
            resolveStatusVisible = !resolveStatusVisible;
        });

        binding.txtType.setOnClickListener(v -> {
            binding.recyclerResolveStatus.setVisibility(View.GONE);
            resolveStatusVisible = false;

            if (typeVisible) {
                binding.recyclerType.setVisibility(View.GONE);
            } else {
                binding.recyclerType.setVisibility(View.VISIBLE);
            }
            typeVisible = !typeVisible;
        });

        binding.btnClear.setOnClickListener(v -> {
            binding.txtStatus.setText("");
            binding.txtType.setText("");
        });

        binding.btnApply.setOnClickListener(v -> {
            String txt_status = binding.txtStatus.getText().toString();
            if (txt_status.equals("नई")) {
                txt_status = "0";
            } else {
                txt_status = "1";
            }
            String type = binding.txtType.getText().toString();
            if (type.equals("एसआरपी सक्रियता")) {
                type = "SRP Activation";
            } else {
                type = "Account Activation";
            }
            listener.onFilterClick(txt_status, type);
            bottomSheetMediaActionDialog.dismiss();
        });

        bottomSheetMediaActionDialog.setOnDismissListener(dialogInterface -> {
        });
        bottomSheetMediaActionDialog.show();

    }

    public interface OnClickListener {
        void onFilterClick(String status, String type);
    }

    private void getStatus() {
        /*<item>Unresolved</item>
        <item>Resolved</item>*/
        List<String> status = Arrays.asList(mContext.getResources().getStringArray(R.array.status));

        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        binding.recyclerResolveStatus.setLayoutManager(manager);
        binding.recyclerResolveStatus.setItemAnimator(null);
        resolvedStatusAdapter = new DropdownListAdapter(mContext, status, item -> {
            binding.txtStatus.setText(item);
            binding.recyclerResolveStatus.setVisibility(View.GONE);
            resolveStatusVisible = !resolveStatusVisible;
        });
        binding.recyclerResolveStatus.setAdapter(resolvedStatusAdapter);
    }

    private void getType() {
        /*<item>SRP Activation</item>
        <item>Account Activation</item>*/
        List<String> appeal_type = Arrays.asList(mContext.getResources().getStringArray(R.array.appeal_type));

        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        binding.recyclerType.setLayoutManager(manager);
        binding.recyclerType.setItemAnimator(null);
        typeAdapter = new DropdownListAdapter(mContext, appeal_type, item -> {
            binding.txtType.setText(item);
            binding.recyclerType.setVisibility(View.GONE);
            typeVisible = !typeVisible;
        });
        binding.recyclerType.setAdapter(typeAdapter);
    }
}
