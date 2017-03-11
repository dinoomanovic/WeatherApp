
package com.odin.weatherapp.ModelsRetro;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys_ implements Serializable
{

    @SerializedName("pod")
    @Expose
    private String pod;
    private final static long serialVersionUID = -439973724223602189L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Sys_() {
    }

    /**
     * 
     * @param pod
     */
    public Sys_(String pod) {
        super();
        this.pod = pod;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    public Sys_ withPod(String pod) {
        this.pod = pod;
        return this;
    }

}
