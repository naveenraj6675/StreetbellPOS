package com.example.streetbellpos.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.streetbellpos.R;
import com.example.streetbellpos.adapters.CategoryRecyclerAdapter;
import com.example.streetbellpos.constants.StreetBellConstants;
import com.example.streetbellpos.models.gson.ProductCategories;
import com.example.streetbellpos.models.gson.Products;
import com.example.streetbellpos.views.base.StreetbellppCompatActivity;
import com.example.streetbellpos.views.main.CategoryDeailActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends StreetbellppCompatActivity {
    @BindView(R.id.category_rv)
    RecyclerView mCategoryRV;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    //    private MainViewmodel mViewModel;
    private ArrayList<ProductCategories> mCategoryList;
    private ArrayList<Products> mProductList;
    Gson gson = new Gson();
    private CategoryRecyclerAdapter mCatAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        mViewModel = ViewModelProviders.of(this).get(MainViewmodel.class);
        mCategoryList = new ArrayList<>();
        mProductList = new ArrayList<>();
        initProgress();
        initViews();
        initRecyclerView(mCategoryList);

    }


    @Override
    public void initObservers() {

    }


    private void initViews() {

        String goalList = getSharedPrefManager().getPreference(StreetBellConstants.CATEGORY_LIST);
        ProductCategories[] goal = gson.fromJson(goalList, ProductCategories[].class);
        if (goal != null) {
            mCategoryList.addAll(Arrays.asList(goal));

        }

    }

    private void initRecyclerView(ArrayList<ProductCategories> mList) {


        mCategoryRV.setLayoutManager(new GridLayoutManager(this, 2));
        mCatAdapter = new CategoryRecyclerAdapter(this, mCategoryList, pos -> {
            Intent intent = new Intent(this, CategoryDeailActivity.class);
            intent.putExtra(StreetBellConstants.CLICKED_POS, pos);
            startActivity(intent);
        });
        mCategoryRV.setAdapter(mCatAdapter);
    }



}
