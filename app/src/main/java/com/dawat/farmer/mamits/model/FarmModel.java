package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FarmModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("code")
    String code;

    @SerializedName("status")
    String status;

    @SerializedName("created_at")
    String created_at;

    @SerializedName("total")
    String total;

    @SerializedName("formfield")
    String formfield;

    @SerializedName("prctotal")
    String prctotal;

    public String getPrctotal() {
        return prctotal;
    }

    public void setPrctotal(String prctotal) {
        this.prctotal = prctotal;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getFormfield() {
        return formfield;
    }

    public void setFormfield(String formfield) {
        this.formfield = formfield;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public String toString() {
        return "FarmModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", status='" + status + '\'' +
                ", created_at='" + created_at + '\'' +
                ", total='" + total + '\'' +
                ", formfield='" + formfield + '\'' +
                ", prctotal='" + prctotal + '\'' +
                '}';
    }
}
