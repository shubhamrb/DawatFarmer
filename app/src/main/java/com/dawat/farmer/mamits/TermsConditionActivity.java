package com.dawat.farmer.mamits;

import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
import static com.dawat.farmer.mamits.utils.AppConstant.SHARED_PREF_NAME;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dawat.farmer.mamits.databinding.ActivityTermsConditionBinding;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.ProgressLoading;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import se.warting.signatureview.utils.SignaturePadBindingAdapter;

public class TermsConditionActivity extends AppCompatActivity {

    private ActivityTermsConditionBinding binding;
    private SignaturePadBindingAdapter signaturePadBindingAdapter;
    private SharedPreferences sharedPreferences;
    private ProgressLoading progressLoading;
    private String strToken = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTermsConditionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressLoading = new ProgressLoading();
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");

        binding.btnContinue.setOnClickListener(v -> {
            Bitmap bitmap = binding.signPad.getTransparentSignatureBitmap(true);

            FileOutputStream outputStream;
            File file = new File(getCacheDir(), "sign.jpg");

            try {
                outputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            uploadSignature(file);
        });
        binding.btnClear.setOnClickListener(v -> {
            binding.signPad.clear();
        });
    }

    private void uploadSignature(File file) {
        progressLoading.showLoading(this);
        try {
            MultipartBody.Part sign = MultipartBody.Part.createFormData("signature_file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));

            new ApiHelper().uploadSignature(strToken, sign, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    progressLoading.hideLoading();
                    startActivity(new Intent(TermsConditionActivity.this, MainActivity.class));
                    finishAffinity();
                }

                @Override
                public void onFailed(Throwable throwable) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_ERROR, throwable.getMessage());
                    Toast.makeText(TermsConditionActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            progressLoading.hideLoading();
            Log.e(AppConstant.LOG_KEY_ERROR, e.getMessage());
        }
    }
}