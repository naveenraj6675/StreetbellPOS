package com.example.streetbellpos.models.gson;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

@Entity(tableName = "bookingDetails", primaryKeys = {"id", "uId"})
public class BookingDetails {


    @SerializedName("id")
    @NonNull
    public String id;
    @SerializedName("uId")
    @NonNull
    public String uId;
    @SerializedName("int_sellerid")
    public String intSellerid;
    @SerializedName("apporweb")
    public String apporweb;
    @SerializedName("int_pid")
    public String intPid;
    @SerializedName("")
    public String floatUnitprice;
    @SerializedName("order_status")
    public String orderStatus;
    @SerializedName("str_date")
    public String strDate;
    @SerializedName("int_created")
    public String intCreated;
    @SerializedName("currency_code")
    public String currencyCode;
    @SerializedName("currency_symbol")
    public String currencySymbol;
    @SerializedName("orderid")
    public String orderid;
    @SerializedName("gtotal")
    public String gtotal;
    @SerializedName("stotal")
    public String stotal;
    @SerializedName("gstpercent")
    public String gstpercent;
    @SerializedName("othercharges")
    public String othercharges;
    @SerializedName("enablegst")
    public String enablegst;
    @SerializedName("gstamount")
    public String gstamount;
    @SerializedName("bookingdate")
    public String bookingdate;
    @SerializedName("customername")
    public String customername;
    @SerializedName("customercat")
    public String customercat;
    @SerializedName("proofdoc")
    public String proofdoc;
    @SerializedName("pdocnumber")
    public String pdocnumber;
    @SerializedName("shopping_amount")
    public String shoppingAmount;
    @SerializedName("int_status")
    public String intStatus;
    @SerializedName("str_userip")
    public String strUserip;
    @SerializedName("usecod")
    public String usecod;
    @SerializedName("addon_include")
    public String addonInclude;
    //    @SerializedName("addon_service")
//    public List<AddOnServices> addOnServicesList;
    @SerializedName("addon_gstamount")
    public String addonGstamount;
    @SerializedName("addon_stotal")
    public String addonStotal;
    @SerializedName("addon_total")
    public String addonTotal;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getIntSellerid() {
        return intSellerid;
    }

    public void setIntSellerid(String intSellerid) {
        this.intSellerid = intSellerid;
    }

    public String getApporweb() {
        return apporweb;
    }

    public void setApporweb(String apporweb) {
        this.apporweb = apporweb;
    }

    public String getIntPid() {
        return intPid;
    }

    public void setIntPid(String intPid) {
        this.intPid = intPid;
    }

    public String getFloatUnitprice() {
        return floatUnitprice;
    }

    public void setFloatUnitprice(String floatUnitprice) {
        this.floatUnitprice = floatUnitprice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public String getIntCreated() {
        return intCreated;
    }

    public void setIntCreated(String intCreated) {
        this.intCreated = intCreated;
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

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getGtotal() {
        return gtotal;
    }

    public void setGtotal(String gtotal) {
        this.gtotal = gtotal;
    }

    public String getStotal() {
        return stotal;
    }

    public void setStotal(String stotal) {
        this.stotal = stotal;
    }

    public String getGstpercent() {
        return gstpercent;
    }

    public void setGstpercent(String gstpercent) {
        this.gstpercent = gstpercent;
    }

    public String getOthercharges() {
        return othercharges;
    }

    public void setOthercharges(String othercharges) {
        this.othercharges = othercharges;
    }

    public String getEnablegst() {
        return enablegst;
    }

    public void setEnablegst(String enablegst) {
        this.enablegst = enablegst;
    }

    public String getGstamount() {
        return gstamount;
    }

    public void setGstamount(String gstamount) {
        this.gstamount = gstamount;
    }

    public String getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate(String bookingdate) {
        this.bookingdate = bookingdate;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomercat() {
        return customercat;
    }

    public void setCustomercat(String customercat) {
        this.customercat = customercat;
    }

    public String getProofdoc() {
        return proofdoc;
    }

    public void setProofdoc(String proofdoc) {
        this.proofdoc = proofdoc;
    }

    public String getPdocnumber() {
        return pdocnumber;
    }

    public void setPdocnumber(String pdocnumber) {
        this.pdocnumber = pdocnumber;
    }

    public String getShoppingAmount() {
        return shoppingAmount;
    }

    public void setShoppingAmount(String shoppingAmount) {
        this.shoppingAmount = shoppingAmount;
    }

    public String getIntStatus() {
        return intStatus;
    }

    public void setIntStatus(String intStatus) {
        this.intStatus = intStatus;
    }

    public String getStrUserip() {
        return strUserip;
    }

    public void setStrUserip(String strUserip) {
        this.strUserip = strUserip;
    }

    public String getUsecod() {
        return usecod;
    }

    public void setUsecod(String usecod) {
        this.usecod = usecod;
    }


    public String getAddonInclude() {
        return addonInclude;
    }

    public void setAddonInclude(String addonInclude) {
        this.addonInclude = addonInclude;
    }

//    public List<AddOnServices> getAddonDetails() {
//        return addOnServicesList;
//    }
//
//    public void setAddonDetails(List<AddOnServices> addonDetails) {
//        this.addOnServicesList = addonDetails;
//    }

    public String getAddonGstamount() {
        return addonGstamount;
    }

    public void setAddonGstamount(String addonGstamount) {
        this.addonGstamount = addonGstamount;
    }

    public String getAddonStotal() {
        return addonStotal;
    }

    public void setAddonStotal(String addonStotal) {
        this.addonStotal = addonStotal;
    }

    public String getAddonTotal() {
        return addonTotal;
    }

    public void setAddonTotal(String addonTotal) {
        this.addonTotal = addonTotal;
    }


    public class Converters {
        @TypeConverter
        public ArrayList<String> fromString(String value) {
            Type listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            return new Gson().fromJson(value, listType);
        }

        @TypeConverter
        public String fromArrayList(ArrayList<String> list) {
            Gson gson = new Gson();
            String json = gson.toJson(list);
            return json;
        }
    }


}
