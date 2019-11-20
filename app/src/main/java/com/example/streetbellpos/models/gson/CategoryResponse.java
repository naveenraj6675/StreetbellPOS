package com.example.streetbellpos.models.gson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoryResponse {
    @SerializedName("error")
    String error;
    @SerializedName("status_msg")
    String statusMessage;
    @SerializedName("productcategories")
    ArrayList<ProductCategories> categoriesList;

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

    public ArrayList<ProductCategories> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(ArrayList<ProductCategories> categoriesList) {
        this.categoriesList = categoriesList;
    }
}
