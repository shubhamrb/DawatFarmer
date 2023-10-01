package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NurseryModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("seed_type")
    String seed_type;

    @SerializedName("variety")
    String variety;

    @SerializedName("source_of_seed")
    String source_of_seed;

    @SerializedName("company_name")
    String company_name;

    @SerializedName("seed_used")
    String seed_used;

    @SerializedName("seed_cost")
    String seed_cost;

    @SerializedName("date_of_sowing")
    String date_of_sowing;

    @SerializedName("sown_area")
    String sown_area;

    @SerializedName("field_area")
    String field_area;

    @SerializedName("lc_date")
    String lc_date;

    @SerializedName("total_cost")
    String total_cost;

    @SerializedName("farm_crop_id")
    String farm_crop_id;

    @SerializedName("farm_id")
    String farm_id;

    @SerializedName("farm_field_id")
    String farm_field_id;

    @SerializedName("farm_name")
    String farm_name;

    @SerializedName("farm_field")
    String farm_field;

    @SerializedName("crop_name")
    String crop_name;

    @SerializedName("cultivation_year")
    String cultivation_year;

    @SerializedName("season")
    String season;

    @SerializedName("comments")
    CommentsModel comments;

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", seed_type='" + seed_type + '\'' +
                ", variety='" + variety + '\'' +
                ", source_of_seed='" + source_of_seed + '\'' +
                ", company_name='" + company_name + '\'' +
                ", seed_used='" + seed_used + '\'' +
                ", seed_cost='" + seed_cost + '\'' +
                ", date_of_sowing='" + date_of_sowing + '\'' +
                ", sown_area='" + sown_area + '\'' +
                ", field_area='" + field_area + '\'' +
                ", lc_date='" + lc_date + '\'' +
                ", total_cost='" + total_cost + '\'' +
                ", farm_crop_id='" + farm_crop_id + '\'' +
                ", farm_id='" + farm_id + '\'' +
                ", farm_field_id='" + farm_field_id + '\'' +
                ", farm_name='" + farm_name + '\'' +
                ", farm_field='" + farm_field + '\'' +
                ", crop_name='" + crop_name + '\'' +
                ", cultivation_year='" + cultivation_year + '\'' +
                ", season='" + season + '\'' +
                ", comments=" + comments +
                '}';
    }

    public CommentsModel getComments() {
        return comments;
    }

    public void setComments(CommentsModel comments) {
        this.comments = comments;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeed_type() {
        return seed_type;
    }

    public void setSeed_type(String seed_type) {
        this.seed_type = seed_type;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getSource_of_seed() {
        return source_of_seed;
    }

    public void setSource_of_seed(String source_of_seed) {
        this.source_of_seed = source_of_seed;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getSeed_used() {
        return seed_used;
    }

    public void setSeed_used(String seed_used) {
        this.seed_used = seed_used;
    }

    public String getSeed_cost() {
        return seed_cost;
    }

    public void setSeed_cost(String seed_cost) {
        this.seed_cost = seed_cost;
    }

    public String getDate_of_sowing() {
        return date_of_sowing;
    }

    public void setDate_of_sowing(String date_of_sowing) {
        this.date_of_sowing = date_of_sowing;
    }

    public String getSown_area() {
        return sown_area;
    }

    public void setSown_area(String sown_area) {
        this.sown_area = sown_area;
    }

    public String getField_area() {
        return field_area;
    }

    public void setField_area(String field_area) {
        this.field_area = field_area;
    }

    public String getLc_date() {
        return lc_date;
    }

    public void setLc_date(String lc_date) {
        this.lc_date = lc_date;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
    }

    public String getFarm_crop_id() {
        return farm_crop_id;
    }

    public void setFarm_crop_id(String farm_crop_id) {
        this.farm_crop_id = farm_crop_id;
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

    public String getCrop_name() {
        return crop_name;
    }

    public void setCrop_name(String crop_name) {
        this.crop_name = crop_name;
    }

    public String getCultivation_year() {
        return cultivation_year;
    }

    public void setCultivation_year(String cultivation_year) {
        this.cultivation_year = cultivation_year;
    }

}
