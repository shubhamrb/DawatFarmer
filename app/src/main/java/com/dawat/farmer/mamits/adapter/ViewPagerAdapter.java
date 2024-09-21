package com.dawat.farmer.mamits.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.dawat.farmer.mamits.ui.farmerProfile.CocFragment;
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

public class ViewPagerAdapter extends FragmentStateAdapter {
    private final int PAGE_COUNT = 14;
    public BasicDetailsFragment basicDetailsFragment;
    public FarmListFragment farmListFragment;
    public CropListFragment cropListFragment;
    public NurseryListFragment nurseryListFragment;
    public TreatmentListFragment treatmentListFragment;
    public TransplantationListFragment transplantationListFragment;
    public IrrigationListFragment irrigationListFragment;
    public FertilizationListFragment fertilizationListFragment;
    public PlantProtectionListFragment plantProtectionListFragment;
    public HarvestingListFragment harvestingListFragment;
    public PurchaseListFragment purchaseListFragment;
    public MachineryListFragment machineryListFragment;
    public LaborListFragment laborListFragment;
    public CocFragment cocFragment;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                basicDetailsFragment = new BasicDetailsFragment();
                return basicDetailsFragment;
            case 1:
                cropListFragment = new CropListFragment();
                return cropListFragment;
            case 2:
                farmListFragment = new FarmListFragment();
                return farmListFragment;
            case 3:
                nurseryListFragment = new NurseryListFragment();
                return nurseryListFragment;
            case 4:
                treatmentListFragment = new TreatmentListFragment();
                return treatmentListFragment;
            case 5:
                transplantationListFragment = new TransplantationListFragment();
                return transplantationListFragment;
            case 6:
                irrigationListFragment = new IrrigationListFragment();
                return irrigationListFragment;
            case 7:
                fertilizationListFragment = new FertilizationListFragment();
                return fertilizationListFragment;
            case 8:
                plantProtectionListFragment = new PlantProtectionListFragment();
                return plantProtectionListFragment;
            case 9:
                harvestingListFragment = new HarvestingListFragment();
                return harvestingListFragment;
            case 10:
                purchaseListFragment = new PurchaseListFragment();
                return purchaseListFragment;
            case 11:
                machineryListFragment = new MachineryListFragment();
                return machineryListFragment;
            case 12:
                laborListFragment = new LaborListFragment();
                return laborListFragment;
            default:
                cocFragment = new CocFragment();
                return cocFragment;
        }
    }

    @Override
    public int getItemCount() {
        return PAGE_COUNT;
    }

}
