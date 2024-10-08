package com.dawat.farmer.mamits.ui.dashboard;


import static com.dawat.farmer.mamits.utils.AppConstant.IS_LOGIN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_CURRENT_DATE;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_NAME;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_PROFILE_IMAGE;
import static com.dawat.farmer.mamits.utils.AppConstant.SHARED_PREF_NAME;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dawat.farmer.mamits.LoginActivity;
import com.dawat.farmer.mamits.MainActivity;
import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.adapter.BlogsListAdapter;
import com.dawat.farmer.mamits.adapter.DashboardCategoryListAdapter;
import com.dawat.farmer.mamits.adapter.DashboardSliderAdapter;
import com.dawat.farmer.mamits.adapter.TabListAdapter;
import com.dawat.farmer.mamits.databinding.FragmentDashboardBinding;
import com.dawat.farmer.mamits.model.BlogModel;
import com.dawat.farmer.mamits.model.SliderModel;
import com.dawat.farmer.mamits.model.SrpCategoryModel;
import com.dawat.farmer.mamits.model.Tabs;
import com.dawat.farmer.mamits.model.WeatherModel;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.CustomLinearLayoutManager;
import com.dawat.farmer.mamits.utils.ProgressLoading;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DashboardFragment extends Fragment implements DashboardCategoryListAdapter.OnClickListener, BlogsListAdapter.OnClickListener, TabListAdapter.OnTabClickListener {

    private String[] PERMISSIONS_TO_REQUEST = {};
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
    private WeatherModel weatherModel;
    private FusedLocationProviderClient fusedLocationClient;
    private ActivityResultLauncher<String[]> requestPermissionLauncher;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        String username = sharedPreferences.getString(PREF_NAME, "");
        String profile_image = sharedPreferences.getString(PREF_PROFILE_IMAGE, "");
        binding.txtUsername.setText(username);

        String current_date = new SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(Calendar.getInstance().getTime());
        sharedPreferences.edit().putString(PREF_KEY_CURRENT_DATE, current_date).apply();
        binding.txtTime.setText(current_date);

        Glide.with(getContext()).load(profile_image).error(R.drawable.person_profile).into(binding.profileImage);
        progressLoading = new ProgressLoading();

        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
            boolean allPermissionsGranted = true;
            for (Boolean granted : result.values()) {
                if (!granted) {
                    allPermissionsGranted = false;
                    break;
                }
            }
            if (allPermissionsGranted) {
                getLastKnownLocation();
            } else {
                Toast.makeText(getContext(), "Permissions required", Toast.LENGTH_SHORT).show();
            }
        });


        getCurrentLocation();
        clickListeners();
        setUpSlider();
        setUpTabs();
        setUpCategoryList();
        setUpBlogs();

        return root;
    }

    private void getCurrentLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        getLastKnownLocation();
    }

    private void requestLocationPermission() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            PERMISSIONS_TO_REQUEST = new String[]{Manifest.permission.POST_NOTIFICATIONS, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        } else {
            PERMISSIONS_TO_REQUEST = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        }
        requestPermissionLauncher.launch(PERMISSIONS_TO_REQUEST);
    }

    private void getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission();
            return;
        }
        fusedLocationClient.getLastLocation().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                Location lastLocation = task.getResult();
                double latitude = lastLocation.getLatitude();
                double longitude = lastLocation.getLongitude();

                getCurrentWeather(latitude, longitude);
            }
        });
    }

    private void getCurrentWeather(double latitude, double longitude) {
        try {
            new ApiHelper().getWeatherReport("current.json", "dbe4bb2922d6441692391727232606", latitude + "," + longitude, "0", new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());

                    weatherModel = new Gson().fromJson(jsonObject.toString(), WeatherModel.class);
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

        blogsListAdapter = new BlogsListAdapter(getContext(), this, false);
        binding.recyclerBlog.setAdapter(blogsListAdapter);
        getBlogsList();
    }

    private void getBlogsList() {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().getBlogsList(strToken, null, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Type slider = new TypeToken<List<BlogModel>>() {
                    }.getType();

                    List<BlogModel> list = new Gson().fromJson(jsonObject.get("data").getAsJsonArray().toString(), slider);
                    blogsListAdapter.setList(list);
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

    private void setUpTabs() {
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        binding.recyclerFarmerProfileTab.setLayoutManager(manager);
        binding.recyclerFarmerProfileTab.setItemAnimator(null);

        List<Tabs> list = new ArrayList<>();
        list.add(new Tabs("सपोर्ट", R.drawable.headphone_icon));
        list.add(new Tabs("रिपोर्ट्स", R.drawable.report_icon));
        list.add(new Tabs("समाचार", R.drawable.news_icon));
        list.add(new Tabs("लेख", R.drawable.srp_report_icon));
        list.add(new Tabs("दूकान", R.drawable.shop_icon));

        tabListAdapter = new TabListAdapter(getContext(), list, this);
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
            if (sharedPreferences.getBoolean(IS_LOGIN, false)) {
                Navigation.findNavController(((MainActivity) getContext()).findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_profile);
            } else {
                try {
                    getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finishAffinity();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        binding.profileImage.setOnClickListener(v -> {
            if (sharedPreferences.getBoolean(IS_LOGIN, false)) {
                Navigation.findNavController(((MainActivity) getContext()).findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_profile);
            } else {
                try {
                    getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finishAffinity();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        binding.btnWeather.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("weatherModel", weatherModel);
            Navigation.findNavController(((MainActivity) getContext()).findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_weather, bundle);
        });
        binding.weatherCard.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("weatherModel", weatherModel);
            Navigation.findNavController(((MainActivity) getContext()).findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_weather, bundle);
        });
        binding.btnShop.setOnClickListener(v -> {
            if (sharedPreferences.getBoolean(IS_LOGIN, false)) {
                Navigation.findNavController(((MainActivity) getContext()).findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_shop_list);
            } else {
                try {
                    getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finishAffinity();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        binding.btnNotification.setOnClickListener(v -> {
            if (sharedPreferences.getBoolean(IS_LOGIN, false)) {
                Navigation.findNavController(((MainActivity) getContext()).findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_notifications_list);
            } else {
                try {
                    getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finishAffinity();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        binding.btnSupport.setOnClickListener(v -> {
            if (sharedPreferences.getBoolean(IS_LOGIN, false)) {
                Navigation.findNavController(((MainActivity) getContext()).findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_appeal);
            } else {
                try {
                    getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finishAffinity();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        binding.btnBlog.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("sub_category_name", "लेख");
            Navigation.findNavController(((MainActivity) getContext()).findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_blogs, bundle);
        });
        binding.btnCart.setOnClickListener(v -> {
            if (sharedPreferences.getBoolean(IS_LOGIN, false)) {
                Navigation.findNavController(((MainActivity) getContext()).findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_cart);
            } else {
                try {
                    getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finishAffinity();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        binding.btnOrders.setOnClickListener(v -> {
            if (sharedPreferences.getBoolean(IS_LOGIN, false)) {
                Navigation.findNavController(((MainActivity) getContext()).findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_orders);
            } else {
                try {
                    getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finishAffinity();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        binding.btnProfile.setOnClickListener(v -> {
            if (sharedPreferences.getBoolean(IS_LOGIN, false)) {
                Navigation.findNavController(((MainActivity) getContext()).findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_profile);
            } else {
                try {
                    getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finishAffinity();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setUpCategoryList() {
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerCategory.setLayoutManager(manager);
        binding.recyclerCategory.setItemAnimator(null);
        dashboardCategoryListAdapter = new DashboardCategoryListAdapter(getContext(), this);
        binding.recyclerCategory.setAdapter(dashboardCategoryListAdapter);
        getCategoryList();
    }

    private void getCategoryList() {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().getSrpCategoryList(strToken, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Type slider = new TypeToken<List<SrpCategoryModel>>() {
                    }.getType();

                    List<SrpCategoryModel> list = new Gson().fromJson(jsonObject.get("data").getAsJsonArray().toString(), slider);
                    dashboardCategoryListAdapter.setList(list);
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
        if (update != null) {
            binding.viewpager.removeCallbacks(update);
        }
//        binding = null;
    }

    @Override
    public void onCategoryClick(SrpCategoryModel model) {
        Bundle bundle = new Bundle();
        bundle.putString("category_id", model.getId());
        bundle.putString("category_name", model.getCat_title_hi());

        Navigation.findNavController(((MainActivity) getContext()).findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_sub_category, bundle);
    }

    @Override
    public void onBlogClick(String blog_id, String title) {
        Bundle bundle = new Bundle();
        bundle.putString("blog_id", blog_id);
        bundle.putString("title", title);

        Navigation.findNavController(((MainActivity) getContext()).findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_blog_detail, bundle);
    }

    @Override
    public void onTabClick(String tab) {
        switch (tab) {
            case "सपोर्ट":
                if (sharedPreferences.getBoolean(IS_LOGIN, false)) {
                    Navigation.findNavController(((MainActivity) getContext()).findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_appeal);
                } else {
                    try {
                        getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finishAffinity();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "रिपोर्ट्स":
                if (sharedPreferences.getBoolean(IS_LOGIN, false)) {
                    Navigation.findNavController(((MainActivity) getContext()).findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_srp_report);
                } else {
                    try {
                        getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finishAffinity();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "समाचार":
                Navigation.findNavController(((MainActivity) getContext()).findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_news_list);
                break;
            case "लेख":
                Bundle bundle = new Bundle();
                bundle.putString("sub_category_name", "लेख");
                Navigation.findNavController(((MainActivity) getContext()).findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_blogs, bundle);
                break;
            case "दूकान":
                if (sharedPreferences.getBoolean(IS_LOGIN, false)) {
                    Navigation.findNavController(((MainActivity) getContext()).findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_shop_list);
                } else {
                    try {
                        getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finishAffinity();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}