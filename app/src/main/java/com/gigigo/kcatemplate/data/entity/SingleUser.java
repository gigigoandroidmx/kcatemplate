package com.gigigo.kcatemplate.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Juan Godínez Vera - 6/2/2017.
 */
public class SingleUser {
    @SerializedName("data")
    @Expose
    private UserEntity data;

    public UserEntity getData() {
        return data;
    }

    public void setData(UserEntity data) {
        this.data = data;
    }
}
