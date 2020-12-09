package com.module.core_http.http.client;

/**
 * 说明：OkHttpClient配置类
 *
 * @author yy
 * @DATE 2019/5/30
 * @VERSION 6.3.0
 */

public class AppConstants {
    public static final int LIMIT = 10;//每页请求数据条数
    public static final int READ_TIME = 5 * 1000;
    public static final int WRITE_TIME = 5 * 1000;
    public static final int CONNECT_TIME = 5 * 1000;

    public static class HTTP {

        public static final String BASE_URL = "http://baobab.kaiyanapp.com/api/";//开眼

    }
}
