package com.dawat.farmer.mamits.ui.farmerProfile;

import static com.dawat.farmer.mamits.utils.AppConstant.PREF_MOBILE;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_NAME;
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
import androidx.viewpager2.widget.ViewPager2;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.adapter.FarmerProfileTabListAdapter;
import com.dawat.farmer.mamits.adapter.ViewPagerAdapter;
import com.dawat.farmer.mamits.databinding.FragmentFarmerProfileBinding;
import com.dawat.farmer.mamits.model.FarmerProfileTabs;
import com.dawat.farmer.mamits.ui.farmerProfile.basicDetails.BasicDetailsFragment;
import com.dawat.farmer.mamits.ui.farmerProfile.cropDetails.CropListFragment;
import com.dawat.farmer.mamits.ui.farmerProfile.farmDetails.FarmListFragment;
import com.dawat.farmer.mamits.ui.farmerProfile.fertilizationDetails.FertilizationListFragment;
import com.dawat.farmer.mamits.ui.farmerProfile.harvestingDetails.HarvestingListFragment;
import com.dawat.farmer.mamits.ui.farmerProfile.irrigationDetails.IrrigationListFragment;
import com.dawat.farmer.mamits.ui.farmerProfile.laborDetails.LaborListFragment;
import com.dawat.farmer.mamits.ui.farmerProfile.machineryDetails.MachineryListFragment;
import com.dawat.farmer.mamits.ui.farmerProfile.nurseryDetails.NurseryListFragment;
import com.dawat.farmer.mamits.ui.farmerProfile.plantProtectionDetails.PlantProtectionListFragment;
import com.dawat.farmer.mamits.ui.farmerProfile.purchaseDetails.PurchaseListFragment;
import com.dawat.farmer.mamits.ui.farmerProfile.transplantationDetails.TransplantationListFragment;
import com.dawat.farmer.mamits.ui.farmerProfile.treatmentDetails.TreatmentListFragment;
import com.dawat.farmer.mamits.utils.CustomLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class FarmerProfileFragment extends Fragment implements FarmerProfileTabListAdapter.TabClickListener {

    private FragmentFarmerProfileBinding binding;
    private FarmerProfileTabListAdapter farmerProfileTabListAdapter;
    private ViewPagerAdapter adapter;
    private String mobile_number = "", user_name = "";
    private SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFarmerProfileBinding.inflate(inflater, container, false);
        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        user_name = sharedPreferences.getString(PREF_NAME, "");
        mobile_number = sharedPreferences.getString(PREF_MOBILE, "");
        binding.txtUsername.setText(user_name);
        binding.txtNumber.setText(mobile_number);
        View root = binding.getRoot();
        setUpTabs();
        setUpViewPager();
        return root;
    }

    private void setUpViewPager() {
        adapter = new ViewPagerAdapter(getActivity());
        binding.viewPager2.setAdapter(adapter);

        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                farmerProfileTabListAdapter.onclick(position);
                Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentByTag("f" + binding.viewPager2.getCurrentItem());

                if (currentFragment instanceof BasicDetailsFragment) {
                    currentFragment.onResume();
                }
                if (currentFragment instanceof FarmListFragment) {
                    currentFragment.onResume();
                }
                if (currentFragment instanceof CropListFragment) {
                    currentFragment.onResume();
                }
                if (currentFragment instanceof NurseryListFragment) {
                    currentFragment.onResume();
                }
                if (currentFragment instanceof TreatmentListFragment) {
                    currentFragment.onResume();
                }
                if (currentFragment instanceof TransplantationListFragment) {
                    currentFragment.onResume();
                }
                if (currentFragment instanceof IrrigationListFragment) {
                    currentFragment.onResume();
                }
                if (currentFragment instanceof FertilizationListFragment) {
                    currentFragment.onResume();
                }
                if (currentFragment instanceof PlantProtectionListFragment) {
                    currentFragment.onResume();
                }
                if (currentFragment instanceof HarvestingListFragment) {
                    currentFragment.onResume();
                }
                if (currentFragment instanceof PurchaseListFragment) {
                    currentFragment.onResume();
                }
                if (currentFragment instanceof MachineryListFragment) {
                    currentFragment.onResume();
                }
                if (currentFragment instanceof LaborListFragment) {
                    currentFragment.onResume();
                }
                if (currentFragment instanceof CocFragment) {
                    currentFragment.onResume();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setUpTabs() {
        CustomLinearLayoutManager watchListMoviesManager = new CustomLinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        binding.recyclerFarmerProfileTab.setLayoutManager(watchListMoviesManager);
        binding.recyclerFarmerProfileTab.setItemAnimator(null);

        List<FarmerProfileTabs> list = new ArrayList<>();
        list.add(new FarmerProfileTabs("सामान्य विवरण", R.drawable.person_profile));
        list.add(new FarmerProfileTabs("खेत विवरण", R.drawable.golf_icon));
        list.add(new FarmerProfileTabs("फसल विवरण", R.drawable.leaf_icon));
        list.add(new FarmerProfileTabs("नर्सरी", R.drawable.football_icon));
        list.add(new FarmerProfileTabs("इलाज", R.drawable.syringe_icon));
        list.add(new FarmerProfileTabs("ट्रांसप्लांटेशन", R.drawable.dice_icon));
        list.add(new FarmerProfileTabs("सिंचाई प्रबंध", R.drawable.bucket_icon));
        list.add(new FarmerProfileTabs("फर्टिलाइजेशन", R.drawable.test_tube_icon));
        list.add(new FarmerProfileTabs("फसल संरक्षण प्रबंधन", R.drawable.shield_icon));
        list.add(new FarmerProfileTabs("हार्वेस्टिंग", R.drawable.scissors_icon));
        list.add(new FarmerProfileTabs("खरीद", R.drawable.bag));
        list.add(new FarmerProfileTabs("मशीनरी", R.drawable.bus_icon));
        list.add(new FarmerProfileTabs("लेबर", R.drawable.person_multi));
        list.add(new FarmerProfileTabs("खेती की लागत", R.drawable.person_multi));


        farmerProfileTabListAdapter = new FarmerProfileTabListAdapter(getContext(), this, list);
        binding.recyclerFarmerProfileTab.setAdapter(farmerProfileTabListAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(int position) {
        binding.viewPager2.setCurrentItem(position, true);
    }
}