package com.mobo.mobolibrary.parser;


import com.google.gson.reflect.TypeToken;
import com.mobo.mobolibrary.model.Errorinfo;
import com.mobo.mobolibrary.model.ResultMessage;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ErrorinfoParser extends JsonParser<Errorinfo> {

    @Override
    public Type getResultMessageType() {
        return new TypeToken<ResultMessage<Errorinfo>>() {
        }.getType();
    }

    @Override
    public Type getArrayTypeToken() {
        return new TypeToken<ArrayList<Errorinfo>>() {
        }.getType();
    }

}
