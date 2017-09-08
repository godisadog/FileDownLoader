package com.zw.fd.library.utils;

import android.text.TextUtils;

/**
 * Created by zhenwei on 2017/9/5.
 */

public class Utils {

    public static int generateId(String str){
        if (TextUtils.isEmpty(str)) {
            return -1;
        }

        return str.hashCode();
    }

}
