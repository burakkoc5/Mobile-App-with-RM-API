package com.burakkoc.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.burakkoc.myapplication.R;
import com.burakkoc.myapplication.databinding.EpisodeRowBinding;

import java.util.List;

public class EpisodesRecyclerViewAdapter extends RecyclerView.Adapter<EpisodesRecyclerViewAdapter.EpisodeHolder> {

    List<String> episodes;

    public EpisodesRecyclerViewAdapter(List<String> episodes) {
        this.episodes = episodes;
    }

    @NonNull
    @Override
    public EpisodeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EpisodeRowBinding episodeRowBinding = EpisodeRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new EpisodeHolder(episodeRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeHolder holder, int position) {
        holder.binding.textView.setText(episodes.get(position).substring(40)+",");

    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }

    public class EpisodeHolder extends RecyclerView.ViewHolder{

        private EpisodeRowBinding binding;

        public EpisodeHolder(@NonNull EpisodeRowBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }

}
