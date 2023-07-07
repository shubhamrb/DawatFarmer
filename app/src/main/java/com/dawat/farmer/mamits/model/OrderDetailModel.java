package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderDetailModel implements Serializable {

    @SerializedName("order")
    OrderModel order;

    @SerializedName("data")
    List<ProductModel> data;

    @SerializedName("delivery")
    int delivery;

    @SerializedName("total")
    int total;

    public int getDelivery() {
        return delivery;
    }

    public void setDelivery(int delivery) {
        this.delivery = delivery;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public List<ProductModel> getData() {
        return data;
    }

    public void setData(List<ProductModel> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "order=" + order +
                ", data=" + data +
                ", delivery=" + delivery +
                ", total=" + total +
                '}';
    }

    public static class OrderModel implements Serializable {
        @SerializedName("status")
        private String status;

        @SerializedName("order_date")
        private String order_date;

        @SerializedName("total")
        private String total;

        @SerializedName("orderId")
        private String orderId;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOrder_date() {
            return order_date;
        }

        public void setOrder_date(String order_date) {
            this.order_date = order_date;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        @Override
        public String toString() {
            return "{" +
                    "status='" + status + '\'' +
                    ", order_date='" + order_date + '\'' +
                    ", total='" + total + '\'' +
                    ", orderId='" + orderId + '\'' +
                    '}';
        }
    }

    public static class ProductModel implements Serializable {
        @SerializedName("qty")
        private String qty;

        @SerializedName("base_price")
        private String base_price;

        @SerializedName("final_price")
        private String final_price;

        @SerializedName("image")
        private String image;

        @SerializedName("name_en")
        private String name_en;

        @SerializedName("name_hi")
        private String name_hi;

        @SerializedName("cat_title_en")
        private String cat_title_en;

        @SerializedName("cat_title_hi")
        private String cat_title_hi;

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getBase_price() {
            return base_price;
        }

        public void setBase_price(String base_price) {
            this.base_price = base_price;
        }

        public String getFinal_price() {
            return final_price;
        }

        public void setFinal_price(String final_price) {
            this.final_price = final_price;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

        @Override
        public String toString() {
            return "{" +
                    "qty='" + qty + '\'' +
                    ", base_price='" + base_price + '\'' +
                    ", final_price='" + final_price + '\'' +
                    ", image='" + image + '\'' +
                    ", name_en='" + name_en + '\'' +
                    ", name_hi='" + name_hi + '\'' +
                    ", cat_title_en='" + cat_title_en + '\'' +
                    ", cat_title_hi='" + cat_title_hi + '\'' +
                    '}';
        }
    }



}
