package com.odin.weatherapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import com.odin.weatherapp.Models.Weather;

/**
 * Created by mobil on 01.02.2017.
 */

public class WeatherAdapter extends BaseAdapter implements View.OnClickListener {

    private ArrayList<Weather> listData;

    Weather weather = new Weather();

    private LayoutInflater layoutInflater;
    public WeatherAdapter(Context context, ArrayList<Weather> listData){
        this.listData = listData;
        //layoutInflater = LayoutInflater.from(context);
        layoutInflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.weather_layout,null);

        ViewHolder holder;
        if(convertView == null){
        holder = new ViewHolder();

        holder.cityName = (TextView) convertView.findViewById(R.id.placeId);
        holder.temp = (TextView) convertView.findViewById(R.id.tempId);
            convertView.setTag(holder);
    } else {
        holder = (ViewHolder) convertView.getTag();
    }
        Weather weather = listData.get(position);
        holder.temp.setText(weather.temperature.getMinTemp()
                +" to " + weather.temperature.getMaxTemp());
        holder.cityName.setText(weather.place.getCity());
        return convertView;
    }

    /*private Float convertTemp(Float temperature) {
        // convert if Fahrenheit
        if (!systemUnit.equals("Metric")) {
            return temperature * 9 / 5 + 32;
        }
        return temperature;
    }*/
 static class ViewHolder{
     public TextView cityName;
     public TextView temp;
     public TextView celsius;
     public TextView description;
     public TextView humidity;
     public TextView pressure;
     public TextView wind;
     public TextView sunrise;
     public TextView sunset;
     public TextView updated;
     String[] City_World;
 }
}
