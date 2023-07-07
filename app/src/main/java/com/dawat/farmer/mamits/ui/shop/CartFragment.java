package com.dawat.farmer.mamits.ui.shop;

import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_CURRENT_DATE;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.adapter.CartAdapter;
import com.dawat.farmer.mamits.databinding.FragmentCartBinding;
import com.dawat.farmer.mamits.model.CartModel;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.ProgressLoading;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CartFragment extends Fragment implements CartAdapter.OnClickQuantityUpdateListener {

    private FragmentCartBinding binding;
    private ProgressLoading progressLoading;
    private SharedPreferences sharedPreferences;
    private String strToken = "";
    private CartAdapter cartAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressLoading = new ProgressLoading();
        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        String current_date = sharedPreferences.getString(PREF_KEY_CURRENT_DATE, "");
        binding.txtTime.setText(current_date);

        setUpClickListener();
        setUpCartList();
        return root;
    }

    private void setUpClickListener() {
        binding.btnCheckout.setOnClickListener(v -> {
            checkout();
        });
    }

    private void checkout() {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().checkout(strToken, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Navigation.findNavController(getActivity().findViewById(R.id.nav_host_fragment)).popBackStack(R.id.navigation_shop, false);
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

    private void setUpCartList() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerCart.setLayoutManager(manager);
        cartAdapter = new CartAdapter(getContext(), this);
        binding.recyclerCart.setAdapter(cartAdapter);
    }

    private void getCartList() {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().getCartList(strToken, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Type farmer = new TypeToken<List<CartModel>>() {
                    }.getType();

                    List<CartModel> list = new Gson().fromJson(jsonObject.get("data").getAsJsonArray().toString(), farmer);
                    cartAdapter.setList(list);

                    if (list.size() > 0) {
                        binding.emptyCart.setVisibility(View.GONE);
                        binding.recyclerCart.setVisibility(View.VISIBLE);
                        binding.llBottomView.setVisibility(View.VISIBLE);
                        float total = jsonObject.get("total").getAsFloat();
                        float delivery = jsonObject.get("delivery").getAsFloat();

                        binding.txtSubTotal.setText("₹ " + total);
                        binding.txtDelivery.setText("₹ " + delivery);
                        binding.txtTotal.setText("₹ " + (total + delivery));
                    } else {
                        binding.recyclerCart.setVisibility(View.GONE);
                        binding.llBottomView.setVisibility(View.GONE);
                        binding.emptyCart.setVisibility(View.VISIBLE);
                    }
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
        getCartList();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onUpdate(int position, String category_id, String product_id, int quantity) {
        addToCart(category_id, product_id, quantity);
    }

    @Override
    public void onDelete(String id) {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().deleteFromCart(strToken, id, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    getCartList();
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

    private void addToCart(String category_id, String product_id, int quantity) {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().addToCart(strToken, category_id, product_id, quantity, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    getCartList();
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

}