package com.odin.weatherapp;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;

public class DetailItemViewModel extends BaseObservable {
    private String detailText;
    private String textOne;
    private String tempOne;
    private Drawable imageOne;
    private String textTwo;
    private String tempTwo;
    private Drawable imageTwo;
    private String textThree;
    private String tempThree;
    private Drawable imageThree;

    @Bindable
    public String getDetailText() {
        return detailText;
    }

    public void setDetailText(String detailText) {
        this.detailText = detailText;
        notifyPropertyChanged(BR.detailText);
    }

    @Bindable
    public String getTextOne() {
        return textOne;
    }

    public void setTextOne(String textOne) {
        this.textOne = textOne;
        notifyPropertyChanged(BR.textOne);
    }

    @Bindable
    public String getTempOne() {
        return tempOne;
    }

    public void setTempOne(String tempOne) {
        this.tempOne = tempOne;
        notifyPropertyChanged(BR.tempOne);
    }

    @Bindable
    public Drawable getImageOne() {
        return imageOne;
    }

    public void setImageOne(Drawable imageOne) {
        this.imageOne = imageOne;
        notifyPropertyChanged(BR.imageOne);
    }

    @Bindable
    public String getTextTwo() {
        return textTwo;
    }

    public void setTextTwo(String textTwo) {
        this.textTwo = textTwo;
        notifyPropertyChanged(BR.textTwo);
    }

    @Bindable
    public String getTempTwo() {
        return tempTwo;
    }

    public void setTempTwo(String tempTwo) {
        this.tempTwo = tempTwo;
        notifyPropertyChanged(BR.tempTwo);
    }

    @Bindable
    public Drawable getImageTwo() {
        return imageTwo;
    }

    public void setImageTwo(Drawable imageTwo) {
        this.imageTwo = imageTwo;
        notifyPropertyChanged(BR.imageTwo);
    }

    @Bindable
    public String getTextThree() {
        return textThree;
    }

    public void setTextThree(String textThree) {
        this.textThree = textThree;
        notifyPropertyChanged(BR.textThree);
    }

    @Bindable
    public String getTempThree() {
        return tempThree;
    }

    public void setTempThree(String tempThree) {
        this.tempThree = tempThree;
        notifyPropertyChanged(BR.tempThree);
    }

    @Bindable
    public Drawable getImageThree() {
        return imageThree;
    }

    public void setImageThree(Drawable imageThree) {
        this.imageThree = imageThree;
        notifyPropertyChanged(BR.imageThree);
    }
}
