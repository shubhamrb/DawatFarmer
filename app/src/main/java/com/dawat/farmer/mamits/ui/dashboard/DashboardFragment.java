package com.dawat.farmer.mamits.ui.dashboard;


import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_CURRENT_DATE;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_PROFILE_IMAGE;
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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dawat.farmer.mamits.MainActivity;
import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.adapter.BlogsListAdapter;
import com.dawat.farmer.mamits.adapter.DashboardCategoryListAdapter;
import com.dawat.farmer.mamits.adapter.DashboardSliderAdapter;
import com.dawat.farmer.mamits.adapter.TabListAdapter;
import com.dawat.farmer.mamits.databinding.FragmentDashboardBinding;
import com.dawat.farmer.mamits.model.SliderModel;
import com.dawat.farmer.mamits.model.Tabs;
import com.dawat.farmer.mamits.model.WeatherModel;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.CustomLinearLayoutManager;
import com.dawat.farmer.mamits.utils.ProgressLoading;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DashboardFragment extends Fragment implements DashboardCategoryListAdapter.OnClickListener, BlogsListAdapter.OnClickListener {

    private FragmentDashboardBinding binding;
    private SharedPreferences sharedPreferences;
    private String strToken = "";
    private ProgressLoading progressLoading;
    private DashboardCategoryListAdapter dashboardCategoryListAdapter;
    private Runnable update;
    private int currentPage = 0;
    private TabListAdapter tabListAdapter;
    private BlogsListAdapter blogsListAdapter;
    private DashboardSliderAdapter dashboardSliderAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        String username = sharedPreferences.getString(PREF_USER_NAME, "");
        String profile_image = sharedPreferences.getString(PREF_PROFILE_IMAGE, "");
        binding.txtUsername.setText(username);

        String current_date = new SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(Calendar.getInstance().getTime());
        sharedPreferences.edit().putString(PREF_KEY_CURRENT_DATE, current_date).apply();
        binding.txtTime.setText(current_date);

        Glide.with(getContext()).load(profile_image).error(R.drawable.person_profile).into(binding.profileImage);
        progressLoading = new ProgressLoading();

        clickListeners();
        getCurrentWeather();
        setUpSlider();
        setUpTabs();
        setUpCategoryList();
        setUpBlogs();
        return root;
    }

    private void getCurrentWeather() {
        try {
            new ApiHelper().getWeatherReport("current.json", "dbe4bb2922d6441692391727232606", "Indore", "0", new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());

                    WeatherModel weatherModel = new Gson().fromJson(jsonObject.toString(), WeatherModel.class);
                    int current_temp = (int) weatherModel.getCurrent().getTemp_c();
                    binding.txtTemp.setText(current_temp + "' C");

                    Glide.with(getContext()).load("https:" + weatherModel.getCurrent().getCondition().getIcon()).into(binding.weatherIcon);
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

    private void setUpBlogs() {
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        binding.recyclerBlog.setLayoutManager(manager);
        binding.recyclerBlog.setItemAnimator(null);

        blogsListAdapter = new BlogsListAdapter(getContext(), this);
        binding.recyclerBlog.setAdapter(blogsListAdapter);
    }

    private void setUpTabs() {
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        binding.recyclerFarmerProfileTab.setLayoutManager(manager);
        binding.recyclerFarmerProfileTab.setItemAnimator(null);

        List<Tabs> list = new ArrayList<>();
        list.add(new Tabs("रिपोर्ट्स", R.drawable.report_icon));
        list.add(new Tabs("समाचार", R.drawable.news_icon));
        list.add(new Tabs("लेख", R.drawable.srp_report_icon));
        list.add(new Tabs("दूकान", R.drawable.shop_icon));

        tabListAdapter = new TabListAdapter(getContext(), list);
        binding.recyclerFarmerProfileTab.setAdapter(tabListAdapter);
    }

    private void setUpSlider() {
        dashboardSliderAdapter = new DashboardSliderAdapter(requireContext());
        binding.viewpager.setAdapter(dashboardSliderAdapter);
        binding.indicator.attachTo(binding.viewpager);

        if (update != null) {
            binding.viewpager.removeCallbacks(update);
        }
        currentPage = 0;
        update = () -> {
            if (binding != null) {
                if (currentPage == binding.viewpager.getAdapter().getCount()) {
                    currentPage = 0;
                }
                binding.viewpager.setCurrentItem(currentPage++, true);
                binding.viewpager.postDelayed(update, 3000);
            }
        };
        binding.viewpager.postDelayed(update, 3000);

        getSliderList();
    }

    private void getSliderList() {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().getSliderList(strToken, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Type slider = new TypeToken<List<SliderModel>>() {
                    }.getType();

                    List<SliderModel> list = new Gson().fromJson(jsonObject.get("data").getAsJsonArray().toString(), slider);
                    dashboardSliderAdapter.setList(list);
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

    private void clickListeners() {
        binding.rlUserName.setOnClickListener(v -> {
            Navigation.findNavController(((MainActivity) getContext())
                    .findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_profile);
        });
        binding.profileImage.setOnClickListener(v -> {
            Navigation.findNavController(((MainActivity) getContext())
                    .findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_profile);
        });
    }

    private void setUpCategoryList() {
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerCategory.setLayoutManager(manager);
        binding.recyclerCategory.setItemAnimator(null);
        dashboardCategoryListAdapter = new DashboardCategoryListAdapter(getContext(), this);
        binding.recyclerCategory.setAdapter(dashboardCategoryListAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (update != null) {
            binding.viewpager.removeCallbacks(update);
        }
        binding = null;
    }

    @Override
    public void onCategoryClick(String category_id) {
        Bundle bundle = new Bundle();
        bundle.putString("category_id", category_id);

        Navigation.findNavController(((MainActivity) getContext())
                .findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_sub_category, bundle);
    }

    @Override
    public void onBlogClick(String blog_id) {

    }
}