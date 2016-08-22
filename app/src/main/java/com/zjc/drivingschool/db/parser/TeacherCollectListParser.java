package com.zjc.drivingschool.db.parser;

import com.google.gson.reflect.TypeToken;
import com.mobo.mobolibrary.parser.JsonParser;
import com.zjc.drivingschool.db.response.TeacherCollectListResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TeacherCollectListParser extends JsonParser<TeacherCollectListResponse> {

    @Override
    public Type getResultMessageType() {
        return new TypeToken<TeacherCollectListResponse>() {
        }.getType();
    }

    @Override
    public Type getArrayTypeToken() {
        return new TypeToken<ArrayList<TeacherCollectListResponse>>() {
        }.getType();
    }

}
