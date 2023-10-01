package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CommentDataModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("farm_id")
    String farm_id;

    @SerializedName("comment")
    String comment;

    @SerializedName("created_at")
    String created_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFarm_id() {
        return farm_id;
    }

    public void setFarm_id(String farm_id) {
        this.farm_id = farm_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
                ", farm_id='" + farm_id + '\'' +
                ", comment='" + comment + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
