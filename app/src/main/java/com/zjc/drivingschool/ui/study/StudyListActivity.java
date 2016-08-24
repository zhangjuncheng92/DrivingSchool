package com.zjc.drivingschool.ui.study;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.mobo.mobolibrary.ui.base.ZBaseActivity;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.ui.study.adapter.StudyOrderPagerAdapter;
import com.zjc.drivingschool.utils.ConstantsParams;

import java.util.ArrayList;

/**
 * @author Z
 * @Filename CollectActivity.java
 * @Date 2015.11.14
 * @description 我的收藏控制器
 */
public class StudyListActivity extends ZBaseActivity {
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
        setTitle(mToolbar, R.string.title_study_history);
    }

    private void InitViewPager() {
        mPager = (ViewPager) findViewById(R.id.myconferenceVPager);
        StudyListFragment allFragment = StudyListFragment.newInstance(ConstantsParams.STUDY_ORDER_ALL);
        StudyListFragment unPayFragment = StudyListFragment.newInstance(ConstantsParams.STUDY_ORDER_ONE);
        StudyListFragment payFragment = StudyListFragment.newInstance(ConstantsParams.STUDY_ORDER_TWO);

        mFragmentList.add(allFragment);
        mFragmentList.add(unPayFragment);
        mFragmentList.add(payFragment);
        mPager.setAdapter(new StudyOrderPagerAdapter(getSupportFragmentManager(), mFragmentList));
    }

    private void initTab() {
        mTabLayout = (TabLayout) findViewById(R.id.personal_act_tab);
        mTabLayout.setupWithViewPager(mPager);
    }
}
