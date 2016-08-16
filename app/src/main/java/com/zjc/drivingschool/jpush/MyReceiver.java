package com.zjc.drivingschool.jpush;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.zjc.drivingschool.app.MApp;
import com.zjc.drivingschool.db.model.JPushNotification;
import com.zjc.drivingschool.db.parser.JPushNotificationParser;
import com.zjc.drivingschool.eventbus.JpushNotificationEvent;
import com.zjc.drivingschool.ui.main.MainActivity;

import java.util.List;

import cn.jpush.android.api.JPushInterface;
import de.greenrobot.event.EventBus;


/**
 * 自定义接收器
 * <p/>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";
    public static final int NOTICE = 0;
    public static final int CLICK = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
            //send the Registration Id to your server...

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {

            processMessageToMain(context, bundle, NOTICE);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {

            Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
            checkAPPisActive(context, bundle, CLICK);

        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            Log.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
        } else {
            Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
        }
    }

    private void checkAPPisActive(Context context, Bundle bundle, int action) {
        //判断应用是否在运行
        if (isApplicationBroughtToBackground(context)) {
            Intent mainActivityIntent = new Intent(context, MainActivity.class);  // 要启动的Activity
            mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mainActivityIntent);
            Message message = new Message();
            message.setData(bundle);
            message.what = 1;
            mHandler.sendMessageDelayed(message, 1000);
        } else {
//            processMessageToMain(context, bundle, action);
        }
    }

    /**
     * 接收到点击通知栏的动作，发送通知到主界面
     *
     * @param context
     * @param bundle
     */
    private void processMessageToMain(Context context, Bundle bundle, int action) {
        String EXTRA = bundle.getString("cn.jpush.android.EXTRA");
        String ALERT = bundle.getString("cn.jpush.android.ALERT");
        if (!TextUtils.isEmpty(EXTRA)) {
            try {
                Gson gson = new Gson();
                JPushNotification jpushNotification = gson.fromJson(EXTRA, new JPushNotificationParser().getResultMessageType());
                jpushNotification.setAlert(ALERT);
//                如果是通知事件，则存到数据库
                if (action == NOTICE) {
                    if (TextUtils.isEmpty(jpushNotification.getAlert())) {
                        return;
                    }
                    //发送广播
                    if (EventBus.getDefault().hasSubscriberForEvent(JpushNotificationEvent.class)) {
                        EventBus.getDefault().post(new JpushNotificationEvent());
                    }
                } else if (action == CLICK) {
//                    如果是点击事件
                    onNotificationClick(jpushNotification, context);
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    public void onNotificationClick(JPushNotification service, Context context) {
//        Gson gson = new Gson();
//        Bundle bundle = new Bundle();
//        Intent intent = new Intent();
//        try {
//            if (service.getJpushContentType() == ConstantsParams.PUSH_CONTENT_TYPE_REGISTRATION ||
//                    service.getJpushContentType() == ConstantsParams.PUSH_CONTENT_TYPE_REGISTRATION_NOTICE) {
//                //跳转到预约服务
//                AppointmentRegistration appointment = gson.fromJson(service.getExtras(), new AppointmentRegistrationParser().getObjectTypeToken());
//                bundle.putSerializable(Constants.PUSH, appointment);
//                intent.setClass(context, NotificationActivity.class);
//            } else if (service.getJpushContentType() == ConstantsParams.PUSH_CONTENT_TYPE_CUSTOM) {
//                JPushNotification jPushNotification = gson.fromJson(service.getExtras(), new JPushNotificationParser().getObjectTypeToken());
//                bundle.putSerializable(Constants.PUSH, jPushNotification);
//                intent.setClass(context, NotificationActivity.class);
//            }
//            intent.putExtras(bundle);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intent);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    /**
     * 判断当前应用程序处于前台还是后台
     */

    public static boolean isApplicationBroughtToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                int what = msg.what;
                if (what == 1) {
                    processMessageToMain(MApp.getInstance().getApplicationContext(), msg.getData(), CLICK);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }
}
