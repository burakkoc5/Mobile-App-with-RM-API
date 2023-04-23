package com.burakkoc.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("count")
    public int count;
    @SerializedName("pages")
    public int pages;
    @SerializedName("next")
    public String next;
    @SerializedName("prev")
    public String prev;
}
