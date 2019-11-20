package com.example.streetbellpos.views.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.streetbellpos.R;
import com.example.streetbellpos.adapters.CategoryDeailRecyclerAdapter;
import com.example.streetbellpos.constants.StreetBellConstants;
import com.example.streetbellpos.models.gson.ProductCategories;
import com.example.streetbellpos.views.base.StreetbellppCompatActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryDeailActivity extends StreetbellppCompatActivity {

    @BindView(R.id.tabRV)
    RecyclerView mTabRV;



    Gson gson = new Gson();
    private ArrayList<ProductCategories> mCategoryDeailList;
    private CategoryDeailRecyclerAdapter mCatDeailAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_deail);
        ButterKnife.bind(this);
        mCategoryDeailList = new ArrayList<>();
        initProgress();
        initViews();
        initRecyclerView(mCategoryDeailList);
    }


    private void initViews() {

        String goalList = getSharedPrefManager().getPreference(StreetBellConstants.CATEGORY_LIST);
        ProductCategories[] goal = gson.fromJson(goalList, ProductCategories[].class);
        if (goal != null) {
            mCategoryDeailList.addAll(Arrays.asList(goal));
        }


    }

    private void initRecyclerView(ArrayList<ProductCategories> mList){
        mTabRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        mCatDeailAdapter=new CategoryDeailRecyclerAdapter(this,mList,pos ->{

        } );
        mTabRV.setAdapter(mCatDeailAdapter);

    }


    @Override
    public void initObservers() {

    }
}
