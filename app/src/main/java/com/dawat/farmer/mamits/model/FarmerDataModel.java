package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FarmerDataModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("username")
    String username;

    @SerializedName("father_name")
    String father_name;

    @SerializedName("name")
    String name;

    @SerializedName("email")
    String email;

    @SerializedName("mobile")
    String mobile;

    @SerializedName("profile_image")
    String profile_image;

    @SerializedName("created_at")
    String created_at;

    @SerializedName("status")
    String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FarmerDataModel{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", father_name='" + father_name + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", profile_image='" + profile_image + '\'' +
                ", created_at='" + created_at + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
