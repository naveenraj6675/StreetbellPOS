package com.example.streetbellpos.managers;

import android.content.Context;
import android.content.ContextWrapper;

import com.example.streetbellpos.R;
import com.example.streetbellpos.managers.helper.ConnectivityInterceptor;
import com.example.streetbellpos.managers.retrofit.LaunchApi;
import com.example.streetbellpos.managers.retrofit.MainApi;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager extends ContextWrapper {

    private static RetrofitManager mInstance;
    private Retrofit defaultRetrofit;
    private Retrofit userRetrofit;
    private Retrofit fileUploadRetrofit;
    private Retrofit refreshTokenRetrofit;


    private RetrofitManager(Context base) {
        super(base);
    }

    public static synchronized RetrofitManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new RetrofitManager(context.getApplicationContext());
        }
        return mInstance;
    }

    public void clearAllRetrofit() {
        userRetrofit = null;
        fileUploadRetrofit = null;
        refreshTokenRetrofit = null;
    }

    private Retrofit getDefaultRetrofit() {
        if (defaultRetrofit == null) {
            createDefaultRetrofit();
        }
        return defaultRetrofit;
    }


    private Retrofit getUserRetrofit() {
        if (userRetrofit == null) {
            createUserRetrofit();
        }
        return userRetrofit;
    }


    private Retrofit getRefreshTokenRetrofit() {
        if (refreshTokenRetrofit == null) {

        }
        return refreshTokenRetrofit;
    }


    private void createDefaultRetrofit() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(new ConnectivityInterceptor(getApplicationContext()));
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .build();

                return chain.proceed(request);
            }
        });

        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logInterceptor);

        OkHttpClient client = httpClient.build();

        defaultRetrofit = new Retrofit.Builder().baseUrl(getBaseContext().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    private void createUserRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(new ConnectivityInterceptor(getApplicationContext()));
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .build();

            return chain.proceed(request);
        });


        OkHttpClient client = httpClient.build();
//        userRetrofit = new Retrofit.Builder().baseUrl(getBaseContext().getString(R.string.base_url) + getBaseContext().getString(R.string.api_version))
        userRetrofit = new Retrofit.Builder().baseUrl(getBaseContext().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }


    public LaunchApi getLaunchApi() {
        return getDefaultRetrofit().create(LaunchApi.class);
    }

    public MainApi getMainApi() {
        return getDefaultRetrofit().create(MainApi.class);
    }


    public void clearToken() {
        userRetrofit = null;
        fileUploadRetrofit = null;
        refreshTokenRetrofit = null;
    }



}
