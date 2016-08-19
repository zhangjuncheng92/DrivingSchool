package com.zjc.drivingschool.db.model;

import java.io.Serializable;

/**
 * Created by asus1 on 2016/8/16.
 */
public class AppResponse implements Serializable {
    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
