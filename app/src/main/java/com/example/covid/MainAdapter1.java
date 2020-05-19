package com.example.covid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter1 extends RecyclerView.Adapter<MainAdapter1.ViewHolder> {

    ArrayList<MainModel1> mainModel1;
    Context mcontext;

    public MainAdapter1(Context mcontext ,  ArrayList<MainModel1> mainModel1){
        this.mainModel1 = mainModel1;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public MainAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter1.ViewHolder holder, int position) {
        holder.imageView.setImageResource(mainModel1.get(position).getPrevLogo());
        holder.textView.setText(mainModel1.get(position).getPrevName().toString());
    }

    @Override
    public int getItemCount() {
        return mainModel1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView ;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView  = itemView.findViewById(R.id.textView);
        }
    }
}
