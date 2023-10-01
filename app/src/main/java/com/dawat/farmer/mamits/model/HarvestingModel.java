package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HarvestingModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("cultivation_year")
    String cultivation_year;

    @SerializedName("season")
    String season;

    @SerializedName("from_date")
    String from_date;

    @SerializedName("to_date")
    String to_date;

    @SerializedName("method_type")
    String method_type;

    @SerializedName("done_by")
    String done_by;

    @SerializedName("no_of_labor")
    String no_of_labor;

    @SerializedName("per_labor_cost")
    String per_labor_cost;

    @SerializedName("contract_cost")
    String contract_cost;

    @SerializedName("thresher_cost")
    String thresher_cost;

    @SerializedName("cleaning_cost")
    String cleaning_cost;

    @SerializedName("total_cost")
    String total_cost;

    @SerializedName("estimated_area")
    String estimated_area;

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

    public CommentsModel getComments() {
        return comments;
    }

    public void setComments(CommentsModel comments) {
        this.comments = comments;
    }

    public String getDone_by() {
        return done_by;
    }

    public void setDone_by(String done_by) {
        this.done_by = done_by;
    }

    public String getNo_of_labor() {
        return no_of_labor;
    }

    public void setNo_of_labor(String no_of_labor) {
        this.no_of_labor = no_of_labor;
    }

    public String getPer_labor_cost() {
        return per_labor_cost;
    }

    public void setPer_labor_cost(String per_labor_cost) {
        this.per_labor_cost = per_labor_cost;
    }

    public String getContract_cost() {
        return contract_cost;
    }

    public void setContract_cost(String contract_cost) {
        this.contract_cost = contract_cost;
    }

    public String getThresher_cost() {
        return thresher_cost;
    }

    public void setThresher_cost(String thresher_cost) {
        this.thresher_cost = thresher_cost;
    }

    public String getCleaning_cost() {
        return cleaning_cost;
    }

    public void setCleaning_cost(String cleaning_cost) {
        this.cleaning_cost = cleaning_cost;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
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

    public String getMethod_type() {
        return method_type;
    }

    public void setMethod_type(String method_type) {
        this.method_type = method_type;
    }

    public String getEstimated_area() {
        return estimated_area;
    }

    public void setEstimated_area(String estimated_area) {
        this.estimated_area = estimated_area;
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
                ", from_date='" + from_date + '\'' +
                ", to_date='" + to_date + '\'' +
                ", method_type='" + method_type + '\'' +
                ", done_by='" + done_by + '\'' +
                ", no_of_labor='" + no_of_labor + '\'' +
                ", per_labor_cost='" + per_labor_cost + '\'' +
                ", contract_cost='" + contract_cost + '\'' +
                ", thresher_cost='" + thresher_cost + '\'' +
                ", cleaning_cost='" + cleaning_cost + '\'' +
                ", total_cost='" + total_cost + '\'' +
                ", estimated_area='" + estimated_area + '\'' +
                ", farm_id='" + farm_id + '\'' +
                ", farm_field_id='" + farm_field_id + '\'' +
                ", farm_name='" + farm_name + '\'' +
                ", farm_field='" + farm_field + '\'' +
                ", comments=" + comments +
                '}';
    }
}
