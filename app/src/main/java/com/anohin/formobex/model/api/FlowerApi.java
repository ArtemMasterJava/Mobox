package com.anohin.formobex.model.api;

import retrofit.Callback;
import retrofit.http.GET;

///**
// * Created by Artem on 15.09.2016.
// */

public interface FlowerApi {

    @GET("/feeds/flowers.json")
    void getFlowers(Callback<String> flowers);
}
