package com.dawat.farmer.mamits.ui.farmerProfile.farmDetails;

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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.adapter.FarmAdapter;
import com.dawat.farmer.mamits.databinding.FragmentFarmListBinding;
import com.dawat.farmer.mamits.model.FarmModel;
import com.dawat.farmer.mamits.model.OtherDetailsModel;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.CustomLinearLayoutManager;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class FarmListFragment extends Fragment implements FarmAdapter.FarmClickListener {

    private FragmentFarmListBinding binding;
    private FarmAdapter farmAdapter;
    private SharedPreferences sharedPreferences;
    private String strToken = "";

    public FarmListFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFarmListBinding.inflate(inflater, container, false);
        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        View root = binding.getRoot();

        setUpFarmsAdapter();
        return root;
    }

    private void setUpFarmsAdapter() {
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerFarms.setLayoutManager(manager);
        binding.recyclerFarms.setItemAnimator(null);
        farmAdapter = new FarmAdapter(getContext(), this);
        binding.recyclerFarms.setAdapter(farmAdapter);
    }

    private void getFarmList() {
        try {
            new ApiHelper().getFarmsList(strToken, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Type farms = new TypeToken<List<FarmModel>>() {
                    }.getType();

                    OtherDetailsModel detailsModel = new Gson().fromJson(jsonObject.get("otherdata").getAsJsonObject(), OtherDetailsModel.class);
                    binding.txtTotalFarmCount.setText(detailsModel.getTotalform());
                    binding.txtTotalFieldCount.setText("कुल " + detailsModel.getFormfield() + " खेत");
                    binding.txtTotalSize.setText(detailsModel.getTotal() + " Acres");


                    List<FarmModel> list = new Gson().fromJson(jsonObject.get("data").getAsJsonArray().toString(), farms);
                    farmAdapter.setList(list);
                    if (list.size() > 0) {
                        binding.noFarmCard.setVisibility(View.GONE);
                        binding.recyclerFarms.setVisibility(View.VISIBLE);
                    } else {
                        binding.recyclerFarms.setVisibility(View.GONE);
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
    public void onResume() {
        super.onResume();
        getFarmList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(FarmModel model) {
        Bundle bundle = new Bundle();
        bundle.putString("id", model.getId());
        bundle.putString("code", model.getCode());
        Navigation.findNavController(getActivity().findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_farm_fields, bundle);
    }
}