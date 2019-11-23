package com.example.streetbellpos.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.streetbellpos.R;
import com.example.streetbellpos.adapters.CategoryRecyclerAdapter;
import com.example.streetbellpos.constants.StreetBellConstants;
import com.example.streetbellpos.models.gson.BookingDetails;
import com.example.streetbellpos.models.gson.ProductCategories;
import com.example.streetbellpos.room.BookingDatabase;
import com.example.streetbellpos.viewModel.LaunchViewModel;
import com.example.streetbellpos.views.base.StreetbellppCompatActivity;
import com.example.streetbellpos.views.main.CategoryDeailActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends StreetbellppCompatActivity {
    @BindView(R.id.category_rv)
    RecyclerView mCategoryRV;
    @BindView(R.id.ic_sync)
    ImageView mSyncIV;
    private LaunchViewModel mViewModel;
    private List<BookingDetails> mBookingList;
    private ArrayList<ProductCategories> mCategoryList;
    private List<BookingDetails> sendBookingList;
    private BookingDetails bookingDetails = new BookingDetails();
    private Gson gson = new GsonBuilder().create();
    private BookingDatabase bookingDatabase;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
    private CategoryRecyclerAdapter mCatAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mViewModel = ViewModelProviders.of(this).get(LaunchViewModel.class);
        mCategoryList = new ArrayList<>();
        mBookingList = new ArrayList<>();
        sendBookingList = new ArrayList<>();
        bookingDatabase = Room.databaseBuilder(this, BookingDatabase.class, "bookingDatabase").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        mBookingList.addAll(bookingDatabase.bookingDao().getAllBooking());
        initProgress();
        initViews();
        initRecyclerView(mCategoryList);

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
                showAlertDialogOk("POS", "Your bookings uploaded successfully!!!", (dialog, which) -> dialog.dismiss());
            }
        });


    }


    private void initViews() {

        String goalList = getSharedPrefManager().getPreference(StreetBellConstants.CATEGORY_LIST);
        ProductCategories[] goal = gson.fromJson(goalList, ProductCategories[].class);
        if (goal != null) {
            mCategoryList.addAll(Arrays.asList(goal));
        }

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

    private void initRecyclerView(ArrayList<ProductCategories> mList) {


        mCategoryRV.setLayoutManager(new GridLayoutManager(this, 2));
        mCatAdapter = new CategoryRecyclerAdapter(this, mCategoryList, pos -> {
            Intent intent = new Intent(this, CategoryDeailActivity.class);
            intent.putExtra(StreetBellConstants.CLICKED_POS, pos);
            startActivity(intent);
        });
        mCategoryRV.setAdapter(mCatAdapter);
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


//
            JsonParser jsonParser = new JsonParser();

            JsonObject gsonObject = (JsonObject) jsonParser.parse(String.valueOf(userObject));

            mViewModel.uploadBooking(gsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public String fromArrayList(List<BookingDetails> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    public ArrayList<BookingDetails> fromString(String value) {
        Type listType = new TypeToken<ArrayList<BookingDetails>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }



}
