package com.zjc.drivingschool.ui.notification;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.mobo.mobolibrary.ui.base.ZBaseActivity;
import com.zjc.drivingschool.R;
import com.zjc.drivingschool.db.model.JPushNotification;
import com.zjc.drivingschool.db.model.UserInfo;
import com.zjc.drivingschool.utils.Constants;

/**
 * @author Z
 * @Filename NotificationActivity.java
 * @Date 2016.05.31
 * @description 通知提醒详情界面控制器
 */
public class NotificationActivity extends ZBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_act);
    }

    @Override
    protected void initBaseView() {
        if (getIntent().getExtras() == null) {
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            NotificationReferralFrg fragment = new NotificationReferralFrg();
            trans.addToBackStack(null);
            trans.add(R.id.root, fragment).commit();
        } else if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(Constants.TYPE)) {
            int type = getIntent().getExtras().getInt(Constants.TYPE);
//            if (type == ConstantsParams.PUSH_CONTENT_TYPE_REGISTRATION ||
//                    type == ConstantsParams.PUSH_CONTENT_TYPE_REGISTRATION_NOTICE) {
//                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
//                NotificationRegistrationFrg fragment = new NotificationRegistrationFrg();
//                trans.addToBackStack(null);
//                trans.add(R.id.root, fragment).commit();
//            } else if (type == ConstantsParams.PUSH_CONTENT_TYPE_CUSTOM) {
//                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
//                NotificationSystemFrg fragment = new NotificationSystemFrg();
//                trans.addToBackStack(null);
//                trans.add(R.id.root, fragment).commit();
//            } else if (type == ConstantsParams.PUSH_CONTENT_TYPE_USER_RECHARGE) {
//
//            }
        } else if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(Constants.PUSH)) {
            //判断是预约单还是系统消息
            Object o = getIntent().getExtras().getSerializable(Constants.PUSH);
            if (o instanceof UserInfo) {
//                getAppointmentRegistrationById((AppointmentRegistration) o);
            } else if (o instanceof JPushNotification) {
//                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
//                NotificationWebFragment fragment = NotificationWebFragment.newInstance((JPushNotification) o);
//                trans.addToBackStack(null);
//                trans.add(R.id.root, fragment).commit();
            }
        }
    }
}
