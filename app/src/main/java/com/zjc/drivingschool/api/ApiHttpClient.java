package com.zjc.drivingschool.api;

import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mobo.mobolibrary.util.Util;
import com.mobo.mobolibrary.util.UtilPhoto;
import com.zjc.drivingschool.db.SharePreferences.SharePreferencesUtil;
import com.zjc.drivingschool.db.model.SearchHospitalModel;
import com.zjc.drivingschool.db.model.UserInfo;
import com.zjc.drivingschool.db.request.OrderCreateRequest;
import com.zjc.drivingschool.db.request.SignupOrderRequest;
import com.zjc.drivingschool.utils.Constants;
import com.zjc.drivingschool.utils.ConstantsParams;

import java.io.File;

/**
 * @author Z
 * @Filename ApiHttpClient.java
 * @Date 2015-05-30
 * @description 接口管理类
 */
public class ApiHttpClient {

    private static final ApiHttpClient me;

    static {
        me = new ApiHttpClient();
    }

    private ApiHttpClient() {
    }

    public static ApiHttpClient getInstance() {
        return me;
    }

    /**
     * 接口：login
     * 用途：用户登陆
     * 参数：phone(手机号)，password(密码)
     * 调用示例：http://localhost:8080/estate/guardservices/securityGuard/login?phone=13476131467&password=888888
     */
    public void login(String phone, String password, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = new JsonObject();
        postRequest.addProperty("username", phone);
        postRequest.addProperty("password", password);

        HttpUtilsAsync.post(Constants.BASE_URL + "student/login", postRequest, asyncHttpResponseHandler);
    }

    /**
     * 接口：getVerificationCode
     * 用途：获取验证码
     * 参数：phone(手机号)type(验证码类型：0：注册 1：重置密码)
     * 调用示例：http://localhost:8080/estate/services/userinfo/getVerificationCode?phone=13026313632&type=1
     */
    public void getVerificationCode(String phone, int type, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = new JsonObject();
        postRequest.addProperty("phone", phone);
        postRequest.addProperty("type", type);
        HttpUtilsAsync.post(Constants.BASE_URL + "student/regcode", postRequest, asyncHttpResponseHandler);
    }


    /**
     * 接口：userRegister
     * 用途：用户注册
     * 参数：phone(手机号)，password(密码)
     * 调用示例：http://localhost:8080/estate/services/userinfo/userRegister?phone=13026313632&password=888888
     */
    public void userRegister(String phone, String valcode, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = new JsonObject();
        postRequest.addProperty("phone", phone);
        postRequest.addProperty("valcode", valcode);
        HttpUtilsAsync.post(Constants.BASE_URL + "student/register", postRequest, asyncHttpResponseHandler);
    }

    /**
     * 接口：forgetPwd
     * 用途：用户修改密码
     * 参数：phone(手机号)，password(密码)
     * 调用示例：http://localhost:8080/estate/services/userinfo/forgetPwd?phone=13026313632&password=888866
     */
    public void forgetPwd(String phone, String password, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put("phone", phone);
        params.put("password", Util.encrypt(password));
        HttpUtilsAsync.post(Constants.BASE_URL + "userinfo/forgetPwd", params, asyncHttpResponseHandler);
    }

    /**
     * 1.7接口：updatePwd
     * 用途：用户修改密码
     * 参数：phone(手机号)，password(密码),oldpassword(旧密码)
     * 调用示例：http://localhost:8080/estate/services/userinfo/updatePwd?phone=13026313632&oldpassword=888866&password=888888
     */
    public void updatePwd(String phone, String password, String oldpassword, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put("phone", phone);
        params.put("password", Util.encrypt(password));
        params.put("oldpassword", Util.encrypt(oldpassword));
        HttpUtilsAsync.post(Constants.BASE_URL + "userinfo/updatePwd", params, asyncHttpResponseHandler);
    }

    /**
     * 1.4接口：loginout
     * 用途：用户登出
     * 参数：phone(手机号)
     * 调用示例：http://localhost:8080/estate/guardservices/securityGuard/loginout?phone=13476131467
     * 请求成功返回值类型: null
     */
    public void loginOut(String phone, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put("phone", phone);
        HttpUtilsAsync.post(Constants.BASE_URL + "userinfo/loginout", params, asyncHttpResponseHandler);
    }

    /**
     * 1.8接口：getUserById
     * 用途：根据登陆用户Id获取基本资料
     * 参数：id(用户主键)
     * 调用示例：http://localhost:8080/medical/services/userinfo/getUserById?id=1
     */
    public void getUserById(int id, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put("id", id);
        HttpUtilsAsync.post(Constants.BASE_URL + "userinfo/getUserById", params, asyncHttpResponseHandler);
    }

