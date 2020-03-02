package com.example.streetbellpos.views.launch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.lifecycle.ViewModelProviders;

import com.example.streetbellpos.R;
import com.example.streetbellpos.constants.StreetBellConstants;
import com.example.streetbellpos.helpers.FormValidator;
import com.example.streetbellpos.models.gson.LoginResponse;
import com.example.streetbellpos.viewModel.LaunchViewModel;
import com.example.streetbellpos.views.MainActivity;
import com.example.streetbellpos.views.base.StreetbellppCompatActivity;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MobileLoginActivity extends StreetbellppCompatActivity {
    @BindView(R.id.code_et)
    EditText mCodeET;
    @BindView(R.id.number_et)
    EditText mMobileNumberET;

    private String errorMsg;
    private LaunchViewModel mViewModel;
    private LoginResponse mResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_login);
        ButterKnife.bind(this);
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


        mViewModel.getMobileLoginMutableLiveData().observe(this, loginResponses -> {
            hideProgress();
            if (loginResponses.size() > 0) {
                getSharedPrefManager().setPreference(StreetBellConstants.USER_ID, loginResponses.get(0).getUid());
                getSharedPrefManager().setPreference(StreetBellConstants.PSI_NAME, loginResponses.get(0).getPsiName());
                getSharedPrefManager().setPreference(StreetBellConstants.SHOP_ID, loginResponses.get(0).getShopId());
                getSharedPrefManager().setPreference(StreetBellConstants.COUNTER_NUMBER, loginResponses.get(0).getTableNumber());
                getSharedPrefManager().setPreference(StreetBellConstants.USER_MOBILE, loginResponses.get(0).getMobileNumber());

                Intent intent = new Intent(this, MainLoginActivity.class);
                startActivity(intent);
                finish();


            } else {
                mResponse = null;
            }

        });

    }

    @OnClick(R.id.next_btn)
    void onNextClicked() {
        hideKeyboard();
        if (validateFormData()) {
            login();
        } else {
            showSnackbar(errorMsg);
        }

    }


    private boolean validateFormData() {
        boolean formOk = true;
        String code = mCodeET.getText().toString().trim();
        String mobile = mMobileNumberET.getText().toString().trim();

        errorMsg = "";
        if (!FormValidator.requiredField(code, 1)) {
            formOk = false;
            errorMsg = getString(R.string.enter_code);
        } else if (!FormValidator.requiredField(mobile, 1)) {
            formOk = false;
            errorMsg = getString(R.string.enter_number);
        }

        return formOk;
    }

    private void login() {
        showProgress();

        JSONObject userObject = new JSONObject();

        String deviceId = "4b62b331bceee41f";

//        @SuppressLint("HardwareIds")
//        String deviceId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);


        String mobileNumber = mMobileNumberET.getText().toString().trim();

        String code = mCodeET.getText().toString().trim();

        try {
            userObject.put("ccode", code);
            userObject.put("userphone", mobileNumber);
            userObject.put("deviceid", deviceId);
            userObject.put("appversion", "7.8");


            JsonParser jsonParser = new JsonParser();
            JsonObject gsonObject = (JsonObject) jsonParser.parse(userObject.toString());
//            mViewModel.signIn(gsonObject);

            mViewModel.mobileLogin(gsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initViews() {
        if (getSharedPrefManager().getBooleanPreference(StreetBellConstants.IS_USER_LOGGED_IN)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            onResume();
        }
    }
}
