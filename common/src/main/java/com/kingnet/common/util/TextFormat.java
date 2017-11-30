package com.kingnet.common.util;

import android.support.annotation.NonNull;
import android.util.Log;

import java.text.DecimalFormat;

/**
 * Created by livvy on 17-1-19.
 */

public class TextFormat {

    /**
     * 千位符
     */
    public static String formatThousandSeparator(@NonNull String data){
        try{
            double doubleData = Double.valueOf(data);
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###,##0.00");
            return decimalFormat.format(doubleData);
        }catch (Throwable ex){
            Log.e("ex",ex.toString());
            return data;
        }
    }
}
