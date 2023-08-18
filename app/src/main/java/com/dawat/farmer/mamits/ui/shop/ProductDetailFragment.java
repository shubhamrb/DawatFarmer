package com.dawat.farmer.mamits.ui.shop;

import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_CURRENT_DATE;
import static com.dawat.farmer.mamits.utils.AppConstant.SHARED_PREF_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Html;
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

import com.bumptech.glide.Glide;
import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.adapter.AnsQuesListAdapter;
import com.dawat.farmer.mamits.adapter.ProductsAdapter;
import com.dawat.farmer.mamits.databinding.FragmentProductDetailBinding;
import com.dawat.farmer.mamits.model.AnsQuesModel;
import com.dawat.farmer.mamits.model.ProductModel;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.ProgressLoading;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ProductDetailFragment extends Fragment implements ProductsAdapter.OnItemClickListener {

    private FragmentProductDetailBinding binding;
    private ProgressLoading progressLoading;
    private SharedPreferences sharedPreferences;
    private String strToken = "";
    private AnsQuesListAdapter ansQuesListAdapter;
    private ProductsAdapter productsAdapter;
    private ProductModel model;
    private String product_id;
    private int quantity = 1;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProductDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressLoading = new ProgressLoading();
        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        String current_date = sharedPreferences.getString(PREF_KEY_CURRENT_DATE, "");
        binding.txtTime.setText(current_date);

        Bundle bundle = getArguments();
        if (bundle != null) {
            product_id = bundle.getString("product_id");
        }
        setUpClickListener();
        setUpAnsQues();
        setUpForYou();

        setUpPlusMinusView();
        return root;
    }

    private void setUpPlusMinusView() {
        binding.txtQuantity.setText("" + quantity);
        if (quantity > 1) {
            binding.minCard.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.teal, null)));
        } else {
            binding.minCard.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.teal_dim, null)));
        }
    }

    private void setUpClickListener() {
        binding.btnAddToCart.setOnClickListener(v -> {
            addToCart();
        });
        binding.minCard.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                setUpPlusMinusView();
            }
        });
        binding.plusCard.setOnClickListener(v -> {
            quantity++;
            setUpPlusMinusView();
        });
    }

    private void addToCart() {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().addToCart(strToken, model.getCategory_id(), product_id, quantity, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Toast.makeText(getContext(), jsonObject.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(getActivity().findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_cart);
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

    private void setUpAnsQues() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recycleAnsQues.setLayoutManager(manager);
        ansQuesListAdapter = new AnsQuesListAdapter(getContext());
        binding.recycleAnsQues.setAdapter(ansQuesListAdapter);
    }

    private void setUpForYou() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recycleForYou.setLayoutManager(manager);
        productsAdapter = new ProductsAdapter(getContext(), 1, this);
        binding.recycleForYou.setAdapter(productsAdapter);
    }

    private void getProductDetail() {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().getProductDetail(strToken, product_id, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());

                    model = new Gson().fromJson(jsonObject.get("data").getAsJsonObject(), ProductModel.class);
                    setData();

                    Type faq = new TypeToken<List<AnsQuesModel>>() {
                    }.getType();
                    List<AnsQuesModel> list = new Gson().fromJson(jsonObject.get("faq").getAsJsonArray(), faq);
                    ansQuesListAdapter.setList(list);

                    Type product = new TypeToken<List<ProductModel>>() {
                    }.getType();
                    List<ProductModel> similarList = new Gson().fromJson(jsonObject.get("SimilarList").getAsJsonArray(), product);
                    productsAdapter.setList(similarList);

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
        if (model != null) {
            binding.txtName.setText(model.getName_hi());
            binding.txtPrice.setText("₹ " + model.getSelling_price());
            Glide.with(getContext()).load(model.getImage()).into(binding.image);
            binding.txtDes.setText(Html.fromHtml(model.getDescription_hi()));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getProductDetail();
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