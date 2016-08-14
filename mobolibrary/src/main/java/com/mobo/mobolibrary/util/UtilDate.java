package com.mobo.mobolibrary.util;

import android.content.Context;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Administrator on 2015/5/30.
 */
public class UtilDate {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static SimpleDateFormat sdftime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());


    /**
     * * 将date格式化为1999-01-01 的形式
     *
     * @param date Date 需要格式化的Date数据
     * @return String 返回格式化之后的字符串
     */
    public static String formatDate(String format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(date);
    }

    /**
     * * 将date格式化为1999-01-01 的形式
     *
     * @param date Date 需要格式化的Date数据
     * @return String 返回格式化之后的字符串
     */
    public static String formatDate(Date date) {
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(date);
    }

    /**
     * * 将date格式化为1999-01-01 00:00:00的形式
     *
     * @param date Date 需要格式化的Date数据
     * @return String 返回格式化之后的字符串
     */
    public static String formatDatetime(Date date) {
        if (date == null)
            return null;
        sdftime.setTimeZone(TimeZone.getDefault());
        return sdftime.format(date);
    }

    /**
     * 从字符串中解析日期
     *
     * @param dateStr
     * @return
     */
    public static Date parseDate(String dateStr) {
        if (TextUtils.isEmpty(dateStr)) {
            return null;
        }
        sdftime.setTimeZone(TimeZone.getDefault());
        try {
            return sdftime.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从字符串中解析日期
     *
     * @param dateStr
     * @return
     */
    public static Date parseDate(SimpleDateFormat simpleDateFormat, String dateStr) {
        if (TextUtils.isEmpty(dateStr)) {
            return null;
        }
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 当前时间计算
     *
     * @param getDateString
     * @return
     */
    public static String getTimeDisplay(String getDateString, Context context) {
        return getTimeDisplay(getDateString, context, true);
    }

    public static String getTimeDisplay(String getDateString, Context context, boolean timeZone) {
        Date getDate = null;
        try {
            if (timeZone) {
                getDate = getFormat("yyyy-MM-dd HH:mm:ss").parse(getDateString);
            } else {
                getDate = getFormat("yyyyMMdd'T'HH:mm:ss").parse(getDateString);
            }
        } catch (ParseException e) {
            getDate = new Date();
        }

        final long getTime = getDate.getTime();

        final long currTime = System.currentTimeMillis();
        final Date formatSysDate = new Date(currTime);
        // 判断当前总天数
        final int sysMonth = formatSysDate.getMonth() + 1;
        final int sysYear = formatSysDate.getYear();

        // 计算服务器返回时间与当前时间差值
        final long seconds = (currTime - getTime) / 1000;
        final long minute = seconds / 60;
        final long hours = minute / 60;
        final long day = hours / 24;
        final long month = day / calculationDaysOfMonth(sysYear, sysMonth);
        final long year = month / 12;

        if (year > 0 || month > 0 || day > 0) {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return simpleDateFormat.format(getDate);
        } else if (hours > 0) {
            String min = minute - (hours * 60) + "";
            return hours + "小时" + min + "分钟之前";
        } else if (minute > 0) {
            return minute + "分钟之前";
        } else {
            // return "1" + context.getString(R.string.str_secondago);
            return "1" + "分钟之前"; // 都换成分钟前
        }
    }

    public static SimpleDateFormat getFormat(String partten) {
        return new SimpleDateFormat(partten);
    }

    /**
     * 计算月数
     *
     * @return
     */
    private static int calculationDaysOfMonth(int year, int month) {
        int day = 0;
        switch (month) {
            // 31天
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            // 30天
            case 4:
            case 6:
            case 9:
            case 11:
                day = 30;
                break;
            // 计算2月天数
            case 2:
                day = year % 100 == 0 ? year % 400 == 0 ? 29 : 28 : year % 4 == 0 ? 29 : 28;
                break;
        }

        return day;
    }


    /** * 获取指定日期是星期几
     * 参数为null时表示获取当前日期是星期几
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekOfDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        if(date != null){
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekOfDays[w];
    }

    /**
     * 使用系统当前日期加以调整作为照片的名称
     */
    public static String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }
}
