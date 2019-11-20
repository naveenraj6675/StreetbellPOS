package com.example.streetbellpos.views.launch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

import com.example.streetbellpos.R;
import com.example.streetbellpos.constants.StreetBellConstants;
import com.example.streetbellpos.helpers.FormValidator;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
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


        mViewModel.getMainLoginMutableLiveData().observe(this, data -> {
            if (data.size() > 0) {
                getSharedPrefManager().setPreference(StreetBellConstants.IS_USER_LOGGED_IN, true);
                if (data.get(0).getStatusMsg().equals("your account is verified")) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    showSnackbar("something went wrong");
                }

            } else {
                showSnackbar("User empty");
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


}
