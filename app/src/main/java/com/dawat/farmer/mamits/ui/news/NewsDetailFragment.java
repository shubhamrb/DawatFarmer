package com.dawat.farmer.mamits.ui.news;


import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_CURRENT_DATE;
import static com.dawat.farmer.mamits.utils.AppConstant.SHARED_PREF_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.dawat.farmer.mamits.databinding.FragmentNewsDetailBinding;
import com.dawat.farmer.mamits.model.NewsModel;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.BeautifyDate;
import com.dawat.farmer.mamits.utils.ProgressLoading;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class NewsDetailFragment extends Fragment {

    private FragmentNewsDetailBinding binding;
    private SharedPreferences sharedPreferences;
    private String strToken = "";
    private ProgressLoading progressLoading;
    private String news_id, title;
    private NewsModel model;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewsDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");

        String current_date = sharedPreferences.getString(PREF_KEY_CURRENT_DATE, "");
        binding.txtTime.setText(current_date);

        progressLoading = new ProgressLoading();
        Bundle bundle = getArguments();
        if (bundle != null) {
            news_id = bundle.getString("news_id");
        }
        getNewsDetail();
        return root;
    }

    private void getNewsDetail() {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().getNewsDetail(strToken, news_id, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    model = new Gson().fromJson(jsonObject.get("data").getAsJsonObject(), NewsModel.class);
                    setData();
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

    private void setData() {
        if (model != null) {
            binding.txtTitle.setText(Html.fromHtml(model.getTitle_hi()));
            binding.txtDes.setText(Html.fromHtml(model.getDescription_hi()));
            String date = new BeautifyDate().beautifyDate(model.getCreated_at(), "yyyy-MM-dd", "dd MMM, yyyy");
            binding.txtDate.setText(date);

            Glide.with(getContext()).load(model.getAttachment()).into(binding.image);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}