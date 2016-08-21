package com.zjc.drivingschool.db.parser;

import com.google.gson.reflect.TypeToken;
import com.mobo.mobolibrary.parser.JsonParser;
import com.zjc.drivingschool.db.response.OrderListResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class OrderListResponseParser extends JsonParser<OrderListResponse> {

    @Override
    public Type getResultMessageType() {
        return new TypeToken<OrderListResponse>() {
        }.getType();
    }

    @Override
    public Type getArrayTypeToken() {
        return new TypeToken<ArrayList<OrderListResponse>>() {
        }.getType();
    }

}
