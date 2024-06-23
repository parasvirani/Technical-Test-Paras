package com.example.technicaltestparas.apis;

import com.example.technicaltestparas.model.GetSlotsDetailsRes;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("getSlotsDetails")
    Call<GetSlotsDetailsRes> getSlotsDetails();

}
