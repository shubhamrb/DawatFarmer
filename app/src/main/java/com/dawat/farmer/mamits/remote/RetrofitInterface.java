package com.dawat.farmer.mamits.remote;

import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface RetrofitInterface {

    @FormUrlEncoded
    @POST
    Call<JsonObject> loginApi(@Url String fullUrl, @Field("mobile") String mobile);

    @FormUrlEncoded
    @POST
    Call<JsonObject> verifyOtpApi(@Url String fullUrl, @Field("mobile") String mobile, @Field("otp") String otp, @Field("token") String token, @Field("device_id") String device_id);

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

    @GET
    Call<JsonObject> getSrpCategoryList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @Multipart
    @POST
    Call<JsonObject> getSrpSubCategoryList(@Header("Authorization") String accessToken, @Url String fullUrl, @Part("category_id") RequestBody cat_id);

    @GET
    Call<JsonObject> getProductList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @FormUrlEncoded
    @POST
    Call<JsonObject> getProductDetail(@Header("Authorization") String accessToken, @Url String fullUrl,
                                      @Field("product_id") String product_id);

    @FormUrlEncoded
    @POST
    Call<JsonObject> addToCart(@Header("Authorization") String accessToken,
                               @Url String fullUrl,
                               @Field("category_id") String category_id,
                               @Field("product_id") String product_id,
                               @Field("quantity") int quantity
    );

    @FormUrlEncoded
    @POST
    Call<JsonObject> deleteFromCart(@Header("Authorization") String accessToken,
                                    @Url String fullUrl,
                                    @Field("id") String id
    );

    @GET
    Call<JsonObject> getCartList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @POST
    Call<JsonObject> checkout(@Header("Authorization") String accessToken,
                              @Url String fullUrl
    );

    @FormUrlEncoded
    @POST
    Call<JsonObject> cancelOrder(@Header("Authorization") String accessToken,
                                 @Url String fullUrl,
                                 @Field("id") String id,
                                 @Field("status") String status
    );

    @GET
    Call<JsonObject> getOrderList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @FormUrlEncoded
    @POST
    Call<JsonObject> getOrderDetail(@Header("Authorization") String accessToken, @Url String fullUrl,
                                    @Field("id") String id);

    @Multipart
    @POST
    Call<JsonObject> uploadSignature(@Header("Authorization") String accessToken, @Url String fullUrl, @Part MultipartBody.Part sign);

    @GET
    Call<JsonObject> getBlogsList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @FormUrlEncoded
    @POST
    Call<JsonObject> getBlogDetail(@Header("Authorization") String accessToken, @Url String fullUrl,
                                   @Field("event_id") String event_id);

    @FormUrlEncoded
    @POST
    Call<JsonObject> getNewsDetail(@Header("Authorization") String accessToken, @Url String fullUrl,
                                   @Field("news_id") String news_id);

    @Multipart
    @POST
    Call<JsonObject> sendMessage(@Header("Authorization") String accessToken, @Url String fullUrl,
                                 @Part("id") RequestBody id,
                                 @Part("from_user") RequestBody from_user,
                                 @Part("to_user") RequestBody to_user,
                                 @Part("message") RequestBody message,
                                 @Part MultipartBody.Part file,
                                 @Part("type") RequestBody type
    );

    @GET
    Call<JsonObject> getMessagesList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @POST
    Call<JsonObject> getFarmerBasicDetails(@Header("Authorization") String accessToken, @Url String fullUrl);

    @GET
    Call<JsonObject> getCropsList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @FormUrlEncoded
    @POST
    Call<JsonObject> getFarmFieldList(@Header("Authorization") String accessToken, @Url String fullUrl, @Field("id") String id);

    @GET
    Call<JsonObject> getFarmsList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @GET
    Call<JsonObject> getFarmLandList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @FormUrlEncoded
    @POST
    Call<JsonObject> getIrrigationSources(@Header("Authorization") String accessToken,
                                          @Url String fullUrl, @Field("farm_id") String farm_id,
                                          @Field("farm_field_id") String farm_field_id);

    @GET
    Call<JsonObject> getFertilizationList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @GET
    Call<JsonObject> getHarvestingList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @GET
    Call<JsonObject> getIrrigationList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @GET
    Call<JsonObject> getLaborList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @GET
    Call<JsonObject> getMachineryList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @GET
    Call<JsonObject> getNurseryList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @GET
    Call<JsonObject> getPlantProtectionList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @GET
    Call<JsonObject> getPurchasingList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @GET
    Call<JsonObject> getTransplantationList(@Header("Authorization") String accessToken, @Url String fullUrl);

    @GET
    Call<JsonObject> getTreatmentList(@Header("Authorization") String accessToken, @Url String fullUrl);
}
