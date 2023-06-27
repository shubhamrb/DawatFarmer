package com.dawat.farmer.mamits.ui.notifications;

import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_CURRENT_DATE;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_PROFILE_IMAGE;
import static com.dawat.farmer.mamits.utils.AppConstant.SHARED_PREF_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.adapter.NotificationListAdapter;
import com.dawat.farmer.mamits.databinding.FragmentNotificationsBinding;
import com.dawat.farmer.mamits.model.NotificationModel;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.CustomLinearLayoutManager;
import com.dawat.farmer.mamits.utils.ProgressLoading;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private NotificationListAdapter notificationListAdapter;
    private ProgressLoading progressLoading;
    private SharedPreferences sharedPreferences;
    private String strToken = "";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressLoading = new ProgressLoading();

        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        String current_date = sharedPreferences.getString(PREF_KEY_CURRENT_DATE, "");
        binding.txtTime.setText(current_date);
        String profile_image = sharedPreferences.getString(PREF_PROFILE_IMAGE, "");
        Glide.with(getContext()).load(profile_image).error(R.drawable.person_profile).into(binding.profileImage);

        setUpClickListener();
        setUpAppealTicketList();
        return root;
    }

    private void setUpClickListener() {

    }

    private void setUpAppealTicketList() {
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerNotifications.setLayoutManager(manager);
        binding.recyclerNotifications.setItemAnimator(null);
        notificationListAdapter = new NotificationListAdapter(getContext());
        binding.recyclerNotifications.setAdapter(notificationListAdapter);
    }

    private void getNotificationList() {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().getNotificationList(strToken, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Type farmer = new TypeToken<List<NotificationModel>>() {
                    }.getType();

                    List<NotificationModel> list = new Gson().fromJson(jsonObject.get("data").getAsJsonArray().toString(), farmer);
                    notificationListAdapter.setList(list);
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
    public void onResume() {
        super.onResume();
        getNotificationList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}