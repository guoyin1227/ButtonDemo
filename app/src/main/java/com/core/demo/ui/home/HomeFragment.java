package com.core.demo.ui.home;

import android.os.Bundle;

import com.core.core_base.bases.BaseMvpFragment;
import com.core.demo.R;

/**
 * @autor: kifer
 * @date:2020/12/3
 * @desc: todo
 */
public class HomeFragment extends BaseMvpFragment {
    private static final String INDEX = "index";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    /**
     * @param index 当前页面
     * @return
     */
    public static HomeFragment newInstance(int index) {
        HomeFragment frag = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(INDEX, index);
        frag.setArguments(args);
        return frag;
    }


    @Override
    protected void initPrepare() {
    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
