package com.zw.fd.library.service;

import android.content.Intent;
import android.os.IBinder;

/**
 * Created by zhenwei on 2017/9/5.
 */

public interface IServiceLifeCircle {

    void onCreate();

    void onStartCommand();

    void onLowMemory();

    IBinder onBind(Intent intent);

    void onRebind(Intent intent);

    boolean onUnbind(Intent intent);

    void onDestroy();

}
