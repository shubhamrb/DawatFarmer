package com.dawat.farmer.mamits.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.LocaleList;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;

public class CommonUtils {
    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public interface ErrorClass {
        String CODE = "code";
        String STATUS = "status";
        String MESSAGE = "message";
        String DEVELOPER_MESSAGE = "developerMessage";
    }

    public interface TimeOut {
        int IMAGE_UPLOAD_CONNECTION_TIMEOUT = 120;
        int IMAGE_UPLOAD_SOCKET_TIMEOUT = 120;
        int SOCKET_TIME_OUT = 120;
        int CONNECTION_TIME_OUT = 120;
    }

    public static int calculateNoOfColumns(Context context, float columnWidthDp) { // For example columnWidthdp=180
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5); // +0.5 for correct rounding to int.
        return noOfColumns;
    }

    public static String getProperty(String key, Context context) throws IOException {
        Properties properties = new Properties();
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open("config.properties");
        properties.load(inputStream);
        return properties.getProperty(key);
    }


    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }


    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static String convertMillieToHMmSs(long millie) {
        long seconds = (millie / 1000);
        long second = seconds % 60;
        long minute = (seconds / 60) % 60;
        long hour = (seconds / (60 * 60)) % 24;

        if (hour > 0) {
            return String.format("%02d:%02d:%02d", hour, minute, second);
        } else {
            return String.format("%02d:%02d", minute, second);
        }

    }

    public static boolean getTime(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        formatter.setLenient(false);
        Date curDate = new Date();
        String dss = formatter.format(curDate.getTime());
        Log.e("current_date==>12", dss + "");

        Date oldDate = null;
        Date cDate = null;

        try {
            oldDate = formatter.parse(time);
            cDate = formatter.parse(dss);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (oldDate.after(cDate)) {
            Log.e("your_date_is_outdated", "1");
            return true;
        } else {
            Log.e("your_date_is_outdated", "2");

            return false;
        }
    }

    public static void setLocale(Context context, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.setLocales(new LocaleList(locale));
        } else {
            configuration.locale = locale;
        }

        context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
    }

    public static String minutesToDaysHoursMinutes(int time) {
        int years = (time / 24 / 60);
        int days = 0;
        try {
            days = years % 365;
            years = years / 365;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return years + ":" + days + ":" + time / 60 % 24 + ':' + time % 60;
    }

    @SuppressLint("DefaultLocale")
    public static String formatNumber(long count) {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        System.out.println("int " + exp);
        double value = (count / Math.pow(1000, exp));
        System.out.println("exp " + value);
        return String.format("%.2f %c", value, "kMGTPE".charAt(exp - 1));
    }

    public static Spannable colorized(final String text, final String word, final int argb) {
        final Spannable spannable = new SpannableString(text);
        String t = text.toLowerCase(Locale.ROOT);
        String w = word.toLowerCase(Locale.ROOT);
        int substringStart = 0;
        int start;
        while ((start = t.indexOf(w, substringStart)) >= 0) {
            spannable.setSpan(
                    new ForegroundColorSpan(argb), start, start + w.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            );
            substringStart = start + word.length();
        }
        return spannable;
    }

    public static Bitmap retriveVideoFrameFromVideo(String videoPath) throws Throwable {
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
            //   mediaMetadataRetriever.setDataSource(videoPath);
            bitmap = mediaMetadataRetriever.getFrameAtTime();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Throwable("Exception in retriveVideoFrameFromVideo(String videoPath)" + e.getMessage());

        } finally {
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
        }
        return bitmap;
    }

}
