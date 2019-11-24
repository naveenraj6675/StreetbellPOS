package com.example.streetbellpos.views.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.streetbellpos.R;
import com.example.streetbellpos.adapters.CategoryDeailRecyclerAdapter;
import com.example.streetbellpos.constants.StreetBellConstants;
import com.example.streetbellpos.models.gson.BookingDetails;
import com.example.streetbellpos.models.gson.ProductCategories;
import com.example.streetbellpos.models.gson.Products;
import com.example.streetbellpos.room.BookingDatabase;
import com.example.streetbellpos.viewModel.LaunchViewModel;
import com.example.streetbellpos.views.base.StreetbellppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryDeailActivity extends StreetbellppCompatActivity implements ItemDetailFragment.ProductListener {

    @BindView(R.id.tabRV)
    RecyclerView mTabRV;
    @BindView(R.id.ic_sync)
    ImageView mSyncIV;


    int clickedPos;
    private LaunchViewModel mViewModel;

    private Fragment fragment;
    Gson gson = new Gson();
    private FragmentManager fragmentManager;
    private ArrayList<ProductCategories> mCategoryDeailList;
    private BookingDatabase bookingDatabase;
    private List<BookingDetails> mBookingList;
    private CategoryDeailRecyclerAdapter mCatDeailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_deail);
        ButterKnife.bind(this);
        mCategoryDeailList = new ArrayList<>();
        mViewModel = ViewModelProviders.of(this).get(LaunchViewModel.class);
        mBookingList = new ArrayList<>();
        bookingDatabase = Room.databaseBuilder(this, BookingDatabase.class, "bookingDatabase").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        mBookingList.addAll(bookingDatabase.bookingDao().getAllBooking());
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
        mCatDeailAdapter = new CategoryDeailRecyclerAdapter(this, clickedPos, mList, pos -> {
            showframent(pos);
        } );
        mTabRV.setAdapter(mCatDeailAdapter);
        mTabRV.scrollToPosition(clickedPos);

        showframent(clickedPos);

    }

    @OnClick(R.id.ic_sync)
    void onSyncLicked() {
        if (mBookingList.size() == 0) {
            showAlertDialogOk("POS", "You dont have any bookings yet", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        } else {
            showProgress();
            uploadBooking();
        }


    }


    @Override
    public void initObservers() {

        mViewModel.getErrorLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                showSnackbar(s);
            }
        });


        mViewModel.getBookingResponseLiveData().observe(this, bookingResponse -> {
            hideProgress();
            if (bookingResponse.getStatusMessage().equals("success")) {
                showAlertDialogOk("POS", "Your bookings of " + mBookingList.size() + " items uploaded successfully!!!", (dialog, which) -> dialog.dismiss());
            }
        });


    }

    private void uploadBooking() {

        JSONObject userObject = new JSONObject();

        String userId = getSharedPrefManager().getPreference(StreetBellConstants.USER_ID);
        String deviceId = "4b62b331bceee41f";
        String paiName = getSharedPrefManager().getPreference(StreetBellConstants.PSI_NAME);


//        sendBookingList.addAll(fromString(book));


        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < mBookingList.size(); i++) {
            jsonArray.put(mBookingList.get(i).getJSONObject());
        }


        try {

            userObject.put("uid", userId);
            userObject.put("deviceid", deviceId);
            userObject.put("psiname", paiName);
            userObject.put("bookingdetails", jsonArray);

            JsonParser jsonParser = new JsonParser();
            JsonObject gsonObject = (JsonObject) jsonParser.parse(String.valueOf(userObject));

            mViewModel.uploadBooking(gsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }


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
