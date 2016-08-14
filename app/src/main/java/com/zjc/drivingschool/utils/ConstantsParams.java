package com.zjc.drivingschool.utils;

/**
 * Created by Administrator on 2015/6/12.
 */
public class ConstantsParams {
    /**
     * 起始页数
     */
    public static final int PAGE_START = 0;
    /**
     * 分页加载条数
     */
    public static final int PAGE_SIZE = 20;

    /*** ########### 推送 ############*/
    /**
     * 消息_已读
     */
    public static final int NOTIFICATION_YES = 1;

    /**
     * 消息_未读
     */
    public static final int NOTIFICATION_NO = 0;

    /**
     * 推送_系统消息
     */
    public static final int NOTIFICATION_SYS = 1;
    /**
     * 推送_上转单消息
     */
    public static final int NOTIFICATION__REFERRAL_UP = 2;
    /**
     * 推送_下转单消息
     */
    public static final int NOTIFICATION__REFERRAL_DOWN = 3;


    /*** ########### 是否可用 ############*/
    /**
     * 1 是
     */
    public static final int SYSTEM_YES = 1;

    /**
     * 0 否
     */
    public static final int SYSTEM_NO = 0;


    /*** ########### 登录系统 ############*/
    /**
     * 0：注册
     */
    public static final int USER_REGISTER = 0;

    /**
     * 1：重置密码
     */
    public static final int USER_FORGET = 1;

    /**
     * 0：本平台
     */
    public static final int USER_LOGIN_PLATFORM = 0;

    /**
     * 1：外关联
     */
    public static final int USER_LOGIN_EXTERNAL = 1;

    /*** ########### 性别 ############*/
    /**
     * 0：女
     */
    public static final int USER_SEX_WOMEN = 0;

    /**
     * 1：男
     */
    public static final int USER_SEX_MEN = 1;


    /*** ########### 系统角色 ############*/
    /**
     * 系统角色
     * 1：系统管理员
     */
    public static final int USER_ROLE_ADMIN = 1;

    /**
     * 系统角色
     * 2：机构管理员
     */
    public static final int USER_ROLE_ORG_ADMIN = 2;

    /**
     * 系统角色
     * 3：医生
     */
    public static final int USER_ROLE_DOCTOR = 3;

    /**
     * 系统角色
     * 4：护士
     */
    public static final int USER_ROLE_NURSE = 4;


    /*** ########### 转诊单类型 ############*/
    /**
     * 1：上转单界面
     */
    public static final int REFERRAL_UP = 1;

    /**
     * 2：下转单界面
     */
    public static final int REFERRAL_DOWN = 2;

    /**
     * 3：上转接诊单类型
     */
    public static final int RECEPTION_UP = 3;

    /**
     * 4：下转接诊单类型
     */
    public static final int RECEPTION_DOWN = 4;


    /*** ########### 转诊单状态 ############*/
    /**
     * 0发起转诊
     */
    public static final int REFERRAL_STATUS_START = 0;
    public static final String REFERRAL_STATUS_START_TXT = "转诊中";

    /**
     * 1转诊绑定
     */
    public static final int REFERRAL_STATUS_BANDING = 1;
    public static final String REFERRAL_STATUS_BANDING_TXT = "已报到";

    /**
     * 2结束转诊
     */
    public static final int REFERRAL_STATUS_END = 2;
    public static final String REFERRAL_STATUS_END_TXT = "已接诊";

    /**
     * 3已下转
     */
    public static final int REFERRAL_STATUS_END_DOWN = 3;
    public static final String REFERRAL_STATUS_END_DOWN_TXT = "已下转";


    /*** ########### 转诊类型 ############*/
    /**
     * 1门诊
     */
    public static final int REFERRAL_TYPE_CLINIC = 1;

    /**
     * 2住院
     */
    public static final int REFERRAL_TYPE_HOSPITAL = 2;
    /**
     * 3 检查
     */
    public static final int REFERRAL_TYPE_CHECK = 3;
//    /**
//     * 4 120急救
//     */
//    public static final int REFERRAL_TYPE_FIRST = 4;
//    /**
//     * 5 其他
//     */
//    public static final int REFERRAL_TYPE_OTHER = 5;

    /*** ########### 功能介绍显示类型 ############*/
    /**
     * 0 登录
     */
    public static final int FUNCTION_LOGIN = 0;

    /**
     * 1 审核中
     */
    public static final int FUNCTION_AUTHENTICATION = 1;

    /**
     * 2 展示
     */
    public static final int FUNCTION_SHOW = 2;

    /**
     * 3 暂未开通
     */
    public static final int FUNCTION_WAIT = 3;

    /*** ########### 扫描的二维码类型 ############*/
    /**
     * 0 下转
     */
    public static final int SCAN_DOWN = 0;

    /**
     * 1 上转
     */
    public static final int SCAN_UP = 1;


    /*** ########### 转诊单 打印状态 ############*/
    /**
     * 0 未达
     */
    public static final int REFERRAL_PRINT_NO = 0;

    /**
     * 1 已打
     */
    public static final int REFERRAL_PRINT_YES = 1;

    /*** ########### 拍照图片类型 ############*/
    /**
     * 照片通知类型 头像修改
     */
    public static final int PHOTO_TYPE_USER = 1;

    /**
     * 照片通知类型 评价图片提交
     */
    public static final int PHOTO_TYPE_COMMENT = 2;

    /**
     * 流失 id
     */
    public static final String LOSS_ID = "3";
}
