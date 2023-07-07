package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CartModel implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("user_id")
    String user_id;

    @SerializedName("category_id")
    String category_id;

    @SerializedName("product_id")
    String product_id;

    @SerializedName("quantity")
    String quantity;

    @SerializedName("created_at")
    String created_at;

    @SerializedName("name_en")
    String name_en;

    @SerializedName("name_hi")
    String name_hi;

    @SerializedName("cat_title_en")
    String cat_title_en;

    @SerializedName("cat_title_hi")
    String cat_title_hi;

    @SerializedName("selling_price")
    String selling_price;

    @SerializedName("regular_price")
    String regular_price;

    @SerializedName("image")
    String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
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

    public String getCat_title_en() {
        return cat_title_en;
    }

    public void setCat_title_en(String cat_title_en) {
        this.cat_title_en = cat_title_en;
    }

    public String getCat_title_hi() {
        return cat_title_hi;
    }

    public void setCat_title_hi(String cat_title_hi) {
        this.cat_title_hi = cat_title_hi;
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

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", category_id='" + category_id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", quantity='" + quantity + '\'' +
                ", created_at='" + created_at + '\'' +
                ", name_en='" + name_en + '\'' +
                ", name_hi='" + name_hi + '\'' +
                ", cat_title_en='" + cat_title_en + '\'' +
                ", cat_title_hi='" + cat_title_hi + '\'' +
                ", selling_price='" + selling_price + '\'' +
                ", regular_price='" + regular_price + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
