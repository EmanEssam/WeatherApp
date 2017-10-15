package com.appswarrior.www.weatherapp.network;

import com.appswarrior.www.weatherapp.network.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Eman Essam on 14/10/2017.
 */

public interface ApiHelper {

    @GET("weather?")
    Call<WeatherResponse> getWeatherData(@Query("lat") double lat, @Query("lon") double lon, @Query("units") String units, @Query("appid") String api_key);
}
