package com.dawat.farmer.mamits.remote;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface RetrofitInterface {

    @FormUrlEncoded
    @POST
    Call<JsonObject> loginApi(@Url String fullUrl, @Field("mobile") String mobile);

    @FormUrlEncoded
    @POST
    Call<JsonObject> verifyOtpApi(@Url String fullUrl, @Field("mobile") String mobile, @Field("otp") String otp);

    @GET
    Call<JsonObject> getSliderList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @GET
    Call<JsonObject> getWeatherReport(@Url String fullUrl);

    @GET
    Call<JsonObject> getNewsList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @GET
    Call<JsonObject> getNotificationList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @GET
    Call<JsonObject> getSrpStageList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @GET
    Call<JsonObject> getSrpReportList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @GET
    Call<JsonObject> getSrpQuestionList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @GET
    Call<JsonObject> getSrpSubQuestionList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @GET
    Call<JsonObject> getSrpAnswerList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @GET
    Call<JsonObject> getUserDetails(@Header("Authorization") String accessToken, @Url String fullUrl);

    @GET
    Call<JsonObject> getAppealTicketList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @FormUrlEncoded
    @POST
    Call<JsonObject> createTicket(@Header("Authorization") String accessToken, @Url String fullUrl,
                                  @Field("farmer_id") String farmer_id,
                                  @Field("title") String title,
                                  @Field("description") String description);

    @FormUrlEncoded
    @POST
    Call<JsonObject> createAppeal(@Header("Authorization") String accessToken, @Url String fullUrl,
                                  @Field("farmer_id") String farmer_id,
                                  @Field("resion") String resion);
}
