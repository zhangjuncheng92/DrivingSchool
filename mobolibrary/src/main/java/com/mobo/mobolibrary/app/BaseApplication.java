package com.mobo.mobolibrary.app;

import android.app.Application;

public class BaseApplication extends Application {
    private static BaseApplication me;

    public static BaseApplication getInstance() {
        return me;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        me = this;
    }
}
