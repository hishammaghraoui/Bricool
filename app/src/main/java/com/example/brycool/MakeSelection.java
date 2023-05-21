package com.example.brycool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MakeSelection extends AppCompatActivity {
    String numberPhone ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_selection);
        TextView number = findViewById(R.id.Number_value);
        numberPhone = (String) number.getText();
        numberPhone =numberPhone.substring(4);
        findViewById(R.id.viaSms).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),verifyPhoneNo.class);
                intent.putExtra("phoneNo",numberPhone);
                startActivity(intent);
            }
        });
    }
}