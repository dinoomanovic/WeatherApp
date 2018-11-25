
package com.odin.weatherapp.ModelsRetro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rain implements Serializable {

    @SerializedName("3h")
    @Expose
    private Double h3;

    /**
     * No args constructor for use in serialization
     */
    public Rain() {
    }

    /**
     * @param h3
     */
    public Rain(Double h3) {
        super();
        this.h3 = h3;
    }

    public Double get3h() {
        return h3;
    }

    public void set3h(Double h3) {
        this.h3 = h3;
    }

    public Rain with3h(Double h3) {
        this.h3 = h3;
        return this;
    }

}
