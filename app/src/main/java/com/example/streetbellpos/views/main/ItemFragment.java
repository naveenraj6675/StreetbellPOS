package com.example.streetbellpos.views.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.streetbellpos.R;
import com.example.streetbellpos.constants.StreetBellConstants;
import com.example.streetbellpos.managers.GlideManager;
import com.example.streetbellpos.models.gson.Products;
import com.example.streetbellpos.options.GLIDE_TRANSFORM_OPTION;
import com.example.streetbellpos.views.base.StreetbellBaseFragment;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ItemFragment extends StreetbellBaseFragment {
    @BindView(R.id.product_name_tv)
    TextView mProductNameTV;
    @BindView(R.id.product_iv)
    ImageView mProductIV;
    @BindView(R.id.price_tv)
    TextView mPriceTV;
    @BindView(R.id.quantity_tv)
    TextView mQuantityTV;
    @BindView(R.id.add_to_basket_btn)
    Button addToBasketBtn;
    String productObj;
    private Products mProducts;
    private Gson gson;


    public static ItemFragment newInstance(Products products) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putString(StreetBellConstants.PRODUCT, Products.toJson(products));

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProducts = Products.parse(getArguments().getString(StreetBellConstants.PRODUCT));
        }
    }

    @Override
    protected void initObservers() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        ButterKnife.bind(this, view);
        initProgress(view);
        gson = new Gson();
        initViews();

        return view;
    }

    private void initViews() {

        mProductNameTV.setText(mProducts.getName());
        GlideManager.loadImage(getActivity(), mProductIV, mProducts.getImageList().get(0).getUrl(), GLIDE_TRANSFORM_OPTION.NO_TRANSFORM);
        mPriceTV.setText(mProducts.getPrice());


    }

    @OnClick(R.id.add_to_basket_btn)
    void onAddtoBasketClicked() {
        productObj = gson.toJson(mProducts);


        showConfirmation("No", "Yes", "POS", "Are you sure to add this to basket?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), CartActivity.class);
                intent.putExtra(StreetBellConstants.PRODUCT, productObj);
                startActivity(intent);
            }
        });

    }


}
