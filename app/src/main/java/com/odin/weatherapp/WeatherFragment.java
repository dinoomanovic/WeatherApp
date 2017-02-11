package com.odin.weatherapp;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.odin.weatherapp.Models.CityPreferences;
import com.odin.weatherapp.WeatherHttpClient;
import com.odin.weatherapp.RemoteFetch;
import com.odin.weatherapp.Models.Weather;

import java.util.ArrayList;

/**
 * Created by mobil on 29.01.2017.
 */

public class WeatherFragment extends Fragment {

    public ArrayList<Weather> weatherArrayList;
    public WeatherAdapter weatherAdapter;
  /*  public WeatherFragment() {
        // Empty required constructor
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        weatherAdapter = new WeatherAdapter(getActivity(), weatherArrayList);
        View weatherView = inflater.inflate(R.layout.weather_layout,container,false);
        ListView weatherList = (ListView) weatherView.findViewById(R.id.weatherList);
        weatherList.setAdapter(weatherAdapter);
        return weatherView;
        
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        CityPreferences cityPreference = new CityPreferences(getActivity());

        renderWeatherData(cityPreference.getCity());
       // super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    public void renderWeatherData(String city){
        GetWeatherData weatherTask = new GetWeatherData();
        weatherTask.execute(city + "&units=metric");

    }

    public class GetWeatherData extends AsyncTask<String, Void, ArrayList<Weather>> {


        @Override
        protected ArrayList<Weather> doInBackground(String... params) {
           WeatherHttpClient weatherHttpClient = new WeatherHttpClient();
            String data = weatherHttpClient.getWeather(params[0]);
            try{
                weatherArrayList.add( JSONWeatherParser.getWeather(data) );
            }
            catch(Exception NullWeatherData){
                Log.e("Error Parsing: ", "City has space in text");
            }
            /*
            if (weatherArrayList == null){
                showChangeCityDialog();
            }*/
            // weatherHttpClient = weatherHttpClient.getWeather("Tuzla,BA");


           // return weatherString;
            return weatherArrayList;
        }
        protected void onPostExecute(ArrayList<Weather> weatherData){
            super.onPostExecute(weatherData);
            weatherAdapter = new WeatherAdapter(getActivity(),weatherData);

        }
    }

}
