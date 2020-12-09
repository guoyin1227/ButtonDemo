package com.module.core_http.http;

import android.app.Application;
import android.content.Context;

/**
 * @autor: kifer
 * @date:2020/12/3
 * @desc: c程序入口初始化设置
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;

    public static Context getContext() {
        return getInstance();
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
