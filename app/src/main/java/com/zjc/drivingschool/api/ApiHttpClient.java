package com.zjc.drivingschool.api;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mobo.mobolibrary.util.Util;
import com.mobo.mobolibrary.util.UtilPhoto;
import com.zjc.drivingschool.db.SharePreferences.SharePreferencesUtil;
import com.zjc.drivingschool.db.model.SearchHospitalModel;
import com.zjc.drivingschool.utils.Constants;

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
        RequestParams params = new RequestParams(); // 绑定参数
        params.put("phone", phone);
        params.put("password", Util.encrypt(password));
        HttpUtilsAsync.post(Constants.BASE_URL + "userinfo/login", params, asyncHttpResponseHandler);
    }

    /**
     * 接口：getVerificationCode
     * 用途：获取验证码
     * 参数：phone(手机号)type(验证码类型：0：注册 1：重置密码)
     * 调用示例：http://localhost:8080/estate/services/userinfo/getVerificationCode?phone=13026313632&type=1
     */
    public void getVerificationCode(String phone, int type, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put("phone", phone);
        params.put("type", type);
        HttpUtilsAsync.post(Constants.BASE_URL + "userinfo/getVerificationCode", params, asyncHttpResponseHandler);
    }


    /**
     * 接口：userRegister
     * 用途：用户注册
     * 参数：phone(手机号)，password(密码)
     * 调用示例：http://localhost:8080/estate/services/userinfo/userRegister?phone=13026313632&password=888888
     */
    public void userRegister(int registerType, String phone, String password, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        RequestParams params = new RequestParams();
        params.put("phone", phone);
        params.put("registerType", registerType);
        params.put("password", Util.encrypt(password));
        HttpUtilsAsync.post(Constants.BASE_URL + "userinfo/userRegister", params, asyncHttpResponseHandler);
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
     * 1.9接口：updateUserBaseInfo
     * 用途：修改用户基本资料
     * 参数：id(用户主键)email()age()gender(1.男 2.女)logoFile(头像文件)
     * 调用示例：http://localhost:8080/medical/services/userinfo/updateUserBaseInfo?id=1&email=&age=&gender=&logoFile=
     */
    public void updateUserBaseInfo(int id, String logoFile, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        RequestParams params = new RequestParams();
        params.setForceMultipartEntityContentType(true);
        params.setHttpEntityIsRepeatable(true);
        params.put("id", id);
//        params.put("email", email);
//        params.put("age", age);
//        params.put("gender", gender);
//        params.put("logoFile", logoFile);

        try {
            File file;
            //生成输入文件路径，进行压缩
            String filename = Constants.DIR_CACHE + Util.getPhotoFileName();
            UtilPhoto.getSmallFileByFile(logoFile, filename);
            file = new File(filename);

            params.put("logoFile", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpUtilsAsync.post(Constants.BASE_URL + "userinfo/updateUserBaseInfo", params, asyncHttpResponseHandler);
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
            params.put("userId", SharePreferencesUtil.getInstance().readUser().getId());
        }
        //上传经纬度和区域
        params.put("lng", searchHospitalModel.getLatLngLocal().getLongitude());
        params.put("lat", searchHospitalModel.getLatLngLocal().getLatitude());
        HttpUtilsAsync.post(Constants.BASE_URL + "hospitalPharmacy/findHospitalPharmacy", params, asyncHttpResponseHandler);
    }
}
