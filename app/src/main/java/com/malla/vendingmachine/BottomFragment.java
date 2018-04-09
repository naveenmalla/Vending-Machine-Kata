package com.malla.vendingmachine;

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
                EditText s = (EditText) view.findViewById(R.id.txtCoin);
                float usdValue = Float.parseFloat(s.getText().toString());
                int coinValue = (int) Math.floor(usdValue * 100);
                //TextView d = (TextView) view.findViewById(R.id.txtBalance) ;

                if(viewModel.addFunds(coinValue)){
                    s.setText("");
                    //d.setText("25");
                } else {
                    s.setError("Please insert only nickels, dimes, and quarters");
                }
                s.setText("");
            }
        });

        return view;
    }

}
