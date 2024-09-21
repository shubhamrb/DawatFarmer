package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CocModel implements Serializable {


    @SerializedName("id")
    String id;

    @SerializedName("nursery_cost")
    String nursery_cost;

    @SerializedName("seed_treatment_cost")
    String seed_treatment_cost;

    @SerializedName("irrigation_cost")
    String irrigation_cost;

    @SerializedName("fertilizer_cost")
    String fertilizer_cost;

    @SerializedName("plant_protection_cost")
    String plant_protection_cost;

    @SerializedName("harvesting_cost")
    String harvesting_cost;

    @SerializedName("machinery_cost")
    String machinery_cost;

    @SerializedName("labor_cost")
    String labor_cost;

    @SerializedName("other_cost")
    String other_cost;

    @SerializedName("total_cost")
    String total_cost;

    @SerializedName("gross_income")
    String gross_income;

    @SerializedName("other_income")
    String other_income;

    @SerializedName("total_income")
    String total_income;

    @SerializedName("net_income")
    String net_income;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNursery_cost() {
        return nursery_cost;
    }

    public void setNursery_cost(String nursery_cost) {
        this.nursery_cost = nursery_cost;
    }

    public String getSeed_treatment_cost() {
        return seed_treatment_cost;
    }

    public void setSeed_treatment_cost(String seed_treatment_cost) {
        this.seed_treatment_cost = seed_treatment_cost;
    }

    public String getIrrigation_cost() {
        return irrigation_cost;
    }

    public void setIrrigation_cost(String irrigation_cost) {
        this.irrigation_cost = irrigation_cost;
    }

    public String getFertilizer_cost() {
        return fertilizer_cost;
    }

    public void setFertilizer_cost(String fertilizer_cost) {
        this.fertilizer_cost = fertilizer_cost;
    }

    public String getPlant_protection_cost() {
        return plant_protection_cost;
    }

    public void setPlant_protection_cost(String plant_protection_cost) {
        this.plant_protection_cost = plant_protection_cost;
    }

    public String getHarvesting_cost() {
        return harvesting_cost;
    }

    public void setHarvesting_cost(String harvesting_cost) {
        this.harvesting_cost = harvesting_cost;
    }

    public String getMachinery_cost() {
        return machinery_cost;
    }

    public void setMachinery_cost(String machinery_cost) {
        this.machinery_cost = machinery_cost;
    }

    public String getLabor_cost() {
        return labor_cost;
    }

    public void setLabor_cost(String labor_cost) {
        this.labor_cost = labor_cost;
    }

    public String getOther_cost() {
        return other_cost;
    }

    public void setOther_cost(String other_cost) {
        this.other_cost = other_cost;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
    }

    public String getGross_income() {
        return gross_income;
    }

    public void setGross_income(String gross_income) {
        this.gross_income = gross_income;
    }

    public String getOther_income() {
        return other_income;
    }

    public void setOther_income(String other_income) {
        this.other_income = other_income;
    }

    public String getTotal_income() {
        return total_income;
    }

    public void setTotal_income(String total_income) {
        this.total_income = total_income;
    }

    public String getNet_income() {
        return net_income;
    }

    public void setNet_income(String net_income) {
        this.net_income = net_income;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", nursery_cost='" + nursery_cost + '\'' +
                ", seed_treatment_cost='" + seed_treatment_cost + '\'' +
                ", irrigation_cost='" + irrigation_cost + '\'' +
                ", fertilizer_cost='" + fertilizer_cost + '\'' +
                ", plant_protection_cost='" + plant_protection_cost + '\'' +
                ", harvesting_cost='" + harvesting_cost + '\'' +
                ", machinery_cost='" + machinery_cost + '\'' +
                ", labor_cost='" + labor_cost + '\'' +
                ", other_cost='" + other_cost + '\'' +
                ", total_cost='" + total_cost + '\'' +
                ", gross_income='" + gross_income + '\'' +
                ", other_income='" + other_income + '\'' +
                ", total_income='" + total_income + '\'' +
                ", net_income='" + net_income + '\'' +
                '}';
    }
}
