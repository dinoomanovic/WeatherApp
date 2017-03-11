package com.odin.weatherapp;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.odin.weatherapp.ModelsRetro.WeatherData;

import java.util.ArrayList;

/**
 * Created by mobil on 05.03.2017.
 */

public class DetailAdapterRetro extends ArrayAdapter<WeatherData> {

    private ArrayList<WeatherData> listData;
    private Context context;

    private LayoutInflater layoutInflater;
    public DetailAdapterRetro(Activity context, ArrayList<WeatherData> listData){
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
    public WeatherData getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convertView = layoutInflater.inflate(R.layout.weather_layout,null);

        DetailAdapterRetro.ViewHolder holder;
        /*if(convertView == null){
        holder = new ViewHolder();

        holder.cityName = (TextView) convertView.findViewById(R.id.placeId);
        holder.temp = (TextView) convertView.findViewById(R.id.tempId);
            convertView.setTag(holder);
    } else {
        holder = (ViewHolder) convertView.getTag();
    }*/
        if(convertView == null){

            convertView = layoutInflater.from(context).inflate(R.layout.detail_layout, parent,false);
            holder = new ViewHolder();
            holder.detailText=(TextView) convertView.findViewById(R.id.detailText);
            holder.imageDay1=(ImageView) convertView.findViewById(R.id.imageDay1);
            holder.imageDay2=(ImageView) convertView.findViewById(R.id.imageDay2);
            holder.imageDay3=(ImageView) convertView.findViewById(R.id.imageDay3);
            holder.day1=(TextView) convertView.findViewById(R.id.textDay1);
            holder.day2=(TextView) convertView.findViewById(R.id.textDay2);
            holder.day3=(TextView) convertView.findViewById(R.id.textDay3);
            holder.dayTemp1=(TextView) convertView.findViewById(R.id.dayTemp1);
            holder.dayTemp2=(TextView) convertView.findViewById(R.id.dayTemp2);
            holder.dayTemp3=(TextView) convertView.findViewById(R.id.dayTemp3);




            convertView.setTag(holder);
        }
        else
            holder = (DetailAdapterRetro.ViewHolder) convertView.getTag();


        WeatherData weather = listData.get(position);
        // holder.detailText.setText("Humidity: " + weather.currentCondition.getHumidity()+" Wind: " + weather.wind.getSpeed()+" Pressure: " + weather.currentCondition.getPressure());

        holder.detailText.setSelected(true);
        holder.detailText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        holder.detailText.setSingleLine(true);
        holder.detailText.setText(" < " + "Humidity: "+ weather.getList().get(0).getMain().getHumidity() + " | " + "Wind: "  + weather.getList().get(0).getWind().getSpeed() + " | " + "Pressure: " + weather.getList().get(0).getMain().getPressure() + " | " + "Deg: "+ weather.getList().get(0).getWind().getDeg() + " | " + "Latitude: " + weather.getCity().getCoord().getLat() + "| " + "Longitude: " + weather.getCity().getCoord().getLon() + " > " );

        Log.d("Icon1",weather.getList().get(8).getWeather().get(0).getIcon());
        Log.d("Icon2",weather.getList().get(16).getWeather().get(0).getIcon());
        Log.d("Icon3",weather.getList().get(24).getWeather().get(0).getIcon());

        holder.day1.setText(weather.getList().get(8).getWeather().get(0).getDescription()+"");
        holder.day2.setText(weather.getList().get(16).getWeather().get(0).getDescription()+"");
        holder.day3.setText(weather.getList().get(24).getWeather().get(0).getDescription()+"");

        holder.dayTemp1.setText(Math.round(weather.getList().get(8).getMain().getTemp()) + " °C");
        holder.dayTemp2.setText(Math.round(weather.getList().get(16).getMain().getTemp()) + " °C");
        holder.dayTemp3.setText(Math.round(weather.getList().get(24).getMain().getTemp()) + " °C");
        Drawable img1 = ContextCompat.getDrawable(convertView.getContext(),
                getIcon(weather.getList().get(8).getWeather().get(0).getIcon()+""));
        holder.imageDay1.setImageDrawable(img1);

        Drawable img2 = ContextCompat.getDrawable(convertView.getContext(),
                getIcon(weather.getList().get(16).getWeather().get(0).getIcon()+""));
        holder.imageDay2.setImageDrawable(img2);

        Drawable img3 = ContextCompat.getDrawable(convertView.getContext(),
                getIcon(weather.getList().get(24).getWeather().get(0).getIcon()+""));
        holder.imageDay3.setImageDrawable(img3);


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

    static class ViewHolder{

        public TextView celsius;
        public TextView description;
        public TextView detailText;
        public TextView day1;
        public TextView day2;
        public TextView day3;
        public TextView dayTemp1;
        public TextView dayTemp2;
        public TextView dayTemp3;
        public ImageView imageDay1;
        public ImageView imageDay2;
        public ImageView imageDay3;
        String[] City_World;
        private Bitmap bitmap;
    }
}
