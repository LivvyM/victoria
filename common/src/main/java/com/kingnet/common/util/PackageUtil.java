package com.kingnet.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by lvm on 2016/12/21.
 */

public class PackageUtil {

    /**
     * get app package info
     */
    public static PackageInfo getAppPackageInfo(Context context) {
        if (context != null) {
            PackageManager pm = context.getPackageManager();
            if (pm != null) {
                PackageInfo pi;
                try {
                    return pm.getPackageInfo(context.getPackageName(), 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
