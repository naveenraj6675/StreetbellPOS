package com.example.streetbellpos.models.gson;

import com.google.gson.annotations.SerializedName;

public class Images {
    @SerializedName("url")
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
