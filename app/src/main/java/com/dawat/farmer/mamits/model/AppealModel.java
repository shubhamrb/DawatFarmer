package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AppealModel implements Serializable {

    @SerializedName("farmer_id")
    String farmer_id;

    @SerializedName("farmer_name")
    String farmer_name;

    @SerializedName("appeals")
    List<Appeal> appeals;

    public static class Appeal implements Serializable {
        @SerializedName("id")
        String id;

        @SerializedName("assign_to")
        String assign_to;

        @SerializedName("status")
        String status;

        @SerializedName("farmer_id")
        String farmer_id;

        @SerializedName("resion")
        String resion;

        @SerializedName("created_by")
        String created_by;

        @SerializedName("created_at")
        String created_at;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAssign_to() {
            return assign_to;
        }

        public void setAssign_to(String assign_to) {
            this.assign_to = assign_to;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getFarmer_id() {
            return farmer_id;
        }

        public void setFarmer_id(String farmer_id) {
            this.farmer_id = farmer_id;
        }

        public String getResion() {
            return resion;
        }

        public void setResion(String resion) {
            this.resion = resion;
        }

        public String getCreated_by() {
            return created_by;
        }

        public void setCreated_by(String created_by) {
            this.created_by = created_by;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        @Override
        public String toString() {
            return "{" +
                    "id='" + id + '\'' +
                    ", assign_to='" + assign_to + '\'' +
                    ", status='" + status + '\'' +
                    ", farmer_id='" + farmer_id + '\'' +
                    ", resion='" + resion + '\'' +
                    ", created_by='" + created_by + '\'' +
                    ", created_at='" + created_at + '\'' +
                    '}';
        }
    }


    public String getFarmer_id() {
        return farmer_id;
    }

    public void setFarmer_id(String farmer_id) {
        this.farmer_id = farmer_id;
    }

    public String getFarmer_name() {
        return farmer_name;
    }

    public void setFarmer_name(String farmer_name) {
        this.farmer_name = farmer_name;
    }

    public List<Appeal> getAppeals() {
        return appeals;
    }

    public void setAppeals(List<Appeal> appeals) {
        this.appeals = appeals;
    }

    @Override
    public String toString() {
        return "{" +
                "farmer_id='" + farmer_id + '\'' +
                ", farmer_name='" + farmer_name + '\'' +
                ", appeals=" + appeals +
                '}';
    }
}
