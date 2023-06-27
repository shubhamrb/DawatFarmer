package com.dawat.farmer.mamits.customDialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.adapter.DropdownListAdapter;
import com.dawat.farmer.mamits.databinding.BottomsheetCreateAppealReasonBinding;
import com.dawat.farmer.mamits.utils.CustomLinearLayoutManager;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Arrays;
import java.util.List;


public class CreateAppealReasonBottomSheet {
    Context mContext;
    public BottomSheetDialog bottomSheetMediaActionDialog;
    public BottomsheetCreateAppealReasonBinding binding;
    private boolean reasonVisible;
    private DropdownListAdapter reasonAdapter;
    private OnClickListener listener;

    public CreateAppealReasonBottomSheet(Context mContext, OnClickListener listener) {
        this.mContext = mContext;
        this.listener = listener;
    }

    public void openOption(String farmer_id) {

        bottomSheetMediaActionDialog = new BottomSheetDialog(mContext, R.style.NoBackgroundDialogTheme);
        binding = BottomsheetCreateAppealReasonBinding.inflate(LayoutInflater.from(mContext));
        bottomSheetMediaActionDialog.setContentView(binding.getRoot());
        bottomSheetMediaActionDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);

        getReasons();

        binding.txtAppealReason.setOnClickListener(v -> {
            if (reasonVisible) {
                binding.recyclerAppealReason.setVisibility(View.GONE);
            } else {
                binding.recyclerAppealReason.setVisibility(View.VISIBLE);
            }
            reasonVisible = !reasonVisible;
        });

        binding.btnCreate.setOnClickListener(v -> {
            String reason = binding.txtAppealReason.getText().toString();
            if (reason.isEmpty()) {
                Toast.makeText(mContext, "Please select the appeal reason.", Toast.LENGTH_SHORT).show();
                return;
            }
            listener.onAppealCreate(farmer_id, reason);
            bottomSheetMediaActionDialog.dismiss();
        });

        bottomSheetMediaActionDialog.setOnDismissListener(dialogInterface -> {
        });
        bottomSheetMediaActionDialog.show();

    }

    private void getReasons() {
        List<String> reasons = Arrays.asList(mContext.getResources().getStringArray(R.array.reasons));

        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        binding.recyclerAppealReason.setLayoutManager(manager);
        binding.recyclerAppealReason.setItemAnimator(null);
        reasonAdapter = new DropdownListAdapter(mContext, reasons, item -> {
            binding.txtAppealReason.setText(item);
            binding.recyclerAppealReason.setVisibility(View.GONE);
            reasonVisible = !reasonVisible;
        });
        binding.recyclerAppealReason.setAdapter(reasonAdapter);
    }

    public interface OnClickListener {
        void onAppealCreate(String farmer_id, String reason);
    }
}
