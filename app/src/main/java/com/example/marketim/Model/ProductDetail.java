package com.example.marketim.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetail implements Parcelable{

    @SerializedName("orderDetail")
    @Expose
    private String OrderDetail;

    @SerializedName("summaryPrice")
    @Expose
    private double SummaryPrice;

    public String getOrderDetail() {
        return OrderDetail;
    }

    public double getSummaryPrice() {
        return SummaryPrice;
    }

    private ProductDetail() {
    }

    public ProductDetail(String orderDetail, double summaryPrice) {
        OrderDetail = orderDetail;
        SummaryPrice = summaryPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(OrderDetail);
        parcel.writeDouble(SummaryPrice);

    }
    public static final Parcelable.Creator<ProductDetail> CREATOR = new Parcelable.Creator<ProductDetail>() {
        public ProductDetail createFromParcel(Parcel in) {
            return new ProductDetail();
        }

        public ProductDetail[] newArray(int size) {
            return new ProductDetail[size];
        }
    };
}
