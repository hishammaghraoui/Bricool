package com.example.brycool.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.brycool.R;
import com.example.brycool.Servicepage;
import com.example.brycool.services.DRVInterface.LoadMore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class services extends AppCompatActivity  {
    private RecyclerView recyclerView ,recyclerView2;
    private dynamiqueAdapter dynamiqueAdapter ;
    ArrayList<DynamicModel> items  =new ArrayList();
    DynamicAdapter dynamicAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        ArrayList<DynamiqueModel> item  = new ArrayList<>() ;
        item.add(new DynamiqueModel(R.drawable.handyman_tools, "Repair & Fix"));
        item.add(new DynamiqueModel(R.drawable.paint_roller, "Painting"));
        item.add(new DynamiqueModel(R.drawable.pipe, "Plumber"));
        item.add(new DynamiqueModel(R.drawable.water_tap, "Water"));
        item.add(new DynamiqueModel(R.drawable.rounded_plug, "Electrician "));
        recyclerView =  findViewById(R.id.rv_1);
        dynamiqueAdapter= new dynamiqueAdapter(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL , false));
        recyclerView.setAdapter(dynamiqueAdapter);


        items.add(new DynamicModel("Abdlah",R.drawable.p2 )) ;
        items.add(new DynamicModel("Amin",R.drawable.p1 )) ;
        items.add(new DynamicModel("Ahmed",R.drawable.p3 )) ;
        items.add(new DynamicModel("Anas",R.drawable.p1 )) ;
        items.add(new DynamicModel("Hicham",R.drawable.p2 )) ;
        items.add(new DynamicModel("Abdlaahd",R.drawable.p3 )) ;
        recyclerView2  = findViewById(R.id.rv_2);
        dynamicAdapter = new DynamicAdapter(items);
        recyclerView2.setLayoutManager(new LinearLayoutManager(services.this,LinearLayoutManager.VERTICAL , false));
        recyclerView2.setAdapter(dynamicAdapter);
        recyclerView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Servicepage.class));
            }
        });
    }
    public  void sendtoService (View view){
        startActivity(new Intent(getApplicationContext(), Servicepage.class));
    }
}