package com.mobo.mobolibrary.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.Toast;

import com.mobo.mobolibrary.app.BaseApplication;

import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 工具类
 *
 * @author sharoncn
 */
public class Util {
    private static final String TAG = "Util";

    /**
     * 验证是否有安装微信
     *
     * @param mContext
     * @return
     */
    public static boolean isInstallWeChat(Context mContext) {
        Intent intent = mContext.getPackageManager().getLaunchIntentForPackage("com.tencent.mm");
        // 这里如果intent为空，就说名没有安装要跳转的应用嘛
        if (intent != null) {
            // 这里跟Activity传递参数一样的嘛，不要担心怎么传递参数，还有接收参数也是跟Activity和Activity传参数一样
            return true;
        } else {
            // 没有安装要跳转的app应用，提醒一下
            Util.showCustomMsg(mContext, "不能使用微信支付，请先下载微信app");
            return false;
        }
    }

    /**
     * 验证是否有安装支付宝支付
     *
     * @param
     * @return
     */
//    public static void isInstallAliPay(BaseFragment fragment, Handler handler) {
//        AliPayHelper aliPayHelper = new AliPayHelper(fragment);
//        aliPayHelper.check(handler);
//    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 拨打电话
     */
    public static void call(Context context, String telephone) {
        if (TextUtils.isEmpty(telephone)) {
            Util.showCustomMsg("电话号码为空");
            return;
        }
        Uri uri = Uri.parse("tel:" + telephone);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(uri);
        context.startActivity(intent);
    }

    /**
     * 正则表达式验证手机号
     *
     * @param phone
     * @return
     */
    public static boolean checkPhone(String phone) {
        Pattern pattern_phone = Pattern.compile("^[0-9]{7,11}$");
        return pattern_phone.matcher(phone).matches();
    }

    /**
     * Toast显示提示信息
     *
     * @param resId
     */
    public static void showCustomMsg(int resId) {
        showCustomMsg(BaseApplication.getInstance().getApplicationContext().getString(resId));
    }

