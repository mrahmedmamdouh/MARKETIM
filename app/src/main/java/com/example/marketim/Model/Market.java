package com.example.marketim.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Market implements Parcelable {

    @SerializedName("date")
    @Expose
    private final String Date;

    @SerializedName("month")
    @Expose
    private final String Month;

    @SerializedName("marketName")
    @Expose
    private final String MarketName;

    @SerializedName("orderName")
    @Expose
    private final String OrderName;

    @SerializedName("productPrice")
    @Expose
    private final double ProductPrice;

    @SerializedName("productState")
    @Expose
    private final String ProductState;

    @SerializedName("productDetail")
    @Expose
    private ProductDetail ProductDetail;


    public String getDate() {
        return Date;
    }

    public String getMonth() {
        return Month;
    }

    public String getMarketName() {
        return MarketName;
    }

    public String getOrderName() {
        return OrderName;
    }

    public double getProductPrice() {
        return ProductPrice;
    }

    public String getProductState() {
        return ProductState;
    }

    public ProductDetail getProductDetail() {
        return ProductDetail;
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

    private Market(Parcel in) {
        Date = in.readString();
        Month = in.readString();
        MarketName = in.readString();
        OrderName = in.readString();
        ProductPrice = in.readDouble();
        ProductState = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Date);
        parcel.writeString(Month);
        parcel.writeString(MarketName);
        parcel.writeString(OrderName);
        parcel.writeString(ProductState);
        parcel.writeDouble(ProductPrice);

    }
    public static final Parcelable.Creator<Market> CREATOR = new Parcelable.Creator<Market>() {
        public Market createFromParcel(Parcel in) {
            return new Market(in);
        }

        public Market[] newArray(int size) {
            return new Market[size];
        }
    };

}
