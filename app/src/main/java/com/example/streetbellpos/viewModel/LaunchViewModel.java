package com.example.streetbellpos.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.streetbellpos.managers.RetrofitManager;
import com.example.streetbellpos.models.gson.BookingResponse;
import com.example.streetbellpos.models.gson.CategoryResponse;
import com.example.streetbellpos.models.gson.LoginResponse;
import com.example.streetbellpos.models.gson.MainLoginResponse;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LaunchViewModel extends StreetbellBaseViewModel {

    private MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<LoginResponse>> mobileLoginMutableLiveData = new MutableLiveData<ArrayList<LoginResponse>>();
    private MutableLiveData<ArrayList<MainLoginResponse>> passwordCheckMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<CategoryResponse> categoryResponseMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<BookingResponse> bookingResponseMutableLiveData = new MutableLiveData<>();
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


    public void mainLogin(JsonObject gsonObject) {
        RetrofitManager.getInstance(getApplication()).getLaunchApi().getMainLogin(gsonObject).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ArrayList<MainLoginResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("Subscribe On", d.toString());
            }

            @Override
            public void onNext(ArrayList<MainLoginResponse> mainLoginResponses) {
                if (mainLoginResponses.get(0).getError().equals("")) {
                    passwordCheckMutableLiveData.postValue(mainLoginResponses);

                } else {
                    errorLiveData.postValue(mainLoginResponses.get(0).getStatusMsg());
                }


            }

            @Override
            public void onError(Throwable e) {
                errorLiveData.postValue(e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("Complete On", "Passsword Login Complete");
            }
        });

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


    public void uploadBooking(JsonObject jsonObject) {
        RetrofitManager.getInstance(getApplication()).getMainApi().uploadBooking(jsonObject).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BookingResponse>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d("Booking", "Subscribe");
            }

            @Override
            public void onNext(BookingResponse bookingResponse) {
                if (bookingResponse != null) {
                    bookingResponseMutableLiveData.postValue(bookingResponse);
                } else {
                    errorLiveData.postValue(bookingResponse.getError());
                }
            }

            @Override
            public void onError(Throwable e) {
                errorLiveData.postValue(e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("Booking", "Complete");
            }
        });


    }

    public MutableLiveData<CategoryResponse> getCategoryResponseMutableLiveData() {
        return categoryResponseMutableLiveData;
    }

    public MutableLiveData<ArrayList<LoginResponse>> getMobileLoginMutableLiveData() {
        return mobileLoginMutableLiveData;
    }

    public MutableLiveData<ArrayList<MainLoginResponse>> getMainLoginMutableLiveData() {
        return passwordCheckMutableLiveData;
    }
    public MutableLiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    public MutableLiveData<BookingResponse> getBookingResponseLiveData() {
        return bookingResponseMutableLiveData;
    }


}
