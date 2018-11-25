
package com.odin.weatherapp.ModelsRetro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeatherData implements Serializable {

    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private Double message;
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<com.odin.weatherapp.ModelsRetro.List> list;

    /**
     * No args constructor for use in serialization
     */

    /**
     * @param message
     * @param cnt
     * @param cod
     * @param list
     * @param city
     */
    public WeatherData(City city, String cod, Double message, Integer cnt, java.util.List<com.odin.weatherapp.ModelsRetro.List> list) {
        super();
        this.city = city;
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public WeatherData withCity(City city) {
        this.city = city;
        return this;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public WeatherData withCod(String cod) {
        this.cod = cod;
        return this;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public WeatherData withMessage(Double message) {
        this.message = message;
        return this;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public WeatherData withCnt(Integer cnt) {
        this.cnt = cnt;
        return this;
    }

    public java.util.List<com.odin.weatherapp.ModelsRetro.List> getList() {
        return list;
    }

    public void setList(java.util.List<com.odin.weatherapp.ModelsRetro.List> list) {
        this.list = list;
    }

    public WeatherData withList(java.util.List<com.odin.weatherapp.ModelsRetro.List> list) {
        this.list = list;
        return this;
    }

}
