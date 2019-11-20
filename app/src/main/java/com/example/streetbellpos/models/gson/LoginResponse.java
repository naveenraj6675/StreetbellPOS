package com.example.streetbellpos.models.gson;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("error")
    String error;
    @SerializedName("status_message")
    String statusMessage;
    @SerializedName("uid")
    String uid;
    @SerializedName("deviceid")
    String deviceId;
    @SerializedName("mobilenumber")
    String mobileNumber;
    @SerializedName("psiname")
    String psiName;
    @SerializedName("tablenumber")
    String tableNumber;
    @SerializedName("shopid")
    String shopId;
    @SerializedName("shop_access_status")
    String shopAccessStatus;


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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPsiName() {
        return psiName;
    }

    public void setPsiName(String psiName) {
        this.psiName = psiName;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopAccessStatus() {
        return shopAccessStatus;
    }

    public void setShopAccessStatus(String shopAccessStatus) {
        this.shopAccessStatus = shopAccessStatus;
    }
}

