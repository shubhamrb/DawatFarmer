package com.dawat.farmer.mamits.ui.shop;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dawat.farmer.mamits.databinding.FragmentShopBinding;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.ProgressLoading;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ShopFragment extends Fragment {

    private FragmentShopBinding binding;
    private ProgressLoading progressLoading;
    private SharedPreferences sharedPreferences;
    private String strToken = "";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentShopBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressLoading = new ProgressLoading();

        /*sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        String current_date = sharedPreferences.getString(PREF_KEY_CURRENT_DATE, "");
        binding.txtTime.setText(current_date);
        String profile_image = sharedPreferences.getString(PREF_PROFILE_IMAGE, "");
        Glide.with(getContext()).load(profile_image).error(R.drawable.person_profile).into(binding.profileImage);*/

        setUpClickListener();
        return root;
    }

    private void setUpClickListener() {

    }
    @Override
    public void onResume() {
        super.onResume();
//        getNotificationList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}