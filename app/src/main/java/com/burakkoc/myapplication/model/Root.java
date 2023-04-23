package com.burakkoc.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Root<E> {

    @SerializedName("info")
    public Info info;

    @SerializedName("results")
    public E[] elements;


}
