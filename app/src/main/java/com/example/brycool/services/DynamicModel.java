package com.example.brycool.services;

public class DynamicModel {
    String name ;
    private int image ;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DynamicModel(String name , int image) {
        this.name = name;
        this.image = image ;
    }
}
