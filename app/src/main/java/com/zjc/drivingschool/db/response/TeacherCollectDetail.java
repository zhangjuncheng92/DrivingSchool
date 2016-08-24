package com.zjc.drivingschool.db.response;


import java.io.Serializable;

/**
 * 收藏教练列表响应
 *
 * @author LJ
 * @date 2016年7月21日
 */
public class TeacherCollectDetail implements Serializable {
    private String tid;// 教练id

    private String teachername;// 教练名称

    private String photo;// 教练头像

    private int stars;// 评级1-5

    private String schoolname;// 所属驾校名称

    private Boolean gender;// 性别

    private String phone;// 联系电话

    private int experience;// 从教年限

    private String synopsis;// 简介

    private int status;//当前状态  1：空闲  2：训练中

    private Boolean orderswitch;// 接单开关

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Boolean getOrderswitch() {
        return orderswitch;
    }

    public void setOrderswitch(Boolean orderswitch) {
        this.orderswitch = orderswitch;
    }

}
