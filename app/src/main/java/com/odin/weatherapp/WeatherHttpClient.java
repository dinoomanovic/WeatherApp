package com.odin.weatherapp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.GenericArrayType;
import java.net.HttpURLConnection;
import java.net.URL;
import com.odin.weatherapp.RemoteFetch;

/**
 * Created by mobil on 04.02.2017.
 */

public class WeatherHttpClient {

public String getWeather(String place){
    HttpURLConnection connection = null;
    InputStream inputStream = null;
    try {
        connection = (HttpURLConnection) (new URL(RemoteFetch.OPEN_WEATHER_MAP_API + place)).openConnection();
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.connect();

        StringBuffer stringBuffer = new StringBuffer();
        inputStream = connection.getInputStream();
        // Pass input string to buffer
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while((line = bufferedReader.readLine()) != null){
            stringBuffer.append(line + "\r\n");
        }
        inputStream.close();
        connection.disconnect();
        return stringBuffer.toString();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}

}
