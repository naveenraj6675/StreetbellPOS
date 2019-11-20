package com.example.streetbellpos.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.streetbellpos.managers.RetrofitManager;
import com.example.streetbellpos.models.gson.LoginResponse;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LaunchViewModel extends StreetbellBaseViewModel {

    private MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<LoginResponse>> mobileLoginMutableLiveData = new MutableLiveData<ArrayList<LoginResponse>>();

    public LaunchViewModel(@NonNull Application application) {
        super(application);
    }


    public void mobileLogin(JsonObject gsonObject) {
        RetrofitManager.getInstance(getApplication()).getLaunchApi().getMobileLogin(gsonObject).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ArrayList<LoginResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("Subscribe On", d.toString());
            }

            @Override
            public void onNext(ArrayList<LoginResponse> loginResponse) {
                if (loginResponse.get(0).getError().equals("")) {
                    mobileLoginMutableLiveData.postValue(loginResponse);
                } else {
                    errorLiveData.postValue(loginResponse.get(0).getStatusMessage());
                }

            }

            @Override
            public void onError(Throwable e) {
                errorLiveData.postValue(e.toString());
            }

            @Override
            public void onComplete() {
                Log.d("Complete On", "Mobile Login Complete");
            }
        });

    }

    public MutableLiveData<ArrayList<LoginResponse>> getMobileLoginMutableLiveData() {
        return mobileLoginMutableLiveData;
    }

    public MutableLiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

}
