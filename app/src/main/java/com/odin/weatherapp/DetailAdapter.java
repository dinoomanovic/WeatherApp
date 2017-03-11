package com.odin.weatherapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.odin.weatherapp.Models.Weather;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * Created by mobil on 18.02.2017.
 */

public class DetailAdapter extends ArrayAdapter<Weather>{





/**
 * Created by mobil on 01.02.2017.
 */



    private ArrayList<Weather> listData;
    private Context context;

    private LayoutInflater layoutInflater;
    public DetailAdapter(Activity context, ArrayList<Weather> listData) {
        super(context, 0, listData);
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

            convertView = layoutInflater.from(context).inflate(R.layout.detail_layout, parent,false);
            holder = new ViewHolder();
            //holder.detailText=(TextView) convertView.findViewById(R.id.detailedText);


            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();


            Weather weather = listData.get(position);
           // holder.detailText.setText("Humidity: " + weather.currentCondition.getHumidity()+" Wind: " + weather.wind.getSpeed()+" Pressure: " + weather.currentCondition.getPressure());

          //  holder.detailText.setSelected(true);
            //holder.detailText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            //holder.detailText.setSingleLine(true);
            //holder.detailText.setText(" | " + "Humidity: "+ weather.currentCondition.getHumidity() + " | " + "Wind: "  + weather.wind.getSpeed() + " | " + "Pressure: " + weather.currentCondition.getPressure() + " | " + "Deg: "+ weather.wind.getDeg() + " | " );





        return convertView;
    }


    static class ViewHolder{

        public TextView celsius;
        public TextView description;
        public TextView detailText;
        public TextView sunrise;
        public TextView sunset;
        public TextView updated;
        String[] City_World;
        private Bitmap bitmap;
    }

}
