package com.zw.fd.library;

import android.content.Context;

/**
 * Created by zhenwei on 2017/9/6.
 */

public class FileDownLoaderHelper {
    //application
    private static Context APPLICATION;
    public static void holdApplication(Context application){
        APPLICATION = application;
    }

    public static Context getAPPLICATION() {
        return APPLICATION;
    }

    //global options
    private static GlobalOptions globalOptions;
    public static void holdGlobalOptions(GlobalOptions options){
        globalOptions = options;
    }

    public static GlobalOptions getGlobalOptions() {
        return globalOptions;
    }
}
