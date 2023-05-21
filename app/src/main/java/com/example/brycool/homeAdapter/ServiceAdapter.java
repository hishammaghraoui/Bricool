package com.example.brycool.homeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brycool.R;

import java.util.ArrayList;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>{


    ArrayList<ServiceHelperClass> serviceLocations ;

    public ServiceAdapter(ArrayList<ServiceHelperClass> serviceLocations) {
        this.serviceLocations = serviceLocations;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.services_card_desgn, parent, false);
        ServiceViewHolder serviceViewHolder = new ServiceViewHolder(view);
        return serviceViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {


        ServiceHelperClass serviceHelperClass = serviceLocations.get(position);
        holder.image.setImageResource(serviceHelperClass.getImage());
        holder.title.setText(serviceHelperClass.getTitle());
        holder.desc.setText(serviceHelperClass.getDescription());


    }

    @Override
    public int getItemCount() {
        return serviceLocations.size();
    }


    public  static class ServiceViewHolder extends RecyclerView.ViewHolder{
        ImageView image ;
        TextView title , desc;
        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);


            image = itemView.findViewById(R.id.service_image);
            title = itemView.findViewById(R.id.service_title);
            desc = itemView.findViewById(R.id.service_description);


        }
    }
}
