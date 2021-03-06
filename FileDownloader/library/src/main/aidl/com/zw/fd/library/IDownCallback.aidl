// IDownCallback.aidl
package com.zw.fd.library;

// Declare any non-default types here with import statements
import com.zw.fd.library.persistance.DownStatusModel;

interface IDownCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    /*void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);*/

    void onCallback(in DownStatusModel msg);

}
