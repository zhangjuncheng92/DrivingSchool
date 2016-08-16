package com.mobo.mobolibrary.parser;

import com.google.gson.Gson;
import com.mobo.mobolibrary.model.ResultMessage;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author Z
 * @Filename ResultResponseHandler.java
 * @Date 2015-05-30
 * @description 解析器父类
 */
public abstract class JsonParser<T> {

    /**
     * 解析ResultMessage封装
     *
     * @param json Json字符串
     * @return ResultMessage
     */
    public T parseResultMessage(String json) {
        if (json == null) {
            return null;
        }

        try {
            final Gson gson = new Gson();
            return gson.fromJson(json, getResultMessageType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析ResultMessage封装
     *
     * @param json Json字符串
     * @return ResultMessage
     */
    public ArrayList<T> parseArrayResult(String json) {
        if (json == null) {
            return null;
        }

        try {
            final Gson gson = new Gson();
            return gson.fromJson(json, getArrayTypeToken());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到ResultMessage的TypeToken
     *
     * @return
     */
    public abstract Type getResultMessageType();

    /**
     * 得到T的TypeToken
     *
     * @return
     */
    public abstract Type getArrayTypeToken();
}
