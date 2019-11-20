package com.example.streetbellpos.models.gson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductCategories {
    @SerializedName("categoryid")
    String catId;
    @SerializedName("categorytext")
    String catText;
    @SerializedName("product")
    ArrayList<Products> products;

    public ProductCategories(String catId, String catText, ArrayList<Products> products) {
        this.catId = catId;
        this.catText = catText;
        this.products = products;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatText() {
        return catText;
    }

    public void setCatText(String catText) {
        this.catText = catText;
    }

    public ArrayList<Products> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Products> products) {
        this.products = products;
    }
}
