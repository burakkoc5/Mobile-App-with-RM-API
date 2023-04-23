package com.burakkoc.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Episode implements Serializable {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("air_date")
    public String air_date;
    @SerializedName("episode")
    public String episode;
    @SerializedName("characters")
    public List<String> characters	;
    @SerializedName("name")
    public String url;
    @SerializedName("created")
    public String created;
}
