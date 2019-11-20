package com.example.streetbellpos.models.gson;

import com.google.gson.annotations.SerializedName;

public class MainLoginResponse {
    @SerializedName("error")
    String error;
    @SerializedName("status_msg")
    String statusMsg;
    @SerializedName("shopid")
    String shopId;
    @SerializedName("is_service")
    String isService;
    @SerializedName("tablenumber")
    String tableNumber;
    @SerializedName("psiname")
    String psiName;


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getIsService() {
        return isService;
    }

    public void setIsService(String isService) {
        this.isService = isService;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getPsiName() {
        return psiName;
    }

    public void setPsiName(String psiName) {
        this.psiName = psiName;
    }
}
