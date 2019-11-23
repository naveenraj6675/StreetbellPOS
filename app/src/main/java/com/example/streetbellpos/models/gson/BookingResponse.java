package com.example.streetbellpos.models.gson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookingResponse {

    @SerializedName("error")
    String error;
    @SerializedName("status_message")
    String statusMessage;
    @SerializedName("orders")
    ArrayList<Orders> orderList;


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public ArrayList<Orders> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Orders> orderList) {
        this.orderList = orderList;
    }
}
