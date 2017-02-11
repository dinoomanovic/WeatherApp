package com.odin.weatherapp.Models;

/**
 * Created by mobil on 11.02.2017.
 */



import android.app.Activity;
        import android.content.SharedPreferences;

/**
 * Created by arun.bhaskar on 1/11/2017.
 */
public class CityPreferences {
    SharedPreferences preferences;

    public CityPreferences(Activity activity){
        preferences = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public String getCity(){
        return preferences.getString("city", "Tuzla");
    }

    public void setCity(String city) {
        preferences.edit().putString("city", city).commit();
    }
}