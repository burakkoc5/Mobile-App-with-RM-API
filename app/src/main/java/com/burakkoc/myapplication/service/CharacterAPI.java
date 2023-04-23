package com.burakkoc.myapplication.service;

import com.burakkoc.myapplication.model.Character;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CharacterAPI {


    @GET("api/character/{ids}")
    Call<List<Character>> getMultiple(@Path("ids") String groupId);
}
