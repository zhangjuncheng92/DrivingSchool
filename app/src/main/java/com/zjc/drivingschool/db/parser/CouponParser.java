package com.zjc.drivingschool.db.parser;

import com.google.gson.reflect.TypeToken;
import com.mobo.mobolibrary.parser.JsonParser;
import com.zjc.drivingschool.db.response.Coupon;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CouponParser extends JsonParser<Coupon> {

    @Override
    public Type getResultMessageType() {
        return new TypeToken<Coupon>() {
        }.getType();
    }

    @Override
    public Type getArrayTypeToken() {
        return new TypeToken<ArrayList<Coupon>>() {
        }.getType();
    }

}
