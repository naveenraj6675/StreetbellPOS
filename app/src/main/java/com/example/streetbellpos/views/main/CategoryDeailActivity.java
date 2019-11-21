package com.example.streetbellpos.views.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.streetbellpos.R;
import com.example.streetbellpos.adapters.CategoryDeailRecyclerAdapter;
import com.example.streetbellpos.constants.StreetBellConstants;
import com.example.streetbellpos.models.gson.ProductCategories;
import com.example.streetbellpos.models.gson.Products;
import com.example.streetbellpos.views.base.StreetbellppCompatActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryDeailActivity extends StreetbellppCompatActivity implements ItemDetailFragment.ProductListener {

    @BindView(R.id.tabRV)
    RecyclerView mTabRV;


    int clickedPos;
    private Fragment fragment;
    Gson gson = new Gson();
    private FragmentManager fragmentManager;
    private ArrayList<ProductCategories> mCategoryDeailList;
    private CategoryDeailRecyclerAdapter mCatDeailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_deail);
        ButterKnife.bind(this);
        mCategoryDeailList = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();
        initProgress();
        initViews();
        initRecyclerView(mCategoryDeailList);
    }


    private void initViews() {

        if (getIntent() != null) {
            clickedPos = getIntent().getIntExtra(StreetBellConstants.CLICKED_POS, 1);
        }

        String goalList = getSharedPrefManager().getPreference(StreetBellConstants.CATEGORY_LIST);
        ProductCategories[] goal = gson.fromJson(goalList, ProductCategories[].class);
        if (goal != null) {
            mCategoryDeailList.addAll(Arrays.asList(goal));
        }



    }

    private void initRecyclerView(ArrayList<ProductCategories> mList){


        mTabRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        mCatDeailAdapter=new CategoryDeailRecyclerAdapter(this,mList,pos ->{
            showframent(pos);
        } );
        mTabRV.setAdapter(mCatDeailAdapter);

        showframent(clickedPos);

    }


    @Override
    public void initObservers() {

    }

    private void showframent(int pos) {
        fragment = ItemDetailFragment.newInstance(mCategoryDeailList.get(pos).getProducts());
        fragmentManager.beginTransaction().replace(R.id.categoryFrame, fragment).commit();

    }

    @Override
    public void onProducetClicked(Products products) {
        fragment = ItemFragment.newInstance(products);
        fragmentManager.beginTransaction().replace(R.id.categoryFrame, fragment).addToBackStack(null).commit();
    }
}
