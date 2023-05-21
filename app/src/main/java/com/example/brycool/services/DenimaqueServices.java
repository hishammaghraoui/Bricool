package com.example.brycool.services;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DenimaqueServices extends RecyclerView.Adapter<DenimaqueServices.DynamiqueHolder> {
    @NonNull
    @Override
    public DenimaqueServices.DynamiqueHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DenimaqueServices.DynamiqueHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public ArrayList<DynamiqueModel> dynamiqueModels ;
    public DenimaqueServices (ArrayList<DynamiqueModel> dynamiqueModels){

    }
    public class DynamiqueHolder extends RecyclerView.ViewHolder {
        public DynamiqueHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
