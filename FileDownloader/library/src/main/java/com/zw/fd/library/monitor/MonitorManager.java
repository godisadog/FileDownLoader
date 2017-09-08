package com.zw.fd.library.monitor;

import android.util.SparseArray;

import com.zw.fd.library.FileDownLoadStatus;
import com.zw.fd.library.IDownCallback;
import com.zw.fd.library.IRequestDownLoadListener;
import com.zw.fd.library.persistance.DownStatusModel;
import com.zw.fd.library.request.Request;
import com.zw.fd.library.request.RequestRunnable;
import com.zw.fd.library.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhenwei on 2017/9/6.
 */

public class MonitorManager {
    private static class MonitorManagerHolder {
        private static MonitorManager instance = new MonitorManager();
    }

    public static MonitorManager getInstance() {
        return MonitorManagerHolder.instance;
    }

    private SparseArray<List<IRequestDownLoadListener>> subsciberList;
    private MonitorManager() {
        subsciberList = new SparseArray<>();
    }

    public void subscribe(Request request) {
        if (request != null) {
            List<IRequestDownLoadListener> listeners = subsciberList.get(request.getId());
            if (listeners == null) {
                listeners = new ArrayList<>();
                subsciberList.put(request.getId(), listeners);
            }
            if (!listeners.contains(request.getDownLoadListener())){
                listeners.add(request.getDownLoadListener());
            }
        }
    }

    public void notifyChange(DownStatusModel msg) {
        if (msg != null) {
            List<IRequestDownLoadListener> listeners = subsciberList.get(msg.getId());
            if (CollectionUtils.isNotEmpty(listeners)) {
                for (IRequestDownLoadListener listener : listeners) {
                    callback(listener, msg);
                }
            }
        }
    }

    private void callback(IRequestDownLoadListener listener, DownStatusModel msg){
        if (msg.getStatus() == FileDownLoadStatus.PROGRESS) {
            listener.onProgress(msg.getSofar());
        }else if (msg.getStatus() == FileDownLoadStatus.START) {
            listener.onStart();
        }else if (msg.getStatus() == FileDownLoadStatus.PAUSE) {
            listener.onPause();
        }else if (msg.getStatus() == FileDownLoadStatus.ERROR) {
            listener.onFail();
        }else if (msg.getStatus() == FileDownLoadStatus.COMPLETE) {
            listener.onComplete();
        }
    }

    public void onWarning(int requestId, String warn){

    }


}
