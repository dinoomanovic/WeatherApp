
package com.odin.weatherapp.ModelsRetro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SysPod implements Serializable {

    @SerializedName("pod")
    @Expose
    private String pod;

    /**
     * No args constructor for use in serialization
     */

    /**
     * @param pod
     */
    public SysPod(String pod) {
        super();
        this.pod = pod;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    public SysPod withPod(String pod) {
        this.pod = pod;
        return this;
    }

}
