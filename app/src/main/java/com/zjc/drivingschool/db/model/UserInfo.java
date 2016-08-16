package com.zjc.drivingschool.db.model;

import java.io.Serializable;


public class UserInfo extends BaseInfo{


    /**
     * address : fsdafasf
     * birthday : 2016-08-01
     * email : fdsafsafa a
     * gender : true
     * identityno : fdsafsafasfaf
     * loginname : 13797039695
     * nickname : fdsfasf
     * phone : 13797039695
     * photourl : /upload/images/20160815/1471245751373555665.png
     * qq : 2423424
     * stuid : SJ16072767980000001
     * uid : 42164b4381024eb1b6d6b9c0836aaa59
     */

    private int id;
    private String address;
    private String birthday;
    private String email;
    private boolean gender;
    private String identityno;
    private String loginname;
    private String nickname;
    private String phone;
    private String photourl;
    private String qq;
    private String stuid;
    private String uid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getIdentityno() {
        return identityno;
    }

    public void setIdentityno(String identityno) {
        this.identityno = identityno;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
