package com.zw.fd.library.service;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.zw.fd.library.IDownCallback;
import com.zw.fd.library.IDownInterface;
import com.zw.fd.library.request.Request;

/**
 * Created by zhenwei on 2017/9/5.
 */

public class LocalProcessServiceHandler extends Binder implements IDownInterface, IServiceLifeCircle {

    //IServiceLifeCircle
    @Override
    public void onCreate() {

    }

    @Override
    public void onStartCommand() {

    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return this;
    }

    @Override
    public void onRebind(Intent intent) {

    }

    @Override
    public boolean onUnbind(Intent intent) {
        return false;
    }

    @Override
    public void onDestroy() {

    }

    //IDownInterface
    @Override
    public void start(Request request) throws RemoteException {

    }

    @Override
    public void pause(int requestId) throws RemoteException {

    }

    @Override
    public void delete() throws RemoteException {

    }

    @Override
    public void clearAll() throws RemoteException {

    }

    @Override
    public void registCallback(IDownCallback callback) throws RemoteException {

    }

    @Override
    public IBinder asBinder() {
        return null;
    }
}
