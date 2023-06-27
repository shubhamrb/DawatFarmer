package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProfileModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("father_name")
    String father_name;

    @SerializedName("user_type")
    String user_type;

    @SerializedName("email")
    String email;

    @SerializedName("mobile")
    String mobile;

    @SerializedName("status")
    String status;

    @SerializedName("profile_image")
    String profile_image;

    @SerializedName("coordinatormobile")
    String coordinatormobile;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getCoordinatormobile() {
        return coordinatormobile;
    }

    public void setCoordinatormobile(String coordinatormobile) {
        this.coordinatormobile = coordinatormobile;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", father_name='" + father_name + '\'' +
                ", user_type='" + user_type + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", status='" + status + '\'' +
                ", profile_image='" + profile_image + '\'' +
                ", coordinatormobile='" + coordinatormobile + '\'' +
                '}';
    }
}
