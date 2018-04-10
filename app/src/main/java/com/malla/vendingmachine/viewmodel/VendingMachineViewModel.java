package com.malla.vendingmachine.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Locale;

public class VendingMachineViewModel extends ViewModel {

    /**
     * Nickel ($0.05)
     */
    private static final int COIN_NICKEL = 5;

    /**
     * Dime ($0.10)
     */
    private static final int COIN_DIME = 10;

    /**
     * Quarter ($0.25)
     */
    private static final int COIN_QUARTER = 25;

    private int currencyInUsc = 0;

    private int returnInUsc = 0;

    private ArrayList<ProductViewModel> productsList = new ArrayList<>();
    // - format: like normal, but also must be run through string.Format() with a float (price/value/USD)
    private static final String MSG_STATIC_FORMAT_AVAILABLE = "$%3.2f";

    @NonNull
    public final MutableLiveData<String> balanceCredit = new MutableLiveData<>();

    @NonNull
    public final MutableLiveData<String> userMessage = new MutableLiveData<>();

    @NonNull
    public MutableLiveData<ArrayList<ProductViewModel>> productsLiveData;

    public LiveData<String> getVendingMachineBalance() {

        return this.balanceCredit;
    }

    public LiveData<String> getVendingMachineMessage() {
        return this.userMessage;
    }

    public LiveData<ArrayList<ProductViewModel>> getProductsList() {
        if (productsLiveData == null) {
            productsLiveData = new MutableLiveData<>();
            loadProducts();
        }
        return productsLiveData;
    }

    private void loadProducts() {


        ProductViewModel product1 = new ProductViewModel("Coke","1.00",2, "");
        ProductViewModel product2 = new ProductViewModel("Chips","0.50",5,"");
        ProductViewModel product3 = new ProductViewModel("Candy","0.65",7,"");
        productsList.add(product1);
        productsList.add(product2);
        productsList.add(product3);

        productsLiveData.setValue(productsList);
    }

    public boolean addFunds(int usc) {

        switch (usc) {
            case COIN_NICKEL:
            case COIN_DIME:
            case COIN_QUARTER:
                // valid
                this.currencyInUsc += usc;
                this.balanceCredit.postValue(String.format(
                        Locale.US,
                        MSG_STATIC_FORMAT_AVAILABLE,
                        (float) this.currencyInUsc / 100));
                return true;
            default:
                // invalid coins: pennies, drachmas, kronors, pfennigs, etc.
                this.returnInUsc += usc;
                return false;
        }
    }

    public void returnFunds() {
        this.returnInUsc += this.currencyInUsc;
        this.currencyInUsc = 0;
        this.balanceCredit.postValue(String.format(
                Locale.US,
                MSG_STATIC_FORMAT_AVAILABLE,
                (float) this.currencyInUsc / 100));
    }

    public boolean removeFunds(int usc) {
        if(this.currencyInUsc >= usc) {
            this.currencyInUsc -= usc;
            this.balanceCredit.postValue(String.format(
                    Locale.US,
                    MSG_STATIC_FORMAT_AVAILABLE,
                    (float) this.currencyInUsc / 100));
            return true;
        }
        else {
            return false;
        }
    }

    public void purchaseProduct(int productIndex) {

        ProductViewModel product = productsList.get(productIndex);

        if(product.getStock() > 0) {
            if (this.currencyInUsc - product.getPriceInUSC() < 0) {
                // not enough money
                this.userMessage.postValue("INSERT COINS");
            } else {
                productsList.get(productIndex).setStock(product.getStock() - 1);
                this.productsLiveData.postValue(productsList);
                this.removeFunds(product.getPriceInUSC());
                this.userMessage.postValue("THANK YOU FOR PURCHASE");
            }
        } else {
            this.userMessage.postValue("OUT OF STOCK");
        }
    }
}
