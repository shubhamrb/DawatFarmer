package com.dawat.farmer.mamits.ui.shop;

import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_CURRENT_DATE;
import static com.dawat.farmer.mamits.utils.AppConstant.SHARED_PREF_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.databinding.FragmentTrackOrdersBinding;
import com.dawat.farmer.mamits.model.OrderDetailModel;
import com.dawat.farmer.mamits.utils.ProgressLoading;

public class TrackOrdersFragment extends Fragment {

    private FragmentTrackOrdersBinding binding;
    private ProgressLoading progressLoading;
    private SharedPreferences sharedPreferences;
    private String strToken = "";
    private OrderDetailModel order;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTrackOrdersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressLoading = new ProgressLoading();
        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        String current_date = sharedPreferences.getString(PREF_KEY_CURRENT_DATE, "");
        binding.txtTime.setText(current_date);

        Bundle bundle = getArguments();
        if (bundle != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                order = bundle.getSerializable("order", OrderDetailModel.class);
            } else {
                order = (OrderDetailModel) bundle.getSerializable("order");
            }
            binding.labelHello.setText("ORDID" + order.getOrder().getOrderId());
        }
        setUpClickListener();
        setData();
        return root;
    }

    private void setUpClickListener() {
        binding.btnCancel.setOnClickListener(v -> {

        });
    }

    private void setData() {
        if (order != null) {
            binding.txtCount.setText(order.getData().size() + " Items");
            binding.btnCancel.setVisibility(View.VISIBLE);
            switch (order.getOrder().getStatus()) {
                case "1":
                    binding.txtOrderTime.setText("Order Placed");
                    break;
                case "2":
                    binding.txtAccepted.setTextColor(getResources().getColor(R.color.black, null));
                    binding.cardAccepted.setCardBackgroundColor(getResources().getColor(R.color.teal, null));
                    binding.viewAccepted.setBackgroundColor(getResources().getColor(R.color.teal, null));
                    binding.txtOrderTime.setText("Order Accepted");
                    break;
                case "3":
                    binding.btnCancel.setVisibility(View.GONE);
                    binding.llAcceptedStatus.setVisibility(View.GONE);
                    binding.llCancelledStatus.setVisibility(View.VISIBLE);
                    binding.txtCancelled.setText("Order Cancelled");
                    binding.txtOrderTime.setText("Order Cancelled");
                    break;
                case "4":
                    binding.btnCancel.setVisibility(View.GONE);
                    binding.llAcceptedStatus.setVisibility(View.GONE);
                    binding.llCancelledStatus.setVisibility(View.VISIBLE);
                    binding.txtCancelled.setText("Order Rejected");
                    binding.txtOrderTime.setText("Order Rejected");
                    break;
                case "5":
                    binding.llAcceptedStatus.setVisibility(View.GONE);
                    binding.llCancelledStatus.setVisibility(View.VISIBLE);
                    binding.txtCancelled.setText("Order on hold");
                    binding.txtOrderTime.setText("Order on hold");
                    break;
                case "6":
                    binding.btnCancel.setVisibility(View.GONE);
                    binding.txtAccepted.setTextColor(getResources().getColor(R.color.black, null));
                    binding.cardAccepted.setCardBackgroundColor(getResources().getColor(R.color.teal, null));
                    binding.viewAccepted.setBackgroundColor(getResources().getColor(R.color.teal, null));

                    binding.txtDelivery.setTextColor(getResources().getColor(R.color.black, null));
                    binding.cardDelivery.setCardBackgroundColor(getResources().getColor(R.color.teal, null));
                    binding.txtOrderTime.setText("Order Delivered");
                    break;

            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}