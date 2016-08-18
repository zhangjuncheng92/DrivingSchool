package com.zjc.drivingschool.ui.collect;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.mobo.mobolibrary.ui.base.ZBaseActivity;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.ui.order.OrderManagerFrg;

/**
 * Created by Administrator on 2016/8/18.
 */
public class CollectManagerActivity extends ZBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_act);
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        CollectManagerFragment fragment = new CollectManagerFragment();
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.addToBackStack(null);
        trans.add(R.id.root, fragment).commit();
    }
}
