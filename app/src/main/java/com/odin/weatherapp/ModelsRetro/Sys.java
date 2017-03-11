
package com.odin.weatherapp.ModelsRetro;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys implements Serializable
{

    @SerializedName("population")
    @Expose
    private Integer population;
    private final static long serialVersionUID = 467884441353404158L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Sys() {
    }

    /**
     * 
     * @param population
     */
    public Sys(Integer population) {
        super();
        this.population = population;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Sys withPopulation(Integer population) {
        this.population = population;
        return this;
    }

}
