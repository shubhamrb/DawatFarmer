package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SrpCategoryModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("cat_title_en")
    String cat_title_en;

    @SerializedName("cat_title_hi")
    String cat_title_hi;

    @SerializedName("icon")
    String icon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", cat_title_en='" + cat_title_en + '\'' +
                ", cat_title_hi='" + cat_title_hi + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
