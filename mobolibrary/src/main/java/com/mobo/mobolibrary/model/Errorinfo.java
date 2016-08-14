package com.mobo.mobolibrary.model;

/**
 * Created by Administrator on 2015/6/2.
 */
public class Errorinfo {
    private int code;
    private String message;

    public Errorinfo() {
    }

    public Errorinfo(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
