package com.dawat.farmer.mamits.ui.farmerProfile.transplantationDetails;

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

import com.dawat.farmer.mamits.adapter.TransplantationAdapter;
import com.dawat.farmer.mamits.databinding.FragmentTransplantationListBinding;
import com.dawat.farmer.mamits.model.TransplantationModel;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.CustomLinearLayoutManager;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class TransplantationListFragment extends Fragment {

    private FragmentTransplantationListBinding binding;
    private TransplantationAdapter transplantationAdapter;
    private SharedPreferences sharedPreferences;
    private String strToken = "";

    public TransplantationListFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTransplantationListBinding.inflate(inflater, container, false);
        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        View root = binding.getRoot();
        setUpCropsAdapter();
        return root;
    }

    private void setUpCropsAdapter() {
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerTransplantation.setLayoutManager(manager);
        binding.recyclerTransplantation.setItemAnimator(null);
        transplantationAdapter = new TransplantationAdapter(getContext());
        binding.recyclerTransplantation.setAdapter(transplantationAdapter);
    }

    private void getTransplantationList() {
        try {
            new ApiHelper().getTransplantationList(strToken, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Type treatment = new TypeToken<List<TransplantationModel>>() {
                    }.getType();

                    List<TransplantationModel> list = new Gson().fromJson(jsonObject.get("data").getAsJsonArray().toString(), treatment);
                    transplantationAdapter.setList(list);
                    if (list.size() > 0) {
                        binding.noFarmCard.setVisibility(View.GONE);
                        binding.recyclerTransplantation.setVisibility(View.VISIBLE);
                    } else {
                        binding.recyclerTransplantation.setVisibility(View.GONE);
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
        getTransplantationList();
    }

}