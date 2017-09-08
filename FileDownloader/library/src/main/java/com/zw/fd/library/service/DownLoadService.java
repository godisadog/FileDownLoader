package com.zw.fd.library.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zhenwei on 2017/9/5.
 */
public abstract class DownLoadService extends Service {
    protected IServiceLifeCircle serviceLifeCircle;

    protected abstract IServiceLifeCircle createServicelifeCircle();

    @Override
    public void onCreate() {
        super.onCreate();
        serviceLifeCircle = createServicelifeCircle();
        if (serviceLifeCircle != null) {
            serviceLifeCircle.onCreate();
        }
        Log.d("zhenwei", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("zhenwei", "onStartCommand");
        if (serviceLifeCircle != null) {
            serviceLifeCircle.onStartCommand();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (serviceLifeCircle != null) {
            serviceLifeCircle.onLowMemory();
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        if (serviceLifeCircle != null) {
            return serviceLifeCircle.onUnbind(intent);
        }
        return super.onUnbind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("zhenwei", "onBind");
        if (serviceLifeCircle != null) {
            return serviceLifeCircle.onBind(intent);
        }
        return null;
    }

    @Override
    public void onDestroy() {
        if (serviceLifeCircle != null) {
            serviceLifeCircle.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        if (serviceLifeCircle != null) {
            serviceLifeCircle.onRebind(intent);
        }
    }

    //本地进程
    public static class LocalProcessService extends DownLoadService{
        @Override
        protected IServiceLifeCircle createServicelifeCircle() {
            return new LocalProcessServiceHandler();
        }
    }

    public static class RemoteProcessService extends DownLoadService {
        @Override
        protected IServiceLifeCircle createServicelifeCircle() {
            return new RemoteProcessServiceHandler();
        }
    }
}
