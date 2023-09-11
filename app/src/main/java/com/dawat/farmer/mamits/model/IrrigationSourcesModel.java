package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IrrigationSourcesModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("irrigation_sources")
    String irrigation_sources;

    @SerializedName("other_name")
    String other_name;

    @SerializedName("farm_name")
    String farm_name;

    @SerializedName("farm_field")
    String farm_field;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "IrrigationSourcesModel{" +
                "id='" + id + '\'' +
                ", irrigation_sources='" + irrigation_sources + '\'' +
                ", other_name='" + other_name + '\'' +
                ", farm_name='" + farm_name + '\'' +
                ", farm_field='" + farm_field + '\'' +
                '}';
    }
}
