package com.dawat.farmer.mamits.ui.farmerProfile.harvestingDetails;

import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
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

import com.dawat.farmer.mamits.adapter.HarvestingAdapter;
import com.dawat.farmer.mamits.databinding.FragmentHarvestingListBinding;
import com.dawat.farmer.mamits.model.HarvestingModel;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.CustomLinearLayoutManager;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class HarvestingListFragment extends Fragment {

    private FragmentHarvestingListBinding binding;
    private HarvestingAdapter harvestingAdapter;
    private SharedPreferences sharedPreferences;
    private String strToken = "";

    public HarvestingListFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHarvestingListBinding.inflate(inflater, container, false);
        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        View root = binding.getRoot();

        setUpProtectionAdapter();
        return root;
    }

    private void setUpProtectionAdapter() {
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerHarvesting.setLayoutManager(manager);
        binding.recyclerHarvesting.setItemAnimator(null);
        harvestingAdapter = new HarvestingAdapter(getContext());
        binding.recyclerHarvesting.setAdapter(harvestingAdapter);
    }

    private void getHarvestingList() {
        try {
            new ApiHelper().getHarvestingList(strToken, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Type treatment = new TypeToken<List<HarvestingModel>>() {
                    }.getType();

                    List<HarvestingModel> list = new Gson().fromJson(jsonObject.get("data").getAsJsonArray().toString(), treatment);
                    harvestingAdapter.setList(list);
                    if (list.size() > 0) {
                        binding.noFarmCard.setVisibility(View.GONE);
                        binding.recyclerHarvesting.setVisibility(View.VISIBLE);
                    } else {
                        binding.recyclerHarvesting.setVisibility(View.GONE);
                        binding.noFarmCard.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    Log.e(AppConstant.LOG_KEY_ERROR, throwable.getMessage());
                    Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.e(AppConstant.LOG_KEY_ERROR, e.getMessage());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        getHarvestingList();
    }
}