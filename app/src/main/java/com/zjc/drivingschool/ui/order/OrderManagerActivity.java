package com.zjc.drivingschool.ui.order;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.mobo.mobolibrary.ui.base.ZBaseActivity;
import com.zjc.drivingschool.R;

/**
 * Created by Administrator on 2016/8/17.
 */
public class OrderManagerActivity extends ZBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_act);
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        OrderManagerFrg fragment = new OrderManagerFrg();
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.addToBackStack(null);
        trans.add(R.id.root, fragment).commit();
    }
}
