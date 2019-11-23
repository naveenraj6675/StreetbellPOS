package com.example.streetbellpos.managers.retrofit;

import com.example.streetbellpos.models.gson.BookingResponse;
import com.example.streetbellpos.models.gson.CategoryResponse;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MainApi {
    @POST("loadservices_pos.php")
    Observable<CategoryResponse> getCategory(@Body JsonObject gsonObject);

    @POST("offlinebookings_toserver.php")
    Observable<BookingResponse> uploadBooking(@Body JsonObject gsonObject);
}
