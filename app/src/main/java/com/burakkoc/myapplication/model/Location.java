package com.burakkoc.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Location implements Serializable {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("type")
    public String type;

    @SerializedName("dimension")
    public String dimension;

    @SerializedName("residents")
    public List<String> residents;

    @SerializedName("url")
    public String url;

    @SerializedName("created")
    public String created;
}
