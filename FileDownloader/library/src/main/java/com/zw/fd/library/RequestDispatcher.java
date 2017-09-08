package com.zw.fd.library;

import android.util.SparseArray;

import com.zw.fd.library.monitor.MonitorManager;
import com.zw.fd.library.request.Request;
import com.zw.fd.library.request.RequestBuilder;
import com.zw.fd.library.utils.Utils;

/**
 * Created by zhenwei on 2017/9/5.
 */
public class RequestDispatcher {

    private static class DispatchHolder {
        private static RequestDispatcher instance = new RequestDispatcher();
    }

    public static RequestDispatcher getInstance() {
        return DispatchHolder.instance;
    }

    private RequestDispatcher() {
    }

    public void start(Request request) {
        MonitorManager.getInstance().subscribe(request);

        GlobalOptions options = FileDownLoaderHelper.getGlobalOptions();
        if (options != null) {
            if (options.getProcess() == RequestBuilder.PROCESS.REMOTE_PROCESS) {
                ServiceManager.getInstance().start(request);
            } else if (options.getProcess() == RequestBuilder.PROCESS.LOCAL_SERVICE) {

            } else {

            }
        }
    }

    public void pause(String url){
        GlobalOptions options = FileDownLoaderHelper.getGlobalOptions();
        if (options != null) {
            if (options.getProcess() == RequestBuilder.PROCESS.REMOTE_PROCESS) {
                ServiceManager.getInstance().pause(Utils.generateId(url));
            } else if (options.getProcess() == RequestBuilder.PROCESS.LOCAL_SERVICE) {

            } else {

            }
        }
    }


}
