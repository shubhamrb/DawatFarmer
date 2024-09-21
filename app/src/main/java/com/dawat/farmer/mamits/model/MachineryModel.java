package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MachineryModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("cultivation_year")
    String cultivation_year;

    @SerializedName("season")
    String season;

    @SerializedName("operation")
    String operation;

    @SerializedName("method")
    String method;

    @SerializedName("tool_used")
    String tool_used;

    @SerializedName("units")
    String units;

    @SerializedName("rate")
    String rate;

    @SerializedName("type_owned")
    String type_owned;

    @SerializedName("machine")
    String machine;

    @SerializedName("cost")
    String cost;

    @SerializedName("total_cost")
    String total_cost;

    @SerializedName("farm_id")
    String farm_id;

    @SerializedName("farm_field_id")
    String farm_field_id;

    @SerializedName("farm_name")
    String farm_name;

    @SerializedName("farm_field")
    String farm_field;

    @SerializedName("land_preparation")
    String land_preparation;

    @SerializedName("spray")
    String spray;

    @SerializedName("land_preparation_cost")
    String land_preparation_cost;

    @SerializedName("spray_cost")
    String spray_cost;

    @SerializedName("tillage_date")
    String tillage_date;

    @SerializedName("depth")
    String depth;

    @SerializedName("soil_inversion")
    String soil_inversion;

    @SerializedName("comments")
    CommentsModel comments;

    public String getLand_preparation() {
        return land_preparation;
    }

    public void setLand_preparation(String land_preparation) {
        this.land_preparation = land_preparation;
    }

    public String getSpray() {
        return spray;
    }

    public void setSpray(String spray) {
        this.spray = spray;
    }

    public String getLand_preparation_cost() {
        return land_preparation_cost;
    }

    public void setLand_preparation_cost(String land_preparation_cost) {
        this.land_preparation_cost = land_preparation_cost;
    }

    public String getSpray_cost() {
        return spray_cost;
    }

    public void setSpray_cost(String spray_cost) {
        this.spray_cost = spray_cost;
    }

    public String getTillage_date() {
        return tillage_date;
    }

    public void setTillage_date(String tillage_date) {
        this.tillage_date = tillage_date;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getSoil_inversion() {
        return soil_inversion;
    }

    public void setSoil_inversion(String soil_inversion) {
        this.soil_inversion = soil_inversion;
    }

    public CommentsModel getComments() {
        return comments;
    }

    public void setComments(CommentsModel comments) {
        this.comments = comments;
    }

    public String getTool_used() {
        return tool_used;
    }

    public void setTool_used(String tool_used) {
        this.tool_used = tool_used;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getType_owned() {
        return type_owned;
    }

    public void setType_owned(String type_owned) {
        this.type_owned = type_owned;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
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
                ", operation='" + operation + '\'' +
                ", method='" + method + '\'' +
                ", tool_used='" + tool_used + '\'' +
                ", units='" + units + '\'' +
                ", rate='" + rate + '\'' +
                ", type_owned='" + type_owned + '\'' +
                ", machine='" + machine + '\'' +
                ", cost='" + cost + '\'' +
                ", total_cost='" + total_cost + '\'' +
                ", farm_id='" + farm_id + '\'' +
                ", farm_field_id='" + farm_field_id + '\'' +
                ", farm_name='" + farm_name + '\'' +
                ", farm_field='" + farm_field + '\'' +
                ", comments=" + comments +
                '}';
    }
}
