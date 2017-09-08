package com.zw.fd.library;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.zw.fd.library.request.Request;
import com.zw.fd.library.request.RequestBuilder;

/**
 * Created by zhenwei on 2017/9/6.
 */

public abstract class BaseServiceConnection implements ServiceConnection, IRequestManager {
    private IDownInterface SERVICE;
    private IDownCallback CALLBACK;

    protected abstract Class<? extends Service> getServiceClass();

    protected abstract IDownInterface createDownInterface(IBinder service);

    protected abstract void connected();

    public void setDownCallback(IDownCallback downCallback) {
        this.CALLBACK = downCallback;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        SERVICE = createDownInterface(service);
        if (SERVICE != null && CALLBACK != null) {
            try {
                SERVICE.registCallback(CALLBACK);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            connected();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        SERVICE = null;
        //unregistCallback
    }

    public void startAndBindService(Context context) {
        Intent intent = new Intent(context, getServiceClass());
        context.bindService(intent, this, Context.BIND_AUTO_CREATE);
        context.startService(intent);
    }

    public IDownInterface getService() {
        return SERVICE;
    }

    public boolean isConnected() {
        return SERVICE != null;
    }

    @Override
    public RequestBuilder create(String url) {
        return null;
    }

    @Override
    public void start(Request request) {
        if (!isConnected()) {
            //TODO not connected
            return;
        }
        try {
            getService().start(request);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(int taskId) {
        if (!isConnected()) {
            //TODO not connected
            return;
        }

//        try {
//            getService().start();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void start(int taskId, int groupId) {
        if (!isConnected()) {
            //TODO not connected
            return;
        }

        /*try {
            getService().start();
        } catch (RemoteException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void pause(int taskId) {
        if (!isConnected()) {
            //TODO not connected
            return;
        }

        try {
            getService().pause(taskId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pause(int taskId, int groupId) {
        if (!isConnected()) {
            //TODO not connected
            return;
        }

        try {
            getService().pause(taskId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pauseAll() {
        if (!isConnected()) {
            //TODO not connected
            return;
        }

      /*  try {
            getService().pause();
        } catch (RemoteException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void delete(int taskId) {
        if (!isConnected()) {
            //TODO not connected
            return;
        }

        try {
            getService().delete();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int taskId, int groupId) {
        if (!isConnected()) {
            //TODO not connected
            return;
        }

        try {
            getService().delete();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        if (!isConnected()) {
            //TODO not connected
            return;
        }

        try {
            getService().delete();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
