package com.example.brycool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.brycool.chat.main_chat;
import com.example.brycool.homeAdapter.CategoriesAdapter;
import com.example.brycool.homeAdapter.CategoriesHelperClass;
import com.example.brycool.homeAdapter.MostViewdHelperClass;
import com.example.brycool.homeAdapter.MostViewedAdpater;
import com.example.brycool.homeAdapter.ServiceAdapter;
import com.example.brycool.homeAdapter.ServiceHelperClass;
import com.example.brycool.services.services;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class DashboerdUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView serviceRec , mostViewedRecycler, categoriesRecycler;
    RecyclerView.Adapter adapter;
    static final float END_SCALE = 0.7f;
    LinearLayout contentView ;
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;
    TextView viewallcategories ;
    String username ;
    DrawerLayout drawerLayout;
    Button btn_viewall_categories , btn_mostviewd_viewall , allservices_btn;
    NavigationView navigationView;
    ImageView menu_icon ;
    Intent intenglobal = getIntent();
    UserHelperClass userHelperClass ;
    LinearLayout fast_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboerd_user);
        username = getIntent().getStringExtra("username");
        userHelperClass =  (UserHelperClass) getIntent().getSerializableExtra("userHelper");

        serviceRec = findViewById(R.id.services_recycler) ;
        mostViewedRecycler = findViewById(R.id.most_viewed_recycler);
        categoriesRecycler = findViewById(R.id.categories_recycler);
        menu_icon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        btn_mostviewd_viewall = findViewById(R.id.btn_mostviewd_viewall);
        btn_viewall_categories =  findViewById(R.id.btn_catgeroies_viewALl);
        allservices_btn =findViewById(R.id.btn_AllServices_viewall) ;
        fast_btn = findViewById(R.id.fast_btn);
        allservices_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), services.class));
            }
        });
        fast_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), services.class));
            }
        });
        btn_viewall_categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AllCategories.class));
            }
        });
        viewallcategories =findViewById(R.id.viewall_catgeroies);
        viewallcategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AllCategories.class));
            }
        });
        navigationDrawer();
        ServiceRecycler();
        mostViewedRecycler();
        categoriesRecycler();
    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener( this);
        navigationView.setCheckedItem(R.id.nav_home);
        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START))drawerLayout.closeDrawer(GravityCompat.START);
        else
        super.onBackPressed();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                Intent intent9  =new Intent(getApplicationContext(),DashboerdUser.class);
                intent9.putExtra("userHelper", userHelperClass);
                startActivity(intent9);
                break;
            case R.id.all_categories:
                Intent intent  =new Intent(getApplicationContext(),AllCategories.class);
                intent.putExtra("userHelper", userHelperClass);
                startActivity(intent);
                break;
            case R.id.Profile:
                Intent intent6  = new Intent(getApplicationContext(), UserProfile.class);
                intent6.putExtra("userHelper", userHelperClass);
                startActivity(intent6);
                break;
            case R.id.Chat:
                Intent intent10  = new Intent(getApplicationContext(), main_chat.class);
                intent10.putExtra("userHelper", userHelperClass);
                intent10.putExtra("name",username);
                startActivity(intent10);
                break;



        }
        return true;
    }


    private void ServiceRecycler() {
        serviceRec.setHasFixedSize(true);
        serviceRec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        ArrayList<ServiceHelperClass> serviceLocations =  new ArrayList<>();
        serviceLocations.add(new ServiceHelperClass(R.drawable.tt_removebg_preview,"Repair Bathroom","i can fix any problem you have in your bathroom from the pipes to the door."));
        serviceLocations.add(new ServiceHelperClass(R.drawable.worker5,"Construction","More the 20 years experience in Construction field so anything you wanna build i can do it " ));
        serviceLocations.add(new ServiceHelperClass(R.drawable.gi,"Rpear laptop","Our team is expert in laptops especially in the hardware side so we can fix any issues your laptop has "));
        adapter =  new ServiceAdapter(serviceLocations);
        serviceRec.setAdapter(adapter);
        serviceRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Servicepage.class));
            }
        });

    }
    private void categoriesRecycler() {

        //All Gradients
        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});


        ArrayList<CategoriesHelperClass> categoriesHelperClasses = new ArrayList<>();
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.fixitt, "Construction", gradient1));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.tt_removebg_preview, "Plumbing", gradient2));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.opnenig, "Car mechanic", gradient3));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.worker5, "Electrician", gradient4));



        categoriesRecycler.setHasFixedSize(true);
        adapter = new CategoriesAdapter(categoriesHelperClasses);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoriesRecycler.setAdapter(adapter);

    }
    private void mostViewedRecycler() {

        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<MostViewdHelperClass> mostViewedLocations = new ArrayList<>();
        mostViewedLocations.add(new MostViewdHelperClass(R.drawable.worker1, "Repair & Fixing "));
        mostViewedLocations.add(new MostViewdHelperClass(R.drawable.worker6, "Painting"));
        mostViewedLocations.add(new MostViewdHelperClass(R.drawable.worker7, "Construction"));
        mostViewedLocations.add(new MostViewdHelperClass(R.drawable.worker6, "Electrician"));

        adapter = new MostViewedAdpater(mostViewedLocations);
        mostViewedRecycler.setAdapter(adapter);
        mostViewedRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 startActivity(new Intent(getApplicationContext(), Servicepage.class));
            }
        });

    }


}