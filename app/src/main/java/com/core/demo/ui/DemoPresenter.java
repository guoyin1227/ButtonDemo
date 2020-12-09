package com.core.demo.ui;

import com.core.core_base.bases.BasePresenter;
import com.module.core_http.http.client.RxScheduler;


/**
 * @autor: kifer
 * @date:2020/12/8
 * @desc: todo
 */
public class DemoPresenter extends BasePresenter<DemoContract.View> implements  DemoContract.Presenter {

    private final DemoModel demoModel;

    public DemoPresenter() {
        demoModel = new DemoModel();
    }

    @Override
    public void getDemoBean() {
        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();
        demoModel.getDemoBean()
                .compose(RxScheduler.Obs_io_main())
                .as(mView.bindAutoDispose())
                .subscribe(bean -> {
                    mView.onDemoBeanSuccess(bean);
                    mView.hideLoading();
                }, throwable -> {
                    mView.onError(throwable);
                    mView.hideLoading();
                });
    }
}
