package com.dawat.farmer.mamits;

import static com.dawat.farmer.mamits.utils.AppConstant.IS_LOGIN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_EMAIL;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_MOBILE;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_NAME;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_PROFILE_IMAGE;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_USER_ID;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_USER_NAME;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_USER_TYPE;
import static com.dawat.farmer.mamits.utils.AppConstant.SHARED_PREF_NAME;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dawat.farmer.mamits.databinding.ActivityOtpBinding;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.CommonUtils;
import com.dawat.farmer.mamits.utils.ProgressLoading;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.JsonObject;

public class OtpActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private ActivityOtpBinding binding;
    private ProgressLoading progressLoading;
    private String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        progressLoading = new ProgressLoading();
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnSendOtp.setOnClickListener(v -> otpVerify());
        mobile = getIntent().getStringExtra("mobile");

    }

    private void otpVerify() {
        String otp = binding.etOtp.getText().toString();
        if (otp.isEmpty()) {
            Toast.makeText(this, "Please enter your OTP", Toast.LENGTH_LONG).show();
            return;
        }
        if (otp.length() != 6) {
            Toast.makeText(this, "OTP is incorrect.", Toast.LENGTH_LONG).show();
            return;
        }

        if (mobile == null) {
            Toast.makeText(this, "Something went wrong, Try again.", Toast.LENGTH_LONG).show();
            return;
        }
        progressLoading.showLoading(this);

        try {
            FirebaseMessaging.getInstance().getToken().addOnSuccessListener(token -> {
                if (token != null && token.length() != 0) {
                    String device_id = CommonUtils.getDeviceId(OtpActivity.this);
                    Log.e("FCM token", token);
                    Log.e("device_id", device_id);
                    new ApiHelper().verifyOtp(mobile, otp, token, device_id, new ResponseListener() {
                        @Override
                        public void onSuccess(JsonObject jsonObject) {
                            progressLoading.hideLoading();
                            Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());

                            boolean status = jsonObject.get("status").getAsBoolean();
                            String message = jsonObject.get("message").getAsString();
                            if (status) {
                                sharedPreferences.edit().putBoolean(IS_LOGIN, true).apply();
                                sharedPreferences.edit().putString(PREF_KEY_ACCESS_TOKEN, "Bearer " + jsonObject.get("access_token").getAsString()).apply();

                                JsonObject dataObject = jsonObject.get("data").getAsJsonObject();

                                sharedPreferences.edit().putString(PREF_NAME, dataObject.get("name").getAsString()).apply();
                                sharedPreferences.edit().putString(PREF_USER_NAME, dataObject.get("username").getAsString()).apply();
                                sharedPreferences.edit().putString(PREF_USER_TYPE, dataObject.get("user_type").getAsString()).apply();
                                sharedPreferences.edit().putString(PREF_MOBILE, dataObject.get("mobile").getAsString()).apply();
                                sharedPreferences.edit().putString(PREF_EMAIL, dataObject.get("email").getAsString()).apply();
                                sharedPreferences.edit().putString(PREF_USER_ID, dataObject.get("id").getAsString()).apply();
                                sharedPreferences.edit().putString(PREF_PROFILE_IMAGE, dataObject.get("profile_image").getAsString()).apply();
                                startActivity(new Intent(OtpActivity.this, TermsConditionActivity.class));
                                finishAffinity();
                            } else {
                                Toast.makeText(OtpActivity.this, message, Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailed(Throwable throwable) {
                            progressLoading.hideLoading();
                            Log.e(AppConstant.LOG_KEY_ERROR, throwable.getMessage());
                            Toast.makeText(OtpActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
        } catch (Exception e) {
            progressLoading.hideLoading();
            Log.e(AppConstant.LOG_KEY_ERROR, e.getMessage());
        }
    }
}