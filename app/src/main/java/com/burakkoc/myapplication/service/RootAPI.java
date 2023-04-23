package com.burakkoc.myapplication.service;

import com.burakkoc.myapplication.model.Character;
import com.burakkoc.myapplication.model.Location;
import com.burakkoc.myapplication.model.Root;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RootAPI {

    @GET("api/character")
    Call<Root<Character>> getAllCharacters();


    @GET("api/location?page=1")
    Call<Root<Location>> getLocations();
}
