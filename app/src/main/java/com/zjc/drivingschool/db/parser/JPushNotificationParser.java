package com.zjc.drivingschool.db.parser;

import com.google.gson.reflect.TypeToken;
import com.mobo.mobolibrary.model.ResultMessage;
import com.mobo.mobolibrary.parser.JsonParser;
import com.zjc.drivingschool.db.model.JPushNotification;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class JPushNotificationParser extends JsonParser<JPushNotification> {

    @Override
    public Type getResultMessageType() {
        return new TypeToken<ResultMessage<JPushNotification>>() {
        }.getType();
    }

    @Override
    public Type getTypeToken() {
        return new TypeToken<ArrayList<JPushNotification>>() {
        }.getType();
    }


    public Type getObjectTypeToken() {
        return new TypeToken<JPushNotification>() {
        }.getType();
    }
}
