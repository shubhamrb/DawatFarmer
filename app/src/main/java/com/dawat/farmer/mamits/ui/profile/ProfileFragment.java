package com.dawat.farmer.mamits.ui.profile;

import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_CURRENT_DATE;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_PROFILE_IMAGE;
import static com.dawat.farmer.mamits.utils.AppConstant.SHARED_PREF_NAME;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.dawat.farmer.mamits.LoginActivity;
import com.dawat.farmer.mamits.MainActivity;
import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.databinding.FragmentProfileBinding;
import com.dawat.farmer.mamits.model.ProfileModel;
import com.dawat.farmer.mamits.notification.NotificationService;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.ProgressLoading;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private SharedPreferences sharedPreferences;
    private ProgressLoading progressLoading;
    private String strToken = "";
    private ProfileModel model;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressLoading = new ProgressLoading();
        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        String current_date = sharedPreferences.getString(PREF_KEY_CURRENT_DATE, "");
        binding.txtTime.setText(current_date);

        setClickListeners();
        getUserDetails();
        return root;
    }

    private void setClickListeners() {
        binding.btnAppeal.setOnClickListener(v -> {
            Navigation.findNavController(((MainActivity) getContext())
                    .findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_appeal);
        });

        binding.btnSupport.setOnClickListener(v -> {
            if (model != null && model.getCoordinatormobile() != null && !model.getCoordinatormobile().isEmpty()) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + model.getCoordinatormobile()));
                startActivity(callIntent);
            }
        });

        binding.btnLogout.setOnClickListener(v -> {
            final AlertDialog.Builder newBuilder = new AlertDialog.Builder(getContext());
            newBuilder.setMessage("Are you sure you want to Logout?");
            newBuilder.setPositiveButton("Yes", (dialog, which) -> {
                getActivity().stopService(new Intent(getActivity(), NotificationService.class));
                sharedPreferences.edit().putBoolean(AppConstant.IS_LOGIN, false).apply();
                sharedPreferences.edit().clear().apply();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finishAffinity();

            });
            newBuilder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
            newBuilder.show();
        });

    }

    private void getUserDetails() {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().getUserDetails(strToken, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());

                    model = new Gson().fromJson(jsonObject.get("data").getAsJsonObject().toString(), ProfileModel.class);
                    binding.txtUsername.setText(model.getName());
                    binding.txtMobile.setText(model.getMobile());
                    binding.txtEmail.setText(model.getEmail());
                    binding.txtAccountStatus.setText(model.getStatus().equals("1") ? "सक्रिय" : "निष्क्रय");
                    Glide.with(getContext()).load(model.getProfile_image()).error(R.drawable.person_profile).into(binding.profileImage);
                    sharedPreferences.edit().putString(PREF_PROFILE_IMAGE, model.getProfile_image()).apply();
                }

                @Override
                public void onFailed(Throwable throwable) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_ERROR, throwable.getMessage());
                    Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            progressLoading.hideLoading();
            Log.e(AppConstant.LOG_KEY_ERROR, e.getMessage());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}