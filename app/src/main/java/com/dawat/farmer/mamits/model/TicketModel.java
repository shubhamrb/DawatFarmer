package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TicketModel implements Serializable {

    @SerializedName("farmer_id")
    String farmer_id;

    @SerializedName("farmer_name")
    String farmer_name;

    @SerializedName("tickets")
    List<Tickets> tickets;

    public static class Tickets implements Serializable {
        @SerializedName("id")
        String id;

        @SerializedName("farmer_id")
        String farmer_id;

        @SerializedName("title")
        String title;

        @SerializedName("description")
        String description;

        @SerializedName("created_by")
        String created_by;

        @SerializedName("created_at")
        String created_at;

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFarmer_id() {
            return farmer_id;
        }

        public void setFarmer_id(String farmer_id) {
            this.farmer_id = farmer_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCreated_by() {
            return created_by;
        }

        public void setCreated_by(String created_by) {
            this.created_by = created_by;
        }

        @Override
        public String toString() {
            return "{" +
                    "id='" + id + '\'' +
                    ", farmer_id='" + farmer_id + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
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

    public List<Tickets> getTickets() {
        return tickets;
    }

    public void setTickets(List<Tickets> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "{" +
                "farmer_id='" + farmer_id + '\'' +
                ", farmer_name='" + farmer_name + '\'' +
                ", tickets=" + tickets +
                '}';
    }
}
