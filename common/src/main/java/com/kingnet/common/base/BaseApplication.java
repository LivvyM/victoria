package com.kingnet.common.base;

import android.app.Application;

/**
 * Created by lvm on 2016/12/15.
 */

public class BaseApplication extends Application{

    public static BaseApplication context;

    @Override
    public void onCreate() {
        super.onCreate();
        if (context == null) {
            context = this;
        }
    }

    public static BaseApplication getInstance() {
        return context;
    }
}
