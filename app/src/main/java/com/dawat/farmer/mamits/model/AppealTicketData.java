package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AppealTicketData implements Serializable {

    @SerializedName("total")
    int total;

    @SerializedName("appeal")
    int appeal;

    @SerializedName("ticket")
    int ticket;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAppeal() {
        return appeal;
    }

    public void setAppeal(int appeal) {
        this.appeal = appeal;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "{" +
                "total=" + total +
                ", appeal=" + appeal +
                ", ticket=" + ticket +
                '}';
    }
}



