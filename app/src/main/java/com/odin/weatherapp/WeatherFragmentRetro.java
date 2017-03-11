package com.odin.weatherapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.odin.weatherapp.Models.CityPreferences;
import com.odin.weatherapp.ModelsRetro.WeatherData;
import com.odin.weatherapp.Utils.WeatherApi;
import com.odin.weatherapp.Utils.WeatherRestAdapter;

import java.util.ArrayList;

/**
 * Created by mobil on 04.03.2017.
 */

public class WeatherFragmentRetro extends Fragment {
    public WeatherFragmentRetro(){

    }
    public ArrayList<WeatherData> weatherArrayList = new ArrayList<>();
    public WeatherAdapterRetro weatherAdapter;
    public ProgressBar progressBar;
    public  WeatherData weatherData;
    public static final String APP_ID = "14c1ad343b75d7f6fbe4f14fd766f0f7";
    public static final String METRIC = "metric";
   // public WeatherData weather = new WeatherData();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View weatherView = inflater.inflate(R.layout.weather_fragment, container, false);
        CityPreferences cityPreferences = new CityPreferences(getActivity());
        progressBar = ((ProgressBar) weatherView.findViewById(R.id.progressBar));
        final ListView weatherList = (ListView) weatherView.findViewById(R.id.mainList);
       // new WeatherFragmentRetro.GetWeatherData(getActivity()).execute(cityPreferences.getCity()+","+cityPreferences.getCountry()+RemoteFetch.METRIC);
        progressBar.setVisibility(View.VISIBLE);

        WeatherRestAdapter adapter = new WeatherRestAdapter();
        WeatherApi client = adapter.createService();
        Call<WeatherData> call = client.getWeather(cityPreferences.getCity(),APP_ID, METRIC);
        call.enqueue(new GetWeatherData(weatherList));
        /*
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) weatherView.findViewById(R.id.activity_main);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Intent refresh = new Intent(getActivity(), MainActivity.class);
                //   refresh.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //refresh.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                //refresh.putExtra("flag","add");
                //startActivity(refresh);
                //finish();
                //overridePendingTransition(0,0);
                CityPreferences cityPreferences = new CityPreferences(getActivity());

                WeatherRestAdapter adapter = new WeatherRestAdapter();
                WeatherApi client = adapter.createService();
                Call<WeatherData> call = client.getWeather(cityPreferences.getCity(),APP_ID, METRIC);

                call.enqueue(new GetWeatherData(weatherList));

            }
        });
*/
//        Log.d("WeatherData2: ", weatherData.getName() + " " + weatherData.getMain().getTemp()+ " " +weatherData.getWeather());
   //     Log.d("WeatherArrayList2: ", weatherArrayList.toString());
       // weatherAdapter.notifyDataSetChanged();
        //Log.d("weatherAdapter",weatherAdapter.getLog().toString());
        return weatherView;
    }


    public class GetWeatherData implements Callback<WeatherData> {
        private ListView weatherList;
        public GetWeatherData(ListView weatherList){
            this.weatherList = weatherList;
        }
        @Override
        public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {

            weatherData=response.body();
            weatherArrayList.add(weatherData);
            weatherAdapter = new WeatherAdapterRetro(getActivity(), weatherArrayList);
            weatherAdapter.notifyDataSetChanged();
            Log.d("Call Response: ", call.toString() + " "  + response.toString() + " " +  response.body().toString() + " " + response.message());
//            Log.d("WeatherData: ", weatherData.getCity().getName() + " " + weatherData.getList().get(0).getMain().getTemp()+ " " +weatherData.getList().get(0).getWeather());
            Log.d("WeatherArrayList: ", weatherArrayList.toString());
            Log.d("WeatherAdapter: ", weatherAdapter.toString());
            weatherList.setAdapter(weatherAdapter);

            progressBar.setVisibility(View.GONE);

        }

        @Override
        public void onFailure(Call<WeatherData> call, Throwable t) {
            progressBar.setVisibility(View.GONE);
            Log.d("Error: ", call.toString() + " " + t.getMessage() + " " + t.getCause());

        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



}
