package com.dawat.farmer.mamits.ui.farmerProfile;

import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_CURRENT_DATE;
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

import com.dawat.farmer.mamits.databinding.FragmentCocBinding;
import com.dawat.farmer.mamits.model.CocModel;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.ProgressLoading;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class CocFragment extends Fragment {

    private FragmentCocBinding binding;
    private ProgressLoading progressLoading;
    private SharedPreferences sharedPreferences;
    private String strToken = "";
    private String farmer_id;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCocBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        String current_date = sharedPreferences.getString(PREF_KEY_CURRENT_DATE, "");

        Bundle bundle = getArguments();
        if (bundle != null) {
            farmer_id = bundle.getString("farmer_id");
        }

        progressLoading = new ProgressLoading();
        getCocDetail();

        return root;
    }


    private void getCocDetail() {
        try {
            progressLoading.showLoading(getContext());
            new ApiHelper().getCocDetail(strToken, farmer_id, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    CocModel cocModel = new Gson().fromJson(jsonObject.get("total").getAsJsonObject().toString(), CocModel.class);

                    if (cocModel != null) {
                        setCocDetails(cocModel);
                    }
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

    private void setCocDetails(CocModel cocModel) {
        binding.txtNursery.setText("₹ " + cocModel.getNurserytotal());
        binding.txtSeed.setText("₹ " + cocModel.getSeedtreatmenttotal());
        binding.txtIrrigation.setText("₹ " + cocModel.getIrrigationtotal());
        binding.txtFertilizer.setText("₹ " + cocModel.getFertilizationtotal());
        binding.txtPlant.setText("₹ " + cocModel.getPlantprotectiontotal());
        binding.txtHarvesting.setText("₹ " + cocModel.getHarvestingtotal());
        binding.txtMachinery.setText("₹ " + cocModel.getMachinerytotal());
        binding.txtLabour.setText("₹ " + cocModel.getLabourtotal());
        binding.txtPurchases.setText("₹ " + cocModel.getPurchasingtotal());
        binding.txtGrossIncome.setText("₹ " + cocModel.getPurchasingtotal());
        binding.txtTotalCost.setText("₹ " + cocModel.getTotalcost());
        binding.txtNetTotalIncome.setText("₹ " + cocModel.getNettotalincome());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}