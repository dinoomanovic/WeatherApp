
package com.odin.weatherapp.ModelsRetro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Clouds implements Serializable {

    @SerializedName("all")
    @Expose
    private Integer all;

    /**
     * No args constructor for use in serialization
     *
     */

    /**
     * @param all
     */
    public Clouds(Integer all) {
        super();
        this.all = all;
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    public Clouds withAll(Integer all) {
        this.all = all;
        return this;
    }

}
