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
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.adapter.OrderCartAdapter;
import com.dawat.farmer.mamits.databinding.FragmentOrdersDetailBinding;
import com.dawat.farmer.mamits.model.OrderDetailModel;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.ProgressLoading;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class OrdersDetailFragment extends Fragment {

    private FragmentOrdersDetailBinding binding;
    private ProgressLoading progressLoading;
    private SharedPreferences sharedPreferences;
    private String strToken = "";
    private OrderCartAdapter orderCartAdapter;
    private OrderDetailModel orderDetailModel;
    private String order_id;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentOrdersDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressLoading = new ProgressLoading();
        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        String current_date = sharedPreferences.getString(PREF_KEY_CURRENT_DATE, "");
        binding.txtTime.setText(current_date);

        Bundle bundle = getArguments();
        if (bundle != null) {
            order_id = bundle.getString("order_id");
        }
        setUpClickListener();
        setUpProductList();
        return root;
    }

    private void setUpClickListener() {
        binding.btnTrack.setOnClickListener(v -> {
            if (orderDetailModel != null) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("order", orderDetailModel);
                Navigation.findNavController(getActivity().findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_track_orders, bundle);
            }
        });
        binding.btnCancel.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Cancel order");
            builder.setMessage("Are you sure you want to cancel?");

            builder.setPositiveButton("Yes", (dialog, which) -> {
                dialog.dismiss();
                cancelOrder();
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> {
                dialog.dismiss();
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

    private void cancelOrder() {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().cancelOrder(strToken, order_id, "3", new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    getOrderDetail();
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

    private void setUpProductList() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerOrdersCart.setLayoutManager(manager);
        orderCartAdapter = new OrderCartAdapter(getContext());
        binding.recyclerOrdersCart.setAdapter(orderCartAdapter);
    }

    private void getOrderDetail() {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().getOrderDetail(strToken, order_id, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    orderDetailModel = new Gson().fromJson(jsonObject, OrderDetailModel.class);
                    setData();
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

    private void setData() {
        if (orderDetailModel != null) {
            binding.labelHello.setText("ORDID" + orderDetailModel.getOrder().getOrderId());
            binding.txtOrderId.setText("ORDID" + orderDetailModel.getOrder().getOrderId());
            binding.txtCount.setText(orderDetailModel.getData().size() + " Items");
            binding.btnCancel.setVisibility(View.VISIBLE);
            switch (orderDetailModel.getOrder().getStatus()) {
                case "1":
                    binding.txtOrderTime.setText("Order Placed");
                    break;
                case "2":
                    binding.txtOrderTime.setText("Order Accepted");
                    break;
                case "3":
                    binding.btnCancel.setVisibility(View.GONE);
                    binding.txtOrderTime.setText("Order Cancelled");
                    break;
                case "4":
                    binding.btnCancel.setVisibility(View.GONE);
                    binding.txtOrderTime.setText("Order Rejected");
                    break;
                case "5":
                    binding.txtOrderTime.setText("Order on hold");
                    break;
                case "6":
                    binding.btnCancel.setVisibility(View.GONE);
                    binding.txtOrderTime.setText("Order Delivered");
                    break;
            }
            orderCartAdapter.setList(orderDetailModel.getData());

            float total = orderDetailModel.getTotal();
            float delivery = orderDetailModel.getDelivery();

            binding.txtSubTotal.setText("₹ " + total);
            binding.txtDelivery.setText("₹ " + delivery);
            binding.txtTotal.setText("₹ " + (total + delivery));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getOrderDetail();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}