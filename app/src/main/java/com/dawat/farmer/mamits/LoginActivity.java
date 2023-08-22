package com.dawat.farmer.mamits;

import static com.dawat.farmer.mamits.utils.AppConstant.IS_LOGIN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_SIGNATURE_ADDED;
import static com.dawat.farmer.mamits.utils.AppConstant.SHARED_PREF_NAME;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dawat.farmer.mamits.databinding.ActivityLoginBinding;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.ProgressLoading;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.JsonObject;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    ProgressLoading progressLoading;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressLoading = new ProgressLoading();
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        if (sharedPreferences.getBoolean(IS_LOGIN, false)) {
            if (sharedPreferences.getBoolean(PREF_SIGNATURE_ADDED, false)) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            } else {
                startActivity(new Intent(LoginActivity.this, TermsConditionActivity.class));
            }
            finishAffinity();
        }
        binding.btnSendOtp.setOnClickListener(v -> sendOtp());
    }

    private void sendOtp() {
        String number = binding.etMobile.getText().toString();
        if (number.isEmpty()) {
            Toast.makeText(this, "Please enter mobile number", Toast.LENGTH_LONG).show();
            return;
        }
        if (number.length() != 10) {
            Toast.makeText(this, "Please enter 10 digits mobile number", Toast.LENGTH_LONG).show();
            return;
        }
        progressLoading.showLoading(this);

        try {
            new ApiHelper().doLogin(number, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    boolean status = jsonObject.get("status").getAsBoolean();
                    String message = jsonObject.get("message").getAsString();
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();

                    if (status) {
                        startActivity(new Intent(LoginActivity.this, OtpActivity.class).putExtra("mobile", number));
                    }

                }

                @Override
                public void onFailed(Throwable throwable) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_ERROR, throwable.getMessage());
                    Toast.makeText(LoginActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            progressLoading.hideLoading();
            Log.e(AppConstant.LOG_KEY_ERROR, e.getMessage());
        }
    }
}