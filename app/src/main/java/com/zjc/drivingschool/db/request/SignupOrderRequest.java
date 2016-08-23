package com.zjc.drivingschool.db.request;


/**
 * 学员报名价格获取请求
 *
 * @author huxiaofei
 * @date 2016年8月12日
 */
public class SignupOrderRequest {
    private String uid;// 用户ID

    private String uname;// 用户姓名

    private String uphone;// 用户电话

    private Boolean gender;// 学车人性别

    private String birthday;// 学车人出生年月  yyyy-MM-dd

    private double longitude;// 开始位置经度

    private double latitude;// 开始位置纬度

    private Boolean isreplace;// 是否代人下单

    private String education;// 学历

    private String vid;// 优惠券ID（非必传，格式:多个ID用','分割）

    private String contactsname;// 学车人姓名（非必传，代人下单时必填）

    private String contactsphone;// 学车人电话（非必传，代人下单时必填）


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

}
