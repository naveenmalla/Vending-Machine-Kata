package com.malla.vendingmachine.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.malla.vendingmachine.R;
import com.malla.vendingmachine.adapter.ProductViewAdapter;
import com.malla.vendingmachine.viewmodel.ProductViewModel;
import com.malla.vendingmachine.viewmodel.VendingMachineViewModel;

import java.util.ArrayList;
import java.util.List;

public class CenterFragment extends Fragment {

    private ListView listView;
    private ArrayList<ProductViewModel> productsList;
    private ProductViewAdapter productViewAdapter;
    private VendingMachineViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.center_layout, container, false);

        viewModel = ViewModelProviders.of(getActivity()).get(VendingMachineViewModel.class);
        loadProducts(view, viewModel);

        return view;
    }

    //Add products to view
    private void loadProducts(View view, VendingMachineViewModel vModel) {

        listView = (ListView) view.findViewById(R.id.productListView);

        viewModel.getProductsList().observe(this, productsList -> {
            // update UI
            productViewAdapter = new ProductViewAdapter(getActivity(),productsList);
            // Assign adapter to ListView
            listView.setAdapter(productViewAdapter);
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                //String prodPrice = productsList.get(position).getPrice();

                //float usdValue = Float.parseFloat(prodPrice);
                //int coinValue = (int) Math.floor(usdValue * 100);

                //For now just sending coinValue instead of Product as I not maintaining product list extensively with stock availability
                vModel.purchaseProduct(position);
            }
        });
    }
}
