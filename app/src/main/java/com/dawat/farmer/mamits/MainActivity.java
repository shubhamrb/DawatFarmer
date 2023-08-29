package com.dawat.farmer.mamits;

import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_CURRENT_DATE;
import static com.dawat.farmer.mamits.utils.AppConstant.SHARED_PREF_NAME;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.dawat.farmer.mamits.databinding.ActivityMainBinding;
import com.dawat.farmer.mamits.notification.NotificationService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavController.OnDestinationChangedListener {

    private ActivityMainBinding binding;
    private NavController navController;
    private boolean doubleBackToExitPressedOnce;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd MMM, yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        sharedPreferences.edit().putString(PREF_KEY_CURRENT_DATE, formattedDate).apply();
        try {
            startService(new Intent(this, NotificationService.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.addOnDestinationChangedListener(this);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    @Override
    public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination destination, @Nullable Bundle bundle) {

        if (destination.getId() == R.id.navigation_home
                || destination.getId() == R.id.navigation_report
                || destination.getId() == R.id.navigation_news
                || destination.getId() == R.id.navigation_shop
                || destination.getId() == R.id.navigation_notifications
        ) {
            binding.navView.setVisibility(View.VISIBLE);
        } else {
            binding.navView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        try {
            if (navController.getCurrentDestination() != null && navController.getCurrentDestination().getId() != 0) {
                if (navController.getBackQueue().size() == 2 && navController.getCurrentDestination() != null && (navController.getCurrentDestination().getId() == R.id.navigation_home)) {
                    if (doubleBackToExitPressedOnce) {
                        super.onBackPressed();
                        return;
                    }
                    this.doubleBackToExitPressedOnce = true;
                    Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
                    new Handler(Looper.getMainLooper()).postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
                } else {
                    navController.popBackStack();
                }
            } else {
                super.onBackPressed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}