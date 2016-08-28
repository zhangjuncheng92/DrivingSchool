package com.zjc.drivingschool.ui.notification.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.mobo.mobolibrary.ui.base.adapter.FragmentViewPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/7/22.
 */
public class NotificationPagerAdapter extends FragmentViewPagerAdapter {
    private String tabTitles[] = new String[]{"全部", "未读", "已读"};

    public NotificationPagerAdapter(FragmentManager fm, List<Fragment> lists) {
        super(fm, lists);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
