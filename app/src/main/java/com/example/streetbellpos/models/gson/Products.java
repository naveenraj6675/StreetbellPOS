package com.example.streetbellpos.models.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Products {

    @SerializedName("pid")
    String pId;
    @SerializedName("sortid")
    String sortId;
    @SerializedName("name")
    String name;
    @SerializedName("desc")
    String desc;
    @SerializedName("catid")
    String catId;
    @SerializedName("catname")
    String catName;
    @SerializedName("subcatid")
    String subCatId;
    @SerializedName("subcatname")
    String subCatName;
    @SerializedName("time")
    String time;
    @SerializedName("shareInt")
    String shareInt;
    @SerializedName("orderedInt")
    String orderInt;
    @SerializedName("liked")
    Boolean likes;
    @SerializedName("likeInt")
    String linkInt;
    @SerializedName("price")
    String price;
    @SerializedName("currency_code")
    String currencyCode;
    @SerializedName("currency_symbol")
    String currencySymbol;
    @SerializedName("unit")
    String unit;
    @SerializedName("pricedetails")
    ArrayList<PriecDetails> priceList;
    @SerializedName("max_capacity")
    String maxCapacity;
    @SerializedName("stock")
    String stock;
    @SerializedName("images")
    ArrayList<Images> imageList;
    @SerializedName("isaddon")
    String isAddon;
    @SerializedName("Addonservices")
    ArrayList<AddOnServices> addonList;
    @SerializedName("enablegst")
    String enableGst;
    @SerializedName("gstpercent")
    String gstPercent;
    @SerializedName("othercharges")
    String otherCharges;


    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(String subCatId) {
        this.subCatId = subCatId;
    }

    public String getSubCatName() {
        return subCatName;
    }

    public void setSubCatName(String subCatName) {
        this.subCatName = subCatName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getShareInt() {
        return shareInt;
    }

    public void setShareInt(String shareInt) {
        this.shareInt = shareInt;
    }

    public String getOrderInt() {
        return orderInt;
    }

    public void setOrderInt(String orderInt) {
        this.orderInt = orderInt;
    }

    public Boolean getLikes() {
        return likes;
    }

    public void setLikes(Boolean likes) {
        this.likes = likes;
    }

    public String getLinkInt() {
        return linkInt;
    }

    public void setLinkInt(String linkInt) {
        this.linkInt = linkInt;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public ArrayList<PriecDetails> getPriceList() {
        return priceList;
    }

    public void setPriceList(ArrayList<PriecDetails> priceList) {
        this.priceList = priceList;
    }

    public String getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(String maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public ArrayList<Images> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<Images> imageList) {
        this.imageList = imageList;
    }

    public String getIsAddon() {
        return isAddon;
    }

    public void setIsAddon(String isAddon) {
        this.isAddon = isAddon;
    }

    public ArrayList<AddOnServices> getAddonList() {
        return addonList;
    }

    public void setAddonList(ArrayList<AddOnServices> addonList) {
        this.addonList = addonList;
    }

    public String getEnableGst() {
        return enableGst;
    }

    public void setEnableGst(String enableGst) {
        this.enableGst = enableGst;
    }

    public String getGstPercent() {
        return gstPercent;
    }

    public void setGstPercent(String gstPercent) {
        this.gstPercent = gstPercent;
    }

    public String getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(String otherCharges) {
        this.otherCharges = otherCharges;
    }


    public static Products parse(String exerciseData) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.fromJson(exerciseData, Products.class);
    }

    public static String toJson(Products exercise) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(exercise);
    }
}
