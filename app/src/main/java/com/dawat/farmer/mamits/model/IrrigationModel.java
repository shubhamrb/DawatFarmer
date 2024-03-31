package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IrrigationModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("cultivation_year")
    String cultivation_year;

    @SerializedName("season")
    String season;

    @SerializedName("crop_stage")
    String crop_stage;

    @SerializedName("irrigation_date")
    String irrigation_date;

    @SerializedName("application_place")
    String application_place;

    @SerializedName("application_method")
    String application_method;

    @SerializedName("tensio_meter")
    String tensio_meter;

    @SerializedName("action_taken")
    String action_taken;

    @SerializedName("cost")
    String cost;

    @SerializedName("irrigation_source_id")
    String irrigation_source_id;

    @SerializedName("farm_id")
    String farm_id;

    @SerializedName("farm_field_id")
    String farm_field_id;

    @SerializedName("farm_name")
    String farm_name;

    @SerializedName("farm_field")
    String farm_field;

    @SerializedName("irrigation_sources")
    String irrigation_sources;

    @SerializedName("other_name")
    String other_name;

    @SerializedName("comments")
    CommentsModel comments;

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public CommentsModel getComments() {
        return comments;
    }

    public void setComments(CommentsModel comments) {
        this.comments = comments;
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

    public String getCrop_stage() {
        return crop_stage;
    }

    public void setCrop_stage(String crop_stage) {
        this.crop_stage = crop_stage;
    }

    public String getIrrigation_date() {
        return irrigation_date;
    }

    public void setIrrigation_date(String irrigation_date) {
        this.irrigation_date = irrigation_date;
    }

    public String getApplication_place() {
        return application_place;
    }

    public void setApplication_place(String application_place) {
        this.application_place = application_place;
    }

    public String getApplication_method() {
        return application_method;
    }

    public void setApplication_method(String application_method) {
        this.application_method = application_method;
    }

    public String getTensio_meter() {
        return tensio_meter;
    }

    public void setTensio_meter(String tensio_meter) {
        this.tensio_meter = tensio_meter;
    }

    public String getAction_taken() {
        return action_taken;
    }

    public void setAction_taken(String action_taken) {
        this.action_taken = action_taken;
    }

    public String getIrrigation_source_id() {
        return irrigation_source_id;
    }

    public void setIrrigation_source_id(String irrigation_source_id) {
        this.irrigation_source_id = irrigation_source_id;
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

    public String getIrrigation_sources() {
        return irrigation_sources;
    }

    public void setIrrigation_sources(String irrigation_sources) {
        this.irrigation_sources = irrigation_sources;
    }

    public String getOther_name() {
        return other_name;
    }

    public void setOther_name(String other_name) {
        this.other_name = other_name;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", cultivation_year='" + cultivation_year + '\'' +
                ", season='" + season + '\'' +
                ", crop_stage='" + crop_stage + '\'' +
                ", irrigation_date='" + irrigation_date + '\'' +
                ", application_place='" + application_place + '\'' +
                ", application_method='" + application_method + '\'' +
                ", tensio_meter='" + tensio_meter + '\'' +
                ", action_taken='" + action_taken + '\'' +
                ", cost='" + cost + '\'' +
                ", irrigation_source_id='" + irrigation_source_id + '\'' +
                ", farm_id='" + farm_id + '\'' +
                ", farm_field_id='" + farm_field_id + '\'' +
                ", farm_name='" + farm_name + '\'' +
                ", farm_field='" + farm_field + '\'' +
                ", irrigation_sources='" + irrigation_sources + '\'' +
                ", other_name='" + other_name + '\'' +
                ", comments=" + comments +
                '}';
    }
}
