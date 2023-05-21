package com.example.brycool.services;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brycool.R;

import java.util.ArrayList;

public  class DynamicAdapter extends RecyclerView.Adapter<DynamicAdapter.DynamicHolder> {
    public ArrayList<DynamicModel> dynamicModels ;
    public DynamicAdapter(ArrayList<DynamicModel> dynamicModels){
        this.dynamicModels= dynamicModels ;
    }
    public class DynamicHolder  extends RecyclerView.ViewHolder{

        public ImageView imageView ;
        public TextView textView ;
        ConstraintLayout constraintLayout ;
        public DynamicHolder( View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_ser);
            textView = itemView.findViewById(R.id.text_ser);
            constraintLayout = itemView.findViewById(R.id.constraint1);
        }
    }
    @NonNull
    @Override
    public DynamicAdapter.DynamicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rv_item , parent ,false);
        DynamicHolder dynamicHolder = new DynamicHolder(view);
        return dynamicHolder ;
    }

    @Override
    public void onBindViewHolder(final DynamicAdapter.DynamicHolder holder, int position) {
        DynamicModel currentItem  =   dynamicModels.get(position);
        if(currentItem == null){
            holder.imageView.setImageResource(R.drawable.p1);
            holder.textView.setText("AbduAllah");
        }
        else {
                Log.d("image", String.valueOf(R.drawable.p1));
//            holder.imageView.setImageResource(R.drawable.p1);
          //  holder.textView.setText(currentItem.getName());
        }
    }

    @Override
    public int getItemCount() {
        return dynamicModels.size();
    }


}