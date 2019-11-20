package com.example.streetbellpos.managers.retrofit;

import com.example.streetbellpos.models.gson.LoginResponse;
import com.example.streetbellpos.models.gson.MainLoginResponse;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LaunchApi {


    @POST("loginuser_phone_pos.php")
    Observable<ArrayList<LoginResponse>> getMobileLogin(@Body JsonObject gsonObject);

    @POST("password_check_pos.php")
    Observable<ArrayList<MainLoginResponse>> getMainLogin(@Body JsonObject gsonObject);


}
