package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("category_id")
    String category_id;

    @SerializedName("name_en")
    String name_en;

    @SerializedName("name_hi")
    String name_hi;

    @SerializedName("description_en")
    String description_en;

    @SerializedName("description_hi")
    String description_hi;

    @SerializedName("selling_price")
    String selling_price;

    @SerializedName("regular_price")
    String regular_price;

    @SerializedName("image")
    String image;

    @SerializedName("attachment")
    String attachment;


    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getName_hi() {
        return name_hi;
    }

    public void setName_hi(String name_hi) {
        this.name_hi = name_hi;
    }

    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
    }

    public String getDescription_hi() {
        return description_hi;
    }

    public void setDescription_hi(String description_hi) {
        this.description_hi = description_hi;
    }

    public String getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(String selling_price) {
        this.selling_price = selling_price;
    }

    public String getRegular_price() {
        return regular_price;
    }

    public void setRegular_price(String regular_price) {
        this.regular_price = regular_price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", category_id='" + category_id + '\'' +
                ", name_en='" + name_en + '\'' +
                ", name_hi='" + name_hi + '\'' +
                ", description_en='" + description_en + '\'' +
                ", description_hi='" + description_hi + '\'' +
                ", selling_price='" + selling_price + '\'' +
                ", regular_price='" + regular_price + '\'' +
                ", image='" + image + '\'' +
                ", attachment='" + attachment + '\'' +
                '}';
    }
}
