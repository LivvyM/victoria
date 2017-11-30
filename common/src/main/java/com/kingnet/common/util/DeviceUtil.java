package com.kingnet.common.util;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 *
 * Created by livvy on 17-1-16.
 */

public class DeviceUtil {

    /**
     * 获取设备唯一标识
     */
    public static String getIMEI(Context context){
        TelephonyManager TelephonyMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        return TelephonyMgr.getDeviceId();
    }
}
