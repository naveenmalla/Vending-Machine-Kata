package com.malla.vendingmachine;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.malla.vendingmachine.adapter.ProductViewAdapter;
import com.malla.vendingmachine.viewmodel.ProductViewModel;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ListView listView;
    private ArrayList<ProductViewModel> productsList;
    private ProductViewAdapter productViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadProducts();

        TopFragment topFragment = new TopFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.topFragment, topFragment).commit();

        BottomFragment bottomFragment = new BottomFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.bottomFragment, bottomFragment);
        transaction.commit();

       // if (bottomFragment != null) {
        //    getSupportFragmentManager().beginTransaction().replace(R.id.bottomFragment, bottomFragment).commit();
        //}


    }

    private void loadProducts() {
        //Add products to view
        listView = (ListView) findViewById(R.id.productListView);
        productsList = new ArrayList<>();

        ProductViewModel product1 = new ProductViewModel("Coke","1.00","");
        ProductViewModel product2 = new ProductViewModel("Chips","0.50","");
        ProductViewModel product3 = new ProductViewModel("Candy","0.65","");

        productsList.add(product1);
        productsList.add(product2);
        productsList.add(product3);

        productViewAdapter = new ProductViewAdapter(this, productsList);
        listView.setAdapter(productViewAdapter);
    }
}
