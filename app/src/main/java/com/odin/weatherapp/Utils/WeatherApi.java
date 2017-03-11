package com.odin.weatherapp.Utils;

/**
 * Created by mobil on 04.03.2017.
 */

import com.odin.weatherapp.ModelsRetro.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



    public interface WeatherApi {
       /* @GET("data/2.5/weather")
        void getWeatherFromApi (
                @Query("q") String cityName,
                @Query("APPID") String appId,
                Call<ArrayList<WeatherData>> callback);
        @GET("/data/2.5/weather")
        WeatherData getWeatherFromApiSync (
                @Query("q") String cityName,
                @Query("APPID") String appId);
                */

       @GET("data/2.5/forecast")
        Call<WeatherData> getWeather(
        @Query("q") String cityName,
        @Query("APPID") String appId,
        @Query("units") String units
        );

    }

