package com.example.streetbellpos.views.main;

import android.os.Bundle;

import com.example.streetbellpos.R;
import com.example.streetbellpos.constants.StreetBellConstants;
import com.example.streetbellpos.models.gson.Products;
import com.example.streetbellpos.views.base.StreetbellppCompatActivity;

public class CartActivity extends StreetbellppCompatActivity {

    private Products mProducts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initViews();
        System.out.println(mProducts);
    }

    @Override
    public void initObservers() {

    }

    private void initViews() {

        if (getIntent() != null) {
            mProducts = Products.parse(getIntent().getStringExtra(StreetBellConstants.PRODUCT));
        }

    }
}