    /**
     * 1.7.2上传个人头像
     * 调用示例：http://localhost:8080/hms/appservices/user/upLoadUserImg
     */
    public void upLoadUserImg(String id, String img, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        RequestParams params = new RequestParams();
        params.setForceMultipartEntityContentType(true);
        params.setHttpEntityIsRepeatable(true);
        params.put("uid", id);
        try {
            File file;
            //生成输入文件路径，进行压缩
            String filename = Constants.DIR_CACHE + Util.getPhotoFileName("1");
            UtilPhoto.getSmallFileByFile(img, filename);
            file = new File(filename);
            params.put("uploadimg", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpUtilsAsync.postInfinite(Constants.BASE_URL + "student/subphoto", params, asyncHttpResponseHandler);
    }

    /**
     * 1.9接口：updateUserBaseInfo
     * 用途：修改用户基本资料
     * 参数：
     * 地址	address	string	必填
     * 生日 --格式：yyyy-MM-dd	birthday	string	必填
     * QQ号码	qq	string	必填
     * 邮箱	email	string	必填
     * 身份证号码	identityno	string	必填
     * 用户昵称	nickname	string	必填
     * 用户ID	uid	string	必填
     * 性别 true:男 false:女
     * 调用示例：http://localhost:8080/medical/services/userinfo/updateUserBaseInfo?id=1&email=&age=&gender=&logoFile=
     */
    public void updateUserBaseInfo(UserInfo userInfo, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = new JsonObject();
        postRequest.addProperty("uid", userInfo.getUid());
        postRequest.addProperty("birthday", userInfo.getBirthday());
        postRequest.addProperty("gender", userInfo.isGender());
        postRequest.addProperty("nickname", userInfo.getNickname());
        postRequest.addProperty("email", userInfo.getEmail());
        postRequest.addProperty("qq", userInfo.getQq());
        postRequest.addProperty("phone", userInfo.getPhone());
        postRequest.addProperty("address", userInfo.getAddress());
        postRequest.addProperty("identityno", userInfo.getIdentityno());
        HttpUtilsAsync.post(Constants.BASE_URL + "student/subinfo", postRequest, asyncHttpResponseHandler);
    }

    /**
     * 1.10接口：findPatientByUserId
     * 用途：根据用户Id获取亲友列表
     * 参数：userId(用户主键)
     * 调用示例：http://localhost:8080/medical/services/userinfo/findPatientByUserId?userId=1
     */

    public void findPatientByUserId(int userId, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put("userId", userId);
        HttpUtilsAsync.post(Constants.BASE_URL + "userinfo/findPatientByUserId", params, asyncHttpResponseHandler);
    }

    /**
     * 1.13接口：addPatient
     * 用途：添加亲友资料
     * 参数：
     * userId(登录用户主键)
     * name(亲友姓名)
     * idCardType(证件类型1.居民身份证 2.居民户口簿 3.护照 4.军官证 5.驾驶证 6.港澳居民来往内地通行证 7.台湾居民来往内地通行证 99.其他法定有效证件)
     * idCardNo(证件号)
     * isDefault(可选，1.默认 2.非默认)
     * 其他(可选)
     * 调用示例：http://localhost:8080/medical/services/userinfo/addPatient
     */
    public void addPatient(int userId, String name, int idCardType, String idCardNo, int gender, int age, String city, String phone, int isDefault, String birth, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put("userId", userId);
        params.put("name", name);
        params.put("idCardType", idCardType);
        params.put("idCardNo", idCardNo);
        params.put("gender", gender);
        params.put("age", age);
        params.put("city", city);
        params.put("phone", phone);
        params.put("isDefault", isDefault);
        params.put("birthday", birth);
        HttpUtilsAsync.post(Constants.BASE_URL + "userinfo/addPatient", params, asyncHttpResponseHandler);
    }


    /**
     * 4.1接口：findHospitalPharmacy
     * 用途：获取医院列表
     * lng(经度)，lat(纬度)， departmentName(科室名称), divisionId(区域id)，
     * parkingStatus(停车位可用状态), bedStatus(床位可用状态),
     * registrationStatus(挂号可用状态),distance(查询范围),
     */
    public void findHospitalPharmacy(SearchHospitalModel searchHospitalModel, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put("zoom", searchHospitalModel.getZoom());
        if (SharePreferencesUtil.getInstance().isLogin()) {
            params.put("userId", SharePreferencesUtil.getInstance().readUser().getIdentityno());
        }
        //上传经纬度和区域
        params.put("lng", searchHospitalModel.getLatLngLocal().getLongitude());
        params.put("lat", searchHospitalModel.getLatLngLocal().getLatitude());
        HttpUtilsAsync.post(Constants.BASE_URL + "hospitalPharmacy/findHospitalPharmacy", params, asyncHttpResponseHandler);
    }


    /**
     * 6.2接口：getMyAccount
     * 用途：获取我的账户信息
     * 参数：uid(登录用户主键)
     * 调用示例：/app/student/account/balance
     */
    public void getMyAccount(String userId, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = new JsonObject();
        postRequest.addProperty("uid", userId);
        HttpUtilsAsync.post(Constants.BASE_URL + "student/account/balance", postRequest, asyncHttpResponseHandler);
    }

    /**
     * * ###########   预约学车   ############
     * 1.1.22 学员报名订单创建
     * 学车人电话	contactsphone	string	（非必传，代人下单时必填）
     * 开始位置纬度	latitude	number
     * 用户ID	uid	string
     * 用户姓名	uname	string
     * 学历	education	string
     * 用户电话	uphone	string
     * 学车人性别	gender	boolean
     * 优惠券ID	vid	string	（非必传，格式:多个ID用','分割）
     * 学车人姓名	contactsname	string	（非必传，代人下单时必填）
     * 学车人出生年月	birthday	string	yyyy-MM-dd
     * 开始位置经度	longitude	number
     * 是否代人下单	isreplace	boolean
     * /app/student/order/create
     */
    public void startApply(SignupOrderRequest signupOrder, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = new JsonObject();
        postRequest.addProperty("uid", signupOrder.getUid());
        postRequest.addProperty("longitude", signupOrder.getLongitude());
        postRequest.addProperty("latitude", signupOrder.getLatitude());

        postRequest.addProperty("uname", signupOrder.getUname());
        postRequest.addProperty("uphone", signupOrder.getUphone());
        postRequest.addProperty("gender", signupOrder.getGender());
        postRequest.addProperty("birthday", signupOrder.getBirthday());
        postRequest.addProperty("education", signupOrder.getEducation());

        postRequest.addProperty("isreplace", signupOrder.getIsreplace());
        postRequest.addProperty("contactsname", signupOrder.getContactsname());
        postRequest.addProperty("contactsphone", signupOrder.getContactsphone());
        HttpUtilsAsync.post(Constants.BASE_URL + "student/signup", postRequest, asyncHttpResponseHandler);
    }

    /**
     * 1.1.21 学员报名订单列表
     * 参数：pagesize  uid offset   state  orderid creatdate
     * 调用示例：/app/student/order/list
     */
    public void startOrders(String userId, int start, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = new JsonObject();
        postRequest.addProperty("uid", userId);
        postRequest.addProperty("offset", start);
        postRequest.addProperty("pagesize", ConstantsParams.PAGE_SIZE);
        HttpUtilsAsync.post(Constants.BASE_URL + "student/signuporder/list", postRequest, asyncHttpResponseHandler);
    }

    /**
     * 1.1.23 学员报名订单详情
     * 参数：orid  uid
     * 调用示例：/app/student/order/detail
     */
    public void getApplyDetail(String userId, String orid, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = new JsonObject();
        postRequest.addProperty("uid", userId);
        postRequest.addProperty("orid", orid);
        HttpUtilsAsync.post(Constants.BASE_URL + "student/signuporder/detail", postRequest, asyncHttpResponseHandler);
    }


    /**
     * ###########   预约学车   ############
     * 1.1.12 获取学员可预约产品
     * 参数：pagesize  uid offset   state  orderid creatdate
     * 调用示例：/app/student/product
     */
    public void findProducts(String userId, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = new JsonObject();
        postRequest.addProperty("uid", userId);
        HttpUtilsAsync.post(Constants.BASE_URL + "student/product", postRequest, asyncHttpResponseHandler);
    }

    /**
     * 1.1.13 学员创建学车订单
     * 开始位置经度	longitude	number	必填
     * * 开始位置纬度	latitude	number	必填
     * 项目类型ID	subjectid	string	必填
     * 项目类型名称	subjectname	string	必填
     * 是否VIP	isvip	boolean	必填
     * 车型名称	carsname	string	必填
     * 是否代人下单	isreplace	boolean	必填
     * 下单用户ID	uid	string	必填
     * 联系人姓名	contactsname	string	isreplace为true时必传
     * 联系人电话	contactsphone	string	isreplace为true时必传
     * 数量 单位：小时	number	number	必填
     * 开始时间 格式：yyyy-MM-dd hh:mm:ss	starttime	string	必填
     * 车型ID	carsid	string	必填
     * 下单用户名	loginname	string	必填
     * 下单用户昵称	nickname	string	必填
     * 优惠券ID	vid	string	非必传，格式:多个ID用','分割
     * /app/student/order/create
     */
    public void learnApply(OrderCreateRequest orderCreateRequest, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = new JsonObject();
        postRequest.addProperty("uid", orderCreateRequest.getUid());
        postRequest.addProperty("longitude", orderCreateRequest.getLongitude());
        postRequest.addProperty("latitude", orderCreateRequest.getLatitude());
        postRequest.addProperty("subjectid", orderCreateRequest.getSubjectid());
        postRequest.addProperty("subjectname", orderCreateRequest.getSubjectname());
        postRequest.addProperty("carsname", orderCreateRequest.getCarsname());
        postRequest.addProperty("carsid", orderCreateRequest.getCarsid());

        postRequest.addProperty("isvip", orderCreateRequest.getIsvip());
        postRequest.addProperty("isreplace", orderCreateRequest.getIsreplace());
        postRequest.addProperty("contactsname", orderCreateRequest.getContactsname());
        postRequest.addProperty("contactsphone", orderCreateRequest.getContactsphone());

        postRequest.addProperty("number", orderCreateRequest.getNumber());
        postRequest.addProperty("starttime", orderCreateRequest.getStarttime());
        postRequest.addProperty("loginname", orderCreateRequest.getLoginname());
        postRequest.addProperty("nickname", orderCreateRequest.getNickname());
        HttpUtilsAsync.post(Constants.BASE_URL + "student/order/create", postRequest, asyncHttpResponseHandler);
    }


    /**
     * 1.1.14 学员学车订单列表
     * 参数：pagesize  uid offset   state  orderid creatdate
     * 调用示例：/app/student/order/list
     */
    public void findOrders(String userId, int start, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = new JsonObject();
        postRequest.addProperty("uid", userId);
        postRequest.addProperty("offset", start);
        postRequest.addProperty("pagesize", ConstantsParams.PAGE_SIZE);
        HttpUtilsAsync.post(Constants.BASE_URL + "student/order/list", postRequest, asyncHttpResponseHandler);
    }

    /**
     * 1.1.15 学员学车订单详情
     * 参数：orid  uid
     * 调用示例：/app/student/order/detail
     */
    public void getOrderDetailById(String userId, String orid, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = new JsonObject();
        postRequest.addProperty("uid", userId);
        postRequest.addProperty("orid", orid);
        HttpUtilsAsync.post(Constants.BASE_URL + "student/order/detail", postRequest, asyncHttpResponseHandler);
    }

    /**
     * 1.1.26 学员取消学车订单
     * 参数：orid  uid
     * 调用示例：/app/student/order/cancel
     */
    public void cancelStudyOrder(String userId, String orid, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = new JsonObject();
        postRequest.addProperty("uid", userId);
        postRequest.addProperty("orid", orid);
        HttpUtilsAsync.post(Constants.BASE_URL + "student/order/cancel", postRequest, asyncHttpResponseHandler);
    }

    /**
     * 1.1.27 学员申请订单退订
     * 参数：orid  uid
     * 调用示例：/app/student/order/refund
     */
    public void unSubjectStudyOrder(String userId, String orid, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = new JsonObject();
        postRequest.addProperty("uid", userId);
        postRequest.addProperty("orid", orid);
        HttpUtilsAsync.post(Constants.BASE_URL + "student/order/refund", postRequest, asyncHttpResponseHandler);
    }

    /**
     * ###########驾校主页############
     * 6.2接口：detail
     * 用途：地图驾校详情
     * 参数：uid;// 用户ID  sid;// 驾校ID
     * 调用示例：/app/student/school/detail
     */
    public void getSchoolInfo(String uid, String sid, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = new JsonObject();
        postRequest.addProperty("uid", uid);
        postRequest.addProperty("sid", sid);
        HttpUtilsAsync.post(Constants.BASE_URL + "student/school/detail", postRequest, asyncHttpResponseHandler);
    }


    /**
     * ###########消息中心模块############
     * 11.1.16 消息列表请求
     * 参数   偏移量	offset	number	必填/用户ID	uid	string	必填/分页大小	pagesize	number	必填
     * 调用示例：message/list
     */
    public void getMessageByTags(String userId, int start, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = new JsonObject();
        postRequest.addProperty("uid", userId);
        postRequest.addProperty("offset", start);
        postRequest.addProperty("pagesize", ConstantsParams.PAGE_SIZE);
        HttpUtilsAsync.post(Constants.BASE_URL + "student/message/list", postRequest, asyncHttpResponseHandler);
    }

    /**
     * ###########  收藏模块  ############
     * 1.1.30 收藏教练列表
     * 参数   偏移量	offset	number	必填/用户ID	uid	string	必填/分页大小	pagesize	number	必填
     * 调用示例：/app/student/teacher/collect/list
     */
    public void getCollects(String userId, int start, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        JsonObject postRequest = new JsonObject();
        postRequest.addProperty("uid", userId);
        postRequest.addProperty("offset", start);
        postRequest.addProperty("pagesize", ConstantsParams.PAGE_SIZE);
        HttpUtilsAsync.post(Constants.BASE_URL + "student/teacher/collect/list", postRequest, asyncHttpResponseHandler);
    }
}
