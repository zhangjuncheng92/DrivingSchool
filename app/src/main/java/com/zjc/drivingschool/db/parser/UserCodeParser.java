package com.zjc.drivingschool.db.parser;

import com.google.gson.reflect.TypeToken;
import com.mobo.mobolibrary.model.ResultMessage;
import com.mobo.mobolibrary.parser.JsonParser;
import com.zjc.drivingschool.db.model.UserCode;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserCodeParser extends JsonParser<UserCode> {

    @Override
    public Type getResultMessageType() {
        return new TypeToken<ResultMessage<UserCode>>() {
        }.getType();
    }

    @Override
    public Type getTypeToken() {
        return new TypeToken<ArrayList<UserCode>>() {
        }.getType();
    }

}
