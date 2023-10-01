package com.example.lab3_20200334_iot.api;

import com.example.lab3_20200334_iot.pojos.RootPojo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("/api/")
    Call<RootPojo> getUserInfo();
}
