package com.core.demo.ui;

import com.module.core_http.http.base.BaseObjectBean;
import com.module.core_http.http.api.ApiService;
import com.module.core_http.http.domain.DemoBean;
import com.module.core_http.http.utils.RetrofitUtils;

import io.reactivex.Observable;

/**
 * @autor: kifer
 * @date:2020/12/8
 * @desc: 获取测试数据的网络交互
 */
public class DemoModel implements DemoContract.Model {
    @Override
    public Observable<DemoBean> getDemoBean() {
        return RetrofitUtils.getInstance().getRetrofit().create(ApiService.class).getDemoBean();
    }
}
