package com.dawat.farmer.mamits.ui.farmerProfile.purchaseDetails;

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

import com.dawat.farmer.mamits.adapter.PurchasingAdapter;
import com.dawat.farmer.mamits.databinding.FragmentPurchaseListBinding;
import com.dawat.farmer.mamits.model.PurchasingModel;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.CustomLinearLayoutManager;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class PurchaseListFragment extends Fragment {

    private FragmentPurchaseListBinding binding;
    private PurchasingAdapter purchasingAdapter;
    private SharedPreferences sharedPreferences;
    private String strToken = "";

    public PurchaseListFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPurchaseListBinding.inflate(inflater, container, false);
        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        View root = binding.getRoot();

        setUpProtectionAdapter();
        return root;
    }

    private void setUpProtectionAdapter() {
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerPurchasing.setLayoutManager(manager);
        binding.recyclerPurchasing.setItemAnimator(null);
        purchasingAdapter = new PurchasingAdapter(getContext());
        binding.recyclerPurchasing.setAdapter(purchasingAdapter);
    }

    private void getPurchasingList() {
        try {
            new ApiHelper().getPurchasingList(strToken, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Type treatment = new TypeToken<List<PurchasingModel>>() {
                    }.getType();

                    List<PurchasingModel> list = new Gson().fromJson(jsonObject.get("data").getAsJsonArray().toString(), treatment);
                    purchasingAdapter.setList(list);
                    if (list.size() > 0) {
                        binding.noFarmCard.setVisibility(View.GONE);
                        binding.recyclerPurchasing.setVisibility(View.VISIBLE);
                    } else {
                        binding.recyclerPurchasing.setVisibility(View.GONE);
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
        getPurchasingList();
    }
}