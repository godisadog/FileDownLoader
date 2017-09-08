package com.zw.fd.library.utils;

import java.util.Collection;

/**
 * Created by zhenwei on 2017/9/6.
 */

public class CollectionUtils {

    public static boolean isEmpty(Collection collection){
        if (collection != null && !collection.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean isNotEmpty(Collection collection){
        return !isEmpty(collection);
    }


}
