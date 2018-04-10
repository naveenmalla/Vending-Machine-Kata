package com.malla.vendingmachine.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.malla.vendingmachine.R;
import com.malla.vendingmachine.viewmodel.VendingMachineViewModel;

public class BottomFragment extends Fragment {

    private VendingMachineViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.bottom_layout, container, false);

        viewModel = ViewModelProviders.of(getActivity()).get(VendingMachineViewModel.class);

        view.findViewById(R.id.btnInsertCoin).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText txtCoin = (EditText) view.findViewById(R.id.txtCoin);
                String coin = txtCoin.getText().toString();
                if(coin.length() != 0) {
                    float usdValue = Float.parseFloat(coin);
                    int coinValue = (int) Math.floor(usdValue * 100);
                    if (viewModel.addFunds(coinValue)) {
                        txtCoin.setText("");
                    } else {
                        txtCoin.setError("Please insert only nickels, dimes, and quarters");
                    }
                } else {
                    txtCoin.setError("Please insert only nickels, dimes, and quarters");
                }
                txtCoin.setText("");
            }
        });

        return view;
    }

}
