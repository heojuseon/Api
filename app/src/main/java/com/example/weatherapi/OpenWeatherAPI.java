package com.example.weatherapi;

import com.example.weatherapi.model.OpenWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherAPI {

    @GET("weather")
    Call<OpenWeather> getWeather(@Query("q") String q,
                                 @Query("appid") String appid,
                                 @Query("lang") String lang);


}
