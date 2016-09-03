package com.zjc.drivingschool.db.parser;

import com.google.gson.reflect.TypeToken;
import com.mobo.mobolibrary.parser.JsonParser;
import com.zjc.drivingschool.db.response.OrderDetail;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class OrderDetailResponseParser extends JsonParser<OrderDetail> {

    @Override
    public Type getResultMessageType() {
        return new TypeToken<OrderDetail>() {
        }.getType();
    }

    @Override
    public Type getArrayTypeToken() {
        return new TypeToken<ArrayList<OrderDetail>>() {
        }.getType();
    }

}
