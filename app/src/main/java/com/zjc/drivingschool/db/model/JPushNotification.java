package com.zjc.drivingschool.db.model;


import java.util.List;

/**
 * Entity mapped to table "JPUSH_NOTIFICATION".
 */
public class JPushNotification implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private int id;

    //按设备别名推送，需要app向JPUSH注册
    private String alias;

    //按标签推送
    private String tags;

    //通知内容
    private String alert;

    //通知标题，
    private String title;

    //扩展内容
    private String extras;

    //1.APP  2WEB
    private int apptype;

    //发送通知的时间
    private String modifyTime;

    //推送状态（1.仅保存 2.推送）
    private int status;

    //推送内容类型（0发起转诊,1转诊报到 2转诊确认3已下转）
    private int jpushContentType;

    //推送内容主键
    private int jpushContentId;

    //推送图片
    private String jpushPicture;

    //推送内容
    private String jpushContent;

    private List<String> aliasList;

    private int userId;//用户ID

    private int state;//0未读 1已读

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public int getApptype() {
        return apptype;
    }

    public void setApptype(int apptype) {
        this.apptype = apptype;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getJpushContentType() {
        return jpushContentType;
    }

    public void setJpushContentType(int jpushContentType) {
        this.jpushContentType = jpushContentType;
    }

    public int getJpushContentId() {
        return jpushContentId;
    }

    public void setJpushContentId(int jpushContentId) {
        this.jpushContentId = jpushContentId;
    }

    public String getJpushPicture() {
        return jpushPicture;
    }

    public void setJpushPicture(String jpushPicture) {
        this.jpushPicture = jpushPicture;
    }

    public String getJpushContent() {
        return jpushContent;
    }

    public void setJpushContent(String jpushContent) {
        this.jpushContent = jpushContent;
    }

    public List<String> getAliasList() {
        return aliasList;
    }

    public void setAliasList(List<String> aliasList) {
        this.aliasList = aliasList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


}
