package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SliderModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("title_hi")
    String title_hi;

    @SerializedName("title_en")
    String title_en;

    @SerializedName("attachment")
    String attachment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", title_hi='" + title_hi + '\'' +
                ", title_en='" + title_en + '\'' +
                ", attachment='" + attachment + '\'' +
                '}';
    }
}
