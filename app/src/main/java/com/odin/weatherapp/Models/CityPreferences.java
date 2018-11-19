package com.odin.weatherapp.Models;

/**
 * Created by mobil on 11.02.2017.
 */

import android.app.Activity;
import android.content.SharedPreferences;

public class CityPreferences {
    private SharedPreferences preferences;

    public CityPreferences(Activity activity) {
        if ((activity == null) || activity.isFinishing()) {
            return;
        }
        preferences = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    // Get default city
    public String getCity() {
        return preferences.getString("city", "Tuzla,BA");
    }

    public void setCity(String city) {
        preferences.edit().putString("city", city).apply();
    }

}