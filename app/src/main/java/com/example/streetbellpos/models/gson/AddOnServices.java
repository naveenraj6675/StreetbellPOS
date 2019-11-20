package com.example.streetbellpos.models.gson;

import com.google.gson.annotations.SerializedName;

public class AddOnServices {
    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("optional")
    String optional;
    @SerializedName("price")
    String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOptional() {
        return optional;
    }

    public void setOptional(String optional) {
        this.optional = optional;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
