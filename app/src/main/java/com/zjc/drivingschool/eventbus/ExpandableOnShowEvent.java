package com.zjc.drivingschool.eventbus;

import com.zjc.drivingschool.db.model.MessageItem;

/**
 * Created by Administrator on 2015/7/14.
 */
public class ExpandableOnShowEvent {
    private MessageItem messageItem;

    public ExpandableOnShowEvent(MessageItem messageItem) {
        this.messageItem = messageItem;
    }

    public MessageItem getMessageItem() {
        return messageItem;
    }
}
