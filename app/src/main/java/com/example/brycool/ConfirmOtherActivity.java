package com.example.brycool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class ConfirmOtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_oder);
        Toolbar toolbar  = findViewById(R.id.toolbar);
        toolbar.setTitle("hiii");
    }
}