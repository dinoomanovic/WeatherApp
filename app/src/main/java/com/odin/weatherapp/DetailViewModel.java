package com.odin.weatherapp;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.odin.weatherapp.Models.CityPreferences;
import com.odin.weatherapp.ModelsRetro.WeatherData;
import com.odin.weatherapp.Utils.Methods;
import com.odin.weatherapp.Utils.WeatherApi;
import com.odin.weatherapp.Utils.WeatherRestAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.odin.weatherapp.Utils.Constants.APP_ID;
import static com.odin.weatherapp.Utils.Constants.EMPTY_STRING;
import static com.odin.weatherapp.Utils.Constants.METRIC;
import static com.odin.weatherapp.Utils.Constants.TEMP_C;
import static java.lang.Math.round;
import static java.lang.String.format;

public class DetailViewModel extends BaseObservable {
    private WeakReference<Context> contextWeakReference;
    public WeatherData weatherData;
    private DetailFragment.DetailAdapter adapter = new DetailFragment.DetailAdapter();

    private List<DetailItemViewModel> detailItemViewModels = new ArrayList<>();
    public ArrayList<WeatherData> weatherArrayList = new ArrayList<>();

    private int recyclerViewVisible = GONE;

    public DetailViewModel(Context context) {
        this.contextWeakReference = new WeakReference<>(context);
    }

    public void loadData(CityPreferences cityPreferences) {
        Context context = contextWeakReference.get();
        if (context == null) {
            return;
        }
        WeatherRestAdapter adapter = new WeatherRestAdapter();
        WeatherApi client = adapter.createService();
        Call<WeatherData> call = client.getWeather(cityPreferences.getCity(), APP_ID, METRIC);
        call.enqueue(new GetWeatherData());
    }

    public class GetWeatherData implements Callback<WeatherData> {

        private GetWeatherData() {
        }

        @Override
        public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
            Context context = contextWeakReference.get();
            if ((context == null) || (response == null)) {
                noData();
                return;
            }
            weatherData = response.body();
            weatherArrayList.add(weatherData);
            WeatherData weatherDataList = weatherArrayList.get(0);
            if (weatherDataList == null) {
                return;
            }
            List<com.odin.weatherapp.ModelsRetro.List> listData = weatherDataList.getList();

            Drawable imgOne = ContextCompat.getDrawable(contextWeakReference.get(),
                    Methods.getIcon(format(context.getString(R.string.connect_two_strings),
                            listData.get(8).getWeather().get(0).getIcon(), EMPTY_STRING)));
            Drawable imgTwo = ContextCompat.getDrawable(contextWeakReference.get(),
                    Methods.getIcon(format(context.getString(R.string.connect_two_strings),
                            listData.get(16).getWeather().get(0).getIcon(), EMPTY_STRING)));
            Drawable imgThree = ContextCompat.getDrawable(contextWeakReference.get(),
                    Methods.getIcon(format(context.getString(R.string.connect_two_strings),
                            listData.get(24).getWeather().get(0).getIcon(), EMPTY_STRING)));
            DetailItemViewModel detailItemViewModel = new DetailItemViewModel();
            detailItemViewModel.setDetailText(" < " + "Humidity: " + listData.get(0).getMain().getHumidity() +
                    " | " + "Wind: " + listData.get(0).getWind().getSpeed() + " | " + "Pressure: "
                    + listData.get(0).getMain().getPressure() + " | " + "Deg: "
                    + listData.get(0).getWind().getDeg() + " | " + "Latitude: "
                    + weatherDataList.getCity().getCoord().getLat() + "| " + "Longitude: " + weatherDataList.getCity().getCoord().getLon() + " > ");

            detailItemViewModel.setImageOne(imgOne);
            detailItemViewModel.setImageTwo(imgTwo);
            detailItemViewModel.setImageThree(imgThree);

            detailItemViewModel.setTextOne(format(context.getString(R.string.connect_two_strings),
                    listData.get(8).getWeather().get(0).getDescription(), EMPTY_STRING));
            detailItemViewModel.setTextTwo(format(context.getString(R.string.connect_two_strings),
                    listData.get(16).getWeather().get(0).getDescription(), EMPTY_STRING));
            detailItemViewModel.setTextThree(format(context.getString(R.string.connect_two_strings),
                    listData.get(24).getWeather().get(0).getDescription(), EMPTY_STRING));

            detailItemViewModel.setTempOne(format(context.getString(R.string.connect_number),
                    round(listData.get(8).getMain().getTemp()), TEMP_C));
            detailItemViewModel.setTempTwo(format(context.getString(R.string.connect_number),
                    round(listData.get(16).getMain().getTemp()), TEMP_C));
            detailItemViewModel.setTempThree(format(context.getString(R.string.connect_number),
                    round(listData.get(24).getMain().getTemp()), TEMP_C));

            detailItemViewModels.add(detailItemViewModel);

            getAdapter().setDetailItem(detailItemViewModels);
            setRecyclerViewVisible(VISIBLE);
        }

        @Override
        public void onFailure(Call<WeatherData> call, Throwable t) {

        }
    }

    public void noData() {
        setRecyclerViewVisible(GONE);
    }

    @Bindable
    public int getRecyclerViewVisible() {
        return recyclerViewVisible;
    }

    public void setRecyclerViewVisible(int recyclerViewVisible) {
        this.recyclerViewVisible = recyclerViewVisible;
        notifyPropertyChanged(BR.recyclerViewVisible);
    }

    @Bindable
    public DetailFragment.DetailAdapter getAdapter() {
        return adapter;
    }
}
