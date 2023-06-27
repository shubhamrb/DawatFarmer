package com.dawat.farmer.mamits.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.dawat.farmer.mamits.R;


public class ProgressLoading {

    public ProgressDialog progressDialog;

    private ProgressDialog showLoadingDialog(Context context) {
        try {
            progressDialog = new ProgressDialog(context);
            if (!((Activity) context).isFinishing()) {
                try {
                    progressDialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (progressDialog.getWindow() != null) {
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            progressDialog.setContentView(R.layout.progress_dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            return progressDialog;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void showLoading(Context context) {
        hideLoading();
        progressDialog = showLoadingDialog(context);
    }

    public void hideLoading() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
