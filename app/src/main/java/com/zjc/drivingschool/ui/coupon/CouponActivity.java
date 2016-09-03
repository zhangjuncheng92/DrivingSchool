package com.zjc.drivingschool.ui.coupon;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.mobo.mobolibrary.ui.base.ZBaseActivity;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.ui.coupon.adapter.CouponPagerAdapter;
import com.zjc.drivingschool.utils.ConstantsParams;

import java.util.ArrayList;

/**
 * @author Z
 * @Filename CouponActivity.java
 * @Date 2015.11.14
 * @description 我的优惠券控制器
 */
public class CouponActivity extends ZBaseActivity {
    public ArrayList<Fragment> mFragmentList = new ArrayList<>();

    private ViewPager mPager;// 页卡内容
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_list_act);
        initEmptyLayout();
        initTitle();
        InitViewPager();
        initTab();
    }

    private void initTitle() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.comm_back);
        setSupportActionBar(mToolbar);
        OnBackPressClick onBackPressClick = new OnBackPressClick();
        mToolbar.setNavigationOnClickListener(onBackPressClick);
        setTitle(mToolbar, R.string.title_coupon);
    }

    private void InitViewPager() {
        mPager = (ViewPager) findViewById(R.id.myconferenceVPager);
        CouponListFragment allFragment = CouponListFragment.newInstance(ConstantsParams.COUPON_ENABLE);
        CouponListFragment unPayFragment = CouponListFragment.newInstance(ConstantsParams.COUPON_USED);
        CouponListFragment payFragment = CouponListFragment.newInstance(ConstantsParams.COUPON_OVERDUE);

        mFragmentList.add(allFragment);
        mFragmentList.add(unPayFragment);
        mFragmentList.add(payFragment);
        mPager.setAdapter(new CouponPagerAdapter(getSupportFragmentManager(), mFragmentList));
    }

    private void initTab() {
        mTabLayout = (TabLayout) findViewById(R.id.personal_act_tab);
        mTabLayout.setupWithViewPager(mPager);
    }
}
