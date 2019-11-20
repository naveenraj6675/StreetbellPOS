package com.example.streetbellpos.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.streetbellpos.managers.RetrofitManager;
import com.example.streetbellpos.models.gson.CategoryResponse;
import com.google.gson.JsonObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewmodel extends StreetbellBaseViewModel {
    private MutableLiveData<CategoryResponse> categoryResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    public MainViewmodel(@NonNull Application application) {
        super(application);
    }

    public void getCategory(JsonObject jsonObject) {
        RetrofitManager.getInstance(getApplication()).getMainApi().getCategory(jsonObject).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CategoryResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("Category", "Subscribe");
            }

            @Override
            public void onNext(CategoryResponse categoryResponse) {
                if (categoryResponse != null) {
                    categoryResponseMutableLiveData.postValue(categoryResponse);
                } else {
                    errorLiveData.postValue(categoryResponse.getError());
                }
            }

            @Override
            public void onError(Throwable e) {
                errorLiveData.postValue(e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("Category", "Complete");
            }
        });
    }

    public MutableLiveData<CategoryResponse> getCategoryResponseMutableLiveData() {
        return categoryResponseMutableLiveData;
    }

    public MutableLiveData<String> getErrorLiveData() {
        return errorLiveData;
    }


}
