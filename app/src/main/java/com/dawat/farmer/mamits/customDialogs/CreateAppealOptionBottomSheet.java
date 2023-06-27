package com.dawat.farmer.mamits.customDialogs;

import android.content.Context;
import android.view.LayoutInflater;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.databinding.BottomsheetCreateAppealBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class CreateAppealOptionBottomSheet {
    Context mContext;
    public BottomSheetDialog bottomSheetMediaActionDialog;
    public BottomsheetCreateAppealBinding binding;
    private OnClickCreateButtonListener listener;

    public CreateAppealOptionBottomSheet(Context mContext, OnClickCreateButtonListener listener) {
        this.mContext = mContext;
        this.listener = listener;
    }

    public void openOption() {

        bottomSheetMediaActionDialog = new BottomSheetDialog(mContext, R.style.NoBackgroundDialogTheme);
        binding = BottomsheetCreateAppealBinding.inflate(LayoutInflater.from(mContext));
        bottomSheetMediaActionDialog.setContentView(binding.getRoot());
        bottomSheetMediaActionDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);

        binding.btnAppeal.setOnClickListener(v -> {
            bottomSheetMediaActionDialog.dismiss();
            listener.onCreateClick("Appeal");

        });

        binding.btnTicket.setOnClickListener(v -> {
            bottomSheetMediaActionDialog.dismiss();
            listener.onCreateClick("Ticket");
        });

        bottomSheetMediaActionDialog.setOnDismissListener(dialogInterface -> {
        });
        bottomSheetMediaActionDialog.show();

    }

    public interface OnClickCreateButtonListener {
        void onCreateClick(String type);
    }
}
