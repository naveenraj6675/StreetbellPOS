package com.example.streetbellpos.models.gson;

import com.google.gson.annotations.SerializedName;

public class Orders {

    @SerializedName("Exist")
    String exist;
    @SerializedName("orderid")
    String orderId;
    @SerializedName("insertid")
    String insertId;
    @SerializedName("appintid")
    String appIntId;


    public String getExist() {
        return exist;
    }

    public void setExist(String exist) {
        this.exist = exist;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getInsertId() {
        return insertId;
    }

    public void setInsertId(String insertId) {
        this.insertId = insertId;
    }

    public String getAppIntId() {
        return appIntId;
    }

    public void setAppIntId(String appIntId) {
        this.appIntId = appIntId;
    }
}
