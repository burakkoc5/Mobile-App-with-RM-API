package com.burakkoc.myapplication.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.burakkoc.myapplication.helper.ImageLoadTask;
import com.burakkoc.myapplication.model.Character;
import com.burakkoc.myapplication.R;
import com.burakkoc.myapplication.DetailsActivity;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CharacterHolder> {

    private List<Character> characterList;
    //private String[] colors = {"#90edf9","#ffeb3b","#424675","#6f95a6","#544953","#62526a","#965762","#bc3f67"};

    public RecyclerViewAdapter(List<Character> characterList) {
        this.characterList = characterList;



    }

    @NonNull
    @Override
    public CharacterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new CharacterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterHolder holder, int position) {

        if (characterList.size()==0){
            System.out.println("Size 0");
            holder.genderImg.setVisibility(View.GONE);
            holder.charImg.setVisibility(View.GONE);
            holder.charName.setVisibility(View.GONE);
        }
        holder.bind(characterList.get(position),position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                intent.putExtra("character",characterList.get(holder.getAdapterPosition()));

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public class CharacterHolder extends RecyclerView.ViewHolder{

        TextView charName;
        ImageView charImg;
        ImageView genderImg;

        public CharacterHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Character character, Integer position){
            charName = itemView.findViewById(R.id.charName);
            charImg = itemView.findViewById(R.id.charImg);
            genderImg = itemView.findViewById(R.id.genderImg);

            if (character.gender.toLowerCase().equals("male")){
                genderImg.setImageResource(R.drawable.ic_malesign);
            }else if(character.gender.toLowerCase().equals("female")){
                genderImg.setImageResource(R.drawable.ic_femalesign);
            }else{
                genderImg.setImageResource(R.drawable.ic_qmarksign);
            }

            charName.setText(character.name);
            new ImageLoadTask(character.image, charImg).execute();

        }
    }


}
