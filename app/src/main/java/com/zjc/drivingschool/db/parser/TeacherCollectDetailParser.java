package com.zjc.drivingschool.db.parser;

import com.google.gson.reflect.TypeToken;
import com.mobo.mobolibrary.parser.JsonParser;
import com.zjc.drivingschool.db.response.TeacherCollectDetail;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TeacherCollectDetailParser extends JsonParser<TeacherCollectDetail> {

    @Override
    public Type getResultMessageType() {
        return new TypeToken<TeacherCollectDetail>() {
        }.getType();
    }

    @Override
    public Type getArrayTypeToken() {
        return new TypeToken<ArrayList<TeacherCollectDetail>>() {
        }.getType();
    }

}
