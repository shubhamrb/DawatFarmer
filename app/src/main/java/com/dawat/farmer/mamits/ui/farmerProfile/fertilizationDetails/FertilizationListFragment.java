package com.dawat.farmer.mamits.ui.farmerProfile.fertilizationDetails;

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

import com.dawat.farmer.mamits.adapter.FertilizationAdapter;
import com.dawat.farmer.mamits.databinding.FragmentFertilizationListBinding;
import com.dawat.farmer.mamits.model.FertilizationModel;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.CustomLinearLayoutManager;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class FertilizationListFragment extends Fragment {

    private FragmentFertilizationListBinding binding;
    private FertilizationAdapter fertilizationAdapter;
    private SharedPreferences sharedPreferences;
    private String strToken = "";

    public FertilizationListFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFertilizationListBinding.inflate(inflater, container, false);
        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        View root = binding.getRoot();

        setUpCropsAdapter();
        return root;
    }

    private void setUpCropsAdapter() {
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerFertilization.setLayoutManager(manager);
        binding.recyclerFertilization.setItemAnimator(null);
        fertilizationAdapter = new FertilizationAdapter(getContext());
        binding.recyclerFertilization.setAdapter(fertilizationAdapter);
    }

    private void getFertilizationList() {
        try {
            new ApiHelper().getFertilizationList(strToken, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Type irrigation = new TypeToken<List<FertilizationModel>>() {
                    }.getType();

                    List<FertilizationModel> list = new Gson().fromJson(jsonObject.get("data").getAsJsonArray().toString(), irrigation);
                    fertilizationAdapter.setList(list);
                    if (list.size() > 0) {
                        binding.noFarmCard.setVisibility(View.GONE);
                        binding.recyclerFertilization.setVisibility(View.VISIBLE);
                    } else {
                        binding.recyclerFertilization.setVisibility(View.GONE);
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
        getFertilizationList();
    }

}