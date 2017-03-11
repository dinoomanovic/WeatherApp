package com.odin.weatherapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.odin.weatherapp.Models.CityPreferences;
import com.odin.weatherapp.ModelsRetro.WeatherData;
import com.odin.weatherapp.Utils.WeatherApi;
import com.odin.weatherapp.Utils.WeatherRestAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mobil on 05.03.2017.
 */

public class DetailFragmentRetro extends Fragment {
    public ArrayList<WeatherData> weatherArrayList = new ArrayList<>();
    public DetailAdapterRetro weatherAdapter;
    public ProgressBar progressBar;
    public WeatherData weatherData;

    public static final String APP_ID = "14c1ad343b75d7f6fbe4f14fd766f0f7";
    public static final String METRIC = "metric";
    // public WeatherData weather = new WeatherData();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View weatherView = inflater.inflate(R.layout.detail_fragment, container, false);
        CityPreferences cityPreferences = new CityPreferences(getActivity());
        //weatherAdapter = new DetailAdapterRetro(getActivity(), weatherArrayList);
        progressBar = ((ProgressBar) weatherView.findViewById(R.id.detailProgressBar));
        final ListView weatherList = (ListView) weatherView.findViewById(R.id.detailList);
        // new WeatherFragmentRetro.GetWeatherData(getActivity()).execute(cityPreferences.getCity()+","+cityPreferences.getCountry()+RemoteFetch.METRIC);
        progressBar.setVisibility(View.VISIBLE);

        WeatherRestAdapter adapter = new WeatherRestAdapter();
        WeatherApi client = adapter.createService();
        Call<WeatherData> call = client.getWeather(cityPreferences.getCity(),APP_ID, METRIC);
        call.enqueue(new Callback<WeatherData>(){

            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {

                weatherData=response.body();
                weatherArrayList.add(weatherData);
                weatherAdapter = new DetailAdapterRetro(getActivity(), weatherArrayList);
                weatherAdapter.notifyDataSetChanged();
                weatherList.setAdapter(weatherAdapter);

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                progressBar.setVisibility(View.GONE);

            }
        });

       // weatherAdapter.addAll(weatherArrayList);


        return weatherView;
    }

}
