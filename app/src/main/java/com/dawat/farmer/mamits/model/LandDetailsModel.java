package com.dawat.farmer.mamits.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LandDetailsModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("farm_id")
    String farm_id;

    @SerializedName("farm_field_id")
    String farm_field_id;

    @SerializedName("state_id")
    String state_id;

    @SerializedName("district_id")
    String district_id;

    @SerializedName("block")
    String block;

    @SerializedName("village")
    String village;

    @SerializedName("land_area")
    String land_area;

    @SerializedName("land_area_owned")
    String land_area_owned;

    @SerializedName("land_area_onlease")
    String land_area_onlease;

    @SerializedName("land_area_underproject")
    String land_area_underproject;

    @SerializedName("ll_cost")
    String ll_cost;

    @SerializedName("geo_fencing_data")
    List<LatLng> geo_fencing_data;

    @SerializedName("farm_name")
    String farm_name;

    @SerializedName("farm_field")
    String farm_field;

    @SerializedName("state")
    String state;

    @SerializedName("district")
    String district;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getLand_area() {
        return land_area;
    }

    public void setLand_area(String land_area) {
        this.land_area = land_area;
    }

    public String getLand_area_owned() {
        return land_area_owned;
    }

    public void setLand_area_owned(String land_area_owned) {
        this.land_area_owned = land_area_owned;
    }

    public String getLand_area_onlease() {
        return land_area_onlease;
    }

    public void setLand_area_onlease(String land_area_onlease) {
        this.land_area_onlease = land_area_onlease;
    }

    public String getLand_area_underproject() {
        return land_area_underproject;
    }

    public void setLand_area_underproject(String land_area_underproject) {
        this.land_area_underproject = land_area_underproject;
    }

    public String getLl_cost() {
        return ll_cost;
    }

    public void setLl_cost(String ll_cost) {
        this.ll_cost = ll_cost;
    }

    public List<LatLng> getGeo_fencing_data() {
        return geo_fencing_data;
    }

    public void setGeo_fencing_data(List<LatLng> geo_fencing_data) {
        this.geo_fencing_data = geo_fencing_data;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", farm_id='" + farm_id + '\'' +
                ", farm_field_id='" + farm_field_id + '\'' +
                ", state_id='" + state_id + '\'' +
                ", district_id='" + district_id + '\'' +
                ", block='" + block + '\'' +
                ", village='" + village + '\'' +
                ", land_area='" + land_area + '\'' +
                ", land_area_owned='" + land_area_owned + '\'' +
                ", land_area_onlease='" + land_area_onlease + '\'' +
                ", land_area_underproject='" + land_area_underproject + '\'' +
                ", ll_cost='" + ll_cost + '\'' +
                ", geo_fencing_data=" + geo_fencing_data +
                ", farm_name='" + farm_name + '\'' +
                ", farm_field='" + farm_field + '\'' +
                ", state='" + state + '\'' +
                ", district='" + district + '\'' +
                '}';
    }
}
