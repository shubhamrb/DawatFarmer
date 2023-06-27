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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.MainActivity;
import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.adapter.BlogsListAdapter;
import com.dawat.farmer.mamits.adapter.DashboardSubCategoryListAdapter;
import com.dawat.farmer.mamits.databinding.FragmentSubCategoryBinding;
import com.dawat.farmer.mamits.utils.CustomLinearLayoutManager;
import com.dawat.farmer.mamits.utils.ProgressLoading;

public class SubCategoryFragment extends Fragment implements DashboardSubCategoryListAdapter.OnClickListener, BlogsListAdapter.OnClickListener {

    private FragmentSubCategoryBinding binding;
    private SharedPreferences sharedPreferences;
    private String strToken = "";
    private ProgressLoading progressLoading;
    private DashboardSubCategoryListAdapter dashboardSubCategoryListAdapter;

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
            String category_id = bundle.getString("category_id");
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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onSubCategoryClick(String sub_category_id) {
        Bundle bundle = new Bundle();
        bundle.putString("sub_category_id", sub_category_id);

        Navigation.findNavController(((MainActivity) getContext())
                .findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_category_detail, bundle);
    }

    @Override
    public void onBlogClick(String blog_id) {

    }
}