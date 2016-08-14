package com.zjc.drivingschool.db.SharePreferences;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.mobo.mobolibrary.util.Util;
import com.zjc.drivingschool.app.MApp;
import com.zjc.drivingschool.db.model.NotificationTypeModel;
import com.zjc.drivingschool.db.model.UserInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/24.
 */
public class SharePreferencesUtil {
    private static SharePreferencesUtil me;

    public static SharePreferencesUtil getInstance() {
        if (me == null) {
            me = new SharePreferencesUtil();
        }
        return me;
    }

    public Application getApplication() {
        return MApp.getInstance();
    }


    public void savePwd(String pwd) {
        Util.writePreferences(getApplication(), "password", pwd);
    }

    public String readPwd() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
        return sp.getString("password", "");
    }

    public void removePwd() {
        Util.removePreferences(getApplication(), "password");
    }

    public void saveUserName(String phone) {
        Util.writePreferences(getApplication(), "userName", phone);
    }

    public String readPhone() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
        return sp.getString("userName", "");
    }

    public void setLogin(boolean isLogin) {
        Util.writePreferences(getApplication(), "login", isLogin);
    }

    public boolean isLogin() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
        return sp.getBoolean("login", false);
    }

    public void setAuthenticationStatus(boolean isLogin) {
        Util.writePreferences(getApplication(), "AuthenticationStatus", isLogin);
    }

    public boolean isAuthenticationStatus() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
        return sp.getBoolean("AuthenticationStatus", false);
    }


    /**
     * 获取用户信息
     *
     * @return
     */
    public UserInfo readUser() {
        UserInfo userInfo = new UserInfo();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
        String string2obj = sp.getString("userInfo", "");
        if (!TextUtils.isEmpty(string2obj)) {
            try {
                userInfo = (UserInfo) SerializableUtil.str2Obj(string2obj);
            } catch (IOException e) {
                e.printStackTrace();
                return userInfo;
            }
        }
        return userInfo;
    }

    /**
     * 保存用户信息
     *
     * @return
     */
    public void saveUser(UserInfo userInfo) {
        try {
            String obj2Str = SerializableUtil.obj2Str(userInfo);
            Util.writePreferences(getApplication(), "userInfo", obj2Str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取通知类型信息
     *
     * @return
     */
    public void saveNotificationTypeModelList(List<NotificationTypeModel> notificationTypeModels) {
        try {
            String obj2Str = SerializableUtil.list2String(notificationTypeModels);
            Util.writePreferences(getApplication(), "notificationTypeModels", obj2Str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存通知类型信息
     *
     * @return
     */
    public List<NotificationTypeModel> getNotificationTypeModelList() {
        List<NotificationTypeModel> list = new ArrayList<>();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
        String stringList = sp.getString("notificationTypeModels", "");
        if (stringList != "") {
            try {
                list = (List<NotificationTypeModel>) SerializableUtil.string2List(stringList);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    /**
     * 得到是否自动登录
     */
    public boolean isRememberLogin() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
        return sp.getBoolean("isrememberlogin", false);
    }

    /**
     * 设置是否自动登录
     */
    public void setRememberLogin(boolean isRememberLogin) {
        Util.writePreferences(getApplication(), "isrememberlogin", isRememberLogin);
    }

    public int readPayAction() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
        return sp.getInt("payAction", -1);
    }

    public void savePayAction(int payAction) {
        Util.writePreferences(getApplication(), "payAction", payAction);
    }


    /**
     * 得到存储的版本号
     */
    public int getVersionCode() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
        return sp.getInt("versionCode", 0);
    }

    /**
     * 设置存储的版本号
     */
    public void setVersionCode(int versionCode) {
        Util.writePreferences(getApplication(), "versionCode", versionCode);
    }

    /**
     * 得到医院id
     *
     * @return
     */
    public int getHospitalId() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplication());
        return sp.getInt("hospitalId", -1);
    }

    /**
     * 保存医院ID，为了在挂号医生详情里面传id
     *
     * @param hospitalId
     */
    public void setHospitalId(int hospitalId) {
        Util.writePreferences(getApplication(), "hospitalId", hospitalId);
    }

}
