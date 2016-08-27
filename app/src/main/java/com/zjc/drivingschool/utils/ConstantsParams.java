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


    /*** ########### 性别 ############*/
    /**
     * 0：女
     */
    public static final int USER_SEX_WOMEN = 0;

    /**
     * 1：男
     */
    public static final int USER_SEX_MEN = 1;


    /*** ########### 拍照图片类型 ############*/
    /**
     * 照片通知类型 头像修改
     */
    public static final int PHOTO_TYPE_USER = 1;

    /**
     * 照片通知类型 评价图片提交
     */
    public static final int PHOTO_TYPE_COMMENT = 2;


    /*** ########### 单状态 ############
     *  1.预订成功 2.已支付 3.申请退订 4.已退订 5.消费中 6.已消费 7.待评价 8.已完成 9.已取消*/

    /**
     * 预订成功
     */
    public static final String STUDY_ORDER_ALL = null;

    /**
     * 预订成功
     */
    public static final String STUDY_ORDER_ONE = "1";
    public static final String STUDY_ORDER_ONE_TXT = "等待接单";

    /**
     * 已支付
     */
    public static final String STUDY_ORDER_TWO = "2";
    public static final String STUDY_ORDER_TWO_TXT = "已支付";

    /**
     * 申请退订
     */
    public static final String STUDY_ORDER_THREE = "3";
    public static final String STUDY_ORDER_THREE_TXT = "申请退订";

    /**
     * 已退订
     */
    public static final String STUDY_ORDER_FOUR = "4";
    public static final String STUDY_ORDER_FOUR_TXT = "已退订";

    /**
     * 消费中
     */
    public static final String STUDY_ORDER_FIVE = "5";
    public static final String STUDY_ORDER_FIVE_TXT = "消费中";

    /**
     * 已消费
     */
    public static final String STUDY_ORDER_SIX = "6";
    public static final String STUDY_ORDER_SIX_TXT = "已消费";

    /**
     * 待评价
     */
    public static final String STUDY_ORDER_SEVEN = "7";
    public static final String STUDY_ORDER_SEVEN_TXT = "待评价";

    /**
     * 已完成
     */
    public static final String STUDY_ORDER_EIGHT = "8";
    public static final String STUDY_ORDER_EIGHT_TXT = "已完成";

    /**
     * 已取消
     */
    public static final String STUDY_ORDER_NINE = "9";
    public static final String STUDY_ORDER_NINE_TXT = "已取消";

    public static String getStatus(String status) {
        if (STUDY_ORDER_ONE.equals(status)) {
            return STUDY_ORDER_ONE_TXT;
        } else if (STUDY_ORDER_TWO.equals(status)) {
            return STUDY_ORDER_TWO_TXT;
        } else if (STUDY_ORDER_THREE.equals(status)) {
            return STUDY_ORDER_THREE_TXT;
        } else if (STUDY_ORDER_FOUR.equals(status)) {
            return STUDY_ORDER_FOUR_TXT;
        } else if (STUDY_ORDER_FIVE.equals(status)) {
            return STUDY_ORDER_FIVE_TXT;
        } else if (STUDY_ORDER_SIX.equals(status)) {
            return STUDY_ORDER_SIX_TXT;
        } else if (STUDY_ORDER_SEVEN.equals(status)) {
            return STUDY_ORDER_SEVEN_TXT;
        } else if (STUDY_ORDER_EIGHT.equals(status)) {
            return STUDY_ORDER_EIGHT_TXT;
        } else if (STUDY_ORDER_NINE.equals(status)) {
            return STUDY_ORDER_NINE_TXT;
        } else {
            return "";
        }
    }

}
