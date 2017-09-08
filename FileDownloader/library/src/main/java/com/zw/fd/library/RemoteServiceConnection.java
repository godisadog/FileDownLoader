package com.zw.fd.library;

import android.app.Service;
import android.os.IBinder;
import android.os.RemoteException;

import com.zw.fd.library.service.DownLoadService;
import com.zw.fd.library.utils.CollectionUtils;

/**
 * Created by zhenwei on 2017/9/6.
 */

public class RemoteServiceConnection extends BaseServiceConnection{
    @Override
    protected Class<? extends Service> getServiceClass() {
        return DownLoadService.RemoteProcessService.class;
    }

    @Override
    protected IDownInterface createDownInterface(IBinder service) {
        return IDownInterface.Stub.asInterface(service);
    }

    @Override
    protected void connected() {
        if (CollectionUtils.isNotEmpty(ServiceManager.getInstance().getRequestList().getPendingList())) {
            try {
                // TODO 不应该只获取第一个
                getService().start(ServiceManager.getInstance().getRequestList().getPendingList().get(0));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
