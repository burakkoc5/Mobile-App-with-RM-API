package com.burakkoc.myapplication.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.burakkoc.myapplication.R;
import com.burakkoc.myapplication.databinding.LocationsRowBinding;
import com.burakkoc.myapplication.model.Character;
import com.burakkoc.myapplication.model.Location;
import com.burakkoc.myapplication.service.CharacterAPI;
import com.burakkoc.myapplication.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.LocationHolder> {

    private int selectedPos = 0;
    private List<Location> locations;

    public HorizontalRecyclerViewAdapter(List<Location> locations) {
        this.locations = locations;
    }

    @NonNull
    @Override
    public LocationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LocationsRowBinding binding = LocationsRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new LocationHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationHolder holder, int position) {

        holder.binding.textView2.setText(locations.get(position).name);
        holder.binding.cardView.setBackgroundColor(selectedPos == position ? Color.WHITE : Color.GRAY);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemChanged(selectedPos);
                selectedPos = holder.getAdapterPosition();
                notifyItemChanged(selectedPos);
                if(locations.get(holder.getAdapterPosition()).residents.size()==0){
                    MainActivity.recyclerView.setAdapter(new RecyclerViewAdapter(new ArrayList<>()));
                    MainActivity.gone.setVisibility((View.VISIBLE));
                }else{
                    MainActivity.gone.setVisibility(View.GONE);
                    updateCharacters(locations.get(holder.getAdapterPosition()).residents);
                }
            }
        });


        //notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class LocationHolder extends RecyclerView.ViewHolder{

        LocationsRowBinding binding;

        public LocationHolder(@NonNull LocationsRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    public void updateCharacters(List<String> residents){

        List<String> characters = new ArrayList<>();

        for (String s :
                residents) {
            characters.add(s);
        }

        for (String c :
                characters) {
            //System.out.println(c + " ");
        }


        String str ="";
        for (int i =0; i<characters.size(); i++) {

            characters.set(i,characters.get(i).substring(42));

                str += characters.get(i) +",";
        }

        //System.out.println(str);

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        CharacterAPI characterAPI = retrofit.create(CharacterAPI.class);
        Call<List<Character>> charsCall = characterAPI.getMultiple(str);

        charsCall.enqueue(new Callback<List<Character>>() {
            @Override
            public void onResponse( Call<List<Character>> call, Response<List<Character>> response) {
                if (response.isSuccessful()){
                    List<Character> characterList = response.body();
                    if (characterList.size()==0){
                        System.out.println("size 0");
                    }
                    //System.out.println("Character list size :" + characterList.size());
                    MainActivity.recyclerView.setAdapter(new RecyclerViewAdapter(characterList));
                }
                else{
                    System.out.println("iyi ama olmadı");
                }
            }

            @Override
            public void onFailure(Call<List<Character>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
