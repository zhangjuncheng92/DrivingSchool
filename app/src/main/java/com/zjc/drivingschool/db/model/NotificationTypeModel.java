package com.zjc.drivingschool.db.model;

import android.widget.ImageView;

import java.io.Serializable;

public class NotificationTypeModel implements Serializable {
    private String title;
    private String content;
    private int count;
    private String createTime;
    private int type;

    public NotificationTypeModel() {
    }

    public NotificationTypeModel(String title, String content, int count, String createTime, int type) {
        this.title = title;
        this.content = content;
        this.count = count;
        this.createTime = createTime;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int id;
    private int photo;
    private String name;
    private String detail;
    private JPushNotification jPushNotification;


    public NotificationTypeModel(int id, String name, String detail, JPushNotification jPushNotification) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.jPushNotification = jPushNotification;
    }

    public NotificationTypeModel(int id, int photo, String name, String detail, JPushNotification jPushNotification) {
        this.id = id;
        this.photo = photo;
        this.name = name;
        this.detail = detail;
        this.jPushNotification = jPushNotification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public JPushNotification getjPushNotification() {
        return jPushNotification;
    }

    public void setjPushNotification(JPushNotification jPushNotification) {
        this.jPushNotification = jPushNotification;
    }

    public static void setNotificationIconByPosition(ImageView imageView, NotificationTypeModel model) {
//        if (model.getType() == ConstantsParams.PUSH_CONTENT_TYPE_REGISTRATION) {// 预约挂号通知
//            imageView.setImageResource(R.drawable.icon_notification_notice);
//        } else if (model.getType() == ConstantsParams.PUSH_CONTENT_TYPE_USER_RECHARGE) {// 就诊时间通知
//            imageView.setImageResource(R.drawable.icon_notification_alarm);
//
//        } else if (model.getType() == ConstantsParams.PUSH_CONTENT_TYPE_CUSTOM) {// 武汉城市医伴
//            imageView.setImageResource(R.drawable.icon_circle_logo);
//
//        } else if (model.getType() == 4) {// 等等
//          //  imageView.setImageResource(R.drawable.icon_circle_logo);
//        }
    }

}
