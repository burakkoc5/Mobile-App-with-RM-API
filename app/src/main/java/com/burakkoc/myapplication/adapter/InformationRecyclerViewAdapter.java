package com.burakkoc.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.burakkoc.myapplication.R;
import com.burakkoc.myapplication.databinding.EpisodeRowBinding;
import com.burakkoc.myapplication.databinding.InformationRowBinding;
import com.burakkoc.myapplication.model.RowInformation;

import java.util.List;

public class InformationRecyclerViewAdapter extends RecyclerView.Adapter<InformationRecyclerViewAdapter.InformationHolder> {

    List<RowInformation> data;

    public InformationRecyclerViewAdapter(List<RowInformation> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public InformationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InformationRowBinding binding= InformationRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new InformationHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull InformationHolder holder, int position) {
        holder.binding.label.setText(data.get(position).label);
        holder.binding.info.setText(data.get(position).text);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class InformationHolder extends RecyclerView.ViewHolder{

        private InformationRowBinding binding;

        public InformationHolder(@NonNull InformationRowBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }

}
