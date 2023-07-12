package com.dawat.farmer.mamits.ui.messages;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_CURRENT_DATE;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_PROFILE_IMAGE;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_USER_ID;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_USER_NAME;
import static com.dawat.farmer.mamits.utils.AppConstant.SHARED_PREF_NAME;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
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
import com.dawat.farmer.mamits.utils.FileDownloader;
import com.dawat.farmer.mamits.utils.ProgressLoading;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MessengerFragment extends Fragment implements MessageListAdapter.OnDownloadListener {

    private FragmentMessengerBinding binding;
    private SharedPreferences sharedPreferences;
    private String strToken = "";
    private ProgressLoading progressLoading;
    private MessageListAdapter messageListAdapter;
    private String ticket_id, farmer_id;
    private String current_user_id;
    private String type = "ticket";
    private Handler ha;
    private Runnable runnable;
    private int IMAGE_TYPE = 1;
    private File file = null;
    private boolean srollToBottom;
    private List<MessageModel> list;
    private boolean isAttachmentCardVisible;
    private final int REQUEST_CODE_VIDEO = 100;
    private final int REQUEST_CODE_AUDIO = 101;
    private final int REQUEST_CODE_DOCS = 103;
    private ActivityResultLauncher<String[]> somePermissionResultLauncher;

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

        somePermissionResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), (Map<String, Boolean> isGranted) -> {
            boolean granted = true;
            for (Map.Entry<String, Boolean> x : isGranted.entrySet()) {
                if (!x.getValue()) granted = false;
            }
            if (granted) {
                startActivity(new Intent(getContext(), VideoRecorderActivity.class));
            } else {
                Toast.makeText(getContext(), "Permission Denied.", Toast.LENGTH_SHORT).show();
            }

        });

        return root;
    }

    public boolean checkPermissionCamera() {
        int result = ContextCompat.checkSelfPermission(getContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getContext(),
                CAMERA);
        int result2 = ContextCompat.checkSelfPermission(getContext(), RECORD_AUDIO);

        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED &&
                result2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissionCamera() {
        somePermissionResultLauncher.launch(new
                String[]{WRITE_EXTERNAL_STORAGE, CAMERA, RECORD_AUDIO});
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
            if (isAttachmentCardVisible) {
                binding.attachmentCard.setVisibility(View.GONE);
            } else {
                binding.attachmentCard.setVisibility(View.VISIBLE);
            }
            isAttachmentCardVisible = !isAttachmentCardVisible;
        });

        binding.btnMedia.setOnClickListener(v -> {
            binding.btnAttach.performClick();
            ImagePicker.Companion.with(this).crop().compress(1024).maxResultSize(1080, 1080).start();
            IMAGE_TYPE = 1;
        });
        binding.btnVideo.setOnClickListener(v -> {
            binding.btnAttach.performClick();
            showChooser();
        });
        binding.btnAudio.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("audio/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, REQUEST_CODE_AUDIO);
        });
        binding.btnDocs.setOnClickListener(v -> {
            binding.btnAttach.performClick();

            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);

            String[] mimeTypes = {
                    "application/pdf",
                    "application/vnd.ms-powerpoint",
                    "application/vnd.openxmlformats-officedocument.presentationml.presentation",
                    "application/msword",
                    "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
            };
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            startActivityForResult(intent, REQUEST_CODE_DOCS);
        });

        binding.btnDiscard.setOnClickListener(v -> {
            discardImage();
        });
    }

    private void showChooser() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.chooser_dialog, null);

        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle("Choose")
                .setView(customView)
                .setOnCancelListener(DialogInterface::dismiss)
                .setNegativeButton("Cancel", (dialog1, which) -> {
                    dialog1.dismiss();
                })
                .show();

        customView.findViewById(R.id.lytCameraPick).setOnClickListener(v -> {
            if (checkPermissionCamera()) {
                Intent intent = new Intent(getContext(), VideoRecorderActivity.class);
                intent.putExtra("ticket_id", ticket_id);
                intent.putExtra("current_user_id", current_user_id);
                intent.putExtra("farmer_id", farmer_id);
                intent.putExtra("type", type);
                startActivity(intent);
            } else {
                requestPermissionCamera();
            }
            dialog.dismiss();
        });

        customView.findViewById(R.id.lytGalleryPick).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("video/*"); // Set the MIME type to filter video files
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, REQUEST_CODE_VIDEO);
            dialog.dismiss();
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
            if (requestCode == REQUEST_CODE_VIDEO) {
                Uri selectedVideoUri = data.getData();
                // Handle the selected video file
                try {
                    file = uriToFile(selectedVideoUri);
                    if (file != null) {
                        binding.txtFile.setText(file.getName());
                        binding.txtFile.setVisibility(View.VISIBLE);
                        binding.btnDiscard.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_CODE_AUDIO) {
                Uri selectedAudioUri = data.getData();
                // Handle the selected audio file
                try {
                    file = uriToFile(selectedAudioUri);
                    if (file != null) {
                        binding.txtFile.setText(file.getName());
                        binding.txtFile.setVisibility(View.VISIBLE);
                        binding.btnDiscard.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_CODE_DOCS) {
                Uri selectedAudioUri = data.getData();
                // Handle the selected audio file
                try {
                    file = uriToFile(selectedAudioUri);
                    if (file != null) {
                        binding.txtFile.setText(file.getName());
                        binding.txtFile.setVisibility(View.VISIBLE);
                        binding.btnDiscard.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (IMAGE_TYPE == 1) {
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

    public File uriToFile(Uri uri) {
        File file = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            String fileName = getFileNameFromUri(getContext(), uri);
            if (fileName != null) {
                file = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), fileName);
                inputStream = getContext().getContentResolver().openInputStream(uri);
                outputStream = new FileOutputStream(file);
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            file = null; // Set file to null in case of an exception
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }

    private String getFileNameFromUri(Context context, Uri uri) {
        String fileName = null;
        Cursor cursor = null;

        try {
            String[] projection = {MediaStore.MediaColumns.DISPLAY_NAME};
            cursor = context.getContentResolver().query(uri, projection, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);
                fileName = cursor.getString(columnIndex);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return fileName;
    }

    private void sendMessage(String message) {
        try {
            RequestBody ticket_id_body = null;
            MultipartBody.Part fileDoc = null;

            if (ticket_id != null) {
                ticket_id_body = RequestBody.create(MultipartBody.FORM, ticket_id);
            }
            RequestBody current_user_id_body = RequestBody.create(MultipartBody.FORM, current_user_id);
            RequestBody farmer_id_body = RequestBody.create(MultipartBody.FORM,farmer_id);
            RequestBody message_body = RequestBody.create(MultipartBody.FORM, message);
            RequestBody type_body = RequestBody.create(MultipartBody.FORM, type);
            if (file != null)
                fileDoc = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));

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
                    srollToBottom = false;
                    getMessageList();
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

    private void setUpMessageList() {
        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerMessages.setLayoutManager(manager);
        binding.recyclerMessages.setItemAnimator(null);
        messageListAdapter = new MessageListAdapter(getContext(), current_user_id, this);
        binding.recyclerMessages.setAdapter(messageListAdapter);
        ha.postDelayed(runnable, 5000);
    }

    private void getMessageList() {
        try {
            new ApiHelper().getMessagesList(strToken, ticket_id, type, new ResponseListener() {
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
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        srollToBottom = false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (ha != null) {
            ha.removeCallbacks(runnable);
        }
        binding = null;
    }

    @Override
    public void onDownload(String url, String file_name) {
        Toast.makeText(getContext(), "Downloading...", Toast.LENGTH_SHORT).show();
        FileDownloader.downloadFile(getContext(), url, file_name);
    }
}