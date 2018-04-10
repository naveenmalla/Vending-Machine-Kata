package com.malla.vendingmachine.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.malla.vendingmachine.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TopFragment topFragment = new TopFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.topFragment, topFragment).commit();

        CenterFragment centerFragment = new CenterFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.centerFragment, centerFragment).commit();

        BottomFragment bottomFragment = new BottomFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.bottomFragment, bottomFragment);
        transaction.commit();

       // if (bottomFragment != null) {
        //    getSupportFragmentManager().beginTransaction().replace(R.id.bottomFragment, bottomFragment).commit();
        //}


    }
}
