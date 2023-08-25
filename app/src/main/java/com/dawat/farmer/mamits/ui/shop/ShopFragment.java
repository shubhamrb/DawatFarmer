package com.dawat.farmer.mamits.ui.shop;

import static com.dawat.farmer.mamits.utils.AppConstant.IS_LOGIN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_CURRENT_DATE;
import static com.dawat.farmer.mamits.utils.AppConstant.SHARED_PREF_NAME;

import android.content.Context;
import android.content.Intent;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.LoginActivity;
import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.adapter.ProductsAdapter;
import com.dawat.farmer.mamits.databinding.FragmentShopBinding;
import com.dawat.farmer.mamits.model.ProductModel;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.ProgressLoading;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.dawat.farmer.mamits.utils.SpacesItemDecoration;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ShopFragment extends Fragment implements ProductsAdapter.OnItemClickListener {

    private FragmentShopBinding binding;
    private ProgressLoading progressLoading;
    private SharedPreferences sharedPreferences;
    private String strToken = "";
    private ProductsAdapter productsAdapter;
    private SpacesItemDecoration spacesItemDecoration;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentShopBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (!sharedPreferences.getBoolean(IS_LOGIN, false)) {
            try {
                getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finishAffinity();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        progressLoading = new ProgressLoading();
        spacesItemDecoration = new SpacesItemDecoration(16);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        String current_date = sharedPreferences.getString(PREF_KEY_CURRENT_DATE, "");
        binding.txtTime.setText(current_date);

        setUpClickListener();
        setUpProductsList();
        return root;
    }

    private void setUpClickListener() {
        binding.btnMyOrders.setOnClickListener(v -> {
            Navigation.findNavController(getActivity().findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_orders);
        });
        binding.btnSearch.setOnClickListener(v -> {
        });
        binding.btnCart.setOnClickListener(v -> {
            Navigation.findNavController(getActivity().findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_cart);
        });
    }

    private void setUpProductsList() {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        binding.recyclerProducts.setLayoutManager(manager);
        binding.recyclerProducts.removeItemDecoration(spacesItemDecoration);
        productsAdapter = new ProductsAdapter(getContext(), 0, this);
        binding.recyclerProducts.addItemDecoration(spacesItemDecoration);
        binding.recyclerProducts.setAdapter(productsAdapter);
    }

    private void getProductList() {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().getProductList(strToken, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Type farmer = new TypeToken<List<ProductModel>>() {
                    }.getType();

                    List<ProductModel> list = new Gson().fromJson(jsonObject.get("data").getAsJsonArray().toString(), farmer);
                    productsAdapter.setList(list);
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
    public void onResume() {
        super.onResume();
        getProductList();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(String product_id) {
        Bundle bundle = new Bundle();
        bundle.putString("product_id", product_id);
        Navigation.findNavController(getActivity().findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_product_detail, bundle);
    }
}