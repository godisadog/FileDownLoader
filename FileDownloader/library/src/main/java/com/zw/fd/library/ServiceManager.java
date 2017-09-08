package com.zw.fd.library;

import android.os.RemoteException;
import android.util.Log;

import com.zw.fd.library.monitor.MonitorManager;
import com.zw.fd.library.persistance.DownStatusModel;
import com.zw.fd.library.request.Request;
import com.zw.fd.library.request.RequestBuilder;

/**
 * Created by zhenwei on 2017/9/6.
 */

public class ServiceManager {
    private static class ServiceManagerHolder {
        private static ServiceManager instance = new ServiceManager();
    }

    public static ServiceManager getInstance() {
        return ServiceManagerHolder.instance;
    }

    private RequestList requestList;
    private BaseServiceConnection serviceConnection;

    public ServiceManager() {
        requestList = new RequestList();
    }

    public void start(Request request) {
        initServiceConnection();
        if (needStartAndBindService(request)) {
            return;
        }
        serviceConnection.start(request);
    }

    public void pause(int requestId) {
        serviceConnection.pause(requestId);
    }

    private void initServiceConnection() {
        if (serviceConnection == null) {
            GlobalOptions options = FileDownLoaderHelper.getGlobalOptions();
            if (options.getProcess() == RequestBuilder.PROCESS.REMOTE_PROCESS) {
                serviceConnection = new RemoteServiceConnection();
            } else {
                serviceConnection = new LocalServiceConnection();
            }
            serviceConnection.setDownCallback(new IDownCallback.Stub() {
                @Override
                public void onCallback(DownStatusModel msg) throws RemoteException {
                    MonitorManager.getInstance().notifyChange(msg);
                }
            });
        }
    }

    private boolean needStartAndBindService(Request request) {
        if (!serviceConnection.isConnected()) {
            requestList.addPendingRequest(request);
            serviceConnection.startAndBindService(FileDownLoaderHelper.getAPPLICATION());
            return true;
        }
        return false;
    }

    public RequestList getRequestList() {
        return requestList;
    }
}
