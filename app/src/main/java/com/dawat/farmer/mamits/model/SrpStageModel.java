package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SrpStageModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("stage")
    String stage;

    @SerializedName("status")
    String status;

    @SerializedName("created_at")
    String created_at;

    @SerializedName("totals")
    int totals;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
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

    public int getTotals() {
        return totals;
    }

    public void setTotals(int totals) {
        this.totals = totals;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", stage='" + stage + '\'' +
                ", status='" + status + '\'' +
                ", created_at='" + created_at + '\'' +
                ", totals=" + totals +
                '}';
    }
}
