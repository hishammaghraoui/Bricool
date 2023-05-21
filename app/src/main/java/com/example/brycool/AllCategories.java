package com.example.brycool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.brycool.services.services;

public class AllCategories extends AppCompatActivity {
    ImageView backbtn;
    Button expand_all;
    UserHelperClass userHelperClass;
    Intent intentglobal = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);
        expand_all = findViewById(R.id.expand_all);
        userHelperClass = (UserHelperClass) getIntent().getSerializableExtra("userHelper");

        expand_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), services.class);
                intent.putExtra("userHelper", userHelperClass);
                startActivity(intent);
            }
        });

        backbtn = findViewById(R.id.back_pressed);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllCategories.super.onBackPressed();
            }
        });
    }
    public void expandALL(View view){
        Intent intent = new Intent(getApplicationContext(), services.class);
        intent.putExtra("userHelper", userHelperClass);
        startActivity(intent);
    }
}