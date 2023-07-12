package com.dawat.farmer.mamits.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import android.util.Patterns;

public class CommonUtils {
    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public interface TimeOut {
        int IMAGE_UPLOAD_CONNECTION_TIMEOUT = 120;
        int IMAGE_UPLOAD_SOCKET_TIMEOUT = 120;
        int SOCKET_TIME_OUT = 120;
        int CONNECTION_TIME_OUT = 120;
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
