package com.mobo.mobolibrary.ui.base.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/7/22.
 */
public class FragmentViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;

    public FragmentViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public FragmentViewPagerAdapter(FragmentManager fm, List<Fragment> lists) {
        super(fm);
        mFragments = lists;
    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return mFragments.get(arg0);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mFragments.size();
    }
}
