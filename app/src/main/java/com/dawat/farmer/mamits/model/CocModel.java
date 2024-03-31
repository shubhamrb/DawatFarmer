package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CocModel implements Serializable {

    @SerializedName("plantprotectiontotal")
    float plantprotectiontotal;

    @SerializedName("fertilizationtotal")
    float fertilizationtotal;

    @SerializedName("labourtotal")
    float labourtotal;

    @SerializedName("machinerytotal")
    float machinerytotal;

    @SerializedName("nurserytotal")
    float nurserytotal;

    @SerializedName("harvestingtotal")
    float harvestingtotal;

    @SerializedName("irrigationtotal")
    float irrigationtotal;

    @SerializedName("seedtreatmenttotal")
    float seedtreatmenttotal;

    @SerializedName("purchasingtotal")
    float purchasingtotal;

    @SerializedName("totalcost")
    String totalcost;

    @SerializedName("nettotalincome")
    String nettotalincome;

    public String getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(String totalcost) {
        this.totalcost = totalcost;
    }

    public String getNettotalincome() {
        return nettotalincome;
    }

    public void setNettotalincome(String nettotalincome) {
        this.nettotalincome = nettotalincome;
    }

    public float getPlantprotectiontotal() {
        return plantprotectiontotal;
    }

    public void setPlantprotectiontotal(float plantprotectiontotal) {
        this.plantprotectiontotal = plantprotectiontotal;
    }

    public float getFertilizationtotal() {
        return fertilizationtotal;
    }

    public void setFertilizationtotal(float fertilizationtotal) {
        this.fertilizationtotal = fertilizationtotal;
    }

    public float getLabourtotal() {
        return labourtotal;
    }

    public void setLabourtotal(float labourtotal) {
        this.labourtotal = labourtotal;
    }

    public float getMachinerytotal() {
        return machinerytotal;
    }

    public void setMachinerytotal(float machinerytotal) {
        this.machinerytotal = machinerytotal;
    }

    public float getNurserytotal() {
        return nurserytotal;
    }

    public void setNurserytotal(float nurserytotal) {
        this.nurserytotal = nurserytotal;
    }

    public float getHarvestingtotal() {
        return harvestingtotal;
    }

    public void setHarvestingtotal(float harvestingtotal) {
        this.harvestingtotal = harvestingtotal;
    }

    public float getIrrigationtotal() {
        return irrigationtotal;
    }

    public void setIrrigationtotal(float irrigationtotal) {
        this.irrigationtotal = irrigationtotal;
    }

    public float getSeedtreatmenttotal() {
        return seedtreatmenttotal;
    }

    public void setSeedtreatmenttotal(float seedtreatmenttotal) {
        this.seedtreatmenttotal = seedtreatmenttotal;
    }

    public float getPurchasingtotal() {
        return purchasingtotal;
    }

    public void setPurchasingtotal(float purchasingtotal) {
        this.purchasingtotal = purchasingtotal;
    }
}
