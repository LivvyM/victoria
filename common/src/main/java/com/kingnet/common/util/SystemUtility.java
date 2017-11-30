package com.kingnet.common.util;

import android.os.Environment;

/**
 * Created by livvy on 16-12-29.
 */

public class SystemUtility {


    public static boolean hasSDCard() {
        boolean mHasSDcard = false;
        if (Environment.MEDIA_MOUNTED.endsWith(Environment.getExternalStorageState())) {
            mHasSDcard = true;
        } else {
            mHasSDcard = false;
        }

        return mHasSDcard;
    }


    public static String getSdcardPath() {

        if (hasSDCard())
            return Environment.getExternalStorageDirectory().getAbsolutePath();

        return "/sdcard/";
    }
}