    /**
     * Toast显示提示信息
     *
     * @param context
     * @param msg
     */
    public static void showCustomMsg(Context context, String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * Toast显示提示信息
     *
     * @param msg
     */
    public static void showCustomMsgLong(String msg) {
        Toast toast = Toast.makeText(BaseApplication.getInstance().getApplicationContext(), msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * Toast显示提示信息
     *
     * @param msg
     */
    public static void showCustomMsg(String msg) {
        Toast toast = Toast.makeText(BaseApplication.getInstance().getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    /**
     * 显示ProgressDialog
     *
     * @param context Context
     * @param msg     文本
     * @param params  如果需要设置样式,放置在这里
     * @return
     */
    public static ProgressDialog showProgressDialog(Context context, String msg, int... params) {
        int style = ProgressDialog.STYLE_SPINNER;
        try {
            style = params[0];
        } catch (Exception e) {
        }
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage(msg);
        pd.setProgressStyle(style);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        return pd;
    }

    /**
     * 身份证号密文显示
     *
     * @param cardNo
     * @return
     */
    public static String transCardNo(String cardNo) {
        if (TextUtils.isEmpty(cardNo)) {
            return null;
        }
        int stars = cardNo.length() - 8;
        if (stars == 10) {
            return (cardNo.substring(0, 3) + "**********" + cardNo.substring(12, 18));
        } else if (stars == 7) {
            return (cardNo.substring(0, 3) + "*******" + cardNo.substring(9, 15));
        }
        return null;
    }


    /**
     * 手机号密文显示
     *
     * @param phone
     * @return
     */
    public static String transPhoneNumber(String phone) {
        if (phone == null) {
            return "";
        } else {
            return (phone.substring(0, 3) + "*****" + phone.substring(8, 11));
        }
    }

    /**
     * 显示ProgressDialog
     *
     * @param context Context
     * @param resId   文本ID
     * @param params  如果需要设置样式,放置在这里
     * @return
     */
    public static ProgressDialog showProgressDialog(Context context, int resId, int... params) {
        return showProgressDialog(context, context.getString(resId), params);
    }

    /**
     * 设置EditText只保留两位小数点
     *
     * @param editText
     */
    public static void setPricePoint(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0, s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }

                if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
    }

    /**
     * 检查是否存在SDCard
     *
     * @return
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获得SD卡根目录
     *
     * @return
     */
    public static String getSdCardPath() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().getPath();
        }
        return null;
    }


    /**
     * 根据对象获得和字段名相匹配的方法
     *
     * @param field 字段名
     * @param obj   对象
     * @return 匹配到的方法
     */
    public static Method getMethodByField(String field, Object obj) {
        return getMethodByField(field, obj.getClass());
    }

    /**
     * 根据Class对象获得和字段名相匹配的方法
     *
     * @param field 字段名
     * @param clazz Class
     * @return 匹配到的方法
     */
    public static Method getMethodByField(String field, Class<?> clazz) {
        final String get = "get" + field;
        final Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (get.equalsIgnoreCase(method.getName())) {
                return method;
            }
        }
        return null;
    }

    public static void removePreferences(Context mContext, String key) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        final SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * 像Preferences中写入键值对
     *
     * @param mContext context
     * @param key      键
     * @param value    值
     */
    public static void writePreferences(Context mContext, String key, String value) {
        if (TextUtils.isEmpty(value)) {
            return;
        }
        final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        final SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void writePreferences(Context mContext, String key, boolean value) {
        final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        final SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void writePreferences(Context mContext, String key, int value) {
        final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        final SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }


    /**
     * MD5加密
     *
     * @param content 待加密内容
     * @return 密文
     */
    public static String encrypt(String content) {
        StringBuffer sb = null;
        try {
            final MessageDigest md = MessageDigest.getInstance("md5");
            md.update(content.getBytes());
            byte b[] = md.digest();

            int i;
            sb = new StringBuffer();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    sb.append("0");
                sb.append(Integer.toHexString(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb == null ? null : sb.toString().toUpperCase();
    }

    /**
     * 循环加密
     *
     * @param content 待加密内容
     * @param count   循环加密次数
     * @return 密文
     */
    public static String circleEncrypt(String content, int count) {
        String result = content;
        for (int i = 0; i < count; i++) {
            result = encrypt(result);
        }
        return result;
    }

    /**
     * 使用系统当前日期加以调整作为照片的名称
     */
    public static String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

    /**
     * 使用系统当前日期加以调整作为照片的名称
     */
    public static String getPhotoFileName(String tag) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + "_" + tag+ ".jpg";
    }


    /**
     * 使用系统当前日期时间
     */
    public static String getCurrentTime() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
        return dateFormat.format(date);
    }

    public static String getCurrentTime2() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }


    /**
     * 获取现在的日期
     *
     * @return
     */
    public static Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取本地软件版本
     */
    public static int getLocalVersion(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 获取本地软件版本名称
     */
    public static String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 判断手机号码是否符合规则
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        String telRegex = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }

    /**
     * 根据日期获取年龄
     *
     * @param date
     * @return
     */
    public static int getAge(Date date) {
        int n = 0;
        Calendar calendar = Calendar.getInstance();
        if (date == null)
            calendar.setTimeInMillis(System.currentTimeMillis());
        else
            calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(System.currentTimeMillis());
        int yearNow = calendar1.get(Calendar.YEAR);
        int monthNow = calendar1.get(Calendar.MONTH) + 1;
        int dayNow = calendar1.get(Calendar.DAY_OF_MONTH);
        if (monthNow - month > 0) {
            n = yearNow - year;
        } else if (monthNow - month == 0) {
            if (dayNow - day >= 0) {
                n = yearNow - year;
            } else if (dayNow - day < 0) {
                n = yearNow - year - 1;
            }
        } else if (monthNow - month < 0) {
            n = yearNow - year - 1;
        }
        return n;
    }

}
