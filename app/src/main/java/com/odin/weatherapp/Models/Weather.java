package com.odin.weatherapp.Models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mobil on 02.02.2017.
 */

public class Weather implements Serializable {

    private static final long serialVersionUID = 7468907373314597663L;

    public Place place;
    public String iconData;
    public CurrentCondition currentCondition = new CurrentCondition();
    public Temperature temperature = new Temperature();
    public Wind wind = new Wind();
    public Snow snow = new Snow();
    public Cloud clouds = new Cloud();
    public Rain rain = new Rain();

}
