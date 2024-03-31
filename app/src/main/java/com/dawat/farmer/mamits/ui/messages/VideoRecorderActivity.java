package com.dawat.farmer.mamits.ui.messages;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
import static com.dawat.farmer.mamits.utils.AppConstant.SHARED_PREF_NAME;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.video.MediaStoreOutputOptions;
import androidx.camera.video.Quality;
import androidx.camera.video.QualitySelector;
import androidx.camera.video.Recorder;
import androidx.camera.video.Recording;
import androidx.camera.video.VideoCapture;
import androidx.camera.video.VideoRecordEvent;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.android.gms.common.util.IOUtils;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import io.github.krtkush.lineartimer.LinearTimer;
import io.github.krtkush.lineartimer.LinearTimerStates;
import io.github.krtkush.lineartimer.LinearTimerView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class VideoRecorderActivity extends AppCompatActivity implements LinearTimer.TimerListener {
    private PreviewView previewView;
    public static final int RequestPermissionCode = 1;
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    boolean isRecording = false;
    File file = null;
    private ImageView camera_flash, camera_rotate, picture;
    private LinearTimer linearTimer;
    private TextView time;
    int time_count;
    private SharedPreferences sharedPreferences;
    private String strToken = "";
    private String ticket_id, current_user_id, farmer_id, type;
    private Camera camera;
    Recording recording = null;
    VideoCapture<Recorder> videoCapture = null;
    int cameraFacing = CameraSelector.LENS_FACING_BACK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_recorder);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        strToken = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "");

        Intent intent = getIntent();
        ticket_id = intent.getStringExtra("ticket_id");
        current_user_id = intent.getStringExtra("current_user_id");
        farmer_id = intent.getStringExtra("farmer_id");
        type = intent.getStringExtra("type");

        previewView = findViewById(R.id.previewView);
        LinearTimerView linearTimerView = findViewById(R.id.linearTimer);
        time = findViewById(R.id.time);
        picture = findViewById(R.id.picture);
        camera_flash = (ImageView) findViewById(R.id.camera_flash);
        camera_rotate = (ImageView) findViewById(R.id.camera_rotate);

        long duration = 30 * 1000;

        linearTimer = new LinearTimer.Builder()
                .linearTimerView(linearTimerView)
                .duration(duration)
                .timerListener(this)
                .getCountUpdate(LinearTimer.COUNT_DOWN_TIMER, 1000)
                .build();

        camera_rotate.setOnClickListener(v -> rotateCamera());
        camera_flash.setOnClickListener(v -> toggleFlash(camera));
        picture.setOnClickListener(v -> {
            if (checkPermission()) {
                if (!isRecording) {
                    captureVideo();

                    camera_flash.setVisibility(View.GONE);
                    camera_rotate.setVisibility(View.GONE);
                    try {
                        linearTimer.startTimer();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    if (linearTimer.getState() == LinearTimerStates.ACTIVE) {
                        if (time_count <= 44) {
                            recording.stop();
                            camera_flash.setVisibility(View.VISIBLE);
                            camera_rotate.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(this, "Minimum length should be 15sec.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            } else {
                requestPermission();
            }
        });

        if (!checkPermission()) {
            requestPermission();
        } else {
            startCamera(cameraFacing);
        }
    }

    public void startCamera(int cameraFacing) {
        ListenableFuture<ProcessCameraProvider> processCameraProvider = ProcessCameraProvider.getInstance(VideoRecorderActivity.this);

        processCameraProvider.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = processCameraProvider.get();
                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(previewView.getSurfaceProvider());

                Recorder recorder = new Recorder.Builder()
                        .setQualitySelector(QualitySelector.from(Quality.HIGHEST))
                        .build();
                videoCapture = VideoCapture.withOutput(recorder);

                cameraProvider.unbindAll();

                CameraSelector cameraSelector = new CameraSelector.Builder()
                        .requireLensFacing(cameraFacing).build();

                camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview, videoCapture);

            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(VideoRecorderActivity.this));
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new
                String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, RequestPermissionCode);
    }


    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                CAMERA);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera(cameraFacing);
            } else {
                Toast.makeText(this, "Camera permission required", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void captureVideo() {
        Recording recording1 = recording;
        if (recording1 != null) {
            recording1.stop();
            recording = null;
            return;
        }
        String name = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.getDefault()).format(System.currentTimeMillis());
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4");
        contentValues.put(MediaStore.Video.Media.RELATIVE_PATH, "Movies/CameraX-Video");

        MediaStoreOutputOptions options = new MediaStoreOutputOptions.Builder(getContentResolver(), MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
                .setContentValues(contentValues).build();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        recording = videoCapture.getOutput().prepareRecording(VideoRecorderActivity.this, options).withAudioEnabled().start(ContextCompat.getMainExecutor(VideoRecorderActivity.this), videoRecordEvent -> {
            if (videoRecordEvent instanceof VideoRecordEvent.Start) {
                isRecording = true;
                Log.w("path==", "start");
//                capture.setEnabled(true);
            } else if (videoRecordEvent instanceof VideoRecordEvent.Finalize) {
                isRecording = false;
                if (!((VideoRecordEvent.Finalize) videoRecordEvent).hasError()) {
                    Uri fileUri = ((VideoRecordEvent.Finalize) videoRecordEvent).getOutputResults().getOutputUri();
                    String msg = "Video capture succeeded: " + ((VideoRecordEvent.Finalize) videoRecordEvent).getOutputResults().getOutputUri();
                    Log.w("path==", msg);
//                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                    file = getFileFromURI(VideoRecorderActivity.this, fileUri);
                    if (linearTimer.getState() == LinearTimerStates.ACTIVE) {
                        linearTimer.pauseTimer();
                    }
                    Log.w("filea", file.getAbsolutePath());
                    sendMessage();
                    finish();

                    /*Intent i = new Intent(this, PlayerActivity.class);
                    i.putExtra("filepath", file.getAbsolutePath());
                    startActivity(i);
                    finish();*/
                } else {
                    recording.close();
                    recording = null;
                    String msg = "Error: " + ((VideoRecordEvent.Finalize) videoRecordEvent).getError();
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public File getFileFromURI(Context context, Uri contentUri) {
        //copy file and send new file path
        String fileName = getFileName(contentUri);
        if (!TextUtils.isEmpty(fileName)) {
            File copyFile = new File(context.getExternalFilesDir(null).getAbsolutePath() + File.separator + fileName);
            copy(context, contentUri, copyFile);
            return copyFile;
        }
        return null;
    }

    public String getFileName(Uri uri) {
        if (uri == null) return null;
        String fileName = null;
        String path = uri.getPath();
        int cut = path.lastIndexOf('/');
        if (cut != -1) {
            fileName = path.substring(cut + 1);
        }
        return fileName;
    }

    public void copy(Context context, Uri srcUri, File dstFile) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(srcUri);
            if (inputStream == null) return;
            OutputStream outputStream = new FileOutputStream(dstFile);
            IOUtils.copyStream(inputStream, outputStream);
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toggleFlash(Camera camera) {
        if (camera != null && camera.getCameraInfo().hasFlashUnit()) {
            camera.getCameraControl().enableTorch(camera.getCameraInfo().getTorchState().getValue() == 0);
        } else {
            runOnUiThread(() -> Toast.makeText(VideoRecorderActivity.this, "Flash is not available currently", Toast.LENGTH_SHORT).show());
        }
    }

    private String getVideoFilePath(Context context) {
        final File dir = context.getExternalFilesDir(null);
        return (dir == null ? "" : (dir.getAbsolutePath() + "/"))
                + System.currentTimeMillis() + ".mp4";
    }

    public void rotateCamera() {
        if (cameraFacing == CameraSelector.LENS_FACING_BACK) {
            cameraFacing = CameraSelector.LENS_FACING_FRONT;
        } else {
            cameraFacing = CameraSelector.LENS_FACING_BACK;
        }
        startCamera(cameraFacing);
    }

    /*private void stopRecording() {
        isRecording = false;
        cameraView.stopVideo();
        if (linearTimer.getState() == LinearTimerStates.ACTIVE) {
            linearTimer.pauseTimer();
        }
        Log.w("filea", "" + file.getAbsolutePath());

        sendMessage();
        finish();
    }*/

    private void sendMessage() {
        try {
            RequestBody ticket_id_body = null;
            MultipartBody.Part fileDoc = null;

            if (ticket_id != null) {
                ticket_id_body = RequestBody.create(MultipartBody.FORM, ticket_id);
            }
            RequestBody current_user_id_body = RequestBody.create(MultipartBody.FORM, current_user_id);
            RequestBody farmer_id_body = RequestBody.create(MultipartBody.FORM, farmer_id);
            RequestBody message_body = RequestBody.create(MultipartBody.FORM, "Video message recording");
            RequestBody type_body = RequestBody.create(MultipartBody.FORM, type);
            if (file != null)
                fileDoc = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));

            new ApiHelper().sendMessage(strToken, ticket_id_body, current_user_id_body, farmer_id_body, message_body, fileDoc, type_body, new ResponseListener() {
                @Override
                public void onSuccess(JsonObject jsonObject) {
                    Log.e(AppConstant.LOG_KEY_RESPONSE, jsonObject.toString());
                    boolean status = jsonObject.get("status").getAsBoolean();
                    if (status) {
                        finish();
                    } else {
                        String message = jsonObject.get("message").getAsString();
                        Toast.makeText(VideoRecorderActivity.this, message, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    Log.e(AppConstant.LOG_KEY_ERROR, throwable.getMessage());
                    Toast.makeText(VideoRecorderActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
                    finish();
                }
            });
        } catch (Exception e) {
            Log.e(AppConstant.LOG_KEY_ERROR, e.getMessage());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void animationComplete() {
        linearTimer.resetTimer();
    }

    @Override
    public void timerTick(long tickUpdateInMillis) {
        time_count = (int) (tickUpdateInMillis / 1000);
        Log.w("timertimer", "" + tickUpdateInMillis + " :" + time_count);
        String formattedTime = String.format(Locale.getDefault(), "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(tickUpdateInMillis),
                TimeUnit.MILLISECONDS.toSeconds(tickUpdateInMillis)
                        - TimeUnit.MINUTES
                        .toSeconds(TimeUnit.MILLISECONDS.toHours(tickUpdateInMillis)));

        time.setText(formattedTime);
    }

    @Override
    public void onTimerReset() {
        time.setText("");
    }
}