package com.dawat.farmer.mamits.ui.news;


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
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.MainActivity;
import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.adapter.NewsListAdapter;
import com.dawat.farmer.mamits.databinding.FragmentNewsBinding;
import com.dawat.farmer.mamits.model.NewsModel;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.CustomLinearLayoutManager;
import com.dawat.farmer.mamits.utils.ProgressLoading;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class NewsFragment extends Fragment implements NewsListAdapter.OnClickListener {

    private FragmentNewsBinding binding;
    private SharedPreferences sharedPreferences;
    private String strToken = "";
    private ProgressLoading progressLoading;
    private NewsListAdapter newsListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");

        String current_date = sharedPreferences.getString(PREF_KEY_CURRENT_DATE, "");
        binding.txtTime.setText(current_date);

        progressLoading = new ProgressLoading();
        Bundle bundle = getArguments();
        if (bundle != null) {
//            String sub_category_id = bundle.getString("sub_category_id");
        }
        clickListeners();
        setUpNewsList();
        return root;
    }

    private void clickListeners() {

    }

    private void setUpNewsList() {
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerNews.setLayoutManager(manager);
        binding.recyclerNews.setItemAnimator(null);
        newsListAdapter = new NewsListAdapter(getContext(), this);
        binding.recyclerNews.setAdapter(newsListAdapter);
    }

    private void getNewsList() {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().getNewsList(strToken, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Type news = new TypeToken<List<NewsModel>>() {
                    }.getType();

                    List<NewsModel> list = new Gson().fromJson(jsonObject.get("data").getAsJsonArray().toString(), news);
                    newsListAdapter.setList(list);
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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        getNewsList();
    }

    @Override
    public void onNewsClick(NewsModel news) {
        Bundle bundle = new Bundle();
        bundle.putString("news_id", news.getId());
        bundle.putString("title", news.getTitle_hi());

        Navigation.findNavController(((MainActivity) getContext())
                .findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_news_detail, bundle);
    }
}