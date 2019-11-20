package com.example.streetbellpos.models.gson;

import com.google.gson.annotations.SerializedName;

public class PriecDetails {
    @SerializedName("id")
    String id;
    @SerializedName("type")
    String type;
    @SerializedName("price")
    String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
