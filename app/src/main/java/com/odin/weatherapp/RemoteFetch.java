package com.odin.weatherapp;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mobil on 03.02.2017.
 */

public class RemoteFetch {

    public static final String OPEN_WEATHER_MAP_API = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";
    public static final String ICON = "http://openweathermap.org/img/w/";
    public static final String METRIC = "&units=metric";
    public static final String IMPERIAL = "&units=imperial";
    public static final String APP_ID = "&appid=14c1ad343b75d7f6fbe4f14fd766f0f7";
    public static JSONObject getJSONObject (String objectName, JSONObject jsonObject) throws JSONException {
        try {
            JSONObject jObject = jsonObject.getJSONObject(objectName);
    return jObject;
        } catch (Exception e) {
            Log.e(e.getMessage(),"getJSONObject error");
            return null;
        }
    }
    public static String getString(String objectName, JSONObject jsonObject) throws JSONException{
        try{
           return jsonObject.getString(objectName);
        }
        catch(JSONException e){
            Log.e(e.getMessage(),"getString error");
            return null;
        }
    }

    public static float getFloat(String objectName, JSONObject jsonObject) throws JSONException{
        try{
            return (float) jsonObject.getDouble(objectName);
        }
        catch(JSONException e){
            Log.e(e.getMessage(),"getFloat error");

            return 0;
        }
    }

    public static double getDouble(String objectName, JSONObject jsonObject) throws JSONException{
        try{
            return (double) jsonObject.getDouble(objectName);
        }
        catch(JSONException e){
            Log.e(e.getMessage(),"getDouble error");

            return 0;
        }
    }

    public static int getInt(String objectName, JSONObject jsonObject) throws JSONException{
        try{
            return (int) jsonObject.getInt(objectName);
        }
        catch(JSONException e){
            Log.e(e.getMessage(),"getInt error");

            return 0;
        }
    }
}