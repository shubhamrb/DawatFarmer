package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BlogModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("title_hi")
    String title_hi;

    @SerializedName("title_en")
    String title_en;

    @SerializedName("description_hi")
    String description_hi;

    @SerializedName("description_en")
    String description_en;

    @SerializedName("attachment")
    String attachment;

    @SerializedName("event_start_date")
    String event_start_date;

    @SerializedName("event_end_date")
    String event_end_date;

    @SerializedName("cat_title_en")
    String cat_title_en;

    @SerializedName("cat_title_hi")
    String cat_title_hi;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription_hi() {
        return description_hi;
    }

    public void setDescription_hi(String description_hi) {
        this.description_hi = description_hi;
    }

    public String getTitle_hi() {
        return title_hi;
    }

    public void setTitle_hi(String title_hi) {
        this.title_hi = title_hi;
    }

    public String getTitle_en() {
        return title_en;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }

    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getEvent_start_date() {
        return event_start_date;
    }

    public void setEvent_start_date(String event_start_date) {
        this.event_start_date = event_start_date;
    }

    public String getEvent_end_date() {
        return event_end_date;
    }

    public void setEvent_end_date(String event_end_date) {
        this.event_end_date = event_end_date;
    }

    public String getCat_title_en() {
        return cat_title_en;
    }

    public void setCat_title_en(String cat_title_en) {
        this.cat_title_en = cat_title_en;
    }

    public String getCat_title_hi() {
        return cat_title_hi;
    }

    public void setCat_title_hi(String cat_title_hi) {
        this.cat_title_hi = cat_title_hi;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", title_hi='" + title_hi + '\'' +
                ", title_en='" + title_en + '\'' +
                ", description_hi='" + description_hi + '\'' +
                ", description_en='" + description_en + '\'' +
                ", attachment='" + attachment + '\'' +
                ", event_start_date='" + event_start_date + '\'' +
                ", event_end_date='" + event_end_date + '\'' +
                ", cat_title_en='" + cat_title_en + '\'' +
                ", cat_title_hi='" + cat_title_hi + '\'' +
                '}';
    }
}
