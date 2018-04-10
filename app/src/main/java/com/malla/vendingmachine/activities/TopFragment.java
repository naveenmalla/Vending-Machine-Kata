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
import android.widget.EditText;
import android.widget.TextView;

import com.malla.vendingmachine.R;
import com.malla.vendingmachine.viewmodel.VendingMachineViewModel;

public class TopFragment extends Fragment {

    private VendingMachineViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.top_layout, container, false);

        viewModel = ViewModelProviders.of(getActivity()).get(VendingMachineViewModel.class);

        // Create the observers which update the UI
        final Observer<String> balanceObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                ((TextView) view.findViewById(R.id.txtBalance)).setText(s);
            }
        };
        viewModel.getVendingMachineBalance().observe(this, balanceObserver);

        final Observer<String> messageObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                ((TextView) view.findViewById(R.id.txtMessage)).setText(s);
            }
        };
        viewModel.getVendingMachineMessage().observe(this, messageObserver);

        view.findViewById(R.id.btnReturnCoins).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                viewModel.returnFunds();
            }
        });

        return view;
    }

}
