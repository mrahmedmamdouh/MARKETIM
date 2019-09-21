package com.example.marketim.Retrofit;

import com.example.marketim.Model.Market;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {


    @GET("/")
    Call<List<Market>> getMyOrder();


}
