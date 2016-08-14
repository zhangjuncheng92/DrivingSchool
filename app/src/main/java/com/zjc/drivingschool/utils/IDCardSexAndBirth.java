package com.zjc.drivingschool.utils;

import java.util.Calendar;

/**
 * Created by Administrator on 15.11.26.
 */
public class IDCardSexAndBirth {
    private int birth;
    private String sex;

    //获取性别
    public String getSex(String code) {
        int checkSex = 0;
        if (code.length() == 15) {
            checkSex = Integer.parseInt(code.substring(14, 15));
        } else if (code.length() == 18) {
            checkSex = Integer.parseInt(code.substring(16, 17));
        }
        if (checkSex % 2 == 0) {
            sex = "女";
        } else if (checkSex % 2 == 1) {
            sex = "男";
        }
        return sex;
    }

    //获取出生日期
    public String getBir(String Bir) {
        String birthday = "";
        if (Bir.length() == 18) {
            birthday = Bir.substring(6, 14);
        }
        if (Bir.length() == 15) {
            birthday = Bir.substring(6, 12);
        }
        String year = birthday.substring(0, 4);
        String month = birthday.substring(4, 6);
        String day = birthday.substring(6, 8);
        birthday = year + "-" + month + "-" + day;
        return birthday;
    }

    //获取年龄
    public int getAge(String code) {
        String birthday = "";
        if (code.length() == 15) {
            birthday = "19" + code.substring(6, 12);
        } else if (code.length() == 18) {
            birthday = code.substring(6, 14);
        }

        int year = Integer.parseInt(birthday.substring(0, 4));
        int month = Integer.parseInt(birthday.substring(4, 6));
        int day = Integer.parseInt(birthday.substring(6, 8));

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(System.currentTimeMillis());
        int yearNow = calendar1.get(Calendar.YEAR);
        int monthNow = calendar1.get(Calendar.MONTH) + 1;
        int dayNow = calendar1.get(Calendar.DAY_OF_MONTH);
        if (monthNow - month > 0) {
            birth = yearNow - year;
        } else if (monthNow - month == 0) {
            if (dayNow - day >= 0) {
                birth = yearNow - year;
            } else if (dayNow - day < 0) {
                birth = yearNow - year - 1;
            }
        } else if (monthNow - month < 0) {
            birth = yearNow - year - 1;
        }
        return birth;
    }
}
