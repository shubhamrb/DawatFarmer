package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CropModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("cultivation_year")
    String cultivation_year;

    @SerializedName("season")
    String season;

    @SerializedName("cropped_area")
    String cropped_area;

    @SerializedName("lease_land")
    String lease_land;

    @SerializedName("lease_land_cost")
    String lease_land_cost;

    @SerializedName("land_area")
    String land_area;

    @SerializedName("area_under_project")
    String area_under_project;

    @SerializedName("varietyList")
    List<VarietyModel> varietyList;

    @SerializedName("farm_id")
    String farm_id;

    @SerializedName("farm_field_id")
    String farm_field_id;

    @SerializedName("farm_name")
    String farm_name;

    @SerializedName("farm_field")
    String farm_field;

    @SerializedName("comments")
    CommentsModel comments;

    public List<VarietyModel> getVarietyList() {
        return varietyList;
    }

    public void setVarietyList(List<VarietyModel> varietyList) {
        this.varietyList = varietyList;
    }

    public String getLease_land() {
        return lease_land;
    }

    public void setLease_land(String lease_land) {
        this.lease_land = lease_land;
    }

    public String getLease_land_cost() {
        return lease_land_cost;
    }

    public void setLease_land_cost(String lease_land_cost) {
        this.lease_land_cost = lease_land_cost;
    }

    public String getLand_area() {
        return land_area;
    }

    public void setLand_area(String land_area) {
        this.land_area = land_area;
    }

    public String getArea_under_project() {
        return area_under_project;
    }

    public void setArea_under_project(String area_under_project) {
        this.area_under_project = area_under_project;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCropped_area() {
        return cropped_area;
    }

    public void setCropped_area(String cropped_area) {
        this.cropped_area = cropped_area;
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
                ", name='" + name + '\'' +
                ", cultivation_year='" + cultivation_year + '\'' +
                ", season='" + season + '\'' +
                ", cropped_area='" + cropped_area + '\'' +
                ", farm_id='" + farm_id + '\'' +
                ", farm_field_id='" + farm_field_id + '\'' +
                ", farm_name='" + farm_name + '\'' +
                ", farm_field='" + farm_field + '\'' +
                ", comments=" + comments +
                '}';
    }
}
