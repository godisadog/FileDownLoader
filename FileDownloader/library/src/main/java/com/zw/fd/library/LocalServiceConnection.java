package com.zw.fd.library;

import android.app.Service;
import android.os.IBinder;

import com.zw.fd.library.service.DownLoadService;
import com.zw.fd.library.service.LocalProcessServiceHandler;

/**
 * Created by zhenwei on 2017/9/6.
 */

public class LocalServiceConnection extends BaseServiceConnection {

    @Override
    protected Class<? extends Service> getServiceClass() {
        return DownLoadService.LocalProcessService.class;
    }

    @Override
    protected IDownInterface createDownInterface(IBinder service) {
        if (service instanceof LocalProcessServiceHandler) {
            return (LocalProcessServiceHandler)service;
        }
        return null;
    }

    @Override
    protected void connected() {

    }
}
