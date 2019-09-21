package com.example.marketim.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetail {

    @SerializedName("orderDetail")
    @Expose
    private String OrderDetail;

    @SerializedName("summaryPrice")
    @Expose
    private double SummaryPrice;

    public String getOrderDetail() {
        return OrderDetail;
    }

    public void setOrderDetail(String orderDetail) {
        OrderDetail = orderDetail;
    }

    public double getSummaryPrice() {
        return SummaryPrice;
    }

    public void setSummaryPrice(double summaryPrice) {
        SummaryPrice = summaryPrice;
    }

    public ProductDetail() {
    }

    public ProductDetail(String orderDetail, double summaryPrice) {
        OrderDetail = orderDetail;
        SummaryPrice = summaryPrice;
    }
}
