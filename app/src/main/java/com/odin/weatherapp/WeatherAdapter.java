package com.odin.weatherapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import java.util.ArrayList;

import com.odin.weatherapp.Models.CityPreferences;
import com.odin.weatherapp.Models.Weather;

import org.w3c.dom.Text;

/**
 * Created by mobil on 01.02.2017.
 */

public class WeatherAdapter extends ArrayAdapter<Weather> {

    private ArrayList<Weather> listData;
    private Context context;

    private LayoutInflater layoutInflater;
    public WeatherAdapter(Activity context, ArrayList<Weather> listData){
        super(context,0,listData);
        this.listData = listData;
        this.context = context;

        //layoutInflater = LayoutInflater.from(context);
        //layoutInflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Weather getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // convertView = layoutInflater.inflate(R.layout.weather_layout,null);

        ViewHolder holder;
        /*if(convertView == null){
        holder = new ViewHolder();

        holder.cityName = (TextView) convertView.findViewById(R.id.placeId);
        holder.temp = (TextView) convertView.findViewById(R.id.tempId);
            convertView.setTag(holder);
    } else {
        holder = (ViewHolder) convertView.getTag();
    }*/
        if(convertView == null){

            convertView = layoutInflater.from(context).inflate(R.layout.weather_layout, parent,false);
            holder = new ViewHolder();
            holder.cityName=(TextView) convertView.findViewById(R.id.placeId);
            holder.temp =(TextView) convertView.findViewById(R.id.tempId);
            holder.dayTemp = (TextView) convertView.findViewById(R.id.dayTempId);
            holder.icon = (ImageView) convertView.findViewById(R.id.weatherImg);

            convertView.setTag(holder);
        }
        else
                holder = (ViewHolder) convertView.getTag();
        if(listData.size()<0){
        //    CityPreferences cityPreferences = new
            holder.cityName.setText("No_data");
        }
        else {
            Weather weather = listData.get(position);
            holder.cityName.setText(weather.place.getCity());
            holder.temp.setText(Math.round(weather.currentCondition.getTemperature()) + " °C");
            holder.dayTemp.setText(weather.currentCondition.getDescription());

            Drawable img = ContextCompat.getDrawable(convertView.getContext(),
                    getIcon(weather.currentCondition.getIcon()));
            holder.icon.setImageDrawable(img);
            // holder.humidity.setText("Humidity: " + weather.currentCondition.getHumidity() + "");
          //  holder.pressure.setText("Pressure: " + weather.currentCondition.getPressure() + "");
          // holder.wind.setText("Wind: " + weather.wind.getSpeed() + "");

        }

        return convertView;
    }

    private int getIcon(String iconName) {
        switch (iconName) {
            case "01d":
                return R.drawable.d01;
            case "01n":
                return R.drawable.n01;
            case "02d":
                return R.drawable.d02;
            case "02n":
                return R.drawable.n02;
            case "03d":
                return R.drawable.d03;
            case "03n":
                return R.drawable.n03;
            case "04d":
                return R.drawable.d04;
            case "04n":
                return R.drawable.n04;
            case "09d":
                return R.drawable.d09;
            case "09n":
                return R.drawable.n09;
            case "10d":
                return R.drawable.d10;
            case "10n":
                return R.drawable.n10;
            case "11d":
                return R.drawable.d11;
            case "11n":
                return R.drawable.n11;
            case "13d":
                return R.drawable.d13;
            case "13n":
                return R.drawable.n13;
            default:
                return R.drawable.n01;
        }
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
        public TextView dayTemp;
        public ImageView icon;
     public TextView celsius;
     public TextView description;
     public TextView humidity;
     public TextView pressure;
     public TextView wind;
     public TextView sunrise;
     public TextView sunset;
     public TextView updated;
     String[] City_World;
        private Bitmap bitmap;
 }

}
