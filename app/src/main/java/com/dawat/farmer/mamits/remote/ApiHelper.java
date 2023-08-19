package com.dawat.farmer.mamits.remote;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.dawat.farmer.mamits.utils.ApiConstant;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiHelper {

    public static RetrofitInterface call;

    public void doLogin(String number, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);
        call.loginApi(ApiConstant.LOGIN_END_POINT, number).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void verifyOtp(String mobile, String otp, String fcm_token, String device_id, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);
        call.verifyOtpApi(ApiConstant.OTP_VERIFY_END_POINT, mobile, otp, fcm_token, device_id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void getSliderList(String token, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.getSliderList(token, ApiConstant.GET_SLIDER_LIST_END_POINT).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void getWeatherReport(String type, String key, String city, String day, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.getWeatherReport(ApiConstant.GET_WEATHER_END_POINT + type + "?key=" + key + "&q=" + city + "&days=" + day).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void getNewsList(String token, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.getNewsList(token, ApiConstant.GET_NEWS_LIST_END_POINT).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void getNotificationList(String token, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.getNotificationList(token, ApiConstant.GET_NOTIFICATION_LIST_END_POINT).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void getSrpStageList(String token, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.getSrpStageList(token, ApiConstant.GET_SRP_STAGE_LIST_END_POINT).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void getSrpReportList(String token, String stage_id, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.getSrpReportList(token, ApiConstant.GET_SRP_REPORT_LIST_END_POINT + "?stage_id=" + stage_id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void getSrpAnswerList(String token, String report_id, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.getSrpAnswerList(token, ApiConstant.GET_SRP_ANSWER_LIST_END_POINT + "?report_id=" + report_id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void getUserDetails(String token, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.getUserDetails(token, ApiConstant.GET_USER_DETAILS_END_POINT).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void getAppealList(String token, String status, String type, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.getAppealTicketList(token, ApiConstant.GET_APPEAL_LIST_END_POINT + "?resion=" + type + "&status=" + status).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void getTicketList(String token, String status, String type, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.getAppealTicketList(token, ApiConstant.GET_TICKET_LIST_END_POINT + "?resion=" + type + "&status=" + status).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void createAppeal(String token, String farmer_id, String reason, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.createAppeal(token, ApiConstant.GET_CREATE_APPEAL_END_POINT, farmer_id, reason).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void createTicket(String token, String farmer_id, String title, String description, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.createTicket(token, ApiConstant.GET_CREATE_TICKET_END_POINT, farmer_id, title, description).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void getSrpCategoryList(String token, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.getSrpCategoryList(token, ApiConstant.GET_SRP_CATEGORY_END_POINT).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void getSrpSubCategoryList(String token, RequestBody cat_id, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.getSrpSubCategoryList(token, ApiConstant.GET_SRP_SUB_CATEGORY_END_POINT, cat_id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void getProductList(String token, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.getProductList(token, ApiConstant.GET_PRODUCT_LIST_END_POINT).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void getProductDetail(String token, String product_id, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.getProductDetail(token, ApiConstant.GET_PRODUCT_DETAIL_END_POINT, product_id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void addToCart(String token, String category_id, String product_id, int quantity, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.addToCart(token, ApiConstant.ADD_TO_CART_END_POINT, category_id, product_id, quantity).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void deleteFromCart(String token, String id, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.deleteFromCart(token, ApiConstant.DELETE_FROM_CART_END_POINT, id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void getCartList(String token, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.getCartList(token, ApiConstant.GET_CART_LIST_END_POINT).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void checkout(String token, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.checkout(token, ApiConstant.CHECKOUT_ORDER_END_POINT).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void getOrderList(String token, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.getOrderList(token, ApiConstant.GET_ORDER_LIST_END_POINT).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void getOrderDetail(String token, String order_id, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.getOrderDetail(token, ApiConstant.GET_ORDER_DETAIL_END_POINT, order_id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void cancelOrder(String token, String id, String status, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.cancelOrder(token, ApiConstant.CANCEL_ORDER_END_POINT, id, status).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });
    }

    public void uploadSignature(String token, MultipartBody.Part sign, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.uploadSignature(token, ApiConstant.UPLOAD_SIGN_END_POINT, sign).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void getBlogsList(String token, String sub_category_id, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);
        String url;
        if (sub_category_id != null) {
            url = ApiConstant.GET_SRP_BLOGS_END_POINT + "?subcat_id=" + sub_category_id;
        } else {
            url = ApiConstant.GET_BLOGS_END_POINT;
        }
        call.getBlogsList(token, url).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void getBlogsDetail(String token, String blog_id, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.getBlogDetail(token, ApiConstant.GET_BLOG_DETAIL_END_POINT, blog_id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void getNewsDetail(String token, String blog_id, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.getNewsDetail(token, ApiConstant.GET_NEWS_DETAIL_END_POINT, blog_id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void sendMessage(String token, RequestBody id, RequestBody from_user, RequestBody to_user, RequestBody message, MultipartBody.Part file, RequestBody type, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.sendMessage(token, ApiConstant.SEND_MESSAGES_LIST_END_POINT, id, from_user, to_user, message, file, type).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

    public void getMessagesList(String token, String ticket_id, String type, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);

        call.getMessagesList(token, ApiConstant.GET_MESSAGES_LIST_END_POINT + "?ticket_id=" + ticket_id + "&type=" + type).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> calll, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (responseListener != null) responseListener.onSuccess(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ANError anError = new ANError();
                        anError.setErrorCode(response.code());
                        anError.setErrorBody(jObjError.toString());
                        Log.e("Error", jObjError.toString());
                        if (responseListener != null) responseListener.onFailed(anError);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (responseListener != null) responseListener.onFailed(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                if (responseListener != null) responseListener.onFailed(t);
            }
        });

    }

}