package com.example.streetbellpos.models.gson;

import java.util.ArrayList;

public class Printable {

    private String amount, mFor, on, guest, paymentMode;
    private ArrayList proofTypes, proofNumbers;
    private ArrayList<String> priceList;

    public Printable(String amount, String mFor, String on, String guest, String paymentMode, ArrayList proofTypes, ArrayList proofNumbers, ArrayList priceList) {
        this.amount = amount;
        this.mFor = mFor;
        this.on = on;
        this.guest = guest;
        this.paymentMode = paymentMode;
        this.proofTypes = proofTypes;
        this.proofNumbers = proofNumbers;
        this.priceList = priceList;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getmFor() {
        return mFor;
    }

    public void setmFor(String mFor) {
        this.mFor = mFor;
    }

    public String getOn() {
        return on;
    }

    public void setOn(String on) {
        this.on = on;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public ArrayList getProofTypes() {
        return proofTypes;
    }

    public void setProofTypes(ArrayList proofTypes) {
        this.proofTypes = proofTypes;
    }

    public ArrayList getProofNumbers() {
        return proofNumbers;
    }

    public void setProofNumbers(ArrayList proofNumbers) {
        this.proofNumbers = proofNumbers;
    }

    public ArrayList<String> getPriceList() {
        return priceList;
    }

    public void setPriceList(ArrayList priceList) {
        this.priceList = priceList;
    }
}
