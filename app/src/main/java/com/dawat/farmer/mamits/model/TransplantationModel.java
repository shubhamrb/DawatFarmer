package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TransplantationModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("cultivation_year")
    String cultivation_year;

    @SerializedName("season")
    String season;

    @SerializedName("transplanted_acres")
    String transplanted_acres;

    @SerializedName("from_date")
    String from_date;

    @SerializedName("to_date")
    String to_date;

    @SerializedName("cultivation_method")
    String cultivation_method;

    @SerializedName("main_field_lc_date")
    String main_field_lc_date;

    @SerializedName("farm_id")
    String farm_id;

    @SerializedName("farm_field_id")
    String farm_field_id;

    @SerializedName("farm_name")
    String farm_name;

    @SerializedName("farm_field")
    String farm_field;

    @SerializedName("transplantation_rate")
    String transplantation_rate;

    @SerializedName("comments")
    CommentsModel comments;

    public CommentsModel getComments() {
        return comments;
    }

    public void setComments(CommentsModel comments) {
        this.comments = comments;
    }

    public String getTransplantation_rate() {
        return transplantation_rate;
    }

    public void setTransplantation_rate(String transplantation_rate) {
        this.transplantation_rate = transplantation_rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCultivation_year() {
        return cultivation_year;
    }

    public void setCultivation_year(String cultivation_year) {
        this.cultivation_year = cultivation_year;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getTransplanted_acres() {
        return transplanted_acres;
    }

    public void setTransplanted_acres(String transplanted_acres) {
        this.transplanted_acres = transplanted_acres;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getCultivation_method() {
        return cultivation_method;
    }

    public void setCultivation_method(String cultivation_method) {
        this.cultivation_method = cultivation_method;
    }

    public String getMain_field_lc_date() {
        return main_field_lc_date;
    }

    public void setMain_field_lc_date(String main_field_lc_date) {
        this.main_field_lc_date = main_field_lc_date;
    }

    public String getFarm_id() {
        return farm_id;
    }

    public void setFarm_id(String farm_id) {
        this.farm_id = farm_id;
    }

    public String getFarm_field_id() {
        return farm_field_id;
    }

    public void setFarm_field_id(String farm_field_id) {
        this.farm_field_id = farm_field_id;
    }

    public String getFarm_name() {
        return farm_name;
    }

    public void setFarm_name(String farm_name) {
        this.farm_name = farm_name;
    }

    public String getFarm_field() {
        return farm_field;
    }

    public void setFarm_field(String farm_field) {
        this.farm_field = farm_field;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", cultivation_year='" + cultivation_year + '\'' +
                ", season='" + season + '\'' +
                ", transplanted_acres='" + transplanted_acres + '\'' +
                ", from_date='" + from_date + '\'' +
                ", to_date='" + to_date + '\'' +
                ", cultivation_method='" + cultivation_method + '\'' +
                ", main_field_lc_date='" + main_field_lc_date + '\'' +
                ", farm_id='" + farm_id + '\'' +
                ", farm_field_id='" + farm_field_id + '\'' +
                ", farm_name='" + farm_name + '\'' +
                ", farm_field='" + farm_field + '\'' +
                ", transplantation_rate='" + transplantation_rate + '\'' +
                ", comments=" + comments +
                '}';
    }
}
