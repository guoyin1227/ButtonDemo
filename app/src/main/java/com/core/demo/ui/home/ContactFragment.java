package com.core.demo.ui.home;

import android.os.Bundle;

import com.core.core_base.bases.BaseMvpFragment;
import com.core.demo.R;

/**
 * @autor: kifer
 * @date:2020/12/3
 * @desc: 联系人界面
 */
public class ContactFragment extends BaseMvpFragment {
    private static final String INDEX = "index";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contact;
    }

    /**
     * @param index 当前页面
     * @return
     */
    public static ContactFragment newInstance(int index) {
        ContactFragment frag = new ContactFragment();
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
