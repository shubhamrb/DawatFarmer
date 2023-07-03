package com.dawat.farmer.mamits.ui.dashboard;


import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_CURRENT_DATE;
import static com.dawat.farmer.mamits.utils.AppConstant.SHARED_PREF_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.adapter.CategoryDetailListAdapter;
import com.dawat.farmer.mamits.databinding.FragmentCategoryDetailBinding;
import com.dawat.farmer.mamits.utils.CustomLinearLayoutManager;
import com.dawat.farmer.mamits.utils.ProgressLoading;

public class CategoryDetailFragment extends Fragment implements CategoryDetailListAdapter.OnClickListener {

    private FragmentCategoryDetailBinding binding;
    private SharedPreferences sharedPreferences;
    private String strToken = "";
    private ProgressLoading progressLoading;
    private CategoryDetailListAdapter categoryDetailListAdapter;
    private String sub_category_id, sub_category_name;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCategoryDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");

        String current_date = sharedPreferences.getString(PREF_KEY_CURRENT_DATE, "");
        binding.txtTime.setText(current_date);

        progressLoading = new ProgressLoading();
        Bundle bundle = getArguments();
        if (bundle != null) {
            sub_category_id = bundle.getString("sub_category_id");
            sub_category_name = bundle.getString("sub_category_name", "");
            binding.labelHello.setText(sub_category_name);
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
        categoryDetailListAdapter = new CategoryDetailListAdapter(getContext(), this);
        binding.recyclerCategory.setAdapter(categoryDetailListAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onListClick(String blog_id) {

    }
}