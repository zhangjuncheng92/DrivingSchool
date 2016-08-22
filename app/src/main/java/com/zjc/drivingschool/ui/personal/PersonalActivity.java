package com.zjc.drivingschool.ui.personal;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.mobo.mobolibrary.ui.base.ZBaseActivity;
import com.zjc.drivingschool.R;


/**
 * @author Z
 * @Filename StudyOrderActivity.java
 * @Date 2015.09.14
 * @description 登录activity
 */
public class PersonalActivity extends ZBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_act);
    }

    @Override
    protected void initBaseView() {
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        PersonalFragment fragment = new PersonalFragment();
        trans.addToBackStack(null);
        trans.add(R.id.root, fragment).commit();
    }
}
