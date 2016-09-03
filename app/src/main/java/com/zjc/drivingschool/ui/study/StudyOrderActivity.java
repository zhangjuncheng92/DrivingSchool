package com.zjc.drivingschool.ui.study;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.mobo.mobolibrary.ui.base.ZBaseActivity;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.db.model.OrderItem;
import com.zjc.drivingschool.utils.Constants;


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
        if (getIntent().getExtras() == null) {
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            StudyOrderFragment fragment = new StudyOrderFragment();
            trans.addToBackStack(null);
            trans.add(R.id.root, fragment).commit();
        } else if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(Constants.ARGUMENT)) {
            if (getIntent().getExtras().getSerializable(Constants.ARGUMENT) instanceof String) {
                //跳转到学车详情
                String orId = (String) getIntent().getExtras().getSerializable(Constants.ARGUMENT);
                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
                StudyDetailFragment fragment = StudyDetailFragment.newInstance((orId));
                trans.addToBackStack(null);
                trans.add(R.id.root, fragment).commit();
            } else if (getIntent().getExtras().getSerializable(Constants.ARGUMENT) instanceof OrderItem) {
                //跳转到评价
                OrderItem orderItem = (OrderItem) getIntent().getExtras().getSerializable(Constants.ARGUMENT);
                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
                StudyOrderAssessFrg fragment = StudyOrderAssessFrg.newInstance((orderItem));
                trans.addToBackStack(null);
                trans.add(R.id.root, fragment).commit();
            }
        }
    }
}
