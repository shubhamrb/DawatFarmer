package com.dawat.farmer.mamits.ui.messages;

import static com.dawat.farmer.mamits.utils.AppConstant.PREF_KEY_ACCESS_TOKEN;
import static com.dawat.farmer.mamits.utils.AppConstant.SHARED_PREF_NAME;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dawat.farmer.mamits.R;
import com.dawat.farmer.mamits.remote.ApiHelper;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.JsonObject;
import com.wonderkiln.camerakit.CameraKit;
import com.wonderkiln.camerakit.CameraView;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.github.krtkush.lineartimer.LinearTimer;
import io.github.krtkush.lineartimer.LinearTimerStates;
import io.github.krtkush.lineartimer.LinearTimerView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class VideoRecorderActivity extends AppCompatActivity implements LinearTimer.TimerListener {
    CameraView cameraView;
    boolean isRecording = false;
    File file = null;
    boolean isFlashOn = false;
    private ImageView camera_flash, camera_rotate, picture;
    private LinearTimer linearTimer;
    private TextView time;
    int time_count;
    private SharedPreferences sharedPreferences;
    private String strToken = "";
    private String ticket_id, current_user_id, farmer_id, type;

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

        cameraView = findViewById(R.id.camera);
        LinearTimerView linearTimerView = findViewById(R.id.linearTimer);
        time = findViewById(R.id.time);
        picture = findViewById(R.id.picture);
        if (!cameraView.isStarted()) {
            cameraView.start();
        }
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
        camera_flash.setOnClickListener(v -> setCamera_flash());
        picture.setOnClickListener(v -> {

            if (!isRecording) {
                isRecording = true;
                file = new File(getVideoFilePath(getApplicationContext()));
                cameraView.captureVideo(file);
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
                        stopRecording();
                        camera_flash.setVisibility(View.VISIBLE);
                        camera_rotate.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(VideoRecorderActivity.this, "Minimum length should be 15sec.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private String getVideoFilePath(Context context) {
        final File dir = context.getExternalFilesDir(null);
        return (dir == null ? "" : (dir.getAbsolutePath() + "/"))
                + System.currentTimeMillis() + ".mp4";
    }

    private void setCamera_flash() {
        if (isFlashOn) {
            isFlashOn = false;
            cameraView.setFlash(CameraKit.Constants.FLASH_OFF);
        } else {
            isFlashOn = true;
            cameraView.setFlash(CameraKit.Constants.FLASH_TORCH);
        }
    }

    public void rotateCamera() {
        cameraView.toggleFacing();
        if (cameraView.getFacing() == CameraKit.Constants.FACING_FRONT)
            cameraView.setScaleX(1);
    }

    private void stopRecording() {
        isRecording = false;
        cameraView.stopVideo();
        if (linearTimer.getState() == LinearTimerStates.ACTIVE) {
            linearTimer.pauseTimer();
        }
        Log.w("filea", "" + file.getAbsolutePath());

        sendMessage();
        finish();
    }

    private void sendMessage() {
        try {
            RequestBody ticket_id_body = null;
            MultipartBody.Part fileDoc = null;

            if (ticket_id != null) {
                ticket_id_body = RequestBody.create(MultipartBody.FORM, ticket_id);
            }
            RequestBody current_user_id_body = RequestBody.create(MultipartBody.FORM, current_user_id);
            RequestBody farmer_id_body = RequestBody.create(MultipartBody.FORM,farmer_id);
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
        cameraView.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        cameraView.start();
    }

    @Override
    protected void onDestroy() {
        cameraView.stop();
        super.onDestroy();

    }

    @Override
    public void animationComplete() {
        stopRecording();
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

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraView.start();
    }
}