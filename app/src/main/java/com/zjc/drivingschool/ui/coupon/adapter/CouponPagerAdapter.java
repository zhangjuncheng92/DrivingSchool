package com.zjc.drivingschool.ui.coupon.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.mobo.mobolibrary.ui.base.adapter.FragmentViewPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/7/22.
 */
public class CouponPagerAdapter extends FragmentViewPagerAdapter {
    private String tabTitles[] = new String[]{"可使用", "已使用", "已过期"};

    public CouponPagerAdapter(FragmentManager fm, List<Fragment> lists) {
        super(fm, lists);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
