package com.odin.weatherapp;

import com.odin.weatherapp.Models.Place;
import com.odin.weatherapp.Models.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mobil on 04.02.2017.
 */

public class JSONWeatherParser {
    public static Weather getWeather(String data) {
        Weather weather = new Weather();
        try {
            JSONObject jsonObject = new JSONObject(data);
            Place place = new Place();
            JSONObject coordObj = RemoteFetch.getJSONObject("coord",jsonObject);
            place.setLat(RemoteFetch.getFloat("lat",coordObj));
            place.setLon(RemoteFetch.getFloat("lon",coordObj));
            JSONObject sysObj = RemoteFetch.getJSONObject("sys",jsonObject);
            place.setCountry(RemoteFetch.getString("city",sysObj));
            place.setLastupdate(RemoteFetch.getInt("dt",jsonObject));
            place.setSunrise(RemoteFetch.getInt("sunrise",sysObj));
            place.setSunset(RemoteFetch.getInt("sunset",sysObj));
            place.setCity(RemoteFetch.getString("name",jsonObject));
            weather.place = place;

            JSONArray jsonArray = jsonObject.getJSONArray("weather");
       // Get first item of the array
            JSONObject jsonWeather = jsonArray.getJSONObject(0);
            weather.currentCondition.setWeatherId(RemoteFetch.getInt("id",jsonWeather));
            weather.currentCondition.setDescription(RemoteFetch.getString("description",jsonWeather));
            weather.currentCondition.setCondition(RemoteFetch.getString("main",jsonWeather));
            weather.currentCondition.setIcon(RemoteFetch.getString("icon",jsonWeather));
            JSONObject jsonWind = RemoteFetch.getJSONObject("wind",jsonObject);
            weather.wind.setSpeed(RemoteFetch.getFloat("speed",jsonWind));
            weather.wind.setDeg(RemoteFetch.getFloat("deg",jsonWind));

            JSONObject jsonCloud = RemoteFetch.getJSONObject("clouds",jsonObject);
            weather.clouds.setPrecipitation(RemoteFetch.getInt("all",jsonCloud));

            return weather;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
