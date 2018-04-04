package com.malla.vendingmachine.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.malla.vendingmachine.R;
import com.malla.vendingmachine.databinding.ProductViewBinding;
import com.malla.vendingmachine.viewmodel.ProductViewModel;

import java.util.ArrayList;

public class ProductViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ProductViewModel> productList;
    private ProductViewBinding productViewBinding;


    public ProductViewAdapter(Context context, ArrayList<ProductViewModel> productList){
        this.context = context;
        this.productList = productList;
    }
    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.productitemlayout,null);
            productViewBinding = DataBindingUtil.bind(convertView);
            convertView.setTag(productViewBinding);
        }
        else {
          productViewBinding = (ProductViewBinding) convertView.getTag();
        }

        productViewBinding.setProductmodel(productList.get(position));

        return productViewBinding.getRoot();
    }
}
