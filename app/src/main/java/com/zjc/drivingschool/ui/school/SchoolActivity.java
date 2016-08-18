package com.zjc.drivingschool.ui.school;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.mobo.mobolibrary.ui.base.ZBaseActivity;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.db.model.School;
import com.zjc.drivingschool.utils.Constants;


/**
 * @author Z
 * @Filename LearnActivity.java
 * @Date 2015.09.14
 * @description 登录activity
 */
public class SchoolActivity extends ZBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_act);
    }

    @Override
    protected void initBaseView() {
        if (getIntent().getExtras() == null) {

        } else if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(Constants.ARGUMENT)) {
            School school = (School) getIntent().getExtras().getSerializable(Constants.ARGUMENT);
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            SchoolFragment fragment = SchoolFragment.newInstance(school);
            trans.addToBackStack(null);
            trans.add(R.id.root, fragment).commit();
        }
    }
}
