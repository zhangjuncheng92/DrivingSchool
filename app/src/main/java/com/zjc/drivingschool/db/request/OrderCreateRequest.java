package com.zjc.drivingschool.db.request;

/**
 * 创建订单请求
 *
 * @author LJ
 * @String 2016年7月21日
 */
public class OrderCreateRequest {
    private String uid;// 下单用户ID

    private String loginname;// 下单用户名

    private String nickname;// 下单用户昵称

    private String carsid;// 车型ID

    private String carsname;// 车型名称

    private String subjectid;// 项目类型ID

    private String subjectname;// 项目类型名称

    private Boolean isvip;// 是否VIP

    private String starttime;// 开始时间 格式：yyyy-MM-dd hh:mm:ss

    private Integer number;// 数量 单位：小时

    private Double longitude;// 开始位置经度

    private Double latitude;// 开始位置纬度

    private Boolean isreplace;// 是否代人下单

    private String vid;// 优惠券ID（非必传，格式:多个ID用','分割）

    private String contactsname;// 联系人姓名（isreplace为true时必传）

    private String contactsphone;// 联系人电话（isreplace为true时必传）

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCarsid() {
        return carsid;
    }

    public void setCarsid(String carsid) {
        this.carsid = carsid;
    }

    public String getCarsname() {
        return carsname;
    }

    public void setCarsname(String carsname) {
        this.carsname = carsname;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public Boolean getIsvip() {
        return isvip;
    }

    public void setIsvip(Boolean isvip) {
        this.isvip = isvip;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Boolean getIsreplace() {
        return isreplace;
    }

    public void setIsreplace(Boolean isreplace) {
        this.isreplace = isreplace;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getContactsname() {
        return contactsname;
    }

    public void setContactsname(String contactsname) {
        this.contactsname = contactsname;
    }

    public String getContactsphone() {
        return contactsphone;
    }

    public void setContactsphone(String contactsphone) {
        this.contactsphone = contactsphone;
    }

}
