package com.dawat.farmer.mamits.customDialogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.databinding.BottomsheetCreateTicketBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class CreateTicketBottomSheet {
    Context mContext;
    private BottomSheetDialog bottomSheetMediaActionDialog;
    private BottomsheetCreateTicketBinding binding;
    private OnClickListener listener;

    public CreateTicketBottomSheet(Context mContext, OnClickListener listener) {
        this.mContext = mContext;
        this.listener = listener;
    }

    public void openOption(String farmer_id) {

        bottomSheetMediaActionDialog = new BottomSheetDialog(mContext, R.style.NoBackgroundDialogTheme);
        binding = BottomsheetCreateTicketBinding.inflate(LayoutInflater.from(mContext));
        bottomSheetMediaActionDialog.setContentView(binding.getRoot());
        bottomSheetMediaActionDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);

        binding.btnCreate.setOnClickListener(v -> {
            String title = binding.txtTitle.getText().toString();
            String description = binding.txtDescription.getText().toString();

            if (title.isEmpty()) {
                Toast.makeText(mContext, "Please the title.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (description.isEmpty()) {
                Toast.makeText(mContext, "Please the description.", Toast.LENGTH_SHORT).show();
                return;
            }
            listener.onTicketCreate(farmer_id, title, description);
            bottomSheetMediaActionDialog.dismiss();
        });

        bottomSheetMediaActionDialog.setOnDismissListener(dialogInterface -> {
        });
        bottomSheetMediaActionDialog.show();

    }

    public interface OnClickListener {
        void onTicketCreate(String farmer_id, String title, String description);
    }
}
