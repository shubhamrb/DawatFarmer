package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VarietyModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("code")
    String code;

    @SerializedName("project_area")
    String project_area;

    @SerializedName("variety_id")
    String variety_id;

    String land_area;

    boolean selected;

    public String getVariety_id() {
        return variety_id;
    }

    public void setVariety_id(String variety_id) {
        this.variety_id = variety_id;
    }

    public String getProject_area() {
        return project_area;
    }

    public void setProject_area(String project_area) {
        this.project_area = project_area;
    }

    public String getLand_area() {
        return land_area;
    }

    public void setLand_area(String land_area) {
        this.land_area = land_area;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", project_area='" + project_area + '\'' +
                ", variety_id='" + variety_id + '\'' +
                ", land_area='" + land_area + '\'' +
                ", selected=" + selected +
                '}';
    }
}
