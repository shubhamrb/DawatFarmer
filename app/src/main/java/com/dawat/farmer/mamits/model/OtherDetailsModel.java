package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OtherDetailsModel implements Serializable {

    @SerializedName("total")
    String total;

    @SerializedName("totalform")
    String totalform;

    @SerializedName("totalfield")
    String totalfield;

    @SerializedName("formfield")
    String formfield;

    @SerializedName("prctotal")
    String prctotal;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalform() {
        return totalform;
    }

    public void setTotalform(String totalform) {
        this.totalform = totalform;
    }

    public String getTotalfield() {
        return totalfield;
    }

    public void setTotalfield(String totalfield) {
        this.totalfield = totalfield;
    }

    public String getFormfield() {
        return formfield;
    }

    public void setFormfield(String formfield) {
        this.formfield = formfield;
    }

    public String getPrctotal() {
        return prctotal;
    }

    public void setPrctotal(String prctotal) {
        this.prctotal = prctotal;
    }

    @Override
    public String toString() {
        return "OtherDetailsModel{" +
                "total='" + total + '\'' +
                ", totalform='" + totalform + '\'' +
                ", totalfield='" + totalfield + '\'' +
                ", formfield='" + formfield + '\'' +
                ", prctotal='" + prctotal + '\'' +
                '}';
    }
}
