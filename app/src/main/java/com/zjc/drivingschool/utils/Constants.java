package com.zjc.drivingschool.utils;

import android.os.Environment;

/**
 * 常量类
 *
 * @author Z-2mobo
 */
public class Constants {

//    public static final String BASE_IP = "http://219.140.188.59:8080/";//发布IP
    public static final String BASE_IP = "http://59.172.153.170:20002";//云服务器测试
//      public static final String BASE_IP = "http://192.168.8.84:8080/";//公司内网

    public static final String BASE_URL = BASE_IP + "/app/";//服务接口

    /**
     * 路径
     */
    public static final String SDCARD = Environment.getExternalStorageDirectory().getPath() + "/";
    public static final String BASE_DIR = SDCARD + "hierarchicalmedical/";
    public static final String DIR_CACHE = BASE_DIR + "cache/";
    public static final String DIR_IMAGE = BASE_DIR + "images/";
    public static final String DIR_FRESCO = "hierarchicalmedical/fresco";
    public static final String DIR_VIDEO = BASE_DIR + "video/";

    // MD5循环加密次数
    public static final int ENCRYPT_TIMES = 1;

    /**
     * 拍照
     */
    public static final int IMAGE_REQUEST_CODE = 0;
    public static final int CAMERA_REQUEST_CODE = 3;
    public static final int RESULT_REQUEST_CODE = 2;

    /**
     * 二维码扫描后传送到主界面的key
     */
    public static final String SCAN_RESULT = "scan_result";

    public static final String ARGUMENT = "argument";
    public static final String PUSH = "jpush";
    public static final String INDEX = "index";
    public static final String TYPE = "type";

    /**
     * 请求码和返回码
     */
    public static final int INTENT_REQUESTCODE = 0;
    public static final int INTENT_RESULTCODE = 1;
    public static final int INTENT_RESULT_CODE_SECOND = 2;

    /**
     * http requestCode
     */
    public static final int REQUEST_CODE_EMPTY = 1;
    public static final int REQUEST_CODE_DIALOG = 2;
    public static final int REQUEST_CODE_SWIPE_REFRESH = 3;


    /**
     * 搜索控制标记
     */
    public static int ACTION_SHOW = 1;
    public static int ACTION_FILTER = 2;
    public static int ACTION_DISMISS = 3;
    public static int ACTION_DELAY_TIME = 100;


    /**
     * BeeCloud-AppID
     */
    public static final String BEECLOUD_APPID = "4ae74b5f-bfe3-415e-b58b-49336224c4bb";

    /**
     * BeeCloud- MasterSecret
     */
    public static final String BEECLOUD_MASTERSECRET = "8553dcb6-d63f-4053-ae6b-307f8cdf4b6a";

    /**
     * BeeCloud- AppSecret
     */
    public static final String BEECLOUD_APPSECRET = "ec938ada-77a3-4f68-a5ea-7f40efedbbef";

    /**
     * BeeCloud- 订单查询(GET)
     */
    public static final String BEECLOUD_ORDER_QUERY = "https://apidynamic.beecloud.cn/2/rest/bills";

    /**
     * BeeCloud- 订单总数查询(GET)
     */
    public static final String BEECLOUD_ORDER_COUNT_QUERY = "https://apidynamic.beecloud.cn/2/rest/bills/count";

    /**
     * BeeCloud- 支付订单查询(指定ID 地址后连接"/{id}")
     */
    public static final String BEECLOUD_ORDER_QUERY_BYID = "https://apidynamic.beecloud.cn/2/rest/bill";

}
