package com.module.core_http.http.api;


import com.module.core_http.http.base.BaseObjectBean;
import com.module.core_http.http.domain.DemoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * API接口
 *
 * @author yy
 * @DATE 2019/6/26
 * @VERSION 6.3.0
 */
public interface ApiService {
    @GET("v4/rankList")
    Observable<DemoBean> getDemoBean();

}
