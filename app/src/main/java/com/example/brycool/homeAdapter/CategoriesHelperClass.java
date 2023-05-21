package com.example.brycool.homeAdapter;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.widget.RelativeLayout;

public class CategoriesHelperClass {

    int image ;
    String title   ;
    GradientDrawable gradient ;

    public CategoriesHelperClass() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GradientDrawable getGradient() {
        return gradient;
    }

    public void setGradient(GradientDrawable gradient) {
        this.gradient = gradient;
    }

    public CategoriesHelperClass(int image, String title, GradientDrawable gradient) {
        this.image = image;
        this.title = title;
        this.gradient = gradient;
    }
}
