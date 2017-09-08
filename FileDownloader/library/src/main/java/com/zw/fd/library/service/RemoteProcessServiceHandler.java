package com.zw.fd.library.service;

import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.zw.fd.library.IDownCallback;
import com.zw.fd.library.IDownInterface;
import com.zw.fd.library.monitor.ISubsciber;
import com.zw.fd.library.monitor.MonitorDispatcher;
import com.zw.fd.library.persistance.DownStatusModel;
import com.zw.fd.library.request.Request;
import com.zw.fd.library.request.RequestManager;

/**
 * Created by zhenwei on 2017/9/5.
 */

public class RemoteProcessServiceHandler extends IDownInterface.Stub implements IServiceLifeCircle, ISubsciber {

    private IDownCallback downCallback;

    public RemoteProcessServiceHandler() {
        MonitorDispatcher.getInstance().subscribe(this);
    }

    //serviceLifeCircle
    @Override
    public void onCreate() {
//        RequestManager.getInstance().
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

    //IDownInterface.aidl
    @Override
    public void start(Request request) throws RemoteException {
        RequestManager.getInstance().start(request);
    }

    @Override
    public void pause(int requestId) throws RemoteException {
        RequestManager.getInstance().pause(requestId);
    }

    @Override
    public void delete() throws RemoteException {

    }

    @Override
    public void clearAll() throws RemoteException {

    }

    @Override
    public void registCallback(IDownCallback callback) throws RemoteException {
        this.downCallback = callback;
    }

    @Override
    public void notifyChange(DownStatusModel msg) {
        if (downCallback != null) {
            try {
                downCallback.onCallback(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
