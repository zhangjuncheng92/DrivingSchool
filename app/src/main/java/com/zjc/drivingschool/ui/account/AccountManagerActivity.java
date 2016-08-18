package com.zjc.drivingschool.ui.account;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.mobo.mobolibrary.ui.base.ZBaseActivity;
import com.zjc.drivingschool.R;

import cn.beecloud.BCPay;

/**
 * Created by Administrator on 15.10.29.
 */
public class AccountManagerActivity extends ZBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_act);
        initWXPay();
    }

    private void initWXPay() {
        String initInfo = BCPay.initWechatPay(this, "wxf1aa465362b4c8f1");
        if (initInfo != null) {
            Toast.makeText(this, "微信初始化失败：" + initInfo, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        AccountManagerFrg fragment = new AccountManagerFrg();
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.addToBackStack(null);
        trans.add(R.id.root, fragment).commit();
    }
}
