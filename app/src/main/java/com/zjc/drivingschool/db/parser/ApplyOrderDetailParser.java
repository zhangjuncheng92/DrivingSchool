package com.zjc.drivingschool.db.parser;

import com.google.gson.reflect.TypeToken;
import com.mobo.mobolibrary.parser.JsonParser;
import com.zjc.drivingschool.db.response.ApplyOrderDetail;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ApplyOrderDetailParser extends JsonParser<ApplyOrderDetail> {

    @Override
    public Type getResultMessageType() {
        return new TypeToken<ApplyOrderDetail>() {
        }.getType();
    }

    @Override
    public Type getArrayTypeToken() {
        return new TypeToken<ArrayList<ApplyOrderDetail>>() {
        }.getType();
    }

}
