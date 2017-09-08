// IDownInterface.aidl
package com.zw.fd.library;

// Declare any non-default types here with import statements
import com.zw.fd.library.IDownCallback;
import com.zw.fd.library.request.Request;

interface IDownInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    /*void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);*/
    void start(in Request request);

    void pause(int requestId);

    void delete();

    void clearAll();

    void registCallback(IDownCallback callback);
}
