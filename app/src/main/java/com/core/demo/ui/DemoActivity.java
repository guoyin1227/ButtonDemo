package com.core.demo.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;

import com.core.core_base.bases.BaseMvpActivity;
import com.core.core_base.util.LogUtils;

import com.core.demo.R;
import com.core.demo.ui.home.ContactFragment;
import com.core.demo.ui.home.HomeFragment;
import com.core.demo.ui.home.MoreFragment;
import com.core.demo.ui.home.SpeechFragment;
import com.google.android.material.tabs.TabLayout;
import com.module.core_http.http.domain.DemoBean;

import java.util.List;
import java.util.Objects;

/**
 * @autor: kifer
 * @date:2020/12/3
 * @desc: 主界面
 */
public class DemoActivity extends BaseMvpActivity implements DemoContract.View {

    TabLayout mTabLayout;

    private String[] mTitles = {"首页", "会话", "联系人", "更多"};
    private int[] selectorImg = new int[]{R.drawable.select_main_bottom_home, R.drawable.select_main_bottom_speech, R.drawable.select_main_bottom_contact,
            R.drawable.select_main_bottom_more};
    public static final int SHOW_TAB_HOME = 0;
    public static final int SHOW_TAB_SPEECH = 1;
    public static final int SHOW_TAB_CONTACT = 2;
    public static final int SHOW_TAB_MORE = 3;
    private int index = 0;// 记录当前的选项
    private String[] FRAGMENT_TAG = {"tab_home", "tab_speech", "tab_contact", "tab_more"};
    private HomeFragment tabHomeFragment;
    private SpeechFragment speechFragment;
    private ContactFragment contactFragment;
    private MoreFragment moreFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_demo;
    }

    @Override
    public void initView() {
        mTabLayout = findViewById(R.id.tablayout);
        initTab();
        intoPage(index);
        DemoPresenter demoPresenter = new DemoPresenter();
        demoPresenter.attachView(this);
        demoPresenter.getDemoBean();
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

    @Override
    public void onDemoBeanSuccess(DemoBean bean) {
        if (bean == null) return;
        DemoBean.TabInfoBean tabInfo = bean.getTabInfo();
        if (tabInfo == null) return;
        int defaultIdx = tabInfo.getDefaultIdx();
        LogUtils.d("defaultIdx:" + defaultIdx);
        List<DemoBean.TabInfoBean.TabListBean> tabList = tabInfo.getTabList();
        if (tabList != null && tabList.size() != 0) {
            LogUtils.d("tabList  size :" + tabList.size());
            DemoBean.TabInfoBean.TabListBean tabListBean = tabList.get(0);
            if (tabListBean != null) {
                if (!TextUtils.isEmpty(tabListBean.getName())) {
                    LogUtils.d(" tabListBean.getName :" + tabListBean.getName());
                }
            }
        }

    }

    //初始化底部tab
    private void initTab() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switchFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0; i < mTitles.length; i++) {
            View inflate = LayoutInflater.from(appCompatActivity).inflate(R.layout.layout_main_tab, null);
            ImageView tabImage = inflate.findViewById(R.id.tab_item_icon);
            TextView tabTitle = inflate.findViewById(R.id.tab_item_title);
            tabImage.setImageResource(selectorImg[i]);
            tabTitle.setText(mTitles[i]);
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(inflate));
        }

    }

    //切换底部tab
    public void intoPage(int intoPage) {
        Objects.requireNonNull(mTabLayout.getTabAt(intoPage)).select();
    }

    //切换当前fragment
    private void switchFragment(int id) {
        index = id;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case SHOW_TAB_HOME://首页的fragment
                if (tabHomeFragment == null) {
                    tabHomeFragment = HomeFragment.newInstance(index);
                    transaction.add(R.id.fl_change, tabHomeFragment, FRAGMENT_TAG[index]);
                } else {
                    transaction.show(tabHomeFragment);
                }
                break;
            case SHOW_TAB_SPEECH://会话fragment
                if (speechFragment == null) {
                    speechFragment = SpeechFragment.newInstance(index);
                    transaction.add(R.id.fl_change, speechFragment, FRAGMENT_TAG[index]);
                } else {
                    transaction.show(speechFragment);
                }
                break;
            case SHOW_TAB_CONTACT://联系人的fragment
                if (contactFragment == null) {
                    contactFragment = ContactFragment.newInstance(index);
                    transaction.add(R.id.fl_change, contactFragment, FRAGMENT_TAG[index]);
                } else {
                    transaction.show(contactFragment);
                }
                break;
            case SHOW_TAB_MORE://更多fragment
                if (moreFragment == null) {
                    moreFragment = MoreFragment.newInstance(index);
                    transaction.add(R.id.fl_change, moreFragment, FRAGMENT_TAG[index]);
                } else {
                    transaction.show(moreFragment);
                }
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    //隐藏当前tab
    private void hideFragments(FragmentTransaction transaction) {
        if (tabHomeFragment != null) {
            transaction.hide(tabHomeFragment);
        }
        if (speechFragment != null) {
            transaction.hide(speechFragment);
        }
        if (contactFragment != null) {
            transaction.hide(contactFragment);
        }
        if (moreFragment != null) {
            transaction.hide(moreFragment);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            // 需要监听的事件
            if (index == 0) {
//点击返回键返回桌面而不是退出程序
                Intent home = new Intent(Intent.ACTION_MAIN);
                home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                home.addCategory(Intent.CATEGORY_HOME);
                startActivity(home);
                return true;
            } else {
                intoPage(0);
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
