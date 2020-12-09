package com.core.demo.ui;

import com.core.core_base.bases.BaseView;
import com.module.core_http.http.domain.DemoBean;

import io.reactivex.Observable;

/**
 * @autor: kifer
 * @date:2020/12/8
 * @desc: 实例代码
 */
public class DemoContract {
    public interface Model {
        Observable<DemoBean> getDemoBean();
    }

    public interface View extends BaseView {
        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void onDemoBeanSuccess(DemoBean bean);
    }

    public interface Presenter {
        //获取测试数据
        void getDemoBean();
    }
}
