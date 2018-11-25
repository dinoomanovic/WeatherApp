package com.odin.weatherapp.Utils;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by mobil on 05.03.2017.
 */

public class WeatherRestAdapter {
    private static final String BASE_URL = "http://api.openweathermap.org";
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = builder.build();

    public WeatherApi createService() {
        return retrofit.create(WeatherApi.class);
    }
}
