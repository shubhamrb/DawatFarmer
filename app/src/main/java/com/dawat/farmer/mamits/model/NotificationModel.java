package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NotificationModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("sender")
    String sender;

    @SerializedName("receiver")
    String receiver;

    @SerializedName("title")
    String title;

    @SerializedName("status")
    String status;

    @SerializedName("created_at")
    String created_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
