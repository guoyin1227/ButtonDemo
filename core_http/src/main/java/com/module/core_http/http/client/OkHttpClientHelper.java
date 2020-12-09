package com.module.core_http.http.client;

import android.content.Context;

import androidx.annotation.NonNull;

import com.module.core_http.http.utils.FileUtils;
import com.module.core_http.http.utils.NetworkUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 说明：OkHttpClient帮助类
 *
 * @author yy
 * @DATE 2019/5/30
 * @VERSION 6.3.0
 */

public class OkHttpClientHelper {
    private OkHttpClient okHttpClient;
    private static Context sContext;

    private OkHttpClientHelper() {
        init();
    }

    public static OkHttpClientHelper getInstance(Context context) {
        sContext = context;
        return OkHttpClientHolder.instance;
    }

    /**
     * OkHttpClient 基本设置
     */
    private void init() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //添加HttpLogging拦截器，方便观察，上传和返回json
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);

        //基本设置
        builder.readTimeout(AppConstants.READ_TIME, TimeUnit.SECONDS)
                .writeTimeout(AppConstants.WRITE_TIME, TimeUnit.SECONDS)
                .connectTimeout(AppConstants.CONNECT_TIME, TimeUnit.SECONDS)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())//配置
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())//配置
                .cookieJar(new CookieJar() {
                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>(16);

                    @Override
                    public void saveFromResponse(@NonNull HttpUrl url, @NonNull List<Cookie> cookies) {
                        cookieStore.put(HttpUrl.parse(url.host()), cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(@NonNull HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(url.host());
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                });

        //添加Cache拦截器，有网时添加到缓存中，无网时取出缓存
        File file = FileUtils.getInstance().getCacheFolder();
        Cache cache = new Cache(file, 1024 * 1024 * 100);
        builder.cache(cache).addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtils.isNetwork(sContext)) {
                    Request newRequest = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();

                    return chain.proceed(newRequest);
                } else {
                    int maxTime = 24 * 60 * 60;
                    Response response = chain.proceed(request);
                    Response newResponse = response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxTime)
                            .removeHeader("Progma")
                            .build();

                    return newResponse;
                }
            }
        });

        okHttpClient = builder.build();
    }

    private static class OkHttpClientHolder {
        private static OkHttpClientHelper instance = new OkHttpClientHelper();
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
