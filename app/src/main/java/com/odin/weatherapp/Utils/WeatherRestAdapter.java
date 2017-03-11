package com.odin.weatherapp.Utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.GsonConverterFactory;


/**
 * Created by mobil on 05.03.2017.
 */

public class WeatherRestAdapter {
    private static final String BASE_URL = "http://api.openweathermap.org";
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = builder.build();
    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();
    public WeatherApi createService(){
        return retrofit.create(WeatherApi.class);
    }
}
