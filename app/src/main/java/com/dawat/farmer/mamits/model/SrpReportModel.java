package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SrpReportModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("farmer_id")
    String farmer_id;

    @SerializedName("title")
    String title;

    @SerializedName("created_at")
    String created_at;

    @SerializedName("totalpoints")
    int totalpoints;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFarmer_id() {
        return farmer_id;
    }

    public void setFarmer_id(String farmer_id) {
        this.farmer_id = farmer_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getTotalpoints() {
        return totalpoints;
    }

    public void setTotalpoints(int totalpoints) {
        this.totalpoints = totalpoints;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", farmer_id='" + farmer_id + '\'' +
                ", title='" + title + '\'' +
                ", created_at='" + created_at + '\'' +
                ", totalpoints=" + totalpoints +
                '}';
    }
}
