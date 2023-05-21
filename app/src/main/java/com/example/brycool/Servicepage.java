package com.example.brycool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.brycool.chat.main_chat;

import java.util.ArrayList;
import java.util.List;

public class Servicepage extends AppCompatActivity {
    Intent intentglobal =getIntent();
    UserHelperClass userHelperClass;
    Button btn_contact ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicepage);
        userHelperClass =  (UserHelperClass) getIntent().getSerializableExtra("userHelper");
        ImageSlider imageSlider =  findViewById(R.id.image_slider);
        List<SlideModel>  slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.worker4, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.flight, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.worker4, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.worker5, ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://picsum.photos/seed/picsum/200/300", ScaleTypes.FIT));
        btn_contact= findViewById(R.id.btn_contact);
        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(getApplicationContext(), main_chat.class);
                intent.putExtra("userHelper", userHelperClass);
                startActivity(intent);
            }
        });

        imageSlider.setImageList(slideModels);

    }
}