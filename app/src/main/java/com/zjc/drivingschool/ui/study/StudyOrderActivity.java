package com.zjc.drivingschool.ui.study;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.mobo.mobolibrary.ui.base.ZBaseActivity;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.ui.apply.ApplyFragment;


/**
 * @author Z
 * @Filename StudyOrderActivity.java
 * @Date 2015.09.14
 * @description 登录activity
 */
public class StudyOrderActivity extends ZBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_act);
    }

    @Override
    protected void initBaseView() {
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        ApplyFragment fragment = new ApplyFragment();
        trans.addToBackStack(null);
        trans.add(R.id.root, fragment).commit();
    }
}
