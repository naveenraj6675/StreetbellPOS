package com.example.streetbellpos.views.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.streetbellpos.R;
import com.example.streetbellpos.adapters.ProductRecyclerAdapter;
import com.example.streetbellpos.constants.StreetBellConstants;
import com.example.streetbellpos.models.gson.Products;
import com.example.streetbellpos.views.base.StreetbellBaseFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemDetailFragment extends StreetbellBaseFragment {


    @BindView(R.id.category_detail_rv)
    RecyclerView mCategoryDetailRV;
    Gson gson = new Gson();
    private String productString;
    private ArrayList<Products> mProductList = new ArrayList<>();
    private ProductListener mListener;
    private ProductRecyclerAdapter mAdapter;

    public static ItemDetailFragment newInstance(ArrayList<Products> mProductList) {
        ItemDetailFragment fragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        Gson gson = new Gson();

        args.putString(StreetBellConstants.PRODUCT_LIST, gson.toJson(mProductList));


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String focusList = getArguments().getString(StreetBellConstants.PRODUCT_LIST);
            Products[] foci = gson.fromJson(focusList, Products[].class);
            mProductList.addAll(Arrays.asList(foci));
        }
    }

    @Override
    protected void initObservers() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);
        ButterKnife.bind(this, view);
        initProgress(view);
        initViews();
        return view;
    }


    private void initViews() {

        mCategoryDetailRV.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mAdapter = new ProductRecyclerAdapter(getActivity(), mProductList, new ProductRecyclerAdapter.ProductInterface() {
            @Override
            public void onProductClicked(int pos) {
                mListener.onProducetClicked(mProductList.get(pos));
            }
        });
        mCategoryDetailRV.setAdapter(mAdapter);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ProductListener) {
            mListener = (ProductListener) context;
        }
    }

    public interface ProductListener {
        void onProducetClicked(Products products);
    }


}
