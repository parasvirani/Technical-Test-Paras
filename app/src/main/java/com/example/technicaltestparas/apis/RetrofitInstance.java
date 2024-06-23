package com.example.technicaltestparas.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static String baseUrl = "https://devapi.recureme.com/";

    private static Retrofit retrofit;

    public static Retrofit getRetroClient(){
        if (retrofit == null) {
             retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
