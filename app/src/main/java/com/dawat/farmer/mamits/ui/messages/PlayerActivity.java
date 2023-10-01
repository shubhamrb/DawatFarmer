package com.dawat.farmer.mamits.ui.messages;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.dawat.farmer.mamits.R;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;

public class PlayerActivity extends AppCompatActivity {
    String path;
    private SimpleExoPlayer player;
    private PlayerView playerView;
    private int currentWindow = 0;
    private long playbackPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        ImageView img_cancel = findViewById(R.id.img_cancel);
        ImageView image_view = findViewById(R.id.image_view);
        playerView = findViewById(R.id.video_view);

        img_cancel.setOnClickListener(v -> {
            try {
                playerView.getPlayer().stop();
                playerView.getPlayer().stop(true);
                if (Util.SDK_INT >= 24) {
                    releasePlayer();
                    finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
                finish();
            }
        });

        path = getIntent().getStringExtra("filepath");
        String file_type = getIntent().getStringExtra("file_type");
        Log.e("Link", path);
        if (file_type.equalsIgnoreCase("png")
                || file_type.equalsIgnoreCase("jpg")
                || file_type.equalsIgnoreCase("jpeg")) {
            playerView.setVisibility(View.GONE);
            image_view.setVisibility(View.VISIBLE);
            Glide.with(this).load(path).into(image_view);
        } else {
            image_view.setVisibility(View.GONE);
            playerView.setVisibility(View.VISIBLE);
            init();
        }
    }

    private void init() {
        player = new SimpleExoPlayer.Builder(this).build();
        playerView.setKeepContentOnPlayerReset(true);

        if (path.startsWith("https")) {
            MediaItem.Builder mediaItem = new MediaItem.Builder();
            mediaItem.setUri(path);
            player.setMediaItem(mediaItem.build());
        } else {
            MediaItem mediaItem = MediaItem.fromUri(path);
            player.setMediaItem(mediaItem);
            player.seekTo(currentWindow, playbackPosition);
        }
        playerView.setPlayer(player);
        player.prepare();
        player.play();
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }

    @Override
    protected void onStart() {
        if (Util.SDK_INT < 24) {
            releasePlayer();
        }
        super.onStart();
    }

    @Override
    public void onStop() {
        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }
        super.onStop();
    }

    @Override
    public void onBackPressed() {

        try {
            playerView.getPlayer().stop();
            playerView.getPlayer().stop(true);
            if (Util.SDK_INT >= 24) {
                releasePlayer();
                finish();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        try {
            if (player != null && player.isPlaying()) {
                playerView.getPlayer().stop();
                playerView.getPlayer().stop(true);
                if (Util.SDK_INT >= 24) {
                    releasePlayer();
                    finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onPause();
    }
}