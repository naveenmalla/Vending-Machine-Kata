package com.malla.vendingmachine.viewmodel;

import android.databinding.BaseObservable;


public class ProductViewModel extends BaseObservable {

    public String name, price, imagepath;
    public int stock;


    public ProductViewModel(String name, String price, int stock, String imagepath) {
        this.name = name;
        this.price = price;
        this.stock = stock;
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

    public int getPriceInUSC() {
        float usdValue = Float.parseFloat(price);
        int priceInUSC = (int) Math.floor(usdValue * 100);
        return priceInUSC;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

}
