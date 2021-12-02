package com.example.dbusage;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ModelHolder>{

    ArrayList<Model> models;
    @NonNull
    @Override
    public ModelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_detail, parent, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT); //makes list sticky when scrolling
        view.setLayoutParams(lp);
        ModelHolder modelHolder = new ModelHolder(view);
        return modelHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ModelHolder holder, int position) {
        Model th = models.get(position);
        holder.date.setText(th.getDate());
        holder.time.setText(th.getTime());
        holder.header.setText(th.getHeader());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(ArrayList<Model> nModels){
        models = nModels;
        Log.d("[- - - - - - - ]", "SET LIST CALLED");
        notifyDataSetChanged();
    }

    public static class ModelHolder extends RecyclerView.ViewHolder{
        TextView header, time, date;

        public ModelHolder(@NonNull View itemView){
            super(itemView);
            header = (TextView) itemView.findViewById(R.id.header);
            time = (TextView) itemView.findViewById(R.id.time);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
