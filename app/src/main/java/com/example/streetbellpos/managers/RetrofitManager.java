package com.example.streetbellpos.managers;

import android.content.Context;
import android.content.ContextWrapper;

import com.example.streetbellpos.R;
import com.example.streetbellpos.managers.helper.ConnectivityInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Converter;
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

    private Retrofit getFileUploadRetrofit(String contentType) {
        if (fileUploadRetrofit == null) {
            createFileUploadRetrofit(contentType);
        }
        return fileUploadRetrofit;
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
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });


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

                    .method(original.method(), original.body())
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


    private void createFileUploadRetrofit(String contentType) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(new ConnectivityInterceptor(getApplicationContext()));
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .header("Content-Type", "image/png")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        });


        OkHttpClient client = httpClient
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
//        userRetrofit = new Retrofit.Builder().baseUrl(getBaseContext().getString(R.string.base_url) + getBaseContext().getString(R.string.api_version))
        fileUploadRetrofit = new Retrofit.Builder().baseUrl(getString(R.string.base_url))
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    public void clearToken() {
        userRetrofit = null;
        fileUploadRetrofit = null;
        refreshTokenRetrofit = null;
    }

    public class NullOnEmptyConverterFactory extends Converter.Factory {

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
            return new Converter<ResponseBody, Object>() {
                @Override
                public Object convert(ResponseBody body) throws IOException {
                    if (body.contentLength() == 0)
                        return "success";
                    return delegate.convert(body);
                }
            };
        }
    }

}
