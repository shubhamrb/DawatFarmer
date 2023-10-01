package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlantProtectionModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("cultivation_year")
    String cultivation_year;

    @SerializedName("season")
    String season;

    @SerializedName("date")
    String date;

    @SerializedName("type_id")
    String type_id;

    @SerializedName("pesticide_id")
    String pesticide_id;

    @SerializedName("compliance_id")
    String compliance_id;

    @SerializedName("treatment_type")
    String treatment_type;

    @SerializedName("chemical_id")
    String chemical_id;

    @SerializedName("dose")
    String dose;

    @SerializedName("cost_unit")
    String cost_unit;

    @SerializedName("cost_per_acre")
    String cost_per_acre;

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

    @SerializedName("type")
    String type;

    @SerializedName("compliance_name")
    String compliance_name;

    @SerializedName("pesticide_name")
    String pesticide_name;

    @SerializedName("chemical_name")
    String chemical_name;

    @SerializedName("brand_name")
    String brand_name;

    @SerializedName("company_name")
    String company_name;

    @SerializedName("measurement")
    String measurement;

    @SerializedName("comments")
    CommentsModel comments;

    public CommentsModel getComments() {
        return comments;
    }

    public void setComments(CommentsModel comments) {
        this.comments = comments;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getPesticide_id() {
        return pesticide_id;
    }

    public void setPesticide_id(String pesticide_id) {
        this.pesticide_id = pesticide_id;
    }

    public String getCompliance_id() {
        return compliance_id;
    }

    public void setCompliance_id(String compliance_id) {
        this.compliance_id = compliance_id;
    }

    public String getTreatment_type() {
        return treatment_type;
    }

    public void setTreatment_type(String treatment_type) {
        this.treatment_type = treatment_type;
    }

    public String getChemical_id() {
        return chemical_id;
    }

    public void setChemical_id(String chemical_id) {
        this.chemical_id = chemical_id;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getCost_unit() {
        return cost_unit;
    }

    public void setCost_unit(String cost_unit) {
        this.cost_unit = cost_unit;
    }

    public String getCost_per_acre() {
        return cost_per_acre;
    }

    public void setCost_per_acre(String cost_per_acre) {
        this.cost_per_acre = cost_per_acre;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompliance_name() {
        return compliance_name;
    }

    public void setCompliance_name(String compliance_name) {
        this.compliance_name = compliance_name;
    }

    public String getPesticide_name() {
        return pesticide_name;
    }

    public void setPesticide_name(String pesticide_name) {
        this.pesticide_name = pesticide_name;
    }

    public String getChemical_name() {
        return chemical_name;
    }

    public void setChemical_name(String chemical_name) {
        this.chemical_name = chemical_name;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", cultivation_year='" + cultivation_year + '\'' +
                ", season='" + season + '\'' +
                ", date='" + date + '\'' +
                ", type_id='" + type_id + '\'' +
                ", pesticide_id='" + pesticide_id + '\'' +
                ", compliance_id='" + compliance_id + '\'' +
                ", treatment_type='" + treatment_type + '\'' +
                ", chemical_id='" + chemical_id + '\'' +
                ", dose='" + dose + '\'' +
                ", cost_unit='" + cost_unit + '\'' +
                ", cost_per_acre='" + cost_per_acre + '\'' +
                ", total_cost='" + total_cost + '\'' +
                ", farm_id='" + farm_id + '\'' +
                ", farm_field_id='" + farm_field_id + '\'' +
                ", farm_name='" + farm_name + '\'' +
                ", farm_field='" + farm_field + '\'' +
                ", type='" + type + '\'' +
                ", compliance_name='" + compliance_name + '\'' +
                ", pesticide_name='" + pesticide_name + '\'' +
                ", chemical_name='" + chemical_name + '\'' +
                ", brand_name='" + brand_name + '\'' +
                ", company_name='" + company_name + '\'' +
                ", measurement='" + measurement + '\'' +
                ", comments=" + comments +
                '}';
    }
}
