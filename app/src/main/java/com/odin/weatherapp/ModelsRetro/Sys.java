
package com.odin.weatherapp.ModelsRetro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sys implements Serializable {

    @SerializedName("population")
    @Expose
    private Integer population;

    /**
     * No args constructor for use in serialization
     *
     */

    /**
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
