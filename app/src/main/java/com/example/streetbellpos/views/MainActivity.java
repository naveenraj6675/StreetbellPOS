package com.example.streetbellpos.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.streetbellpos.R;
import com.example.streetbellpos.adapters.CategoryRecyclerAdapter;
import com.example.streetbellpos.constants.StreetBellConstants;
import com.example.streetbellpos.models.gson.ProductCategories;
import com.example.streetbellpos.models.gson.Products;
import com.example.streetbellpos.viewModel.MainViewmodel;
import com.example.streetbellpos.views.base.StreetbellppCompatActivity;
import com.example.streetbellpos.views.main.CategoryDeailActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends StreetbellppCompatActivity {
    @BindView(R.id.category_rv)
    RecyclerView mCategoryRV;


    private MainViewmodel mViewModel;
    private ArrayList<ProductCategories> mCategoryList;
    private ArrayList<Products> mProductList;
    Gson gson = new Gson();
    private CategoryRecyclerAdapter mCatAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mViewModel = ViewModelProviders.of(this).get(MainViewmodel.class);
        mCategoryList = new ArrayList<>();
        mProductList = new ArrayList<>();
        initProgress();
        initViews();

    }


    @Override
    public void initObservers() {
        mViewModel.getErrorLiveData().observe(this, error -> {
            hideProgress();
            showSnackbar(error);
        });


        mViewModel.getCategoryResponseMutableLiveData().observe(this, categoryResponse -> {
            hideProgress();
            if (categoryResponse != null) {
                mCategoryList.addAll(categoryResponse.getCategoriesList());

                for (int i = 0; i < mCategoryList.size(); i++) {
                    mProductList.addAll(mCategoryList.get(i).getProducts());
                }

                mCategoryList.add(0, new ProductCategories("", "Browse All", mProductList));
                getSharedPrefManager().setPreference(StreetBellConstants.CATEGORY_LIST, gson.toJson(mCategoryList));

                mCatAdapter.notifyDataSetChanged();
            }


        });

    }


    private void initViews() {
        getCategory();

        mCategoryRV.setLayoutManager(new GridLayoutManager(this, 2));
        mCatAdapter = new CategoryRecyclerAdapter(this, mCategoryList, pos -> {
            Intent intent = new Intent(this, CategoryDeailActivity.class);
            startActivity(intent);
        });
        mCategoryRV.setAdapter(mCatAdapter);

    }


    private void getCategory() {
        showProgress();
        JSONObject userObject = new JSONObject();

        String deviceId = "4b62b331bceee41f";
        String userId = getSharedPrefManager().getPreference(StreetBellConstants.USER_ID);

//        @SuppressLint("HardwareIds")
//        String deviceId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        String shopId = getSharedPrefManager().getPreference(StreetBellConstants.SHOP_ID);


        try {
            userObject.put("uid", userId);
            userObject.put("deviceid", deviceId);
            userObject.put("shopid", shopId);


            JsonParser jsonParser = new JsonParser();
            JsonObject gsonObject = (JsonObject) jsonParser.parse(userObject.toString());
//            mViewModel.signIn(gsonObject);

            mViewModel.getCategory(gsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
