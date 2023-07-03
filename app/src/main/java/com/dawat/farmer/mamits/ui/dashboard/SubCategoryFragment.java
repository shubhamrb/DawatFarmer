package com.dawat.farmer.mamits.ui.dashboard;


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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.MainActivity;
import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.adapter.BlogsListAdapter;
import com.dawat.farmer.mamits.adapter.DashboardSubCategoryListAdapter;
import com.dawat.farmer.mamits.databinding.FragmentSubCategoryBinding;
import com.dawat.farmer.mamits.model.SrpCategoryModel;
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

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SubCategoryFragment extends Fragment implements DashboardSubCategoryListAdapter.OnClickListener, BlogsListAdapter.OnClickListener {

    private FragmentSubCategoryBinding binding;
    private SharedPreferences sharedPreferences;
    private String strToken = "";
    private ProgressLoading progressLoading;
    private DashboardSubCategoryListAdapter dashboardSubCategoryListAdapter;
    private String category_id, category_name;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSubCategoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");

        String current_date = sharedPreferences.getString(PREF_KEY_CURRENT_DATE, "");
        binding.txtTime.setText(current_date);

        progressLoading = new ProgressLoading();
        Bundle bundle = getArguments();
        if (bundle != null) {
            category_id = bundle.getString("category_id");
            category_name = bundle.getString("category_name", "");
            binding.labelHello.setText(category_name);
        }
        clickListeners();
        setUpCategoryList();
        return root;
    }

    private void clickListeners() {

    }

    private void setUpCategoryList() {
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerCategory.setLayoutManager(manager);
        binding.recyclerCategory.setItemAnimator(null);
        dashboardSubCategoryListAdapter = new DashboardSubCategoryListAdapter(getContext(), this);
        binding.recyclerCategory.setAdapter(dashboardSubCategoryListAdapter);
        getSubCategoryList();
    }

    private void getSubCategoryList() {
        progressLoading.showLoading(getContext());
        RequestBody cat_id = RequestBody.create(MultipartBody.FORM, category_id);

        try {
            new ApiHelper().getSrpSubCategoryList(strToken, cat_id, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Type slider = new TypeToken<List<SrpCategoryModel>>() {
                    }.getType();

                    List<SrpCategoryModel> list = new Gson().fromJson(jsonObject.get("data").getAsJsonArray().toString(), slider);
                    dashboardSubCategoryListAdapter.setList(list);
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
    public void onSubCategoryClick(SrpCategoryModel model) {
        Bundle bundle = new Bundle();
        bundle.putString("sub_category_id", model.getId());
        bundle.putString("sub_category_name", model.getCat_title_hi());

        Navigation.findNavController(((MainActivity) getContext())
                .findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_category_detail, bundle);
    }

    @Override
    public void onBlogClick(String blog_id) {

    }
}