package com.odin.weatherapp;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;

public class WeatherItemViewModel extends BaseObservable {
    private String placeName;
    private String descriptionText;
    private String tempText;
    private Drawable weatherImage;

    @Bindable
    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
        notifyPropertyChanged(BR.placeName);
    }

    @Bindable
    public Drawable getWeatherImage() {
        return weatherImage;
    }

    public void setWeatherImage(Drawable weatherImage) {
        this.weatherImage = weatherImage;
        notifyPropertyChanged(BR.weatherImage);
    }

    @Bindable
    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
        notifyPropertyChanged(BR.descriptionText);
    }

    @Bindable
    public String getTempText() {
        return tempText;
    }

    public void setTempText(String tempText) {
        this.tempText = tempText;
        notifyPropertyChanged(BR.tempText);
    }
}
