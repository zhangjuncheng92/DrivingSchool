package com.zjc.drivingschool.db.model;

import java.io.Serializable;
import java.util.List;


public class UserInfo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // 主键
    private int id;

    //登录名
    private String userName;

    // 真实姓名
    private String realName;

    // 登录密码
    private String pwd;

    // 状态状态（1.启用 0.停用）
    private int storeStatus;

    // 创建操作员
    private int createAdminId;

    // 创建时间
    private String createTime;

    // 更新操作员
    private int modifyAdminId;

    // 更新时间
    private String modifyTime;

    private int roleId; // 角色ID

    private int age;//年龄

    private int sex;//性别

    private int type;//类别 管理员、医生、护士、行政

    private int typeSecond;//二级类别  系统管理员、平台管理员、上级机构医生、家庭医生等等

    private int userId;//用户ID

    private int organizationId;//机构ID

    private String outReflectionId;

    private String roleName;//角色名

    private String organizationName;//机构

    private int preOrganizationId;//上级ID

    private String preOrganizationName;//上级名

    private String token;

    private String phone;

    private String img;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getStoreStatus() {
        return storeStatus;
    }

    public void setStoreStatus(int storeStatus) {
        this.storeStatus = storeStatus;
    }

    public int getCreateAdminId() {
        return createAdminId;
    }

    public void setCreateAdminId(int createAdminId) {
        this.createAdminId = createAdminId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getModifyAdminId() {
        return modifyAdminId;
    }

    public void setModifyAdminId(int modifyAdminId) {
        this.modifyAdminId = modifyAdminId;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTypeSecond() {
        return typeSecond;
    }

    public void setTypeSecond(int typeSecond) {
        this.typeSecond = typeSecond;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOutReflectionId() {
        return outReflectionId;
    }

    public void setOutReflectionId(String outReflectionId) {
        this.outReflectionId = outReflectionId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
