package com.burakkoc.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.burakkoc.myapplication.adapter.EpisodesRecyclerViewAdapter;
import com.burakkoc.myapplication.adapter.InformationRecyclerViewAdapter;
import com.burakkoc.myapplication.databinding.ActivityDetailsBinding;
import com.burakkoc.myapplication.helper.ImageLoadTask;
import com.burakkoc.myapplication.model.Character;
import com.burakkoc.myapplication.model.RowInformation;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;
    //private RecyclerView recyclerView;
    //private EpisodesRecyclerViewAdapter recyclerViewAdapter;
    private Character character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        character = (Character) intent.getSerializableExtra("character");


        RowInformation status = new RowInformation("Status : ",character.status);
        RowInformation specy = new RowInformation("Specy :",character.species);
        RowInformation gender = new RowInformation("Gender :",character.gender);

        StringBuilder episodes = new StringBuilder();
        for (String e : character.episodes) {
            episodes.append(e.substring(40)).append(",");
        }
        episodes.deleteCharAt(episodes.length()-1);
        


        RowInformation origin = new RowInformation("Origin:",character.origin.name);
        RowInformation location = new RowInformation("Location :",character.location.name);
        RowInformation episode = new RowInformation("Episodes :",episodes.toString());
        

        ArrayList<RowInformation> info = new ArrayList<>();
        info.add(status);
        info.add(specy);
        info.add(gender);
        info.add(episode);
        info.add(origin);
        info.add(location);
        RowInformation created = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            Date date = dateFormat.parse(character.created);//You will get date object relative to server/client timezone wherever it is parsed
            DateFormat formatter = new SimpleDateFormat("dd MMMM yyyy,HH:mm:ss"); //If you need time just put specific format for time like 'HH:mm:ss'
            String dateStr = formatter.format(date);
            created = new RowInformation("Created at\n(in API) :",dateStr);

        }catch (Exception e){
            e.printStackTrace();
        }
        info.add(created);

        binding.name.setText(character.name);


        binding.recyclerView.setLayoutManager(new LinearLayoutManager(DetailsActivity.this, LinearLayoutManager.VERTICAL, false));
        InformationRecyclerViewAdapter recyclerViewAdapter = new InformationRecyclerViewAdapter(info);
        binding.recyclerView.setAdapter(recyclerViewAdapter);



        




        new ImageLoadTask(character.image,binding.image).execute();

    }

    public void goBack(View view){
        finish();
        onBackPressed();

    }
}