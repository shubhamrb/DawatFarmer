package com.dawat.farmer.mamits.ui.report;

import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_USER_NAME;
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

import com.dawat.farmer.mamits.adapter.SrpAnswerListAdapter;
import com.dawat.farmer.mamits.databinding.FragmentSrpReportDetailBinding;
import com.dawat.farmer.mamits.model.SrpAnswerModel;
import com.dawat.farmer.mamits.model.SrpQuestionModel;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.CustomLinearLayoutManager;
import com.dawat.farmer.mamits.utils.ProgressLoading;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SrpReportDetailFragment extends Fragment {

    private FragmentSrpReportDetailBinding binding;
    private SrpAnswerListAdapter srpAnswerListAdapter;
    private SharedPreferences sharedPreferences;
    private String strToken = "";
    private ProgressLoading progressLoading;
    private String report_id;
    private List<SrpQuestionModel> answerList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSrpReportDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressLoading = new ProgressLoading();

        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        String user_name = sharedPreferences.getString(PREF_USER_NAME, "");
        binding.txtTime.setText(user_name + "'s SRP Reports");
        answerList = new ArrayList<>();
        Bundle bundle = getArguments();
        if (bundle != null) {
            report_id = bundle.getString("report_id");
        }
        setUpQuestionsList();
        return root;
    }

    private void setUpQuestionsList() {
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerQuestion.setLayoutManager(manager);
        binding.recyclerQuestion.setItemAnimator(null);
        srpAnswerListAdapter = new SrpAnswerListAdapter(getContext());
        binding.recyclerQuestion.setAdapter(srpAnswerListAdapter);
    }

    private void getSrpAnswerList() {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().getSrpAnswerList(strToken, report_id, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Type farmer = new TypeToken<List<SrpAnswerModel>>() {
                    }.getType();

                    List<SrpAnswerModel> list = new Gson().fromJson(jsonObject.get("data").getAsJsonObject().get("questionanswer").getAsJsonArray().toString(), farmer);
                    srpAnswerListAdapter.setList(list);
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
        getSrpAnswerList();
    }
}