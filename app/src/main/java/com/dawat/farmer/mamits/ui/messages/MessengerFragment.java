package com.dawat.farmer.mamits.ui.messages;

import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_CURRENT_DATE;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_PROFILE_IMAGE;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_USER_ID;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_USER_NAME;
import static com.dawat.farmer.mamits.utils.AppConstant.SHARED_PREF_NAME;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.adapter.MessageListAdapter;
import com.dawat.farmer.mamits.databinding.FragmentMessengerBinding;
import com.dawat.farmer.mamits.model.MessageModel;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.CustomLinearLayoutManager;
import com.dawat.farmer.mamits.utils.ProgressLoading;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MessengerFragment extends Fragment {

    private FragmentMessengerBinding binding;
    private SharedPreferences sharedPreferences;
    private String strToken = "";
    private ProgressLoading progressLoading;
    private MessageListAdapter messageListAdapter;
    private String ticket_id, farmer_id, coordinator_id;
    private String current_user_id;
    private String type = "ticket";
    private Handler ha;
    private Runnable runnable;
    private int IMAGE_TYPE = 1;
    private File file = null;
    private boolean srollToBottom;
    private List<MessageModel> list;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMessengerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");
        String username = sharedPreferences.getString(PREF_USER_NAME, "");
        String profile_image = sharedPreferences.getString(PREF_PROFILE_IMAGE, "");
        String date = sharedPreferences.getString(PREF_KEY_CURRENT_DATE, "");
        current_user_id = sharedPreferences.getString(PREF_USER_ID, "");
        binding.txtUsername.setText(username);

        binding.txtTyping.setText(date);

        Glide.with(getContext()).load(profile_image).error(R.drawable.person_profile).into(binding.profileImage);
        progressLoading = new ProgressLoading();
        Bundle bundle = getArguments();

        if (bundle != null) {
            ticket_id = bundle.getString("ticket_id");
            farmer_id = bundle.getString("farmer_id");
            coordinator_id = bundle.getString("coordinator_id");
            if (coordinator_id != null) {
                type = "chat";
            }
        }
        ha = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                getMessageList();
                ha.postDelayed(this, 5000);
            }
        };
        clickListeners();
        setUpMessageList();
        return root;
    }

    private void clickListeners() {
        binding.btnSend.setOnClickListener(v -> {
            String message = binding.etMessage.getText().toString();
            if (message.isEmpty() || message.replace(" ", "").length() == 0) {
                Toast.makeText(getContext(), "Message can not be empty.", Toast.LENGTH_SHORT).show();
                return;
            }
            sendMessage(message);
        });
        binding.btnAttach.setOnClickListener(v -> {
            ImagePicker.Companion.with(this).crop()
                    .compress(1024)
                    .maxResultSize(1080, 1080).start();
            IMAGE_TYPE = 1;
        });
        binding.btnDiscard.setOnClickListener(v -> {
            discardImage();
        });
    }

    private void discardImage() {
        file = null;
        binding.txtFile.setText("");
        binding.txtFile.setVisibility(View.GONE);
        binding.btnDiscard.setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (IMAGE_TYPE == 1) {
                try {
                    file = new File(new URL(data.getDataString()).toURI());
                    binding.txtFile.setText(file.getName());
                    binding.txtFile.setVisibility(View.VISIBLE);
                    binding.btnDiscard.setVisibility(View.VISIBLE);
                } catch (URISyntaxException | MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void sendMessage(String message) {
        try {
            RequestBody ticket_id_body = null;
            MultipartBody.Part fileDoc = null;

            if (ticket_id != null) {
                ticket_id_body = RequestBody.create(MultipartBody.FORM, ticket_id);
            }
            RequestBody current_user_id_body = RequestBody.create(MultipartBody.FORM, current_user_id);
            RequestBody farmer_id_body = RequestBody.create(MultipartBody.FORM, coordinator_id != null ? coordinator_id : farmer_id);
            RequestBody message_body = RequestBody.create(MultipartBody.FORM, message);
            RequestBody type_body = RequestBody.create(MultipartBody.FORM, type);
            if (file != null)
                fileDoc = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));

/*
            new ApiHelper().sendMessage(strToken, ticket_id_body, current_user_id_body, farmer_id_body, message_body, fileDoc, type_body, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    boolean status = jsonObject.get("status").getAsBoolean();
                    if (status) {
                        binding.recyclerMessages.scrollToPosition(list.size() - 1);
                        binding.etMessage.setText("");
                        discardImage();
                    } else {
                        String message = jsonObject.get("message").getAsString();
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    }
                    getMessageList();
                }

                @Override
                public void onFailed(Throwable throwable) {
                    Log.e(AppConstant.LOG_KEY_ERROR, throwable.getMessage());
                    Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
*/
        } catch (Exception e) {
            Log.e(AppConstant.LOG_KEY_ERROR, e.getMessage());
        }
    }

    private void setUpMessageList() {
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerMessages.setLayoutManager(manager);
        binding.recyclerMessages.setItemAnimator(null);
        messageListAdapter = new MessageListAdapter(getContext(), current_user_id);
        binding.recyclerMessages.setAdapter(messageListAdapter);
//        ha.postDelayed(runnable, 5000);
    }

    private void getMessageList() {
        /*try {
            new ApiHelper().getMessagesList(strToken, coordinator_id != null ? coordinator_id : ticket_id, type, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    progressLoading.hideLoading();
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    Type message = new TypeToken<List<MessageModel>>() {
                    }.getType();

                    list = new Gson().fromJson(jsonObject.get("data").getAsJsonArray().toString(), message);
                    messageListAdapter.setList(list);
                    if (!srollToBottom) {
                        binding.recyclerMessages.scrollToPosition(list.size() - 1);
                        srollToBottom = true;
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    Log.e(AppConstant.LOG_KEY_ERROR, throwable.getMessage());
                    Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.e(AppConstant.LOG_KEY_ERROR, e.getMessage());
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (ha != null) {
            ha.removeCallbacks(runnable);
        }
        binding = null;
    }
}