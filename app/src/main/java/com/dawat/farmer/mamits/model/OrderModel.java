package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderModel implements Serializable {


    @SerializedName("id")
    private String id;

    @SerializedName("orderId")
    private String orderId;

    @SerializedName("final_price")
    private String final_price;

    @SerializedName("base_price")
    private String base_price;

    @SerializedName("payment_channel")
    private String payment_channel;

    @SerializedName("payment_status")
    private String payment_status;

    @SerializedName("reject_reason")
    private String reject_reason;

    @SerializedName("status")
    private String status;

    @SerializedName("order_date")
    private String order_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getFinal_price() {
        return final_price;
    }

    public void setFinal_price(String final_price) {
        this.final_price = final_price;
    }

    public String getBase_price() {
        return base_price;
    }

    public void setBase_price(String base_price) {
        this.base_price = base_price;
    }

    public String getPayment_channel() {
        return payment_channel;
    }

    public void setPayment_channel(String payment_channel) {
        this.payment_channel = payment_channel;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getReject_reason() {
        return reject_reason;
    }

    public void setReject_reason(String reject_reason) {
        this.reject_reason = reject_reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", final_price='" + final_price + '\'' +
                ", base_price='" + base_price + '\'' +
                ", payment_channel='" + payment_channel + '\'' +
                ", payment_status='" + payment_status + '\'' +
                ", reject_reason='" + reject_reason + '\'' +
                ", status='" + status + '\'' +
                ", order_date='" + order_date + '\'' +
                '}';
    }
}
