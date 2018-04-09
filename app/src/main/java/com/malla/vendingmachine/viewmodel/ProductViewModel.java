package com.malla.vendingmachine.viewmodel;

import android.databinding.BaseObservable;

public class ProductViewModel extends BaseObservable{

    public String name, imagepath;
    public String price;

    public ProductViewModel(String name, String price, String imagepath) {
        this.name = name;
        this.price = price;
        this.imagepath = imagepath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }


}
