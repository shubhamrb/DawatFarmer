package com.dawat.farmer.mamits.remote;

import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.dawat.farmer.mamits.utils.AppConstant;
import com.dawat.farmer.mamits.utils.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitBase {
    public Retrofit retrofit;

    public RetrofitBase(boolean addTimeout) {
        try {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> {
                // Log the message or convert it to cURL command and log
                System.out.println("cURL: " + message);
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
            if (addTimeout) {
                httpClientBuilder.readTimeout(CommonUtils.TimeOut.SOCKET_TIME_OUT, TimeUnit.SECONDS);
                httpClientBuilder.connectTimeout(CommonUtils.TimeOut.CONNECTION_TIME_OUT, TimeUnit.SECONDS);
            } else {
                httpClientBuilder.readTimeout(CommonUtils.TimeOut.IMAGE_UPLOAD_SOCKET_TIMEOUT, TimeUnit.SECONDS);
                httpClientBuilder.connectTimeout(CommonUtils.TimeOut.IMAGE_UPLOAD_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
            }
            httpClientBuilder.retryOnConnectionFailure(true);
            httpClientBuilder.addInterceptor(loggingInterceptor);
            OkHttpClient httpClient = httpClientBuilder.build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstant.PREF_KEY_BASE)
                    .client(httpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
