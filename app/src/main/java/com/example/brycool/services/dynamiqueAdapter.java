package com.example.brycool.services;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brycool.R;

import java.util.ArrayList;



public class dynamiqueAdapter extends  RecyclerView.Adapter<dynamiqueAdapter.DynamiqueRViewHolder>{


    private ArrayList<DynamiqueModel> items ;
    int row_index = -1 ;
    UpdateRecycleerView updateRecycleerView ;
    Activity activity ;
    boolean check = true ;
    boolean select = true ;

    public dynamiqueAdapter(ArrayList<DynamiqueModel> items, Activity activity ,UpdateRecycleerView updateRecycleerView) {
        this.items = items;
        this.updateRecycleerView = updateRecycleerView;
        this.activity = activity;
    }

    public dynamiqueAdapter(ArrayList<DynamiqueModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public DynamiqueRViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item,parent,false);
        DynamiqueRViewHolder dynamiqueRViewHolder = new DynamiqueRViewHolder(view);
        return dynamiqueRViewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull DynamiqueRViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        DynamiqueModel currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getText());

        if(check){
            ArrayList<DynamicModel> items = new ArrayList<DynamicModel>();
            items.add(new DynamicModel("Abdlah",R.drawable.p2 )) ;
            items.add(new DynamicModel("Amin",R.drawable.p1 )) ;
            items.add(new DynamicModel("Ahmed",R.drawable.p3 )) ;
            items.add(new DynamicModel("Anas",R.drawable.p1 )) ;
            items.add(new DynamicModel("Hicham",R.drawable.p2 )) ;
            items.add(new DynamicModel("Abdlaahd",R.drawable.p3 )) ;
            check =false ;

        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position ;
                notifyDataSetChanged();



            }
        });
        if (row_index == position){
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_bg);
        }
        else {
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class DynamiqueRViewHolder  extends RecyclerView.ViewHolder {


        TextView textView ;
        ImageView imageView ;
        LinearLayout linearLayout ;

        public DynamiqueRViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView  = itemView.findViewById(R.id.image_ser);
            textView = itemView.findViewById(R.id.text_ser);
            linearLayout = itemView.findViewById(R.id.linear_layout);
        }
    }
}
