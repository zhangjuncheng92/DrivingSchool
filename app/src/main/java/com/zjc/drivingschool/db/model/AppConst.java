package com.zjc.drivingschool.db.model;

/**
 * APP请求与相应常量
 * 
 * @author LJ
 * @date 2016年7月21日
 */
public class AppConst
{
	/**
	 * 接口地址
	 */
	public static final String APP_BASEURL = "http://192.168.10.102:8080";//http://59.172.153.170:20002
	
	/**
	 * 范围检索半径
	 */
	public static final Integer APP_RANGE = 2000;
	
	/**
	 * 移动端交互秘钥
	 */
	public static final String APP_SECRETKEY = "123456";
	
	/**
	 * 服务端标识
	 */
	public static final String APP_SERVER = "SERVER";
	
	/**
	 * 安卓客户端标识
	 */
	public static final String APP_ANDROID = "ANDROID";
	
	/**
	 * 苹果客户端标识
	 */
	public static final String APP_IOS = "IOS";
	
	/**
	 * 请求与响应头时区
	 */
	public static final String HTTP_HEADER_TIME_ZONE = "GMT";
	
	/**
	 * 请求与响应头时间格式
	 */
	public static final String HTTP_HEADER_DATE_FORMAT = "EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'z";
	
	/**
	 * BeeCloud-AppID
	 */
	public static final String BEECLOUD_APPID = "";
	
	/**
	 * BeeCloud- AppSecret
	 */
	public static final String BEECLOUD_APPSECRET = "";
	
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
	
	/**
	 * LBSCloud- AK
	 */
	public static final String LBSCLOUD_AK = "a49QpA1I2HVWCXL3ULMLvPxlU9Chpt98";
	
	/**
	 * LBSCloud- SK
	 */
	public static final String LBSCLOUD_SK = "Wn0unWMc7gpTlETZVcDGbuB8gMd3sSIc";
	
	/**
	 * LBSCloud- 表ID
	 */
	public static final String LBSCLOUD_TABLEID = "148148";
	
	/**
	 * LBSCloud- 创建数据（create poi）接口  POST请求
	 */
	public static final String LBSCLOUD_CREATE_POI = "http://api.map.baidu.com/geodata/v3/poi/create";
	
	/**
	 * LBSCloud- 修改数据（update poi）接口  POST请求
	 */
	public static final String LBSCLOUD_UPDATE_POI = "http://api.map.baidu.com/geodata/v3/poi/update";
	
	/**
	 * LBSCloud- 删除数据（dalete poi）接口  POST请求
	 */
	public static final String LBSCLOUD_DELETE_POI = "http://api.map.baidu.com/geodata/v3/poi/delete";
	
	/**
	 * LBSCloud- poi周边搜索 GET请求
	 */
	public static final String LBSCLOUD_SEARCH_POI_NEARBY = "http://api.map.baidu.com/geosearch/v3/nearby";
	
	/**
	 * JPush-AppKey
	 */
	public static final String JPUSH_APPKEY = "";
	
	/**
	 * JPush-MasterSecret
	 */
	public static final String JPUSH_MASTERSECRET = "";
	
	/**
	 * JPush-消息推送
	 */
	public static final String JPUSH_V3_URL = "https://api.jpush.cn/v3/push";
	
	/**
	 * 请求成功
	 */
	public static final String APP_RESPONSE_SUCCESS = "200";
	
	/**
	 * 响应失败
	 */
	public static final String APP_RESPONSE_ERROR = "300";
	
	/**
	 * 请求验证失败
	 */
	public static final String APP_VERIFICATION_ERROR = "301";
	
	/**
	 * 请求报文不合法
	 */
	public static final String APP_REQUEST_ERROR = "302";
	
	/**
	 * 必填字段为空
	 */
	public static final String APP_FIELD_ISREQUERE = "303";
	
	/**
	 * 上传的图片为空
	 */
	public static final String APP_UPLOADIMG_ISREQUERE = "304";
	
	/**
	 * 上传文件超出大小限制
	 */
	public static final String APP_FILEMAXSIZE_ERROR = "305";
	
	/**
	 * 用户不存在
	 */
	public static final String APP_USER_NO_FOUND = "901";
	
	/**
	 * 用户已存在
	 */
	public static final String APP_USER_IS_FOUND = "902";
	
	/**
	 * 无效用户
	 */
	public static final String APP_USER_ERROR = "903";
	
	/**
	 * 账号已禁用
	 */
	public static final String APP_USER_NOT_USED = "904";
	
	/**
	 * 登录名或密码不正确
	 */
	public static final String APP_USERNAME_PWD_ERROR = "905";

	/**
	 * 验证码已过期
	 */
	public static final String APP_VCODE_EXPIRE = "906";

	/**
	 * 验证码不正确
	 */
	public static final String APP_VCODE_ERROR = "907";
	
	/**
	 * 驾校不存在
	 */
	public static final String APP_SCHOOL_NO_FOUND = "908";
	
	/**
	 * 教练不存在
	 */
	public static final String APP_TEACHER_NO_FOUND = "909";
	
	/**
	 * 教练已被收藏
	 */
	public static final String APP_TEACHER_IS_COLLECT = "910";
	
	/**
	 * 订单不存在
	 */
	public static final String APP_ORDER_NOT_FOUND = "911";
	
	/**
	 * 订单不可取消
	 */
	public static final String APP_ORDER_NOT_CANCEL = "912";
	
	/**
	 * 订单不可退订
	 */
	public static final String APP_ORDER_NOT_REFUND = "913";
	
	/**
	 * 无订单退订权限
	 */
	public static final String APP_ORDER_NO_REFUND_POWER = "914";
	
	/**
	 * 订单状态不能接单
	 */
	public static final String APP_ORDER_NO_TAKE = "915";
	
	/**
	 * 订单已被接单
	 */
	public static final String APP_ORDER_BE_TAKED = "916";
	
	/**
	 * 订单不属于该教练
	 */
	public static final String APP_ORDER_TEACHER_ERROR = "917";
	
	/**
	 * 订单状态错误
	 */
	public static final String APP_ORDER_STATE_ERROR = "918";
	
	/**
	 * 用户余额不足
	 */
	public static final String APP_BALANCE_NOT_ENOUGH = "930";
	
	/**
	 * 报名价格不存在
	 */
	public static final String APP_SIGNUP_PRICE_NOT_FOUND = "931";
	
	
}
