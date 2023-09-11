package com.dawat.farmer.mamits.ui.farmerProfile.farmDetails;

import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_NAME;
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
import com.dawat.farmer.mamits.adapter.FieldAdapter;
import com.dawat.farmer.mamits.databinding.FragmentFarmFieldsBinding;
import com.dawat.farmer.mamits.model.FarmModel;
import com.dawat.farmer.mamits.model.OtherDetailsModel;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.CustomLinearLayoutManager;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class FarmFieldsListFragment extends Fragment implements FieldAdapter.FieldClickListener {

    private FragmentFarmFieldsBinding binding;
    private FieldAdapter fieldAdapter;
    private SharedPreferences sharedPreferences;
    private String strToken = "", user_name = "";
    private String farm_id, farm_code;

    public FarmFieldsListFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFarmFieldsBinding.inflate(inflater, container, false);
        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        user_name = sharedPreferences.getString(PREF_NAME, "");
        View root = binding.getRoot();
        binding.txtSubHeading.setText(user_name + " फार्म विवरण");
        Bundle bundle = getArguments();
        if (bundle != null) {
            farm_id = bundle.getString("id");
            farm_code = bundle.getString("code");
            binding.txtFarmCodeHeading.setText("Farm Code " + farm_code);
            binding.txtFarmName.setText("Farm " + farm_code);
        }
        setUpFarmsAdapter();
        return root;
    }

    private void setUpFarmsAdapter() {
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerField.setLayoutManager(manager);
        binding.recyclerField.setItemAnimator(null);
        fieldAdapter = new FieldAdapter(getContext(), this);
        binding.recyclerField.setAdapter(fieldAdapter);
        getFarmFieldList();
    }

    private void getFarmFieldList() {
        try {
            new ApiHelper().getFarmFieldList(strToken, farm_id, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Type farms = new TypeToken<List<FarmModel>>() {
                    }.getType();

                    OtherDetailsModel detailsModel = new Gson().fromJson(jsonObject.get("otherdata").getAsJsonObject(), OtherDetailsModel.class);
                    binding.txtTotalFieldCount.setText(detailsModel.getTotalfield());
                    binding.txtAcreUnderScheme.setText(detailsModel.getPrctotal() + " Acres under Scheme");
                    binding.txtTotalSize.setText(detailsModel.getTotal() + " Acres");

                    List<FarmModel> list = new Gson().fromJson(jsonObject.get("data").getAsJsonArray().toString(), farms);
                    fieldAdapter.setList(list);

                    binding.txtTotalFieldCount.setText(list.size() + "");
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(FarmModel model) {
        Bundle bundle = new Bundle();
        bundle.putString("farm_id", farm_id);
        bundle.putString("field_id", model.getId());
        bundle.putString("farm_code", farm_code);
        bundle.putString("field_code", model.getCode());
        if (model.getStatus().equals("1")) {
            Navigation.findNavController(((MainActivity) getActivity()).findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_field_details, bundle);
        }
    }
}