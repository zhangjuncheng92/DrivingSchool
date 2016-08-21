package com.zjc.drivingschool.db.response;

import com.zjc.drivingschool.db.model.AppResponse;
import com.zjc.drivingschool.db.model.MessageItem;

import java.util.List;

/**
 * 消息列表响应
 *
 * @author LJ
 * @date 2016年7月21日
 */
public class MessageListResponse extends AppResponse {
    private List<MessageItem> msgitems;// 消息列表对象

    public List<MessageItem> getMsgitems() {
        return msgitems;
    }

    public void setMsgitems(List<MessageItem> msgitems) {
        this.msgitems = msgitems;
    }

}
