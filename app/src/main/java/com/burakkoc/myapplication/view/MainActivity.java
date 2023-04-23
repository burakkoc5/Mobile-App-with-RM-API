package com.burakkoc.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.burakkoc.myapplication.R;
import com.burakkoc.myapplication.adapter.HorizontalRecyclerViewAdapter;
import com.burakkoc.myapplication.adapter.RecyclerViewAdapter;
import com.burakkoc.myapplication.model.Character;
import com.burakkoc.myapplication.model.Location;
import com.burakkoc.myapplication.model.Root;

import com.burakkoc.myapplication.service.CharacterAPI;
import com.burakkoc.myapplication.service.RootAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<Character> characters;
    List<Location> locations;
    Root<Character> rootChars;
    Root<Location> rootLocs;
    public static final String BASE_URL="https://rickandmortyapi.com/";
    Retrofit retrofit;
    public static RecyclerView recyclerView;
    public static TextView gone;
    RecyclerView horizontalRecyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    HorizontalRecyclerViewAdapter horizontalRecyclerViewAdapter;
    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        characters = new ArrayList<>();
        gone=findViewById(R.id.goneText);
        recyclerView = findViewById(R.id.recyclerView);
        horizontalRecyclerView = findViewById(R.id.locationsRecyclerView);

        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadInitialData();


    }



    private void loadInitialData(){
        //locations
        RootAPI rootAPI = retrofit.create(RootAPI.class);
        Call<Root<Location>> firstCall = rootAPI.getLocations();


        firstCall.enqueue(new Callback<Root<Location>>() {
            @Override
            public void onResponse( Call<Root<Location>> call, Response<Root<Location>> response) {
                if (response.isSuccessful()){
                    rootLocs = response.body();
                    locations = Arrays.asList(rootLocs.elements);
                    loadCharacters(locations.get(0).residents);
                    horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    horizontalRecyclerViewAdapter = new HorizontalRecyclerViewAdapter(locations);
                    horizontalRecyclerView.setAdapter(horizontalRecyclerViewAdapter);

                }
            }

            @Override
            public void onFailure(Call<Root<Location>> call, Throwable t) {
                t.printStackTrace();
            }
        });



        /*
      Call<Root<Character>> call = rootAPI.getAllCharacters();

        call.enqueue(new Callback<Root<Character>>() {
            @Override
            public void onResponse( Call<Root<Character>> call, Response<Root<Character>> response) {
                if (response.isSuccessful()){
                    rootChars = response.body();
                    characters = Arrays.asList(rootChars.elements);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter = new RecyclerViewAdapter(characters);
                    recyclerView.setAdapter(recyclerViewAdapter);

                }
            }

            @Override
            public void onFailure(Call<Root<Character>> call, Throwable t) {
                t.printStackTrace();
            }
        });*/


    }

    public void loadCharacters(List<String> residents){
        //residents.stream().forEach(name->name.substring(42));
        List<String> characterList = new ArrayList<>();

        for (String s :
                residents) {
            characterList.add(s);
        }



        String str ="";
        for (int i =0; i<characterList.size(); i++) {

            characterList.set(i,characterList.get(i).substring(42));
            if (i==characterList.size()-1){
                str+=characterList.get(i);
            }else
            {
                str += characterList.get(i) +",";
            }
        }


        //System.out.println(str);

        CharacterAPI characterAPI = retrofit.create(CharacterAPI.class);
        Call<List<Character>> charsCall = characterAPI.getMultiple(str);

        charsCall.enqueue(new Callback<List<Character>>() {
            @Override
            public void onResponse( Call<List<Character>> call, Response<List<Character>> response) {
                if (response.isSuccessful()){
                    characters = response.body();
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter = new RecyclerViewAdapter(characters);
                    recyclerView.setAdapter(recyclerViewAdapter);

                }
                else{

                    System.out.println(response.code() + " " +"response unsuccess");
                }
            }

            @Override
            public void onFailure(Call<List<Character>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

}