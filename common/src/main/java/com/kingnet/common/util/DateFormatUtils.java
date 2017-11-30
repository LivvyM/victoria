package com.kingnet.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * date
 *
 * Created by lvm on 2016/12/19.
 */

public class DateFormatUtils {

    public static final String FORMAT_NORMAL = "yyyy-MM-dd";
    public static final String FORMAT_NORMAL_YEAR_MONTH = "yyyy-MM";
    public static final String FORMAT_NORMAL_YEAR_MONTH_DAY_HH_MM = "yyyy-MM-dd HH:mm";

    /**
     * get current date
     */
    public static String getNowDate(String format){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat(format, Locale.CHINA);
        try{
            return df.format(date);
        }catch (Throwable ex){
            return "";
        }
    }

    public static String getYesterdayDate(String format){
        Date date = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24);
        SimpleDateFormat df = new SimpleDateFormat(format, Locale.CHINA);
        try{
            return df.format(date);
        }catch (Throwable ex){
            return "";
        }
    }

    /**
     * get date
     */
    public static String getDate(long time,String format){
        Date date = new Date(time);
        SimpleDateFormat df = new SimpleDateFormat(format, Locale.CHINA);
        try{
            return df.format(date);
        }catch (Throwable ex){
            return "";
        }
    }

    public static long getSpecifiedTimeStamp(int year,int month){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,0);
        return calendar.getTimeInMillis();
    }

    public static Date getNowDate(){
        return new Date(System.currentTimeMillis());
    }

    public static Date getYesterdayDate(){
        return new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24);
    }

    /**
     * string to date
     */
    public static Date formatStringToDate(String str,String format){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format,Locale.CHINA);
            return formatter.parse(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * date to string
     */
    public static String formatDateToString(Date date,String format){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format,Locale.CHINA);
            return formatter.format(date);
        } catch (Exception e) {
            return "";
        }
    }
}
