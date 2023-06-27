package com.dawat.farmer.mamits.utils;


import com.google.gson.JsonObject;

public interface ResponseListener {

    void onSuccess(JsonObject jsonObject);

    void onFailed(Throwable throwable);
}
