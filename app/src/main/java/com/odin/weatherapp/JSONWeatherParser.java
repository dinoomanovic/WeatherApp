package com.odin.weatherapp;

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
            Weather.Place place = new Weather.Place();
            JSONObject coordObj = RemoteFetch.getJSONObject("coord",jsonObject);
            place.setLat(RemoteFetch.getFloat("lat",coordObj));
            place.setLon(RemoteFetch.getFloat("lon",coordObj));
            place.setLastupdate(RemoteFetch.getInt("dt",jsonObject));
            place.setCity(RemoteFetch.getString("name",jsonObject));



            JSONObject sysObj = RemoteFetch.getJSONObject("sys",jsonObject);
            place.setCountry(RemoteFetch.getString("country",sysObj));
            place.setSunrise(RemoteFetch.getInt("sunrise",sysObj));
            place.setSunset(RemoteFetch.getInt("sunset",sysObj));
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


            JSONObject temperatureObj = RemoteFetch.getJSONObject("main",jsonObject);
            weather.currentCondition.setTemperature(RemoteFetch.getFloat("temp",temperatureObj));
            weather.currentCondition.setMinTemp(RemoteFetch.getFloat("temp_min",temperatureObj));
            weather.currentCondition.setMaxTemp(RemoteFetch.getFloat("temp_max",temperatureObj));
            weather.currentCondition.setHumidity(RemoteFetch.getFloat("humidity",temperatureObj));
            weather.currentCondition.setPressure(RemoteFetch.getFloat("pressure",temperatureObj));

            return weather;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
