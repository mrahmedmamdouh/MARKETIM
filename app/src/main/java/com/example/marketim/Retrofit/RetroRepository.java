package com.example.marketim.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroRepository  {


    private static final String BASE_URL = "http://kariyertechchallenge.mockable.io/";



    public static Retrofit getInstance() {


        return new Retrofit.Builder()
           .baseUrl(BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .build();
    }



}
