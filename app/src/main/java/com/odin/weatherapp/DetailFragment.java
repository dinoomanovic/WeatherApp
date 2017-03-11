package com.odin.weatherapp;

/**
 * Created by mobil on 18.02.2017.
 */

import android.app.Fragment;
import android.content.Context;
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
import android.widget.ListView;
import android.widget.ProgressBar;

import com.odin.weatherapp.Models.CityPreferences;
import com.odin.weatherapp.Models.Weather;

import java.util.ArrayList;



/**
 * Created by mobil on 29.01.2017.
 */

public class DetailFragment extends Fragment {

    public ArrayList<Weather> weatherArrayList = new ArrayList<>();
    public DetailAdapter weatherAdapter;
    public ProgressBar progressBar;
    public Weather weather = new Weather();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View weatherView = inflater.inflate(R.layout.detail_fragment, container, false);
        CityPreferences cityPreferences = new CityPreferences(getActivity());
        weatherAdapter = new DetailAdapter(getActivity(), weatherArrayList);
        progressBar = ((ProgressBar) weatherView.findViewById(R.id.detailProgressBar));
        ListView weatherList = (ListView) weatherView.findViewById(R.id.detailList);
        new GetWeatherData(getActivity()).execute(cityPreferences.getCity()+","+cityPreferences.getCountry()+RemoteFetch.METRIC);
        weatherList.setAdapter(weatherAdapter);


        return weatherView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    public class GetWeatherData extends AsyncTask<String,Void, Void> {
        private Context context;

        public GetWeatherData(Context context) {
            this.context = context;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(String... params) {
            WeatherHttpClient weatherHttpClient = new WeatherHttpClient();
            String data = weatherHttpClient.getWeather(params[0]);
//            Log.d("WeatherDataString", data);
            try {
                //weatherArrayList.clear();
                weather = JSONWeatherParser.getWeather(data);
                weatherArrayList.add(weather);
            } catch (Exception NullWeatherData) {
                Log.e("Error Parsing: ", "City has space in text");
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.GONE);
            weatherAdapter.notifyDataSetChanged();
        }
    }

}
