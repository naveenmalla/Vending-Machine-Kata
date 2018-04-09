package com.malla.vendingmachine.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

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
    // - format: like normal, but also must be run through string.Format() with a float (price/value/USD)
    private static final String MSG_STATIC_FORMAT_AVAILABLE = "$%3.2f";

    @NonNull
    public final MutableLiveData<String> balanceCredit = new MutableLiveData<>();

    public LiveData<String> getVendingMachineBalance() {
        return this.balanceCredit;
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
}
