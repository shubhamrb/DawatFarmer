package com.dawat.farmer.mamits.ui.farmerProfile.basicDetails;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dawat.farmer.mamits.adapter.FarmerProfileTabListAdapter;
import com.dawat.farmer.mamits.databinding.FragmentBasicDetailsBinding;
import com.dawat.farmer.mamits.model.FarmerBasicDetails;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.ProgressLoading;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class BasicDetailsFragment extends Fragment implements FarmerProfileTabListAdapter.TabClickListener {

    private FragmentBasicDetailsBinding binding;
    private FarmerBasicDetails farmerBasicDetails;
    private SharedPreferences sharedPreferences;
    private String strToken = "";
    private ProgressLoading progressLoading;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBasicDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        progressLoading = new ProgressLoading();
        return root;
    }

    private void getBasicDetails() {
        try {
            progressLoading.showLoading(getContext());
            new ApiHelper().getFarmerBasicDetails(strToken, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Type details = new TypeToken<FarmerBasicDetails>() {
                    }.getType();

                    farmerBasicDetails = new Gson().fromJson(jsonObject.get("data").getAsJsonObject().toString(), details);
                    Log.e("Response : ", farmerBasicDetails.toString());
                    if (binding != null && farmerBasicDetails != null)
                        loadData();
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

    private void loadData() {
        binding.txtFarmerCode.setText(farmerBasicDetails.getFarmer_code());
        binding.txtFarmerName.setText(farmerBasicDetails.getName());
        binding.txtGender.setText("" + farmerBasicDetails.getGender());
        binding.txtFatherName.setText(farmerBasicDetails.getFather_name());
        binding.txtFarmerType.setText(farmerBasicDetails.getFarmer_type());
        if (farmerBasicDetails.getMobile().length() > 0)
            binding.txtMobile.setText(farmerBasicDetails.getMobile().substring(0, farmerBasicDetails.getMobile().length() - 2) + "XX");

        binding.txtAccountStatus.setText(farmerBasicDetails.getStatus().equals("1") ? "सक्रिय" : "निष्क्रिय");
        binding.accountSwitch.setChecked(farmerBasicDetails.getStatus().equals("1"));
        if (farmerBasicDetails.getFk_country() != null)
            binding.txtCountry.setText(farmerBasicDetails.getFk_country().getName());
        if (farmerBasicDetails.getFk_state() != null)
            binding.txtState.setText(farmerBasicDetails.getFk_state().getName());
        if (farmerBasicDetails.getFk_district() != null)
            binding.txtDistrict.setText(farmerBasicDetails.getFk_district().getName());
        binding.txtTahseel.setText(farmerBasicDetails.getTehseel());
        binding.txtBlock.setText(farmerBasicDetails.getBlock());
        binding.txtPinCode.setText(farmerBasicDetails.getPin_code());
        binding.txtVillageName.setText(farmerBasicDetails.getVillage_name());
        binding.txtFullAddress.setText(farmerBasicDetails.getFull_address());

        binding.txtAadhar.setText(farmerBasicDetails.getAadhaar_no());
        binding.txtPan.setText(farmerBasicDetails.getPan_no());
        binding.txtBankAccount.setText(farmerBasicDetails.getBank_account_no());
        binding.txtIfsc.setText(farmerBasicDetails.getIfsc_code());
        binding.txtBranch.setText(farmerBasicDetails.getBranch());

        Glide.with(getContext()).load(farmerBasicDetails.getProfile_image()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(binding.imgFarmerPhoto);
        Glide.with(getContext()).load(farmerBasicDetails.getAadhaar_photo_front()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(binding.imgAadharFront);
        Glide.with(getContext()).load(farmerBasicDetails.getAadhaar_photo_back()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(binding.imgAadharBack);
        Glide.with(getContext()).load(farmerBasicDetails.getBank_passbook_photo()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(binding.imgPassbook);
    }

    @Override
    public void onResume() {
        super.onResume();
        getBasicDetails();
        Log.e("Called", "true");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(int position) {

    }
}