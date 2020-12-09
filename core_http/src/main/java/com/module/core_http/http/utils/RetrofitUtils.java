package com.module.core_http.http.utils;


import com.module.core_http.http.BaseApplication;
import com.module.core_http.http.client.AppConstants;
import com.module.core_http.http.client.NullOnEmptyConverterFactory;
import com.module.core_http.http.client.OkHttpClientHelper;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit工具类
 *
 * @author yy
 * @DATE 2019/6/8
 * @VERSION 6.3.0
 */
public class RetrofitUtils {
    static volatile RetrofitUtils mInstance;
    private Retrofit mRetrofit;
    public static RetrofitUtils getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtils.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitUtils();
                }
            }
        }
        return mInstance;
    }

    private RetrofitUtils() {
        initRetrofit();
    }

    /**
     * 初始化Retrofit
     */
    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.HTTP.BASE_URL)
                .client(OkHttpClientHelper.getInstance(BaseApplication.getInstance()).getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}