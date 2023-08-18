package com.dawat.farmer.mamits.ui.weather;


import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_CURRENT_DATE;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_PROFILE_IMAGE;
import static com.dawat.farmer.mamits.utils.AppConstant.SHARED_PREF_NAME;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.adapter.WeatherAdapter;
import com.dawat.farmer.mamits.databinding.FragmentWeatherBinding;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class WeatherFragment extends Fragment {

    private FragmentWeatherBinding binding;
    private ProgressLoading progressLoading;
    private WeatherAdapter weatherAdapter;
    private SharedPreferences sharedPreferences;
    private WeatherModel weatherModel;
    private FusedLocationProviderClient fusedLocationClient;
    private ActivityResultLauncher<String[]> requestPermissionLauncher;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWeatherBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String profile_image = sharedPreferences.getString(PREF_PROFILE_IMAGE, "");

        String current_date = new SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(Calendar.getInstance().getTime());
        sharedPreferences.edit().putString(PREF_KEY_CURRENT_DATE, current_date).apply();
        binding.txtTime.setText(current_date);

        Glide.with(getContext()).load(profile_image).error(R.drawable.person_profile).into(binding.profileImage);
        progressLoading = new ProgressLoading();

        Bundle bundle = getArguments();
        if (bundle != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                weatherModel = bundle.getSerializable("weatherModel", WeatherModel.class);
            } else {
                weatherModel = (WeatherModel) bundle.getSerializable("weatherModel");
            }
        }
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

        setUpCurrentWeather();
        getCurrentLocation();
        setUpTabs();

        return root;
    }

    private void getCurrentLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        getLastKnownLocation();
    }

    private void requestLocationPermission() {
        String[] PERMISSIONS_TO_REQUEST = {
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
        };
        requestPermissionLauncher.launch(PERMISSIONS_TO_REQUEST);
    }

    private void getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission();
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        Location lastLocation = task.getResult();
                        double latitude = lastLocation.getLatitude();
                        double longitude = lastLocation.getLongitude();

                        getFutureWeather(latitude, longitude);
                    }
                });
    }

    private void setUpCurrentWeather() {
        if (weatherModel != null) {
            int current_temp = (int) weatherModel.getCurrent().getTemp_c();
            binding.txtCurrentTemp.setText(current_temp + "' C");
            Glide.with(getContext()).load("https:" + weatherModel.getCurrent().getCondition().getIcon()).into(binding.icon);
            binding.txtWind.setText(((int) weatherModel.getCurrent().getWind_kph()) + " kph");
            binding.txtHumidity.setText(((int) weatherModel.getCurrent().getHumidity()) + " %");
        }
    }

    private void getFutureWeather(double latitude, double longitude) {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().getWeatherReport("forecast.json", "dbe4bb2922d6441692391727232606", latitude + "," + longitude, "7", new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    progressLoading.hideLoading();

                    WeatherModel weatherModel = new Gson().fromJson(jsonObject.toString(), WeatherModel.class);
                    weatherAdapter.setList(weatherModel.getForecast().getForecastday());
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

    private void setUpTabs() {
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        binding.recyclerWeather.setLayoutManager(manager);
        weatherAdapter = new WeatherAdapter(getContext());
        binding.recyclerWeather.setAdapter(weatherAdapter);
    }
}