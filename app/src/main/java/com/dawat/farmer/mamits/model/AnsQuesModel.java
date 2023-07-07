package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AnsQuesModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("title_hi")
    String title_hi;

    @SerializedName("title_en")
    String title_en;

    @SerializedName("description_en")
    String description_en;

    @SerializedName("description_hi")
    String description_hi;

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

    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
    }

    public String getDescription_hi() {
        return description_hi;
    }

    public void setDescription_hi(String description_hi) {
        this.description_hi = description_hi;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", title_hi='" + title_hi + '\'' +
                ", title_en='" + title_en + '\'' +
                ", description_en='" + description_en + '\'' +
                ", description_hi='" + description_hi + '\'' +
                '}';
    }
}
