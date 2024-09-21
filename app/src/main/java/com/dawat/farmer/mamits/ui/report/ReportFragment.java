package com.dawat.farmer.mamits.ui.report;

import static com.dawat.farmer.mamits.utils.AppConstant.IS_LOGIN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_NAME;
import static com.dawat.farmer.mamits.utils.AppConstant.SHARED_PREF_NAME;

import android.content.Context;
import android.content.Intent;
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

import com.dawat.farmer.mamits.LoginActivity;
import com.dawat.farmer.mamits.adapter.SrpStageListAdapter;
import com.dawat.farmer.mamits.databinding.FragmentReportBinding;
import com.dawat.farmer.mamits.model.SrpReportModel;
import com.dawat.farmer.mamits.model.SrpStageModel;
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

public class ReportFragment extends Fragment implements SrpStageListAdapter.OnClickExpandListener {

    private FragmentReportBinding binding;
    private SrpStageListAdapter srpStageListAdapter;
    private SharedPreferences sharedPreferences;
    private String strToken = "";
    private ProgressLoading progressLoading;
//    private String currentActiveId = "";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentReportBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        if (!sharedPreferences.getBoolean(IS_LOGIN, false)) {
            try {
                getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finishAffinity();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        progressLoading = new ProgressLoading();
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        String user_name = sharedPreferences.getString(PREF_NAME, "");
        binding.txtTime.setText(user_name + "'s SRP Reports");

        setUpClickListener();
        setUpFarmersList();
        return root;
    }

    private void setUpClickListener() {

    }

    private void setUpFarmersList() {
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerSrpReport.setLayoutManager(manager);
        binding.recyclerSrpReport.setItemAnimator(null);
        srpStageListAdapter = new SrpStageListAdapter(getContext(), this);
        binding.recyclerSrpReport.setAdapter(srpStageListAdapter);
    }

    private void getSrpStageList() {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().getSrpStageList(strToken, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Type farmer = new TypeToken<List<SrpStageModel>>() {
                    }.getType();

                    List<SrpStageModel> list = new Gson().fromJson(jsonObject.get("data").getAsJsonArray().toString(), farmer);
                    srpStageListAdapter.setList(list);

                    int totalReport = jsonObject.get("totalreport").getAsInt();
                    int totalpoints = jsonObject.get("totalpoints").getAsInt();
                    int averagepoints = jsonObject.get("averagepoints").getAsInt();
//                    currentActiveId = jsonObject.get("currentActiveId").getAsString();

                    binding.txtTotalCount.setText("" + totalReport);
                    binding.txtAvgPoint.setText("Average Points " + averagepoints);
                    binding.txtTotalPoints.setText("Total Points " + totalpoints);
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

    private void getSrpReportList(String stage_id, SrpStageListAdapter.DashboardListViewHolder holder) {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().getSrpReportList(strToken, stage_id, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Type farmer = new TypeToken<List<SrpReportModel>>() {
                    }.getType();

                    List<SrpReportModel> list = new Gson().fromJson(jsonObject.get("data").getAsJsonArray().toString(), farmer);
                    holder.srpReportSubListAdapter.setList(list);
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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        getSrpStageList();
    }

    @Override
    public void onExpand(String stage_id, SrpStageListAdapter.DashboardListViewHolder holder) {
        getSrpReportList(stage_id, holder);
    }
}