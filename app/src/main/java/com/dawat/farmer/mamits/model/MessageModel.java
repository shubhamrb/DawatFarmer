package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MessageModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("ticket_id")
    String ticket_id;

    @SerializedName("title")
    String title;

    @SerializedName("description")
    String description;

    @SerializedName("file")
    String file;

    @SerializedName("file_type")
    String file_type;

    @SerializedName("from_user")
    String from_user;

    @SerializedName("to_user")
    String to_user;

    @SerializedName("from_name")
    String from_name;

    @SerializedName("to_name")
    String to_name;

    @SerializedName("date")
    String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getFrom_user() {
        return from_user;
    }

    public void setFrom_user(String from_user) {
        this.from_user = from_user;
    }

    public String getTo_user() {
        return to_user;
    }

    public void setTo_user(String to_user) {
        this.to_user = to_user;
    }

    public String getFrom_name() {
        return from_name;
    }

    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }

    public String getTo_name() {
        return to_name;
    }

    public void setTo_name(String to_name) {
        this.to_name = to_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", ticket_id='" + ticket_id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", file='" + file + '\'' +
                ", file_type='" + file_type + '\'' +
                ", from_user='" + from_user + '\'' +
                ", to_user='" + to_user + '\'' +
                ", from_name='" + from_name + '\'' +
                ", to_name='" + to_name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
