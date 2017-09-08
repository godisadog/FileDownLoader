package com.zw.fd.library.utils;

import android.util.Log;

/**
 * Created by zhenwei on 2017/9/7.
 */

public class Logger {
    public static void v(String tag, String msg){
        Log.v(tag, msg);
    }

    public static void d(String tag, String msg){
        Log.d(tag, msg);
    }

    public static void d(Object tag, String msg){
        Log.d(tag.getClass().getSimpleName(), msg);
    }


    public static void w(String tag, String msg){
        Log.w(tag, msg);
    }

    public static void e(String tag, String msg){
        Log.e(tag, msg);
    }
}
