package com.zjc.drivingschool.db.parser;

import com.google.gson.reflect.TypeToken;
import com.mobo.mobolibrary.model.ResultMessage;
import com.mobo.mobolibrary.parser.JsonParser;
import com.zjc.drivingschool.db.model.Account;
import com.zjc.drivingschool.db.model.BaseInfo;

import java.lang.reflect.Type;

public class BaseObjectParser extends JsonParser<BaseInfo> {

    @Override
    public Type getResultMessageType() {
        return new TypeToken<BaseInfo>() {
        }.getType();
    }

    @Override
    public Type getArrayTypeToken() {
        return new TypeToken<BaseInfo>() {
        }.getType();
    }

}
