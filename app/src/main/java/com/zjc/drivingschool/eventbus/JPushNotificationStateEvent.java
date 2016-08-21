package com.zjc.drivingschool.eventbus;


import com.zjc.drivingschool.db.model.JPushNotification;
import com.zjc.drivingschool.db.model.MessageItem;

/**
 * Created by Administrator on 2015/7/14.
 */
public class JPushNotificationStateEvent {
    MessageItem messageItem;

    public JPushNotificationStateEvent(MessageItem messageItem) {
        this.messageItem = messageItem;
    }

    public MessageItem getMessageItem() {
        return messageItem;
    }
}
