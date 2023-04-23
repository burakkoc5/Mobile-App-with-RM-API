package com.burakkoc.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Character implements Serializable {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("status")
    public String status;

    @SerializedName("species")
    public String species;

    @SerializedName("gender")
    public String gender;

    @SerializedName("origin")
    public Location origin;

    @SerializedName("location")
    public Location location;

    @SerializedName("image")
    public String image;

    @SerializedName("episode")
    public List<String> episodes;

    @SerializedName("url")
    public String url;

    @SerializedName("created")
    public String created;





}
