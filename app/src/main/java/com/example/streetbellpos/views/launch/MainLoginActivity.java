package com.example.streetbellpos.views.launch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

import com.example.streetbellpos.R;
import com.example.streetbellpos.constants.StreetBellConstants;
import com.example.streetbellpos.helpers.FormValidator;
import com.example.streetbellpos.models.gson.ProductCategories;
import com.example.streetbellpos.models.gson.Products;
import com.example.streetbellpos.viewModel.LaunchViewModel;
import com.example.streetbellpos.views.MainActivity;
import com.example.streetbellpos.views.base.StreetbellppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainLoginActivity extends StreetbellppCompatActivity {
    @BindView(R.id.psiname_tv)
    TextView mPsiNameTV;
    @BindView(R.id.userid_tv)
    TextView mUserIdTV;
    @BindView(R.id.countnumber_tv)
    TextView mCounteNumberTv;
    @BindView(R.id.password_et)
    EditText mPasswordET;

    private String errorMsg;
    private LaunchViewModel mViewModel;
    Gson gson = new Gson();
    private ArrayList<ProductCategories> mCategoryList;
    private ArrayList<Products> mProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        ButterKnife.bind(this);
        mCategoryList = new ArrayList<>();
        mProductList = new ArrayList<>();
        mViewModel = ViewModelProviders.of(this).get(LaunchViewModel.class);
        initProgress();
        initViews();
    }

    @Override
    public void initObservers() {
        mViewModel.getErrorLiveData().observe(this, errordata -> {
            hideProgress();
            showSnackbar(errordata);
        });


        mViewModel.getMainLoginMutableLiveData().observe(this, data -> {
            if (data.size() > 0) {
                getSharedPrefManager().setPreference(StreetBellConstants.IS_USER_LOGGED_IN, true);
                if (data.get(0).getStatusMsg().equals("your account is verified")) {
                    getCategory();
                } else {
                    showSnackbar("something went wrong");
                }

            } else {
                showSnackbar("User empty");
            }
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
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }


        });

    }


    private void initViews() {
        mPsiNameTV.setText(getSharedPrefManager().getPreference(StreetBellConstants.PSI_NAME));
        mUserIdTV.setText(getSharedPrefManager().getPreference(StreetBellConstants.USER_ID));
        mCounteNumberTv.setText(getSharedPrefManager().getPreference(StreetBellConstants.COUNTER_NUMBER));


    }

    @OnClick(R.id.login_btn)
    void onLoginClicked() {
        hideKeyboard();
        if (validateFormData()) {
            login();
        } else {
            showSnackbar(errorMsg);
        }
    }

    private boolean validateFormData() {
        boolean formOk = true;
        String pass = mPasswordET.getText().toString().trim();


        errorMsg = "";
        if (!FormValidator.requiredField(pass, 1)) {
            formOk = false;
            errorMsg = getString(R.string.enter_password);
        } else if (!FormValidator.requiredField(pass, 6)) {
            formOk = false;
            errorMsg = getString(R.string.password_length_error);
        }

        return formOk;
    }

    private void login() {
        showProgress();

        JSONObject userObject = new JSONObject();

        String deviceId = "4b62b331bceee41f";
        String userId = getSharedPrefManager().getPreference(StreetBellConstants.USER_ID);

//        @SuppressLint("HardwareIds")
//        String deviceId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        String password = mPasswordET.getText().toString().trim();

        try {
            userObject.put("uid", userId);
            userObject.put("deviceid", deviceId);
            userObject.put("password", password);


            JsonParser jsonParser = new JsonParser();
            JsonObject gsonObject = (JsonObject) jsonParser.parse(userObject.toString());
//            mViewModel.signIn(gsonObject);

            mViewModel.mainLogin(gsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }


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
