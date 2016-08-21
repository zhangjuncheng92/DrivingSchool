package com.zjc.drivingschool.db.parser;

import com.google.gson.reflect.TypeToken;
import com.mobo.mobolibrary.parser.JsonParser;
import com.zjc.drivingschool.db.response.MessageListResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MessageListResponseParser extends JsonParser<MessageListResponse> {

    @Override
    public Type getResultMessageType() {
        return new TypeToken<MessageListResponse>() {
        }.getType();
    }

    @Override
    public Type getArrayTypeToken() {
        return new TypeToken<ArrayList<MessageListResponse>>() {
        }.getType();
    }

}
