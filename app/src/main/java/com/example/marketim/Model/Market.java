package com.example.marketim.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Market {

    @SerializedName("date")
    @Expose
    private String Date;

    @SerializedName("month")
    @Expose
    private String Month;

    @SerializedName("marketName")
    @Expose
    private String MarketName;

    @SerializedName("orderName")
    @Expose
    private String OrderName;

    @SerializedName("productPrice")
    @Expose
    private double ProductPrice;

    @SerializedName("productState")
    @Expose
    private String ProductState;

    @SerializedName("productDetail")
    @Expose
    private ProductDetail ProductDetail;

    public Market() {
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getMarketName() {
        return MarketName;
    }

    public void setMarketName(String marketName) {
        MarketName = marketName;
    }

    public String getOrderName() {
        return OrderName;
    }

    public void setOrderName(String orderName) {
        OrderName = orderName;
    }

    public double getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(double productPrice) {
        ProductPrice = productPrice;
    }

    public String getProductState() {
        return ProductState;
    }

    public void setProductState(String productState) {
        ProductState = productState;
    }

    public ProductDetail getProductDetail() {
        return ProductDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        ProductDetail = productDetail;
    }

    public Market(String date, String month, String marketName, String orderName, double productPrice, String productState, ProductDetail productDetail) {
        Date = date;
        Month = month;
        MarketName = marketName;
        OrderName = orderName;
        ProductPrice = productPrice;
        ProductState = productState;
        ProductDetail = productDetail;
    }
}
