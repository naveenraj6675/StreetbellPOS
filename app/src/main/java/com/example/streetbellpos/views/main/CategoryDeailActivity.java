package com.example.streetbellpos.views.main;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.example.streetbellpos.R;
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
    private ArrayList<ProductCategories> mCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_deail);
        ButterKnife.bind(this);
        mCategoryList = new ArrayList<>();
        initProgress();
        initViews();
    }


    private void initViews() {

        String goalList = getSharedPrefManager().getPreference(StreetBellConstants.CATEGORY_LIST);
        ProductCategories[] goal = gson.fromJson(goalList, ProductCategories[].class);
        if (goal != null)
            mCategoryList.addAll(Arrays.asList(goal));


    }


    @Override
    public void initObservers() {

    }
}
