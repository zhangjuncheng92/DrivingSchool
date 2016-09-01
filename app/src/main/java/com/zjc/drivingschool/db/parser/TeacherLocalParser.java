package com.zjc.drivingschool.db.parser;

import com.google.gson.reflect.TypeToken;
import com.mobo.mobolibrary.parser.JsonParser;
import com.zjc.drivingschool.db.model.AccountBalance;
import com.zjc.drivingschool.db.response.TeacherLocal;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TeacherLocalParser extends JsonParser<TeacherLocal> {

    @Override
    public Type getResultMessageType() {
        return new TypeToken<TeacherLocal>() {
        }.getType();
    }

    @Override
    public Type getArrayTypeToken() {
        return new TypeToken<ArrayList<TeacherLocal>>() {
        }.getType();
    }

}
