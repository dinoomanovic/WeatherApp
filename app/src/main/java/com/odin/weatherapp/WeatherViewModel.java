package com.odin.weatherapp;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ProgressBar;

import com.odin.weatherapp.Models.CityPreferences;
import com.odin.weatherapp.ModelsRetro.WeatherData;
import com.odin.weatherapp.Utils.Methods;
import com.odin.weatherapp.Utils.WeatherApi;
import com.odin.weatherapp.Utils.WeatherRestAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.odin.weatherapp.Utils.Constants.APP_ID;
import static com.odin.weatherapp.Utils.Constants.EMPTY_STRING;
import static com.odin.weatherapp.Utils.Constants.METRIC;
import static com.odin.weatherapp.Utils.Constants.TEMP_C;
import static java.lang.Math.round;
import static java.lang.String.format;

public class WeatherViewModel extends BaseObservable {
    private WeakReference<Context> contextWeakReference;
    private WeatherFragment.WeatherAdapter adapter = new WeatherFragment.WeatherAdapter();

    private int recyclerViewVisible = GONE;
    private ProgressBar progressBar;

    public WeatherViewModel(Context context, ProgressBar progressBar) {
        this.contextWeakReference = new WeakReference<>(context);
        this.progressBar = progressBar;
    }

    public void loadData(CityPreferences cityPreferences) {
        Context context = contextWeakReference.get();
        if ((context == null) || (progressBar == null)) {
            noData();
            return;
        }

        progressBar.setVisibility(VISIBLE);
        WeatherRestAdapter adapter = new WeatherRestAdapter();
        WeatherApi client = adapter.createService();
        Observable<WeatherData> weatherDataObservable = client.getWeather(cityPreferences.getCity(), APP_ID, METRIC);
        weatherDataObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError);
    }

    private void handleResults(WeatherData response) {
        Context context = contextWeakReference.get();
        if ((context == null) || (response == null)) {
            noData();
            return;
        }
        ArrayList<WeatherItemViewModel> weatherViewModels = new ArrayList<>();
        ArrayList<WeatherData> weatherArrayList = new ArrayList<>();
        weatherArrayList.add(response);
        WeatherData weatherDataList = weatherArrayList.get(0);
        if (weatherDataList == null) {
            return;
        }
        com.odin.weatherapp.ModelsRetro.List listData = weatherDataList.getList().get(0);

        Drawable img = ContextCompat.getDrawable(contextWeakReference.get(),
                Methods.getIcon(format(context.getString(R.string.connect_two_strings),
                        listData.getWeather().get(0).getIcon(), EMPTY_STRING)));
        WeatherItemViewModel weatherItemViewModel = new WeatherItemViewModel();
        weatherItemViewModel.setPlaceName(weatherDataList.getCity().getName());
        weatherItemViewModel.setWeatherImage(img);
        weatherItemViewModel.setDescriptionText(format(context.getString(R.string.connect_two_strings),
                listData.getWeather().get(0).getDescription(), EMPTY_STRING));
        weatherItemViewModel.setTempText(format(context.getString(R.string.connect_number),
                round(listData.getMain().getTemp()), TEMP_C));
        weatherViewModels.add(weatherItemViewModel);

        getAdapter().setWeatherItem(weatherViewModels);
        setRecyclerViewVisible(VISIBLE);
        progressBar.setVisibility(GONE);
    }

    private void handleError(Throwable t) {
        progressBar.setVisibility(GONE);
        Log.w("handleError", t.getLocalizedMessage());
    }

    private void noData() {
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
    public WeatherFragment.WeatherAdapter getAdapter() {
        return adapter;
    }
}
