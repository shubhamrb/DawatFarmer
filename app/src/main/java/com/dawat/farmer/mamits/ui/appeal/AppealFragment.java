package com.dawat.farmer.mamits.ui.appeal;

import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_CURRENT_DATE;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_USER_ID;
import static com.dawat.farmer.mamits.utils.AppConstant.SHARED_PREF_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.adapter.AppealListAdapter;
import com.dawat.farmer.mamits.adapter.TicketListAdapter;
import com.dawat.farmer.mamits.customDialogs.CreateAppealOptionBottomSheet;
import com.dawat.farmer.mamits.customDialogs.CreateAppealReasonBottomSheet;
import com.dawat.farmer.mamits.customDialogs.CreateTicketBottomSheet;
import com.dawat.farmer.mamits.databinding.FragmentAppealBinding;
import com.dawat.farmer.mamits.model.AppealModel;
import com.dawat.farmer.mamits.model.AppealTicketData;
import com.dawat.farmer.mamits.model.TicketModel;
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

public class AppealFragment extends Fragment implements CreateAppealOptionBottomSheet.OnClickCreateButtonListener, CreateAppealReasonBottomSheet.OnClickListener, CreateTicketBottomSheet.OnClickListener {

    private FragmentAppealBinding binding;
    private AppealListAdapter appealListAdapter;
    private TicketListAdapter ticketListAdapter;
    private ProgressLoading progressLoading;
    private SharedPreferences sharedPreferences;
    private String strToken = "", current_user_id;
    private int TAB = 0;
    private AppealTicketData appealTickedData;
    private String status_filter = "", type_filter = "";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAppealBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressLoading = new ProgressLoading();

        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        current_user_id = sharedPreferences.getString(PREF_USER_ID, "");
        String current_date = sharedPreferences.getString(PREF_KEY_CURRENT_DATE, "");
        binding.txtTime.setText(current_date);

        Bundle bundle = getArguments();
        if (bundle != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                appealTickedData = bundle.getSerializable("all_appeal", AppealTicketData.class);
            } else {
                appealTickedData = (AppealTicketData) bundle.getSerializable("all_appeal");
            }

            binding.txtTotalCount.setText(appealTickedData.getTotal() + "");
            binding.txtTotalAppeal.setText(appealTickedData.getAppeal() + " अपीलें");
            binding.txtTotalTicket.setText(appealTickedData.getTicket() + " टिकिटें");
        }

        setUpClickListener();
        return root;
    }

    private void setUpClickListener() {
        binding.toggleAppeal.setOnClickListener(v -> {
            TAB = 0;
            binding.txtListHeading.setText("अपीलें");
            binding.listIcon.setImageResource(R.drawable.report_icon);
            binding.toggleAppeal.setCardBackgroundColor(getContext().getResources().getColor(R.color.primary_color, null));
            binding.toggleTicket.setCardBackgroundColor(getContext().getResources().getColor(R.color.teal_dim, null));
            binding.txtToggleAppeal.setTextColor(getContext().getResources().getColor(R.color.white, null));
            binding.txtToggleTicket.setTextColor(getContext().getResources().getColor(R.color.primary_color, null));
            getAppeals(status_filter, type_filter);
        });
        binding.toggleTicket.setOnClickListener(v -> {
            TAB = 1;
            binding.txtListHeading.setText("टिकिटें");
            binding.listIcon.setImageResource(R.drawable.headphone_icon);
            binding.toggleTicket.setCardBackgroundColor(getContext().getResources().getColor(R.color.primary_color, null));
            binding.toggleAppeal.setCardBackgroundColor(getContext().getResources().getColor(R.color.teal_dim, null));
            binding.txtToggleTicket.setTextColor(getContext().getResources().getColor(R.color.white, null));
            binding.txtToggleAppeal.setTextColor(getContext().getResources().getColor(R.color.primary_color, null));

            getTickets(status_filter, type_filter);
        });

        binding.btnCreate.setOnClickListener(v -> {
            new CreateAppealOptionBottomSheet(getContext(), this).openOption();
        });
    }

    private void setUpAppealList(List<AppealModel> list) {
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerAppealTicketList.setLayoutManager(manager);
        binding.recyclerAppealTicketList.setItemAnimator(null);
        appealListAdapter = new AppealListAdapter(getContext(), 0);
        binding.recyclerAppealTicketList.setAdapter(appealListAdapter);
        appealListAdapter.setList(list);
    }

    private void setUpTicketList(List<TicketModel> list) {
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerAppealTicketList.setLayoutManager(manager);
        binding.recyclerAppealTicketList.setItemAnimator(null);

        ticketListAdapter = new TicketListAdapter(getContext(), 1);
        binding.recyclerAppealTicketList.setAdapter(ticketListAdapter);
        ticketListAdapter.setList(list);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (TAB == 0) {
            getAppeals(status_filter, type_filter);
        } else {
            getTickets(status_filter, type_filter);
        }
    }

    private void getAppeals(String status, String type) {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().getAppealList(strToken, status, type, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Type appeals = new TypeToken<List<AppealModel>>() {
                    }.getType();

                    List<AppealModel> list = new Gson().fromJson(jsonObject.get("data").getAsJsonArray().toString(), appeals);
                    setUpAppealList(list);

                    int total = jsonObject.get("total").getAsInt();
                    int ticket = jsonObject.get("ticket").getAsInt();
                    int appeal = jsonObject.get("appeal").getAsInt();

                    binding.txtTotalCount.setText("" + total);
                    binding.txtTotalAppeal.setText(appeal + " अपीलें");
                    binding.txtTotalTicket.setText(ticket + " टिकिटें");
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

    private void getTickets(String status, String type) {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().getTicketList(strToken, status, type, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Type tickets = new TypeToken<List<TicketModel>>() {
                    }.getType();

                    List<TicketModel> list = new Gson().fromJson(jsonObject.get("data").getAsJsonArray().toString(), tickets);
                    setUpTicketList(list);

                    int total = jsonObject.get("total").getAsInt();
                    int ticket = jsonObject.get("ticket").getAsInt();
                    int appeal = jsonObject.get("appeal").getAsInt();

                    binding.txtTotalCount.setText("" + total);
                    binding.txtTotalAppeal.setText(appeal + " अपीलें");
                    binding.txtTotalTicket.setText(ticket + " टिकिटें");
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
    public void onCreateClick(String type) {
        if (current_user_id != null) {
            if (type.equalsIgnoreCase("appeal")) {
                new CreateAppealReasonBottomSheet(getContext(), this).openOption(current_user_id);
            } else {
                new CreateTicketBottomSheet(getContext(), this).openOption(current_user_id);
            }
        }
    }

    @Override
    public void onAppealCreate(String farmer_id, String reason) {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().createAppeal(strToken, farmer_id, reason, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    String message = jsonObject.get("message").getAsString();
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    getAppeals(status_filter, type_filter);
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
    public void onTicketCreate(String farmer_id, String title, String description) {
        progressLoading.showLoading(getContext());
        try {
            new ApiHelper().createTicket(strToken, farmer_id, title, description, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    String message = jsonObject.get("message").getAsString();
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    getTickets(status_filter, type_filter);
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