package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TreatmentModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("cultivation_year")
    String cultivation_year;

    @SerializedName("season")
    String season;

    @SerializedName("name")
    String name;

    @SerializedName("chemical_type")
    String chemical_type;

    @SerializedName("quantity")
    String quantity;

    @SerializedName("measure")
    String measure;

    @SerializedName("date")
    String date;

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

    @SerializedName("comments")
    CommentsModel comments;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChemical_type() {
        return chemical_type;
    }

    public void setChemical_type(String chemical_type) {
        this.chemical_type = chemical_type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
                ", name='" + name + '\'' +
                ", chemical_type='" + chemical_type + '\'' +
                ", quantity='" + quantity + '\'' +
                ", measure='" + measure + '\'' +
                ", date='" + date + '\'' +
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
