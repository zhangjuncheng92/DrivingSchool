package com.zjc.drivingschool.eventbus;


import com.zjc.drivingschool.db.model.JPushNotification;

/**
 * Created by Administrator on 2015/7/14.
 */
public class JPushNotificationStateEvent {
    JPushNotification jPushNotification;

    public JPushNotificationStateEvent(JPushNotification jPushNotification) {
        this.jPushNotification = jPushNotification;
    }

    public JPushNotification getJPushNotification() {
        return jPushNotification;
    }
}
