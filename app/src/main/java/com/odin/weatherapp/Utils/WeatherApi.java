package com.odin.weatherapp.Utils;

/**
 * Created by mobil on 04.03.2017.
 */

import com.odin.weatherapp.ModelsRetro.WeatherData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("data/2.5/forecast")
    Observable<WeatherData> getWeather(
            @Query("q") String cityName,
            @Query("APPID") String appId,
            @Query("units") String units
    );
}

