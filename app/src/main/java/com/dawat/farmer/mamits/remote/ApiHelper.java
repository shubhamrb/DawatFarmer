package com.dawat.farmer.mamits.remote;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.dawat.farmer.mamits.utils.ApiConstant;
import com.dawat.farmer.mamits.utils.ResponseListener;
import com.google.gson.JsonObject;

import org.json.JSONObject;

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

    public void verifyOtp(String mobile, String otp, ResponseListener responseListener) {
        if (call == null) call = new RetrofitBase(true).retrofit.create(RetrofitInterface.class);
        call.verifyOtpApi(ApiConstant.OTP_VERIFY_END_POINT, mobile, otp).enqueue(new Callback<JsonObject>() {
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
}